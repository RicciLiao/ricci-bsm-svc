package ricciliao.bsm.service;

import org.apache.commons.lang3.tuple.Pair;
import ricciliao.bsm.pojo.dto.request.VerifyChallengeDto;
import ricciliao.bsm.pojo.dto.response.GetChallengeDto;
import ricciliao.x.component.challenge.ChallengeFactory;
import ricciliao.x.component.exception.CmnException;

public interface BsmService {

    Pair<GetChallengeDto, String> getChallenge(ChallengeFactory.ChallengeBuilder builder) throws CmnException;

    boolean verifyChallenge(VerifyChallengeDto requestDto);

}
