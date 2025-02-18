package ricciliao.bsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.bsm.pojo.dto.request.VerifyCaptchaDto;
import ricciliao.bsm.pojo.dto.response.GetCaptchaDto;
import ricciliao.bsm.restservice.dto.CaptchaCacheDto;
import ricciliao.bsm.service.BsmService;
import ricciliao.bsm.service.CacheProviderService;
import ricciliao.common.component.cache.pojo.ConsumerOperationDto;
import ricciliao.common.component.exception.CmnException;
import ricciliao.common.component.random.CaptchaGenerator;
import ricciliao.common.component.random.RandomGenerator;

import java.time.LocalDateTime;

@Service
public class BsmServiceImpl implements BsmService {

    private CacheProviderService cacheProviderService;

    @Autowired
    public void setCacheProviderService(CacheProviderService cacheProviderService) {
        this.cacheProviderService = cacheProviderService;
    }

    @Override
    public GetCaptchaDto getCaptcha() throws CmnException {
        CaptchaGenerator.CaptchaResult result = RandomGenerator.nextCaptcha();
        CaptchaCacheDto dto = new CaptchaCacheDto();
        dto.setCaptcha(result.code());
        cacheProviderService.captcha().create(new ConsumerOperationDto<>(dto.getCacheId(), dto));
        ConsumerOperationDto<CaptchaCacheDto> operation = cacheProviderService.captcha().get(dto.getCacheId());

        return new GetCaptchaDto(operation.getId(), result.captchaBase64(), operation.getTtlOfMillis());
    }

    @Override
    public boolean verifyCaptcha(VerifyCaptchaDto requestDto) {
        ConsumerOperationDto<CaptchaCacheDto> operation = cacheProviderService.captcha().get(requestDto.getK());
        CaptchaCacheDto data = operation.getData();
        if (!data.getVerified() && requestDto.getC().equalsIgnoreCase(data.getCaptcha())) {
            data.setUpdatedDtm(LocalDateTime.now());
            data.setVerified(true);
            operation.setData(data);

            return cacheProviderService.captcha().update(new ConsumerOperationDto<>(data.getCacheId(), data)).result();
        }

        return false;
    }

}
