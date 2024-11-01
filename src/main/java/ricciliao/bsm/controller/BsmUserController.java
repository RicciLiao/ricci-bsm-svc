package ricciliao.bsm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.common.component.exception.CmnException;
import ricciliao.common.component.response.ResponseCode;
import ricciliao.common.component.response.ResponseUtils;
import ricciliao.common.component.response.ResponseVo;
import ricciliao.common.component.response.ResponseVoData;
import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.service.BsmUserInfoService;


@Tag(name = "")
//@Api("Api")
@RestController("/user")
public class BsmUserController {

    private BsmUserInfoService bsmUserInfoService;

    @Autowired
    public void setBsmUserInfoService(BsmUserInfoService bsmUserInfoService) {
        this.bsmUserInfoService = bsmUserInfoService;
    }

    @Operation
    //@ApiOperation(value = "sign up user account. step1: validate user email address and send a magic link to the email.")
    @PostMapping("/signUp/sendPost")
    public ResponseVo<? extends ResponseVoData> sendPost(@Validated @RequestBody UserSignUpSendPostDto requestDto,
                                                         BindingResult bindingResult) throws CmnException {
        if (bindingResult.hasErrors()) {

            return ResponseUtils.builder(bindingResult).code(ResponseCode.CommonCode.PARAMETER_ERROR).build();
        }
        // verify captcha code
        bsmUserInfoService.signUpSendPost(requestDto);

        return ResponseUtils.successResponse();
    }

    @Operation
    //@ApiOperation(value = "sign up user account.")
    @PostMapping("/signUp")
    public ResponseVo<? extends ResponseVoData> signUp(@Valid @RequestBody BsmUserInfoDto requestDto,
                                                       BindingResult bindingResult) throws CmnException {
        if (bindingResult.hasErrors()) {

            return ResponseUtils.builder(bindingResult).build();
        }
        bsmUserInfoService.signUp(requestDto);

        return ResponseUtils.successResponse();

    }

}
