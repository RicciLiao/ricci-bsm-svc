package ricciliao.bsm.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.bsm.cache.ChallengeCacheBuilder;
import ricciliao.bsm.cache.component.ChallengeComponent;
import ricciliao.bsm.pojo.dto.request.VerifyChallengeDto;
import ricciliao.x.component.challenge.ChallengeBgStrategy;
import ricciliao.x.component.challenge.ChallengeTypeStrategy;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.payload.response.Response;
import ricciliao.x.component.payload.response.ResponseUtils;

@Tag(name = "BsmController")
@RestController
public class BsmController {

    private ChallengeComponent challengeComponent;

    @Autowired
    public void setChallengeComponent(ChallengeComponent challengeComponent) {
        this.challengeComponent = challengeComponent;
    }

    @Operation
    @GetMapping("/")
    public Response<PayloadData> initialize() {

        return ResponseUtils.success(
                challengeComponent.getChallenge(
                        ChallengeCacheBuilder.get(ChallengeTypeStrategy.CAPTCHA_CODE.apply(ChallengeBgStrategy.TRANSPARENT))
                )
        );
    }

    @Operation
    @GetMapping("/captcha")
    public Response<PayloadData> captcha() {

        return ResponseUtils.success(
                challengeComponent.getChallenge(
                        ChallengeCacheBuilder.get(ChallengeTypeStrategy.CAPTCHA_CODE.apply(ChallengeBgStrategy.TRANSPARENT))
                )
        );
    }

    @Operation
    @PostMapping("/captcha")
    public Response<PayloadData> captcha(@RequestBody VerifyChallengeDto requestDto) throws AbstractException {

        return ResponseUtils.success(
                SimplePayloadData.of(challengeComponent.verifyChallenge(ChallengeCacheBuilder.verify(requestDto)))
        );
    }

}
