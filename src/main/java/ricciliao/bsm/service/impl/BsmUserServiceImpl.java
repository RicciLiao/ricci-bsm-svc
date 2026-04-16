package ricciliao.bsm.service.impl;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.info.BuildProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import ricciliao.bsm.cache.CacheProvider;
import ricciliao.bsm.cache.ChallengeCacheBuilder;
import ricciliao.bsm.cache.component.ChallengeComponent;
import ricciliao.bsm.cache.pojo.SignInLogDto;
import ricciliao.bsm.cache.pojo.SignUpLockDto;
import ricciliao.bsm.common.BsmConstants;
import ricciliao.bsm.common.BsmSecondaryCodeEnum;
import ricciliao.bsm.kafka.dto.SendPostKafkaDto;
import ricciliao.bsm.pojo.dto.BsmUserDto;
import ricciliao.bsm.pojo.dto.request.UserSignInDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.pojo.dto.response.GetChallengeDto;
import ricciliao.bsm.pojo.po.BsmUserAvatarPo;
import ricciliao.bsm.pojo.po.BsmUserPo;
import ricciliao.bsm.repository.BsmUserAvatarLogRepository;
import ricciliao.bsm.repository.BsmUserAvatarRepository;
import ricciliao.bsm.repository.BsmUserLogRepository;
import ricciliao.bsm.repository.BsmUserRepository;
import ricciliao.bsm.service.BsmUserService;
import ricciliao.bsm.utils.BsmPojoUtils;
import ricciliao.bsm.utils.JvmCacheUtils;
import ricciliao.x.component.challenge.ChallengeTypeStrategy;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.DataException;
import ricciliao.x.component.exception.ParameterException;
import ricciliao.x.component.exception.RestException;
import ricciliao.x.component.kafka.KafkaProducer;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.component.persistence.LogAction;
import ricciliao.x.component.security.encode.EncodeStrategy;
import ricciliao.x.fsp.FspSavingDto;
import ricciliao.x.mcp.ConsumerCache;
import ricciliao.x.starter.fsp.FspConsumerRestService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

@Service("bsmUserInfoService")
public class BsmUserServiceImpl implements BsmUserService {

    private BsmUserRepository bsmUserRepository;
    private CacheProvider cacheProvider;
    private KafkaProducer<SendPostKafkaDto> signUpEmailKafka;
    private ChallengeComponent challengeComponent;
    private BsmUserLogRepository bsmUserLogRepository;
    private BuildProperties buildProps;
    private BsmUserAvatarLogRepository bsmUserAvatarLogRepository;
    private BsmUserAvatarRepository bsmUserAvatarRepository;
    private FspConsumerRestService fspConsumerRestService;

    @Autowired
    public void setFspConsumerRestService(FspConsumerRestService fspConsumerRestService) {
        this.fspConsumerRestService = fspConsumerRestService;
    }

    @Autowired
    public void setBsmUserAvatarLogRepository(BsmUserAvatarLogRepository bsmUserAvatarLogRepository) {
        this.bsmUserAvatarLogRepository = bsmUserAvatarLogRepository;
    }

    @Autowired
    public void setBsmUserAvatarRepository(BsmUserAvatarRepository bsmUserAvatarRepository) {
        this.bsmUserAvatarRepository = bsmUserAvatarRepository;
    }

    @Autowired
    public void setBuildProps(BuildProperties buildProps) {
        this.buildProps = buildProps;
    }

    @Autowired
    public void setBsmUserLogRepository(BsmUserLogRepository bsmUserLogRepository) {
        this.bsmUserLogRepository = bsmUserLogRepository;
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
                                .process(cache -> cache.getData().setEmailAddress(requestDto.getEmailAddress()))
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
        SimplePayloadData.Str lockKey = cacheProvider.signUpLock().create(ConsumerCache.of(lock));
        if (Objects.isNull(lockKey)) {

            throw new RestException(SecondaryCodeEnum.BLANK);
        }

        return lockKey.data();
    }

