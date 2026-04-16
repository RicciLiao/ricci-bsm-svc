package ricciliao.bsm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ricciliao.bsm.common.BsmSecondaryCodeEnum;
import ricciliao.bsm.pojo.dto.BsmUserDto;
import ricciliao.bsm.pojo.dto.request.UserSignInDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.bsm.service.BsmUserService;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.ParameterException;
import ricciliao.x.component.exception.SecurityException;
import ricciliao.x.component.payload.PayloadData;
import ricciliao.x.component.payload.SimplePayloadData;
import ricciliao.x.component.payload.response.Response;
import ricciliao.x.component.payload.response.ResponseUtils;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.component.utils.CoreUtils;

@Tag(name = "BsmUserController")
@RestController
@RequestMapping("/user")
public class BsmUserController {

    private BsmUserService bsmUserInfoService;

    @Autowired
    public void setBsmUserInfoService(BsmUserService bsmUserInfoService) {
        this.bsmUserInfoService = bsmUserInfoService;
    }

    @Operation
    @PostMapping("/signUp/sendPost")
    public Response<PayloadData> sendPost(@Validated @RequestBody UserSignUpSendPostDto requestDto,
                                          BindingResult bindingResult) throws AbstractException {
        if (bindingResult.hasErrors()) {

            throw new ParameterException(SecondaryCodeEnum.BLANK, CoreUtils.toFieldViolation(bindingResult));
        }

        return ResponseUtils.success(SimplePayloadData.of(bsmUserInfoService.signUpSendPost(requestDto)));
    }

    @Operation
    @PostMapping("/signUp/pre")
    public Response<PayloadData> preSignUp(@Validated @RequestBody UserSignUpSendPostDto requestDto,
                                           BindingResult bindingResult) throws AbstractException {
        if (bindingResult.hasErrors()) {

            throw new ParameterException(SecondaryCodeEnum.BLANK, CoreUtils.toFieldViolation(bindingResult));
        }

        return ResponseUtils.success(SimplePayloadData.of(bsmUserInfoService.preSignUp(requestDto)));
    }

    @Operation
    @PostMapping(value = "/signUp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<PayloadData> signUp(@RequestParam(name = "k") String k,
                                        @RequestPart(name = "avatar") MultipartFile avatar,
                                        @Valid @RequestPart(name = "user") BsmUserDto user,
                                        BindingResult bindingResult) throws AbstractException {
        if (bindingResult.hasErrors()) {

            throw new ParameterException(SecondaryCodeEnum.BLANK, CoreUtils.toFieldViolation(bindingResult));
        }
        bsmUserInfoService.signUp(k, user, avatar);

        return ResponseUtils.success();
    }

    @Operation
    @PostMapping("/signIn")
    public Response<PayloadData> signIn(@Valid @RequestBody UserSignInDto requestDto,
                                        BindingResult bindingResult) throws AbstractException {
        if (bindingResult.hasErrors()) {

            throw new ParameterException(SecondaryCodeEnum.BLANK, CoreUtils.toFieldViolation(bindingResult));
        }
        if (0L == bsmUserInfoService.signIn(requestDto)) {

            throw new SecurityException(BsmSecondaryCodeEnum.SIGN_IN_FAILED);
        }

        return ResponseUtils.success();
    }

}
