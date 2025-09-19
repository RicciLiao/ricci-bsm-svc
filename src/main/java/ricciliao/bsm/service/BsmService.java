package ricciliao.bsm.service;

import org.apache.commons.lang3.tuple.Pair;
import ricciliao.bsm.pojo.dto.request.VerifyChallengeDto;
import ricciliao.bsm.pojo.dto.response.GetChallengeDto;
import ricciliao.x.component.challenge.Challenge;
import ricciliao.x.component.exception.AbstractException;

public interface BsmService {

    Pair<GetChallengeDto, String> getChallenge(Challenge challenge, String emailAddress);

    boolean verifyChallenge(VerifyChallengeDto requestDto) throws AbstractException;

}
