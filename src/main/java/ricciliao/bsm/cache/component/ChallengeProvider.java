package ricciliao.bsm.cache.component;

import org.apache.commons.lang3.tuple.Pair;
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

import java.time.LocalDateTime;
import java.util.Objects;

@Component("challengeProvider")
public class ChallengeProvider {

    private CacheProvider cacheProvider;

    @Autowired
    public void setCacheProvider(CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    public Pair<GetChallengeDto, String> getChallenge(ChallengeOpBuilder.GetChallenge challenge) {
        ChallengeVerificationDto verification = challenge.get();
        ConsumerOp.Single<ChallengeVerificationDto> operation = new ConsumerOp.Single<>(challenge.get());
        SimpleData.Str result = Objects.requireNonNull(cacheProvider.challenge().create(operation));
        operation = Objects.requireNonNull(cacheProvider.challenge().get(result.result()));

        return Pair.of(
                new GetChallengeDto(operation.getData().getCacheKey(), verification.getImage(), operation.getTtlOfSeconds()),
                verification.getCode()
        );
    }

    public boolean verifyChallenge(ChallengeOpBuilder.VerifyChallenge verifyChallenge) throws AbstractException {
        VerifyChallengeDto dto = verifyChallenge.getVerify();
        ConsumerOp.Single<ChallengeVerificationDto> operation = cacheProvider.challenge().get(dto.getK());
        if (Objects.isNull(operation)) {

            throw new ParameterException(BsmSecondaryCodeEnum.MISMATCHED_CAPTCHA);
        }
        ConsumerCacheStore<ChallengeVerificationDto> data = operation.getData();
        ChallengeVerificationDto cache = data.getData();
        if (cache.isVerified() || !dto.getC().equalsIgnoreCase(cache.getCode())) {

            throw new ParameterException(BsmSecondaryCodeEnum.MISMATCHED_CAPTCHA);

        }
        if (verifyChallenge.get(cache)) {

            throw new ParameterException(BsmSecondaryCodeEnum.MISMATCHED_CAPTCHA);
        }
        data.setUpdatedDtm(LocalDateTime.now());
        cache.setVerified(true);
        operation.setData(data);
        SimpleData.Bool bool = cacheProvider.challenge().update(new ConsumerOp.Single<>(data));

        return Objects.nonNull(bool) && bool.result();
    }

}
