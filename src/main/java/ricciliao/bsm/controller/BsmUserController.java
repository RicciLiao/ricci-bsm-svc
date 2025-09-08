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
import ricciliao.bsm.service.BsmUserInfoService;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.ParameterException;
import ricciliao.x.component.response.Response;
import ricciliao.x.component.response.ResponseUtils;
import ricciliao.x.component.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.component.response.data.ResponseData;
import ricciliao.x.component.response.data.SimpleData;
import ricciliao.x.component.utils.CoreUtils;

@Tag(name = "BsmUserController")
@RestController
@RequestMapping("/user")
public class BsmUserController {

    private BsmUserInfoService bsmUserInfoService;

    @Autowired(required = false)
    public void setBsmUserInfoService(BsmUserInfoService bsmUserInfoService) {
        this.bsmUserInfoService = bsmUserInfoService;
    }

    @Operation
    @PostMapping("/signUp/sendPost")
    public Response<ResponseData> sendPost(@Validated @RequestBody UserSignUpSendPostDto requestDto,
                                           BindingResult bindingResult) throws AbstractException {
        if (bindingResult.hasErrors()) {

            throw new ParameterException(SecondaryCodeEnum.BLANK, CoreUtils.toFieldViolation(bindingResult));
        }

        return ResponseUtils.success(SimpleData.of(bsmUserInfoService.signUpSendPost(requestDto)));
    }

    @Operation
    @PostMapping("/signUp")
    public Response<ResponseData> signUp(@Valid @RequestBody BsmUserInfoDto requestDto,
                                         BindingResult bindingResult) throws AbstractException {
        if (bindingResult.hasErrors()) {

            throw new ParameterException(SecondaryCodeEnum.BLANK, CoreUtils.toFieldViolation(bindingResult));
        }
        bsmUserInfoService.signUp(requestDto);

        return ResponseUtils.success();

    }

}
