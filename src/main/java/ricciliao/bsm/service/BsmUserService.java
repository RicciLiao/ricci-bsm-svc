package ricciliao.bsm.service;

import org.springframework.web.multipart.MultipartFile;
import ricciliao.bsm.pojo.dto.BsmUserDto;
import ricciliao.bsm.pojo.dto.request.UserSignInDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.x.component.exception.AbstractException;

public interface BsmUserService {

    Long signUp(String k, BsmUserDto user, MultipartFile avatar) throws AbstractException;

    String signUpSendPost(UserSignUpSendPostDto requestDto) throws AbstractException;

    String preSignUp(UserSignUpSendPostDto requestDto) throws AbstractException;

    Long initialize();

    Long signIn(UserSignInDto requestDto) throws AbstractException;

}
