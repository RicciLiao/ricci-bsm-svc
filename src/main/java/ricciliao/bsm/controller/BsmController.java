package ricciliao.bsm.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.bsm.pojo.dto.request.VerifyCaptchaDto;
import ricciliao.bsm.service.BsmService;
import ricciliao.x.component.exception.CmnException;
import ricciliao.x.component.response.ResponseData;
import ricciliao.x.component.response.ResponseSimpleData;
import ricciliao.x.component.response.ResponseUtils;
import ricciliao.x.component.response.ResponseVo;

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
    public ResponseVo<ResponseData> captcha() throws CmnException {


        return ResponseUtils.successResponse(bsmService.getCaptcha());
    }

    @Operation
    @PostMapping("/captcha")
    public ResponseVo<ResponseData> captcha(@RequestBody VerifyCaptchaDto requestDto) {

        return ResponseUtils.successResponse(new ResponseSimpleData.Bool(bsmService.verifyCaptcha(requestDto)));
    }

}
