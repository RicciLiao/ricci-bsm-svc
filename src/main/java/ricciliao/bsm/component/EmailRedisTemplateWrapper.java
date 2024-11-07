package ricciliao.bsm.component;

import org.springframework.data.redis.core.RedisTemplate;
import ricciliao.bsm.pojo.bo.EmailRedisBo;

import java.time.Duration;

public class EmailRedisTemplateWrapper extends StringRedisTemplateWrapper<EmailRedisBo> {

    public EmailRedisTemplateWrapper(RedisTemplate<String, EmailRedisBo> redisTemplate, Duration ttl) {
        super(redisTemplate, ttl);
    }

}
