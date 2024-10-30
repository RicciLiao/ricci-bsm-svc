package ricciliao.bsm.service;

import ricciliao.common.component.exception.CmnException;
import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;

public interface BsmUserInfoService {

    Long signUp(BsmUserInfoDto requestDto) throws CmnException;

    Long signUpSendPost(UserSignUpSendPostDto requestDto) throws CmnException;

}
