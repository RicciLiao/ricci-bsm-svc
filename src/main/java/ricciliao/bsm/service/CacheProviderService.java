package ricciliao.bsm.service;

import ricciliao.bsm.restservice.dto.CaptchaCacheDto;
import ricciliao.bsm.restservice.dto.EmailCacheDto;
import ricciliao.common.component.cache.service.CacheRestService;

public interface CacheProviderService {

    CacheRestService<CaptchaCacheDto> captcha();

    CacheRestService<EmailCacheDto> email();

}
