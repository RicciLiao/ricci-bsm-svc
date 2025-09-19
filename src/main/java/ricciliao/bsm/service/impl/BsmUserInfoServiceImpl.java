package ricciliao.bsm.service.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.bsm.cache.CacheProvider;
import ricciliao.bsm.cache.pojo.ChallengeVerificationDto;
import ricciliao.bsm.common.BsmPojoUtils;
import ricciliao.bsm.common.BsmSecondaryCodeEnum;
import ricciliao.bsm.kafka.dto.SendPostKafkaDto;
import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.pojo.dto.response.GetChallengeDto;
import ricciliao.bsm.repository.BsmUserRepository;
import ricciliao.bsm.service.BsmService;
import ricciliao.bsm.service.BsmUserInfoService;
import ricciliao.x.cache.pojo.ConsumerOp;
import ricciliao.x.component.challenge.ChallengeTypeStrategy;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.DataException;
import ricciliao.x.component.exception.ParameterException;
import ricciliao.x.component.kafka.KafkaProducer;
import ricciliao.x.component.utils.CoreUtils;

import java.time.LocalDateTime;
import java.util.Objects;

@Service("bsmUserInfoService")
public class BsmUserInfoServiceImpl implements BsmUserInfoService {

    private BsmUserRepository bsmUserRepository;
    private BsmService bsmService;
    private CacheProvider cacheProvider;
    private KafkaProducer<SendPostKafkaDto> signUpEmailKafka;

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
    public void setBsmService(BsmService bsmService) {
        this.bsmService = bsmService;
    }

    @Autowired
    public void setBsmUserRepository(BsmUserRepository bsmUserRepository) {
        this.bsmUserRepository = bsmUserRepository;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long signUp(String k, BsmUserInfoDto requestDto) throws AbstractException {
        ConsumerOp.Single<ChallengeVerificationDto> operation = cacheProvider.challenge().get(k);
        if (Objects.isNull(operation)
                || !operation.getData().getData().isVerified()
                || !requestDto.getUserEmail().equalsIgnoreCase(operation.getData().getData().getEmailAddress())) {

            throw new ParameterException(BsmSecondaryCodeEnum.TIMEOUT);
        }
        if (bsmUserRepository.existsByLoginNameOrUserEmail(requestDto.getLoginName(), requestDto.getUserEmail())) {

            throw new DataException(BsmSecondaryCodeEnum.EXISTED_EMAIL);
        }
        requestDto.setVersion(null);
        requestDto.setId(null);
        requestDto.setLastLoginDtm(null);
        requestDto.setCreatedDtm(LocalDateTime.now());
        requestDto.setUpdatedDtm(LocalDateTime.now());
        requestDto.setStatusId(0L);
        requestDto.setUserPassword("");
        bsmUserRepository.save(BsmPojoUtils.convert2Po(requestDto));

        return 0L;
    }

    @Override
    public String signUpSendPost(UserSignUpSendPostDto requestDto) throws AbstractException {
        if (bsmUserRepository.existsByUserEmail(requestDto.getEmailAddress())) {
            cacheProvider.challenge().delete(requestDto.getK());

            throw new DataException(BsmSecondaryCodeEnum.EXISTED_EMAIL);
        }
        if (!bsmService.verifyChallenge(requestDto)) {

            throw new ParameterException(BsmSecondaryCodeEnum.MISMATCHED_CAPTCHA);
        }
        Long now = CoreUtils.toLongNotNull(LocalDateTime.now());
        Pair<GetChallengeDto, String> pair =
                bsmService.getChallenge(ChallengeTypeStrategy.VERIFICATION_CODE.get(), requestDto.getEmailAddress());

        signUpEmailKafka.send(new SendPostKafkaDto(
                pair.getRight(),
                requestDto.getEmailAddress(),
                now + pair.getLeft().t()
        ));

        return pair.getLeft().k();
    }

}
