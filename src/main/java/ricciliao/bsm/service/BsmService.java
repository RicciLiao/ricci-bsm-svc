package ricciliao.bsm.service;

import ricciliao.bsm.pojo.dto.request.VerifyCaptchaDto;
import ricciliao.bsm.pojo.dto.response.GetCaptchaDto;
import ricciliao.common.component.exception.CmnException;

public interface BsmService {

    GetCaptchaDto getCaptcha() throws CmnException;

    boolean verifyCaptcha(VerifyCaptchaDto requestDto);

    boolean sendEmail(Long emailKey);

}
