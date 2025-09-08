package ricciliao.bsm.service.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.bsm.cache.CacheProvider;
import ricciliao.bsm.common.BsmPojoUtils;
import ricciliao.bsm.common.BsmSecondaryCodeEnum;
import ricciliao.bsm.kafka.dto.SendPostKafkaDto;
import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.pojo.dto.response.GetChallengeDto;
import ricciliao.bsm.repository.BsmUserRepository;
import ricciliao.bsm.service.BsmService;
import ricciliao.bsm.service.BsmUserInfoService;
import ricciliao.x.component.challenge.ChallengeTypeStrategy;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.DataException;
import ricciliao.x.component.exception.ParameterException;
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
    public Long signUp(BsmUserInfoDto requestDto) throws AbstractException {
        requestDto.setVersion(null);
        requestDto.setId(null);
        requestDto.setLastLoginDtm(null);
        requestDto.setCreatedDtm(LocalDateTime.now());
        requestDto.setUpdatedDtm(LocalDateTime.now());
        if (bsmUserRepository.existsByLoginNameOrUserEmail(requestDto.getLoginName(), requestDto.getUserEmail())) {

            throw new DataException(BsmSecondaryCodeEnum.EXISTED_EMAIL);
        }

        requestDto.setStatusId(0L);
        requestDto.setUserPassword("");
        bsmUserRepository.save(BsmPojoUtils.convert2Po(requestDto));

        return 0L;
    }

    @Override
    public Boolean signUpSendPost(UserSignUpSendPostDto requestDto) throws AbstractException {
        if (bsmUserRepository.existsByUserEmail(requestDto.getEmailAddress())) {
            cacheProviderService.challenge().delete(requestDto.getK());

            throw new DataException(BsmSecondaryCodeEnum.EXISTED_EMAIL);
        }
        if (bsmService.verifyChallenge(requestDto)) {
            Long now = CoreUtils.toLongNotNull(LocalDateTime.now());
            Pair<GetChallengeDto, String> pair = bsmService.getChallenge(ChallengeTypeStrategy.VERIFICATION_CODE.get());
            signUpEmailKafka.send(new SendPostKafkaDto(
                    pair.getRight(),
                    requestDto.getEmailAddress(),
                    now + pair.getLeft().t()
            ));
        } else {

            throw new ParameterException(BsmSecondaryCodeEnum.MISMATCHED_CAPTCHA);
        }

        return Boolean.TRUE;
    }

}
