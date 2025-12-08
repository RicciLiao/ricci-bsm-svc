package ricciliao.bsm.service;

import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignInDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.x.component.exception.AbstractException;

public interface BsmUserService {

    Long signUp(String k, BsmUserInfoDto requestDto) throws AbstractException;

    String signUpSendPost(UserSignUpSendPostDto requestDto) throws AbstractException;

    String preSignUp(UserSignUpSendPostDto requestDto) throws AbstractException;

    Long initialize();

    Long signIn(UserSignInDto requestDto) throws AbstractException;

}
