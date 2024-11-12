package ricciliao.bsm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ricciliao.bsm.component.CaptchaRedisTemplateWrapper;
import ricciliao.bsm.component.EmailRedisTemplateWrapper;
import ricciliao.bsm.pojo.bo.CaptchaRedisBo;
import ricciliao.bsm.pojo.bo.EmailRedisBo;
import ricciliao.bsm.pojo.dto.request.VerifyCaptchaDto;
import ricciliao.bsm.pojo.dto.response.GetCaptchaDto;
import ricciliao.bsm.service.BsmService;
import ricciliao.common.component.exception.CmnException;
import ricciliao.common.component.random.CaptchaGenerator;
import ricciliao.common.component.random.RandomGenerator;

import java.time.LocalDateTime;

@Service
public class BsmServiceImpl implements BsmService {

    private EmailRedisTemplateWrapper emailRedisTemplateWrapper;
    private CaptchaRedisTemplateWrapper captchaRedisTemplate;
    private JavaMailSender javaMailSender;

    @Autowired
    public void setEmailRedisTemplateWrapper(EmailRedisTemplateWrapper emailRedisTemplateWrapper) {
        this.emailRedisTemplateWrapper = emailRedisTemplateWrapper;
    }

    @Autowired
    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Autowired
    public void setCaptchaRedisTemplate(CaptchaRedisTemplateWrapper captchaRedisTemplate) {
        this.captchaRedisTemplate = captchaRedisTemplate;
    }

    @Override
    public GetCaptchaDto getCaptcha() throws CmnException {
        String key = RandomGenerator.nextString(12).allAtLeast(3).generate();
        CaptchaGenerator.CaptchaResult result = RandomGenerator.nextCaptcha();
        CaptchaRedisBo bo = new CaptchaRedisBo();
        bo.setCaptcha(result.code());
        captchaRedisTemplate.set(key, bo);

        return new GetCaptchaDto(key, result.captchaBase64(), captchaRedisTemplate.getTtl().toSeconds());
    }

    @Override
    public boolean verifyCaptcha(VerifyCaptchaDto requestDto) {
        CaptchaRedisBo captchaRedis = captchaRedisTemplate.get(requestDto.getK());
        if (!captchaRedis.getVerified() && requestDto.getC().equalsIgnoreCase(captchaRedis.getCaptcha())) {
            captchaRedis.setUpdatedDtm(LocalDateTime.now());
            captchaRedis.setVerified(true);
            captchaRedisTemplate.set(requestDto.getK(), captchaRedis);

            return true;
        }

        return false;
    }

    @Override
    public boolean sendEmail(Long emailKey) {
        EmailRedisBo emailRedis = new EmailRedisBo();


        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("984175520@qq.com");
            message.setFrom("riccix@163.com");
            message.setSubject("setSubject");
            message.setText("setText");
            javaMailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
