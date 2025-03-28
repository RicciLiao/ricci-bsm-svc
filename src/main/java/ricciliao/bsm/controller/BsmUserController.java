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
import ricciliao.x.component.exception.CmnException;
import ricciliao.x.component.response.ResponseCodeEnum;
import ricciliao.x.component.response.ResponseData;
import ricciliao.x.component.response.ResponseSimpleData;
import ricciliao.x.component.response.ResponseUtils;
import ricciliao.x.component.response.ResponseVo;

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
    public ResponseVo<ResponseData> sendPost(@Validated @RequestBody UserSignUpSendPostDto requestDto,
                                             BindingResult bindingResult) throws CmnException {
        if (bindingResult.hasErrors()) {

            return ResponseUtils.builder(bindingResult).code(ResponseCodeEnum.PARAMETER_ERROR).build();
        }

        return ResponseUtils.successResponse(new ResponseSimpleData.Bool(bsmUserInfoService.signUpSendPost(requestDto)));
    }

    @Operation
    @PostMapping("/signUp")
    public ResponseVo<ResponseData> signUp(@Valid @RequestBody BsmUserInfoDto requestDto,
                                           BindingResult bindingResult) throws CmnException {
        if (bindingResult.hasErrors()) {


            return ResponseUtils.builder(bindingResult).build();
        }
        bsmUserInfoService.signUp(requestDto);

        return ResponseUtils.successResponse();

    }

}
