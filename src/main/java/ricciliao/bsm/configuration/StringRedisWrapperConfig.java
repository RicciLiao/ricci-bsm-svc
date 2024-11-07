package ricciliao.bsm.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ricciliao.bsm.component.CaptchaRedisTemplateWrapper;
import ricciliao.bsm.component.EmailRedisTemplateWrapper;
import ricciliao.bsm.pojo.bo.CaptchaRedisBo;
import ricciliao.bsm.pojo.bo.EmailRedisBo;
import ricciliao.bsm.pojo.bo.RedisCatchBo;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;

@EnableCaching
@Configuration
public class StringRedisWrapperConfig {

    private ApplicationProperties applicationProperties;

    @Autowired
    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public CaptchaRedisTemplateWrapper captchaRedisTemplateWrapper(@Autowired ObjectMapper objectMapper) {

        return new CaptchaRedisTemplateWrapper(
                createRedisTemplate(objectMapper, CaptchaRedisBo.class, applicationProperties.getCaptchaRedisProps()),
                applicationProperties.getCaptchaRedisProps().ttl
        );
    }

    @Bean
    public EmailRedisTemplateWrapper emailRedisTemplateWrapper(@Autowired ObjectMapper objectMapper) {

        return new EmailRedisTemplateWrapper(
                createRedisTemplate(objectMapper, EmailRedisBo.class, applicationProperties.getEmailRedisProps()),
                applicationProperties.getEmailRedisProps().ttl
        );
    }

    private <T extends RedisCatchBo> RedisTemplate<String, T> createRedisTemplate(ObjectMapper objectMapper,
                                                                                  Class<T> tClass,
                                                                                  RedisPropsBo props) {
        Jackson2JsonRedisSerializer<T> serializer = new Jackson2JsonRedisSerializer<>(objectMapper, tClass);
        GenericObjectPoolConfig<RedisStandaloneConfiguration> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(props.maxIdle);
        poolConfig.setMaxTotal(props.maxTotal);
        poolConfig.setMinIdle(props.minIdle);
        poolConfig.setMaxWait(props.timeout);

        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(props.host, props.port);
        configuration.setDatabase(props.database);
        configuration.setPassword(props.password);

        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder();
        builder.poolConfig(poolConfig);
        builder.commandTimeout(poolConfig.getMaxWaitDuration());
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(configuration, builder.build());
        connectionFactory.setValidateConnection(true);
        connectionFactory.afterPropertiesSet();

        RedisTemplate<String, T> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    public record RedisPropsBo(
            String host,
            int port,
            String password,
            int database,
            Duration timeout,
            Duration ttl,
            int minIdle,
            int maxIdle,
            int maxTotal
    ) implements Serializable {
        @Serial
        private static final long serialVersionUID = 5585834914615699994L;
    }

}
