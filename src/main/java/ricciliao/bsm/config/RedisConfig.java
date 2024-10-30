package ricciliao.bsm.config;

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
import ricciliao.bsm.component.CaptchaRedisTemplateWapper;
import ricciliao.bsm.pojo.bo.CaptchaRedisBo;

import java.time.Duration;

@EnableCaching
@Configuration
public class RedisConfig {

    private ApplicationProperties applicationProperties;

    @Autowired
    public void setApplicationProperties(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public CaptchaRedisTemplateWapper captchaRedisTemplate(@Autowired ObjectMapper objectMapper) {
        Jackson2JsonRedisSerializer<CaptchaRedisBo> serializer = new Jackson2JsonRedisSerializer<>(objectMapper, CaptchaRedisBo.class);

        GenericObjectPoolConfig<RedisStandaloneConfiguration> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxIdle(Integer.parseInt(applicationProperties.getRedisForCaptchaPoolMaxIdle()));
        poolConfig.setMaxTotal(Integer.parseInt(applicationProperties.getRedisForCaptchaPoolMaxIdle()));
        poolConfig.setMinIdle(Integer.parseInt(applicationProperties.getRedisForCaptchaPoolMinIdle()));
        poolConfig.setMaxWait(Duration.ofMillis(Long.parseLong(applicationProperties.getRedisForCaptchaTimeout())));

        RedisStandaloneConfiguration configuration =
                new RedisStandaloneConfiguration(applicationProperties.getRedisHost(), Integer.parseInt(applicationProperties.getRedisPort()));
        configuration.setDatabase(Integer.parseInt(applicationProperties.getRedisForCaptchaDataBase()));
        configuration.setPassword(applicationProperties.getRedisPassword());

        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder();
        builder.poolConfig(poolConfig);
        builder.commandTimeout(poolConfig.getMaxWaitDuration());
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory(configuration, builder.build());
        connectionFactory.setValidateConnection(true);
        connectionFactory.afterPropertiesSet();

        RedisTemplate<String, CaptchaRedisBo> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setValueSerializer(serializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return new CaptchaRedisTemplateWapper(redisTemplate, Long.parseLong(applicationProperties.getRedisForCaptchaTtl()));
    }

}
