package ricciliao.bsm.configuration;

import org.springframework.context.annotation.Configuration;
import ricciliao.common.component.context.ApplicationProperties;

import java.time.Duration;

@Configuration
public class BsmProps extends ApplicationProperties {

    public BsmProps() {
        super();
        this.timeZone = yamlProperties.getProperty("time-zone", String.class);
        this.redisHost = yamlProperties.getProperty("redis.host", String.class);
        this.redisPort = yamlProperties.getProperty("redis.port", Integer.class);
        this.password = yamlProperties.getProperty("redis.password", String.class);
        this.captchaRedisProps =
                new StringRedisWrapperConfig.RedisPropsBo(
                        redisHost,
                        redisPort,
                        password,
                        yamlProperties.getProperty("redis.db.captcha.database", Integer.class),
                        Duration.ofMillis(yamlProperties.getProperty("redis.db.captcha.timeout", Long.class)),
                        Duration.ofMillis(yamlProperties.getProperty("redis.db.captcha.ttl", Long.class)),
                        yamlProperties.getProperty("redis.db.captcha.pool.min-idle", Integer.class),
                        yamlProperties.getProperty("redis.db.captcha.pool.max-idle", Integer.class),
                        yamlProperties.getProperty("redis.db.captcha.pool.max-total", Integer.class)
                );
        this.emailRedisProps =
                new StringRedisWrapperConfig.RedisPropsBo(
                        redisHost,
                        redisPort,
                        password,
                        yamlProperties.getProperty("redis.db.email.database", Integer.class),
                        Duration.ofMillis(yamlProperties.getProperty("redis.db.email.timeout", Long.class)),
                        Duration.ofMillis(yamlProperties.getProperty("redis.db.email.ttl", Long.class)),
                        yamlProperties.getProperty("redis.db.email.pool.min-idle", Integer.class),
                        yamlProperties.getProperty("redis.db.email.pool.max-idle", Integer.class),
                        yamlProperties.getProperty("redis.db.email.pool.max-total", Integer.class)
                );
        this.dynamicAopPointCutController = yamlProperties.getProperty("dynamic-aop.point-cut.controller", String.class);
        this.emailSender = yamlProperties.getProperty("spring.mail.username", String.class);
    }

    private final String timeZone;
    private final String dynamicAopPointCutController;
    private final String redisHost;
    private final Integer redisPort;
    private final String password;
    private final StringRedisWrapperConfig.RedisPropsBo captchaRedisProps;
    private final StringRedisWrapperConfig.RedisPropsBo emailRedisProps;
    private final String emailSender;

    public String getTimeZone() {
        return timeZone;
    }

    public String getDynamicAopPointCutController() {
        return dynamicAopPointCutController;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public Integer getRedisPort() {
        return redisPort;
    }

    public String getPassword() {
        return password;
    }

    public StringRedisWrapperConfig.RedisPropsBo getCaptchaRedisProps() {
        return captchaRedisProps;
    }

    public StringRedisWrapperConfig.RedisPropsBo getEmailRedisProps() {
        return emailRedisProps;
    }

    public String getEmailSender() {
        return emailSender;
    }
}
