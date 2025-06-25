package ricciliao.bsm.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ricciliao.bsm.cache.pojo.ChallengeVerificationDto;
import ricciliao.x.cache.ConsumerCacheRestService;

@Service("cacheProvider")
public class CacheProvider {

    private ConsumerCacheRestService<ChallengeVerificationDto> challengeConsumerCacheRestService;

    @Qualifier("challengeConsumerCacheRestService")
    @Autowired
    public void setChallengeConsumerCacheRestService(ConsumerCacheRestService<ChallengeVerificationDto> challengeConsumerCacheRestService) {
        this.challengeConsumerCacheRestService = challengeConsumerCacheRestService;
    }

    public ConsumerCacheRestService<ChallengeVerificationDto> challenge() {

        return challengeConsumerCacheRestService;
    }

}
