package ricciliao.bsm.service;

import ricciliao.bsm.restservice.dto.CaptchaCacheDto;
import ricciliao.bsm.restservice.dto.EmailCacheDto;
import ricciliao.x.component.cache.consumer.ConsumerCacheRestService;

public interface CacheProviderService {

    ConsumerCacheRestService<CaptchaCacheDto> captcha();

    ConsumerCacheRestService<EmailCacheDto> email();

}
