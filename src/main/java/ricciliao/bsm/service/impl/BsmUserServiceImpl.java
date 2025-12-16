package ricciliao.bsm.service.impl;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.bsm.cache.CacheProvider;
import ricciliao.bsm.cache.ChallengeCacheBuilder;
import ricciliao.bsm.cache.component.ChallengeComponent;
import ricciliao.bsm.cache.pojo.SignInLogDto;
import ricciliao.bsm.cache.pojo.SignUpLockDto;
import ricciliao.bsm.common.BsmConstants;
import ricciliao.bsm.common.BsmSecondaryCodeEnum;
import ricciliao.bsm.kafka.dto.SendPostKafkaDto;
import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignInDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.pojo.dto.response.GetChallengeDto;
import ricciliao.bsm.pojo.po.BsmUserPo;
import ricciliao.bsm.repository.BsmUserRepository;
import ricciliao.bsm.service.BsmUserService;
import ricciliao.bsm.utils.BsmPojoUtils;
import ricciliao.bsm.utils.JvmCacheUtils;
import ricciliao.x.cache.pojo.ConsumerCache;
import ricciliao.x.cache.pojo.ConsumerOperation;
import ricciliao.x.component.challenge.ChallengeTypeStrategy;
import ricciliao.x.component.crypto.CryptoResult;
import ricciliao.x.component.crypto.CryptoStrategy;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.DataException;
import ricciliao.x.component.exception.ParameterException;
import ricciliao.x.component.exception.RestException;
import ricciliao.x.component.kafka.KafkaProducer;
import ricciliao.x.component.payload.SimpleData;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.starter.XProperties;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;

@Service("bsmUserInfoService")
public class BsmUserServiceImpl implements BsmUserService {

    private BsmUserRepository bsmUserRepository;
    private CacheProvider cacheProvider;
    private KafkaProducer<SendPostKafkaDto> signUpEmailKafka;
    private ChallengeComponent challengeComponent;
    private XProperties xProperties;

    @Autowired
    public void setxProperties(XProperties xProperties) {
        this.xProperties = xProperties;
    }

    @Autowired
    public void setChallengeComponent(ChallengeComponent challengeComponent) {
        this.challengeComponent = challengeComponent;
    }

    @Qualifier("signUpEmailKafka")
    @Autowired
    public void setSignUpEmailKafka(KafkaProducer<SendPostKafkaDto> signUpEmailKafka) {
        this.signUpEmailKafka = signUpEmailKafka;
    }

