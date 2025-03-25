package ricciliao.bsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ricciliao.bsm.service.CacheProviderService;
import ricciliao.x.cache.ConsumerCacheRestService;
import ricciliao.x.cache.pojo.bsm.CaptchaCacheDto;
import ricciliao.x.cache.pojo.bsm.EmailCacheDto;

@Service("cacheProviderService")
public class CacheProviderServiceImpl implements CacheProviderService {

    private ConsumerCacheRestService<CaptchaCacheDto> captchaConsumerCacheRestService;
    private ConsumerCacheRestService<EmailCacheDto> emailConsumerCacheRestService;

    @Qualifier("captchaConsumerCacheRestService")
    @Autowired
    public void setCaptchaConsumerCacheRestService(ConsumerCacheRestService<CaptchaCacheDto> captchaConsumerCacheRestService) {
        this.captchaConsumerCacheRestService = captchaConsumerCacheRestService;
    }

    @Qualifier("emailConsumerCacheRestService")
    @Autowired
    public void setEmailConsumerCacheRestService(ConsumerCacheRestService<EmailCacheDto> emailConsumerCacheRestService) {
        this.emailConsumerCacheRestService = emailConsumerCacheRestService;
    }

    @Override
    public ConsumerCacheRestService<CaptchaCacheDto> captcha() {

        return captchaConsumerCacheRestService;
    }

    @Override
    public ConsumerCacheRestService<EmailCacheDto> email() {

        return emailConsumerCacheRestService;
    }
}
