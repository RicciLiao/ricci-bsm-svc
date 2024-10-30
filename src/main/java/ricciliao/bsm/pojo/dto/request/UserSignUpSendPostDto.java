package ricciliao.bsm.pojo.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

public class UserSignUpSendPostDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -7398802352610627681L;

    @NotBlank
    private String emailAddress;
    @NotBlank
    private String captchaCode;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCaptchaCode() {
        return captchaCode;
    }

    public void setCaptchaCode(String captchaCode) {
        this.captchaCode = captchaCode;
    }
}