    @Override
    public Long initialize() {
        Long id;
        String version = buildProps.getVersion();
        String loginName = String.format(BsmConstants.APP_VERSION_USER, version);
        Optional<BsmUserPo> poOptional = bsmUserRepository.findByLoginName(loginName);
        if (poOptional.isPresent()) {
            id = poOptional.get().getId();
        } else {
            Instant now = Instant.now();
            BsmUserPo po = new BsmUserPo();
            po.setLoginName(loginName);
            po.setUserPassword(EncodeStrategy.ARGON2.encode(version.getBytes(StandardCharsets.UTF_8)));
            po.setUserEmail(loginName);
            po.setLastLoginDtm(now);
            po.setStatusId(BsmConstants.DATA_STATUS_ACTIVE);
            po.setCreatedBy(0L);
            po.setCreatedDtm(now);
            po.setUpdatedBy(0L);
            po.setUpdatedDtm(now);
            po = bsmUserRepository.saveAndFlush(po);
            bsmUserLogRepository.save(BsmPojoUtils.convert2Po(po, LogAction.update(now)));
            id = po.getId();
        }
        JvmCacheUtils.setSystemUserId(id);

        return id;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long signIn(UserSignInDto requestDto) {
        Long result = 0L;
        Optional<BsmUserPo> poOptional;
        Instant now = Instant.now();
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
            String encodedPassword = EncodeStrategy.ARGON2.encode(requestDto.getSignInPassword().getBytes(StandardCharsets.UTF_8));
            if (EncodeStrategy.ARGON2.matches(requestDto.getSignInPassword().getBytes(StandardCharsets.UTF_8), encodedPassword)) {
                result = po.getId();
                po.setLastLoginDtm(now);
                po.setUpdatedDtm(now);
                po = bsmUserRepository.saveAndFlush(po);
                bsmUserLogRepository.save(BsmPojoUtils.convert2Po(po, LogAction.update(now)));
            }
        }
        cacheProvider.signInLog().create(ConsumerCache.of(logDto));

        return result;
    }

    @Transactional(rollbackFor = Exception.class, noRollbackFor = RestException.class)
    @Override
    public Long signUp(String k, BsmUserDto user, MultipartFile avatar) throws AbstractException {
        ConsumerCache<SignUpLockDto> cache = cacheProvider.signUpLock().get(k);
        if (Objects.isNull(cache)
            || !user.getUserEmail().equalsIgnoreCase(cache.getData().getEmailAddress())) {

            throw new ParameterException(BsmSecondaryCodeEnum.TIMEOUT);
        }
        Instant now = Instant.now();
        user.setVersion(null);
        user.setId(null);
        user.setLastLoginDtm(now);
        user.setCreatedBy(JvmCacheUtils.getSystemUserId());
        user.setCreatedDtm(now);
        user.setUpdatedBy(JvmCacheUtils.getSystemUserId());
        user.setUpdatedDtm(now);
        user.setStatusId(BsmConstants.DATA_STATUS_INITIALIZED);
        user.setUserPassword(EncodeStrategy.ARGON2.encode(user.getUserPassword().getBytes(StandardCharsets.UTF_8)));
        BsmUserPo userPo = bsmUserRepository.save(BsmPojoUtils.convert2Po(user));
        bsmUserLogRepository.save(BsmPojoUtils.convert2Po(userPo, LogAction.insert(now)));
        if (Objects.nonNull(avatar) && !avatar.isEmpty()) {
            try {
                ByteArrayResource resource = new ByteArrayResource(avatar.getBytes()) {
                    @Override
                    public String getFilename() {
                        return avatar.getOriginalFilename();
                    }
                };
                FspSavingDto fspSavingDto = fspConsumerRestService.create(resource);
                if (Objects.isNull(fspSavingDto)) {

                    throw new RestException(BsmSecondaryCodeEnum.UPLOAD_AVATAR_FAILED);
                }
                BsmUserAvatarPo avatarPo = new BsmUserAvatarPo();
                avatarPo.setBsmUserId(userPo.getId());
                avatarPo.setFspToken(fspSavingDto.getToken());
                avatarPo.setCreatedBy(userPo.getCreatedBy());
                avatarPo.setCreatedDtm(userPo.getCreatedDtm());
                avatarPo.setUpdatedBy(userPo.getUpdatedBy());
                avatarPo.setUpdatedDtm(userPo.getUpdatedDtm());
                avatarPo = bsmUserAvatarRepository.saveAndFlush(avatarPo);
                bsmUserAvatarLogRepository.save(BsmPojoUtils.convert2Po(avatarPo, LogAction.insert(now)));
            } catch (RestClientException | IOException e) {

                throw new RestException(BsmSecondaryCodeEnum.UPLOAD_AVATAR_FAILED);
            }
        }

        cacheProvider.signUpLock().delete(k);

        return 0L;
    }
}
