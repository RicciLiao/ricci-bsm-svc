package ricciliao.bsm.service;

import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.x.component.exception.AbstractException;

public interface BsmUserInfoService {

    Long signUp(String k, BsmUserInfoDto requestDto) throws AbstractException;

    String signUpSendPost(UserSignUpSendPostDto requestDto) throws AbstractException;

}