    @Autowired
    public void setCacheProvider(CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    @Autowired
    public void setBsmUserRepository(BsmUserRepository bsmUserRepository) {
        this.bsmUserRepository = bsmUserRepository;
    }

    @Override
    public String signUpSendPost(UserSignUpSendPostDto requestDto) throws AbstractException {
        if (bsmUserRepository.existsByLoginNameOrUserEmail(null, requestDto.getEmailAddress())) {
            cacheProvider.challenge().delete(requestDto.getK());

            throw new DataException(BsmSecondaryCodeEnum.EXISTED_EMAIL);
        }
        if (Objects.nonNull(cacheProvider.signUpLock().get(SignUpLockDto.toCacheKey(requestDto.getEmailAddress())))) {

            throw new DataException(BsmSecondaryCodeEnum.EXISTED_EMAIL);
        }
        challengeComponent.verifyChallenge(ChallengeCacheBuilder.verify(requestDto));
        GetChallengeDto challenge =
                challengeComponent.getChallenge(
                        ChallengeCacheBuilder
                                .get(ChallengeTypeStrategy.VERIFICATION_CODE.get())
                                .process(cache -> cache.getStore().setEmailAddress(requestDto.getEmailAddress()))
                );

        signUpEmailKafka.send(new SendPostKafkaDto(challenge.c(), requestDto.getEmailAddress(), Instant.ofEpochMilli(challenge.t())));

        return challenge.k();
    }

    @Override
    public String preSignUp(UserSignUpSendPostDto requestDto) throws AbstractException {
        challengeComponent.verifyChallenge(
                ChallengeCacheBuilder
                        .verify(requestDto)
                        .process(v -> v.getEmailAddress().equalsIgnoreCase(requestDto.getEmailAddress()))
        );
        if (bsmUserRepository.existsByLoginNameOrUserEmail(null, requestDto.getEmailAddress())) {
            cacheProvider.challenge().delete(requestDto.getK());

            throw new DataException(BsmSecondaryCodeEnum.EXISTED_EMAIL);
        }
        SignUpLockDto lock = new SignUpLockDto();
        lock.setEmailAddress(requestDto.getEmailAddress());
        lock.setChallengeKey(requestDto.getK());
        SimpleData.Str lockKey = cacheProvider.signUpLock().create(ConsumerOperation.ofStore(lock));
        if (Objects.isNull(lockKey)) {

            throw new RestException(SecondaryCodeEnum.BLANK);
        }

        return lockKey.result();
    }

    @Override
    public Long initialize() {
        Long id;
        String version = xProperties.buildProps().getVersion();
        String loginName = String.format(BsmConstants.APP_VERSION_USER, version);
        Optional<BsmUserPo> poOptional = bsmUserRepository.findByLoginName(loginName);
        if (poOptional.isPresent()) {
            id = poOptional.get().getId();
        } else {
            Instant now = Instant.now();
            BsmUserPo po = new BsmUserPo();
            po.setLoginName(loginName);
            po.setUserPassword(Base64.getEncoder().encodeToString(CryptoStrategy.HASH.encrypt(version.getBytes()).getData()));
            po.setUserEmail(loginName);
            po.setLastLoginDtm(now);
            po.setStatusId(BsmConstants.DATA_STATUS_ACTIVE);
            po.setCreatedBy(0L);
            po.setCreatedDtm(now);
            po.setUpdatedBy(0L);
            po.setUpdatedDtm(now);
            id = bsmUserRepository.saveAndFlush(po).getId();
        }
        JvmCacheUtils.setSystemUserId(id);

        return id;
    }

    @Override
    public Long signIn(UserSignInDto requestDto) {
        Long result = 0L;
        Optional<BsmUserPo> poOptional;
        SignInLogDto logDto = new SignInLogDto();
        if (EmailValidator.getInstance().isValid(requestDto.getSignInName())) {
            poOptional = bsmUserRepository.findByUserEmail(requestDto.getSignInName());
            logDto.setSignInWayId(BsmConstants.SIGN_IN_WAY_EMAIL);
        } else {
            poOptional = bsmUserRepository.findByLoginName(requestDto.getSignInName());
            logDto.setSignInWayId(BsmConstants.SIGN_IN_WAY_NAME);
        }
        if (poOptional.isPresent()) {
            BsmUserPo po = poOptional.get();
            CryptoResult encryptResult = CryptoStrategy.HASH.encrypt(requestDto.getSignInPassword().getBytes(StandardCharsets.UTF_8));
            if (po.getUserPassword().equalsIgnoreCase(Base64.getEncoder().encodeToString(encryptResult.getData()))) {
                result = po.getId();
            }
        }
        cacheProvider.signInLog().create(ConsumerOperation.ofStore(logDto));

        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long signUp(String k, BsmUserInfoDto requestDto) throws AbstractException {
        ConsumerCache<SignUpLockDto> cache = cacheProvider.signUpLock().get(k);
        if (Objects.isNull(cache)
                || !requestDto.getUserEmail().equalsIgnoreCase(cache.getStore().getEmailAddress())) {

            throw new ParameterException(BsmSecondaryCodeEnum.TIMEOUT);
        }
        Instant now = Instant.now();
        byte[] password = CryptoStrategy.HASH.encrypt(requestDto.getUserPassword().getBytes(StandardCharsets.UTF_8)).getData();
        requestDto.setVersion(null);
        requestDto.setId(null);
        requestDto.setLastLoginDtm(now);
        requestDto.setCreatedBy(JvmCacheUtils.getSystemUserId());
        requestDto.setCreatedDtm(now);
        requestDto.setUpdatedBy(JvmCacheUtils.getSystemUserId());
        requestDto.setUpdatedDtm(now);
        requestDto.setStatusId(BsmConstants.DATA_STATUS_INITIALIZED);
        requestDto.setUserPassword(Base64.getEncoder().encodeToString(password));
        bsmUserRepository.save(BsmPojoUtils.convert2Po(requestDto));

        cacheProvider.signUpLock().delete(k);

        return 0L;
    }
}
