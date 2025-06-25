package ricciliao.bsm.service.impl;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ricciliao.bsm.cache.CacheProvider;
import ricciliao.bsm.cache.pojo.ChallengeVerificationDto;
import ricciliao.bsm.pojo.dto.request.VerifyChallengeDto;
import ricciliao.bsm.pojo.dto.response.GetChallengeDto;
import ricciliao.bsm.service.BsmService;
import ricciliao.x.cache.pojo.ConsumerOp;
import ricciliao.x.component.challenge.Challenge;
import ricciliao.x.component.challenge.ChallengeFactory;
import ricciliao.x.component.exception.CmnException;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class BsmServiceImpl implements BsmService {

    private CacheProvider cacheProvider;

    @Autowired
    public void setCacheProvider(CacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    @Override
    public Pair<GetChallengeDto, String> getChallenge(ChallengeFactory.ChallengeBuilder builder) throws CmnException {
        Challenge challenge = builder.generate();
        String cacheId = cacheProvider.challenge().create(new ConsumerOp.Single<>(new ChallengeVerificationDto(challenge))).result();
        ConsumerOp.Single<ChallengeVerificationDto> operation = cacheProvider.challenge().get(cacheId);

        return Pair.of(
                new GetChallengeDto(operation.getId(), challenge.getImage(), operation.getTtlOfMillis()),
                challenge.getCode()
        );
    }

    @Override
    public boolean verifyChallenge(VerifyChallengeDto requestDto) {
        ConsumerOp.Single<ChallengeVerificationDto> operation = cacheProvider.challenge().get(requestDto.getK());
        if (Objects.nonNull(operation)) {
            ChallengeVerificationDto data = operation.getData();
            if (!data.isVerified() && requestDto.getC().equalsIgnoreCase(data.getChallenge().getCode())) {
                data.setUpdatedDtm(LocalDateTime.now());
                data.setVerified(true);
                operation.setData(data);

                return cacheProvider.challenge().update(new ConsumerOp.Single<>(data)).result();
            }
        }

        return false;
    }

}
