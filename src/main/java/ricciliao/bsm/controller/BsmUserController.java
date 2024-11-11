package ricciliao.bsm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.pojo.dto.response.SimpleResponse;
import ricciliao.bsm.service.BsmUserInfoService;
import ricciliao.common.component.exception.CmnException;
import ricciliao.common.component.response.CmnResponseCode;
import ricciliao.common.component.response.ResponseUtils;
import ricciliao.common.component.response.ResponseVo;
import ricciliao.common.component.response.ResponseVoData;


@Tag(name = "")
//@Api("Api")
@RestController
@RequestMapping("/user")
public class BsmUserController {

    private BsmUserInfoService bsmUserInfoService;

    @Autowired
    public void setBsmUserInfoService(BsmUserInfoService bsmUserInfoService) {
        this.bsmUserInfoService = bsmUserInfoService;
    }

    @Operation
    @PostMapping("/signUp/sendPost")
    public ResponseVo<? extends ResponseVoData> sendPost(@Validated @RequestBody UserSignUpSendPostDto requestDto,
                                                         BindingResult bindingResult) throws CmnException {
        if (bindingResult.hasErrors()) {

            return ResponseUtils.builder(bindingResult).code(CmnResponseCode.PARAMETER_ERROR).build();
        }

        return ResponseUtils.successResponse(new SimpleResponse.BooleanResult(bsmUserInfoService.signUpSendPost(requestDto)));
    }

    @Operation
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
