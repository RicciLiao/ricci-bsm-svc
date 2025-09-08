package ricciliao.bsm.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.bsm.pojo.dto.request.VerifyChallengeDto;
import ricciliao.bsm.service.BsmService;
import ricciliao.x.component.challenge.ChallengeBgStrategy;
import ricciliao.x.component.challenge.ChallengeTypeStrategy;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.response.Response;
import ricciliao.x.component.response.ResponseUtils;
import ricciliao.x.component.response.data.ResponseData;
import ricciliao.x.component.response.data.SimpleData;

@Tag(name = "BsmController")
@RestController
public class BsmController {

    private BsmService bsmService;

    @Autowired
    public void setBsmService(BsmService bsmService) {
        this.bsmService = bsmService;
    }

    @Operation
    @GetMapping("/captcha")
    public Response<ResponseData> captcha() throws AbstractException {

        return ResponseUtils.success(
                bsmService.getChallenge(ChallengeTypeStrategy.CAPTCHA_CODE.apply(ChallengeBgStrategy.TRANSPARENT)).getLeft()
        );
    }

    @Operation
    @PostMapping("/captcha")
    public Response<ResponseData> captcha(@RequestBody VerifyChallengeDto requestDto) {

        return ResponseUtils.success(SimpleData.of(bsmService.verifyChallenge(requestDto)));
    }

}
