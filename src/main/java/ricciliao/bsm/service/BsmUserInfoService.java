package ricciliao.bsm.service;

import ricciliao.bsm.pojo.dto.BsmUserInfoDto;
import ricciliao.bsm.pojo.dto.request.UserSignUpSendPostDto;
import ricciliao.x.component.exception.CmnException;

public interface BsmUserInfoService {

    Long signUp(BsmUserInfoDto requestDto) throws CmnException;

    Boolean signUpSendPost(UserSignUpSendPostDto requestDto) throws CmnException;

}
