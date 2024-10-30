package ricciliao.bsm.component;

import org.springframework.data.redis.core.RedisTemplate;
import ricciliao.bsm.pojo.bo.CaptchaRedisBo;

import java.time.Duration;

public class CaptchaRedisTemplateWapper {

    private final RedisTemplate<String, CaptchaRedisBo> redisTemplate;
    private final Duration ttl;

    public CaptchaRedisTemplateWapper(RedisTemplate<String, CaptchaRedisBo> redisTemplate,
                                      Long ttl) {
        this.redisTemplate = redisTemplate;
        this.ttl = Duration.ofMillis(ttl);
    }

    public RedisTemplate<String, CaptchaRedisBo> getRedisTemplate() {
        return redisTemplate;
    }

    public Duration getTtl() {
        return ttl;
    }

    public void set(String key, CaptchaRedisBo value) {
        redisTemplate.opsForValue().set(key, value, ttl);
    }

    public CaptchaRedisBo get(String key) {

        return redisTemplate.opsForValue().get(key);
    }

}
