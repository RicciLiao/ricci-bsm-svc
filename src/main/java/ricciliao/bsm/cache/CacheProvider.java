package ricciliao.bsm.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ricciliao.bsm.cache.pojo.ChallengeVerificationDto;
import ricciliao.bsm.cache.pojo.SignUpLockDto;
import ricciliao.x.starter.cache.ConsumerCacheRestService;

@Component("cacheProvider")
public class CacheProvider {

    private ConsumerCacheRestService<ChallengeVerificationDto> challengeConsumerCacheRestService;
    private ConsumerCacheRestService<SignUpLockDto> signUpLockConsumerCacheRestService;

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

    public ConsumerCacheRestService<ChallengeVerificationDto> challenge() {

        return challengeConsumerCacheRestService;
    }

    public ConsumerCacheRestService<SignUpLockDto> signUpLock() {

        return signUpLockConsumerCacheRestService;
    }

}
