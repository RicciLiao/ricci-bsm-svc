package ricciliao.bsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.bsm.common.BsmPojoUtils;
import ricciliao.bsm.component.CaptchaRedisTemplateWrapper;
import ricciliao.bsm.component.CodeListComponent;
import ricciliao.bsm.configuration.ApplicationProperties;
import ricciliao.bsm.pojo.bo.EmailRedisBo;
import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.repository.BsmUserRepository;
import ricciliao.bsm.service.BsmUserInfoService;
import ricciliao.common.component.exception.CmnException;
import ricciliao.common.component.exception.ParameterException;
import ricciliao.common.component.exception.RecordException;

import java.time.LocalDateTime;

@Service("bsmUserInfoService")
public class BsmUserInfoServiceImpl implements BsmUserInfoService {

    private BsmUserRepository bsmUserRepository;
    private BsmServiceImpl bsmService;
    private CaptchaRedisTemplateWrapper captchaRedisTemplateWrapper;
    private ApplicationProperties applicationProperties;
    private CodeListComponent codeListComponent;

    @Autowired
    public void setCodeListComponent(CodeListComponent codeListComponent) {
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

            throw new RecordException();
        }

        requestDto.setStatusId(0L);
        requestDto.setUserPassword("");
        bsmUserRepository.save(BsmPojoUtils.convert2Po(requestDto));

        return 0L;
    }

    @Override
    public Long signUpSendPost(UserSignUpSendPostDto requestDto) throws CmnException {
        if (bsmUserRepository.existsByUserEmail(requestDto.getEmailAddress())) {
            captchaRedisTemplateWrapper.delete(requestDto.getK());

            throw new ParameterException();
        }
        if (bsmService.verifyCaptcha(requestDto)) {
            EmailRedisBo emailRedis = new EmailRedisBo();
            emailRedis.setFrom(applicationProperties.getEmailSender());
            emailRedis.setTo(requestDto.getEmailAddress());
            emailRedis.setTypeCd(codeListComponent.getVerificationForSignUp());
            emailRedis.setSent(false);
        } else {
            captchaRedisTemplateWrapper.delete(requestDto.getK());

            throw new ParameterException();
        }

        return 0L;
    }

}
