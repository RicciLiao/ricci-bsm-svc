package ricciliao.bsm.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ricciliao.bsm.cache.pojo.ChallengeVerificationDto;
import ricciliao.bsm.cache.pojo.SignInLogDto;
import ricciliao.bsm.cache.pojo.SignUpLockDto;
import ricciliao.x.starter.mcp.ConsumerCacheRestService;

@Component("cacheProvider")
public class CacheProvider {

    private ConsumerCacheRestService<ChallengeVerificationDto> challengeConsumerCacheRestService;
    private ConsumerCacheRestService<SignUpLockDto> signUpLockConsumerCacheRestService;
    private ConsumerCacheRestService<SignInLogDto> signInLogDtoConsumerCacheRestService;

    @Qualifier("signUpLockConsumerCacheRestService")
    @Autowired
    public void setSignUpLockConsumerCacheRestService(ConsumerCacheRestService<SignUpLockDto> signUpLockConsumerCacheRestService) {
        this.signUpLockConsumerCacheRestService = signUpLockConsumerCacheRestService;
    }

    @Qualifier("challengeConsumerCacheRestService")
    @Autowired
    public void setChallengeConsumerCacheRestService(ConsumerCacheRestService<ChallengeVerificationDto> challengeConsumerCacheRestService) {
        this.challengeConsumerCacheRestService = challengeConsumerCacheRestService;
    }

    @Qualifier("signInLogConsumerCacheRestService")
    @Autowired
    public void setSignInLogDtoConsumerCacheRestService(ConsumerCacheRestService<SignInLogDto> signInLogDtoConsumerCacheRestService) {
        this.signInLogDtoConsumerCacheRestService = signInLogDtoConsumerCacheRestService;
    }

    public ConsumerCacheRestService<ChallengeVerificationDto> challenge() {

        return challengeConsumerCacheRestService;
    }

    public ConsumerCacheRestService<SignUpLockDto> signUpLock() {

        return signUpLockConsumerCacheRestService;
    }

    public ConsumerCacheRestService<SignInLogDto> signInLog() {

        return signInLogDtoConsumerCacheRestService;
    }

}
