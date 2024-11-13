package ricciliao.bsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.bsm.common.BsmPojoUtils;
import ricciliao.bsm.common.BsmResponseCodeEnum;
import ricciliao.bsm.component.CaptchaRedisTemplateWrapper;
import ricciliao.bsm.component.BsmCodeListComponent;
import ricciliao.bsm.component.EmailRedisTemplateWrapper;
import ricciliao.bsm.configuration.ApplicationProperties;
import ricciliao.bsm.pojo.bo.EmailRedisBo;
import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.repository.BsmUserRepository;
import ricciliao.bsm.service.BsmUserInfoService;
import ricciliao.common.component.exception.CmnException;
import ricciliao.common.component.exception.CmnParameterException;
import ricciliao.common.component.exception.CmnRecordException;

import java.time.LocalDateTime;

@Service("bsmUserInfoService")
public class BsmUserInfoServiceImpl implements BsmUserInfoService {

    private BsmUserRepository bsmUserRepository;
    private BsmServiceImpl bsmService;
    private CaptchaRedisTemplateWrapper captchaRedisTemplateWrapper;
    private ApplicationProperties applicationProperties;
    private BsmCodeListComponent codeListComponent;
    private EmailRedisTemplateWrapper emailRedisTemplateWrapper;

    @Autowired
    public void setEmailRedisTemplateWrapper(EmailRedisTemplateWrapper emailRedisTemplateWrapper) {
        this.emailRedisTemplateWrapper = emailRedisTemplateWrapper;
    }

    @Autowired
    public void setCodeListComponent(BsmCodeListComponent codeListComponent) {
        this.codeListComponent = codeListComponent;
    }

    @Autowired
    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Autowired
    public void setCaptchaRedisTemplateWrapper(CaptchaRedisTemplateWrapper captchaRedisTemplateWrapper) {
        this.captchaRedisTemplateWrapper = captchaRedisTemplateWrapper;
    }

    @Autowired
    public void setBsmService(BsmServiceImpl bsmService) {
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
            captchaRedisTemplateWrapper.delete(requestDto.getK());

            throw new CmnParameterException(BsmResponseCodeEnum.POST_SIGN_UP_SEND_POST_EXISTED_EMAIL);
        }
        if (bsmService.verifyCaptcha(requestDto)) {
            EmailRedisBo emailRedis = new EmailRedisBo();
            emailRedis.setFrom(applicationProperties.getEmailSender());
            emailRedis.setTo(requestDto.getEmailAddress());
            emailRedis.setTypeCd(codeListComponent.getVerificationForSignUp());
            emailRedis.setSent(false);
            emailRedisTemplateWrapper.set(requestDto.getK(), emailRedis);
        } else {
            captchaRedisTemplateWrapper.delete(requestDto.getK());

            throw new CmnParameterException();
        }

        return Boolean.TRUE;
    }

}
