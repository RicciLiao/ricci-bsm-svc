package ricciliao.bsm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    private String timeZone;
    private String dynamicAopPointCutController;
    private String redisHost;
    private String redisPort;
    private String redisPassword;
    private String redisForCaptchaDataBase;
    private String redisForCaptchaPoolMaxIdle;
    private String redisForCaptchaPoolMaxActive;
    private String redisForCaptchaPoolMinIdle;
    private String redisForCaptchaTimeout;
    private String redisForCaptchaTtl;

    @Value("${redis.db.captcha.ttl}")
    private void setRedisForCaptchaTtl(String redisForCaptchaTtl) {
        this.redisForCaptchaTtl = redisForCaptchaTtl;
    }

    @Value("${dynamic-aop.point-cut.controller}")
    private void setDynamicAopPointCutController(String dynamicAopPointCutController) {
        this.dynamicAopPointCutController = dynamicAopPointCutController;
    }

    @Value("${time-zone}")
    private void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    @Value("${redis.host}")
    private void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    @Value("${redis.port}")
    private void setRedisPort(String redisPort) {
        this.redisPort = redisPort;
    }

    @Value("${redis.password}")
    private void setRedisPassword(String redisPassword) {
        this.redisPassword = redisPassword;
    }

    @Value("${redis.db.captcha.database}")
    private void setRedisForCaptchaDataBase(String redisForCaptchaDataBase) {
        this.redisForCaptchaDataBase = redisForCaptchaDataBase;
    }

    @Value("${redis.db.captcha.pool.max-idle}")
    private void setRedisForCaptchaPoolMaxIdle(String redisForCaptchaPoolMaxIdle) {
        this.redisForCaptchaPoolMaxIdle = redisForCaptchaPoolMaxIdle;
    }

    @Value("${redis.db.captcha.pool.max-active}")
    private void setRedisForCaptchaPoolMaxActive(String redisForCaptchaPoolMaxActive) {
        this.redisForCaptchaPoolMaxActive = redisForCaptchaPoolMaxActive;
    }

    @Value("${redis.db.captcha.pool.min-idle}")
    private void setRedisForCaptchaPoolMinIdle(String redisForCaptchaPoolMinIdle) {
        this.redisForCaptchaPoolMinIdle = redisForCaptchaPoolMinIdle;
    }

    @Value("${redis.db.captcha.timeout}")
    private void setRedisForCaptchaTimeout(String redisForCaptchaTimeout) {
        this.redisForCaptchaTimeout = redisForCaptchaTimeout;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getDynamicAopPointCutController() {
        return dynamicAopPointCutController;
    }

    public String getRedisHost() {
        return redisHost;
    }

    public String getRedisPort() {
        return redisPort;
    }

    public String getRedisPassword() {
        return redisPassword;
    }

    public String getRedisForCaptchaDataBase() {
        return redisForCaptchaDataBase;
    }

    public String getRedisForCaptchaPoolMaxIdle() {
        return redisForCaptchaPoolMaxIdle;
    }


    public String getRedisForCaptchaPoolMaxActive() {
        return redisForCaptchaPoolMaxActive;
    }

    public String getRedisForCaptchaPoolMinIdle() {
        return redisForCaptchaPoolMinIdle;
    }

    public String getRedisForCaptchaTimeout() {
        return redisForCaptchaTimeout;
    }

    public String getRedisForCaptchaTtl() {
        return redisForCaptchaTtl;
    }
}
