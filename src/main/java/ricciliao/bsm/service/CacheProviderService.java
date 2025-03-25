package ricciliao.bsm.service;

import ricciliao.x.cache.ConsumerCacheRestService;
import ricciliao.x.cache.pojo.bsm.CaptchaCacheDto;
import ricciliao.x.cache.pojo.bsm.EmailCacheDto;

public interface CacheProviderService {

    ConsumerCacheRestService<CaptchaCacheDto> captcha();

    ConsumerCacheRestService<EmailCacheDto> email();

}
