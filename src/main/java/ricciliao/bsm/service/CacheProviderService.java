package ricciliao.bsm.service;

import ricciliao.bsm.rest.dto.CaptchaCacheDto;
import ricciliao.bsm.rest.dto.EmailCacheDto;
import ricciliao.x.component.cache.consumer.ConsumerCacheRestService;

public interface CacheProviderService {

    ConsumerCacheRestService<CaptchaCacheDto> captcha();

    ConsumerCacheRestService<EmailCacheDto> email();

}
