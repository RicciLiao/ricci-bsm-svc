package ricciliao.bsm.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.bsm.pojo.dto.request.VerifyCaptchaDto;
import ricciliao.bsm.pojo.dto.response.SimpleResponse;
import ricciliao.bsm.service.BsmService;
import ricciliao.common.component.exception.CmnException;
import ricciliao.common.component.response.ResponseUtils;
import ricciliao.common.component.response.ResponseVo;
import ricciliao.common.component.response.ResponseVoData;

@Tag(name = "")
@RestController
public class BsmController {

    private BsmService bsmService;

    @Autowired
    public void setBsmService(BsmService bsmService) {
        this.bsmService = bsmService;
    }

    @Operation
    @GetMapping("/captcha")
    public ResponseVo<? extends ResponseVoData> captcha() throws CmnException {


        return ResponseUtils.successResponse(bsmService.getCaptcha());
    }

    @Operation
    @PostMapping("/captcha")
    public ResponseVo<? extends ResponseVoData> captcha(@RequestBody VerifyCaptchaDto requestDto) throws CmnException {

        return ResponseUtils.successResponse(new SimpleResponse.BooleanResult(bsmService.verifyCaptcha(requestDto)));
    }

}
