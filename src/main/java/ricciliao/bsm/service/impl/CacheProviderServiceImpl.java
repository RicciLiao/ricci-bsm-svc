package ricciliao.bsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ricciliao.bsm.restservice.dto.CaptchaCacheDto;
import ricciliao.bsm.restservice.dto.EmailCacheDto;
import ricciliao.bsm.service.CacheProviderService;
import ricciliao.common.component.cache.service.CacheRestService;

@Service("cacheProviderService")
public class CacheProviderServiceImpl implements CacheProviderService {

    private CacheRestService<CaptchaCacheDto> captchaCacheRestService;
    private CacheRestService<EmailCacheDto> emailCacheRestService;

    @Qualifier("captchaCacheRestService")
    @Autowired
    public void setCaptchaCacheRestService(CacheRestService<CaptchaCacheDto> captchaCacheRestService) {
        this.captchaCacheRestService = captchaCacheRestService;
    }

    @Qualifier("emailCacheRestService")
    @Autowired
    public void setEmailCacheRestService(CacheRestService<EmailCacheDto> emailCacheRestService) {
        this.emailCacheRestService = emailCacheRestService;
    }

    @Override
    public CacheRestService<CaptchaCacheDto> captcha() {

        return captchaCacheRestService;
    }

    @Override
    public CacheRestService<EmailCacheDto> email() {

        return emailCacheRestService;
    }
}
