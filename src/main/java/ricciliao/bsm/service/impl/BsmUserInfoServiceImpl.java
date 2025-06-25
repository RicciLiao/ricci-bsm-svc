package ricciliao.bsm.service.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.bsm.cache.CacheProvider;
import ricciliao.bsm.common.BsmPojoUtils;
import ricciliao.bsm.common.BsmResponseCodeEnum;
import ricciliao.bsm.kafka.dto.SendPostKafkaDto;
import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.pojo.dto.response.GetChallengeDto;
import ricciliao.bsm.repository.BsmUserRepository;
import ricciliao.bsm.service.BsmService;
import ricciliao.bsm.service.BsmUserInfoService;
import ricciliao.x.component.challenge.ChallengeFactory;
import ricciliao.x.component.challenge.ChallengeType;
import ricciliao.x.component.exception.CmnException;
import ricciliao.x.component.exception.CmnParameterException;
import ricciliao.x.component.exception.CmnRecordException;
import ricciliao.x.component.kafka.KafkaProducer;
import ricciliao.x.component.utils.CoreUtils;

import java.time.LocalDateTime;

@Service("bsmUserInfoService")
public class BsmUserInfoServiceImpl implements BsmUserInfoService {

    private BsmUserRepository bsmUserRepository;
    private BsmService bsmService;
    private CacheProvider cacheProviderService;
    private KafkaProducer<SendPostKafkaDto> signUpEmailKafka;

    @Qualifier("signUpEmailKafka")
    @Autowired
    public void setSignUpEmailKafka(KafkaProducer<SendPostKafkaDto> signUpEmailKafka) {
        this.signUpEmailKafka = signUpEmailKafka;
    }

    @Autowired
    public void setCacheProviderService(CacheProvider cacheProviderService) {
        this.cacheProviderService = cacheProviderService;
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
    public Long signUp(BsmUserInfoDto requestDto) throws CmnException {
        requestDto.setVersion(null);
        requestDto.setId(null);
        requestDto.setLastLoginDtm(null);
        requestDto.setCreatedDtm(LocalDateTime.now());
        requestDto.setUpdatedDtm(LocalDateTime.now());
        if (bsmUserRepository.existsByLoginNameOrUserEmail(requestDto.getLoginName(), requestDto.getLoginName())) {

            throw new CmnRecordException();
        }

        requestDto.setStatusId(0L);
        requestDto.setUserPassword("");
        bsmUserRepository.save(BsmPojoUtils.convert2Po(requestDto));

        return 0L;
    }

    @Override
    public Boolean signUpSendPost(UserSignUpSendPostDto requestDto) throws CmnException {
        if (bsmUserRepository.existsByUserEmail(requestDto.getEmailAddress())) {
            cacheProviderService.challenge().delete(requestDto.getK());

            throw new CmnParameterException(BsmResponseCodeEnum.POST_SIGN_UP_SEND_POST_EXISTED_EMAIL);
        }
        if (bsmService.verifyChallenge(requestDto)) {
            Long now = CoreUtils.toLongNotNull(LocalDateTime.now());
            Pair<GetChallengeDto, String> pair = bsmService.getChallenge(new ChallengeFactory.ChallengeBuilder(ChallengeType.VERIFICATION_CODE));
            signUpEmailKafka.send(new SendPostKafkaDto(
                    pair.getRight(),
                    requestDto.getEmailAddress(),
                    now + pair.getLeft().t()
            ));
        } else {

            throw new CmnParameterException();
        }

        return Boolean.TRUE;
    }

}
