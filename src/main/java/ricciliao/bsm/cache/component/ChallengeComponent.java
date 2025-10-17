package ricciliao.bsm.cache.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ricciliao.bsm.cache.CacheProvider;
import ricciliao.bsm.cache.ChallengeOpBuilder;
import ricciliao.bsm.cache.pojo.ChallengeVerificationDto;
import ricciliao.bsm.common.BsmSecondaryCodeEnum;
import ricciliao.bsm.pojo.dto.request.VerifyChallengeDto;
import ricciliao.bsm.pojo.dto.response.GetChallengeDto;
import ricciliao.x.cache.pojo.ConsumerCacheStore;
import ricciliao.x.cache.pojo.ConsumerOp;
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

    public GetChallengeDto getChallenge(ChallengeOpBuilder.GetChallenge challenge) {
        ConsumerOp.Single<ChallengeVerificationDto> operation = challenge.get();
        SimpleData.Str result = Objects.requireNonNull(cacheProvider.challenge().create(operation));
        operation = Objects.requireNonNull(cacheProvider.challenge().get(result.result()));

        return
                new GetChallengeDto(
                        operation.getData().getCacheKey(),
                        operation.getData().getData().getImage(),
                        operation.getTtlOfSeconds(),
                        operation.getData().getData().getCode()
                );
    }

    public boolean verifyChallenge(ChallengeOpBuilder.VerifyChallenge verifyChallenge) throws AbstractException {
        VerifyChallengeDto dto = verifyChallenge.getChallenge();
        ConsumerOp.Single<ChallengeVerificationDto> operation = cacheProvider.challenge().get(dto.getK());
        if (Objects.isNull(operation)) {

            throw new ParameterException(BsmSecondaryCodeEnum.MISMATCHED_CAPTCHA);
        }
        ConsumerCacheStore<ChallengeVerificationDto> data = operation.getData();
        ChallengeVerificationDto cache = data.getData();
        if (cache.isVerified() || !dto.getC().equalsIgnoreCase(cache.getCode())) {

            throw new ParameterException(BsmSecondaryCodeEnum.MISMATCHED_CAPTCHA);

        }

        if (verifyChallenge.apply(cache)) {

            throw new ParameterException(BsmSecondaryCodeEnum.MISMATCHED_CAPTCHA);
        }
        data.setUpdatedDtm(Instant.now());
        cache.setVerified(true);
        operation.setData(data);
        SimpleData.Bool bool = cacheProvider.challenge().update(new ConsumerOp.Single<>(data));

        return Objects.nonNull(bool) && bool.result();
    }

}
