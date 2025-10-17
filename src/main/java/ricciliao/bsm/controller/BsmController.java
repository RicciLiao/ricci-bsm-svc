package ricciliao.bsm.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.bsm.cache.ChallengeOpBuilder;
import ricciliao.bsm.cache.component.ChallengeComponent;
import ricciliao.bsm.pojo.dto.BsmCodeDetailDto;
import ricciliao.bsm.pojo.dto.request.VerifyChallengeDto;
import ricciliao.bsm.pojo.po.BsmCodeDetailPo;
import ricciliao.bsm.repository.BsmCodeDetailRepository;
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

    private ChallengeComponent challengeComponent;

    @Autowired
    public void setChallengeComponent(ChallengeComponent challengeComponent) {
        this.challengeComponent = challengeComponent;
    }

    @Operation
    @GetMapping("/")
    public Response<ResponseData> initialize() {

        return ResponseUtils.success(
                challengeComponent.getChallenge(
                        ChallengeOpBuilder.get(ChallengeTypeStrategy.CAPTCHA_CODE.apply(ChallengeBgStrategy.TRANSPARENT))
                )
        );
    }

    @Operation
    @GetMapping("/captcha")
    public Response<ResponseData> captcha() {

        return ResponseUtils.success(
                challengeComponent.getChallenge(
                        ChallengeOpBuilder.get(ChallengeTypeStrategy.CAPTCHA_CODE.apply(ChallengeBgStrategy.TRANSPARENT))
                )
        );
    }

    @Operation
    @PostMapping("/captcha")
    public Response<ResponseData> captcha(@RequestBody VerifyChallengeDto requestDto) throws AbstractException {

        return ResponseUtils.success(
                SimpleData.of(challengeComponent.verifyChallenge(ChallengeOpBuilder.verify(requestDto)))
        );
    }

    private BsmCodeDetailRepository bsmCodeDetailRepository;

    @Autowired
    public void setBsmCodeDetailRepository(BsmCodeDetailRepository bsmCodeDetailRepository) {
        this.bsmCodeDetailRepository = bsmCodeDetailRepository;
    }

    @Operation
    @PostMapping("/testing")
    public Response<ResponseData> testing(@RequestBody BsmCodeDetailDto request) {
        BsmCodeDetailPo po = bsmCodeDetailRepository.findById(request.getBsmCodeId()).get();
        BsmCodeDetailDto dto = new BsmCodeDetailDto();
        dto.setActive(po.getIsActive() == 1);
        dto.setBsmCodeId(po.getBsmCode());
        dto.setCode(po.getCode());
        dto.setCreatedBy(po.getCreatedBy());
        dto.setCreatedDtm(po.getCreatedDtm());
        dto.setDescription(po.getDescription());
        dto.setId(po.getId());
        dto.setUpdatedBy(po.getUpdatedBy());
        dto.setUpdatedDtm(po.getUpdatedDtm());
        dto.setVersion(po.getVersion());

        return ResponseUtils.success(dto);
    }

}
