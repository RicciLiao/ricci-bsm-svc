package ricciliao.bsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.bsm.pojo.dto.request.VerifyCaptchaDto;
import ricciliao.bsm.pojo.dto.response.GetCaptchaDto;
import ricciliao.bsm.service.BsmService;
import ricciliao.bsm.service.CacheProviderService;
import ricciliao.x.cache.pojo.ConsumerOpDto;
import ricciliao.x.cache.pojo.bsm.CaptchaCacheDto;
import ricciliao.x.component.exception.CmnException;
import ricciliao.x.component.random.CaptchaGenerator;
import ricciliao.x.component.random.RandomGenerator;

import java.time.LocalDateTime;
import java.util.Objects;

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
        String cacheId = cacheProviderService.captcha().create(new ConsumerOpDto.Single<>(dto)).result();
        ConsumerOpDto.Single<CaptchaCacheDto> operation = cacheProviderService.captcha().get(cacheId);

        return new GetCaptchaDto(operation.getId(), result.captchaBase64(), operation.getTtlOfMillis());
    }

    @Override
    public boolean verifyCaptcha(VerifyCaptchaDto requestDto) {
        ConsumerOpDto.Single<CaptchaCacheDto> operation = cacheProviderService.captcha().get(requestDto.getK());
        if (Objects.nonNull(operation)) {
            CaptchaCacheDto data = operation.getData();
            if (!data.getVerified() && requestDto.getC().equalsIgnoreCase(data.getCaptcha())) {
                data.setUpdatedDtm(LocalDateTime.now());
                data.setVerified(true);
                operation.setData(data);

                return cacheProviderService.captcha().update(new ConsumerOpDto.Single<>(data)).result();
            }
        }

        return false;
    }

}
