package ricciliao.bsm.pojo.bo;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CaptchaRedisBo implements Serializable {

    private String captcha;
    private String email;
    private LocalDateTime cacheDtm;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCacheDtm() {
        return cacheDtm;
    }

    public void setCacheDtm(LocalDateTime cacheDtm) {
        this.cacheDtm = cacheDtm;
    }
}
