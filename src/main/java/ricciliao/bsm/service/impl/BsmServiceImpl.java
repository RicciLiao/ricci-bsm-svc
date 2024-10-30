package ricciliao.bsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.bsm.component.CaptchaRedisTemplateWapper;
import ricciliao.bsm.pojo.bo.CaptchaRedisBo;
import ricciliao.bsm.pojo.dto.request.VerifyCaptchaDto;
import ricciliao.bsm.pojo.dto.response.GetCaptchaDto;
import ricciliao.bsm.service.BsmService;
import ricciliao.common.component.exception.CmnException;
import ricciliao.common.component.utils.CaptchaGenerator;
import ricciliao.common.component.utils.RandomGenerator;

import java.time.LocalDateTime;

@Service
public class BsmServiceImpl implements BsmService {

    private CaptchaRedisTemplateWapper captchaRedisTemplate;

    @Autowired
    public void setCaptchaRedisTemplate(CaptchaRedisTemplateWapper captchaRedisTemplate) {
        this.captchaRedisTemplate = captchaRedisTemplate;
    }

    @Override
    public GetCaptchaDto getCaptcha() throws CmnException {
        LocalDateTime now = LocalDateTime.now();
        String captchaCode =
                RandomGenerator.nextString(5).clear(true)
                        .atLeastDigit(1).atLeastLowerLetter(1)
                        .atLeastUpperLetter(1).generate();
        String key = RandomGenerator.nextString(12).atLeastDigit(3).atLeastUpperLetter(3).atLeastUpperLetter(3).generate();
        CaptchaGenerator.CaptchaResult result = CaptchaGenerator.generateCaptchaImage(captchaCode);
        CaptchaRedisBo bo = new CaptchaRedisBo();
        bo.setCaptcha(result.code());
        bo.setCacheDtm(now);
        captchaRedisTemplate.set(key, bo);

        return new GetCaptchaDto(key, result.captchaBase64(), captchaRedisTemplate.getTtl().toSeconds());
    }

    @Override
    public boolean verifyCaptcha(VerifyCaptchaDto requestDto) {

        return requestDto.getC().equalsIgnoreCase(captchaRedisTemplate.get(requestDto.getK()).getCaptcha());
    }

}
