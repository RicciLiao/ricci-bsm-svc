package ricciliao.bsm.cache.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ricciliao.bsm.cache.CacheProvider;
import ricciliao.bsm.cache.ChallengeCacheBuilder;
import ricciliao.bsm.cache.pojo.ChallengeVerificationDto;
import ricciliao.bsm.common.BsmSecondaryCodeEnum;
import ricciliao.bsm.pojo.dto.request.VerifyChallengeDto;
import ricciliao.bsm.pojo.dto.response.GetChallengeDto;
import ricciliao.x.cache.pojo.ConsumerCache;
import ricciliao.x.cache.pojo.ConsumerOperation;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.ParameterException;
import ricciliao.x.component.response.data.SimpleData;

import java.time.Instant;
import java.util.Objects;

@Component("challengeComponent")
public class ChallengeComponent {

    private final CacheProvider cacheProvider;

    public ChallengeComponent(@Autowired CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    public GetChallengeDto getChallenge(ChallengeCacheBuilder.GetChallenge challenge) {
        ConsumerCache<ChallengeVerificationDto> operation = challenge.get();
        SimpleData.Str result = Objects.requireNonNull(cacheProvider.challenge().create(ConsumerOperation.of(operation)));
        ConsumerCache<ChallengeVerificationDto> cache = Objects.requireNonNull(cacheProvider.challenge().get(result.result()));

        return
                new GetChallengeDto(
                        cache.getCacheKey(),
                        cache.getStore().getImage(),
                        cache.getTtlEffectedDtm().toEpochMilli(),
                        cache.getStore().getCode()
                );
    }

    public boolean verifyChallenge(ChallengeCacheBuilder.VerifyChallenge verifyChallenge) throws AbstractException {
        VerifyChallengeDto dto = verifyChallenge.getChallenge();
        ConsumerCache<ChallengeVerificationDto> cache = cacheProvider.challenge().get(dto.getK());
        if (Objects.isNull(cache)) {

            throw new ParameterException(BsmSecondaryCodeEnum.MISMATCHED_CAPTCHA);
        }
        ChallengeVerificationDto store = cache.getStore();
        if (store.isVerified() || !dto.getC().equalsIgnoreCase(store.getCode())) {

            throw new ParameterException(BsmSecondaryCodeEnum.MISMATCHED_CAPTCHA);

        }

        if (verifyChallenge.apply(store)) {

            throw new ParameterException(BsmSecondaryCodeEnum.MISMATCHED_CAPTCHA);
        }
        store.setVerified(true);
        cache.setUpdatedDtm(Instant.now());
        SimpleData.Bool bool = cacheProvider.challenge().update(new ConsumerOperation.Single<>(cache));

        return Objects.nonNull(bool) && bool.result();
    }

}
