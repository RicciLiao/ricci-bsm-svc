package ricciliao.bsm.component;

import org.springframework.data.redis.core.RedisTemplate;
import ricciliao.bsm.pojo.bo.RedisCatchBo;

import java.time.Duration;

public class StringRedisTemplateWrapper<T extends RedisCatchBo> {

    private final RedisTemplate<String, T> redisTemplate;
    private final Duration ttl;

    public StringRedisTemplateWrapper(RedisTemplate<String, T> redisTemplate, Duration ttl) {
        this.redisTemplate = redisTemplate;
        this.ttl = ttl;
    }

    public RedisTemplate<String, T> getRedisTemplate() {

        return redisTemplate;
    }

    public Duration getTtl() {

        return ttl;
    }

    public void set(String key, T value) {

        redisTemplate.opsForValue().set(key, value, ttl);
    }

    public T get(String key) {

        return redisTemplate.opsForValue().get(key);
    }

    public Boolean delete(String key) {

        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

}
