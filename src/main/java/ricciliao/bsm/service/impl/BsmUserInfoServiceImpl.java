package ricciliao.bsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ricciliao.bsm.common.BsmPojoUtils;
import ricciliao.bsm.common.BsmResponseCodeEnum;
import ricciliao.bsm.component.BsmCodeListComponent;
import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.repository.BsmUserRepository;
import ricciliao.bsm.service.BsmService;
import ricciliao.bsm.service.BsmUserInfoService;
import ricciliao.bsm.service.CacheProviderService;
import ricciliao.x.cache.pojo.ConsumerOpDto;
import ricciliao.x.cache.pojo.bsm.EmailCacheDto;
import ricciliao.x.component.exception.CmnException;
import ricciliao.x.component.exception.CmnParameterException;
import ricciliao.x.component.exception.CmnRecordException;

import java.time.LocalDateTime;

@Service("bsmUserInfoService")
public class BsmUserInfoServiceImpl implements BsmUserInfoService {

    private BsmUserRepository bsmUserRepository;
    private BsmService bsmService;
    private BsmCodeListComponent codeListComponent;
    private CacheProviderService cacheProviderService;

    @Autowired
    public void setCacheProviderService(CacheProviderService cacheProviderService) {
        this.cacheProviderService = cacheProviderService;
    }

    @Autowired
    public void setCodeListComponent(BsmCodeListComponent codeListComponent) {
        this.codeListComponent = codeListComponent;
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
            cacheProviderService.captcha().delete(requestDto.getK());

            throw new CmnParameterException(BsmResponseCodeEnum.POST_SIGN_UP_SEND_POST_EXISTED_EMAIL);
        }
        if (bsmService.verifyCaptcha(requestDto)) {
            EmailCacheDto emailRedis = new EmailCacheDto();
            //emailRedis.setSubject("My Subject");
            //emailRedis.setTitle("My Title");
            emailRedis.setTo(requestDto.getEmailAddress());
            emailRedis.setTypeCd(codeListComponent.getVerificationForSignUp());
            emailRedis.setSent(false);
            emailRedis.setCreatedDtm(LocalDateTime.now());
            emailRedis.setUpdatedDtm(emailRedis.getCreatedDtm());
            cacheProviderService.email().create(new ConsumerOpDto.Single<>(emailRedis));
        } else {

            throw new CmnParameterException();
        }

        return Boolean.TRUE;
    }

}
