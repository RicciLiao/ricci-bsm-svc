package ricciliao.bsm.component;

import org.springframework.data.redis.core.RedisTemplate;
import ricciliao.bsm.pojo.bo.CaptchaRedisBo;

import java.time.Duration;

public class CaptchaRedisTemplateWrapper extends StringRedisTemplateWrapper<CaptchaRedisBo> {

    public CaptchaRedisTemplateWrapper(RedisTemplate<String, CaptchaRedisBo> redisTemplate, Duration ttl) {
        super(redisTemplate, ttl);
    }

}
