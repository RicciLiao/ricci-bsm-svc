package ricciliao.bsm.pojo.dto.request;

import java.io.Serializable;

public class VerifyCaptchaDto implements Serializable {

    private String c;
    private String k;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }
}
