package ricciliao.bsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ricciliao.bsm.restservice.dto.CaptchaCacheDto;
import ricciliao.bsm.restservice.dto.EmailCacheDto;
import ricciliao.bsm.service.CacheProviderService;
import ricciliao.common.component.cache.consumer.ConsumerCacheRestService;

@Service("cacheProviderService")
public class CacheProviderServiceImpl implements CacheProviderService {

    private ConsumerCacheRestService<CaptchaCacheDto> captchaCacheRestService;
    private ConsumerCacheRestService<EmailCacheDto> emailCacheRestService;

    @Qualifier("captchaCacheRestService")
    @Autowired
    public void setCaptchaCacheRestService(ConsumerCacheRestService<CaptchaCacheDto> captchaCacheRestService) {
        this.captchaCacheRestService = captchaCacheRestService;
    }

    @Qualifier("emailCacheRestService")
    @Autowired
    public void setEmailCacheRestService(ConsumerCacheRestService<EmailCacheDto> emailCacheRestService) {
        this.emailCacheRestService = emailCacheRestService;
    }

    @Override
    public ConsumerCacheRestService<CaptchaCacheDto> captcha() {

        return captchaCacheRestService;
    }

    @Override
    public ConsumerCacheRestService<EmailCacheDto> email() {

        return emailCacheRestService;
    }
}
