package ricciliao.bsm.pojo.dto.response;


import ricciliao.x.component.response.ResponseData;

import java.io.Serial;

public class GetCaptchaDto implements ResponseData {
    @Serial
    private static final long serialVersionUID = 8830237055676586883L;

    public GetCaptchaDto(String k, String i, Long t) {
        this.k = k;
        this.i = i;
        this.t = t;
    }

    private final String k; //key
    private final String i; //base64 image
    private final Long t;   //reset time

    public String getK() {
        return k;
    }

    public String getI() {
        return i;
    }

    public Long getT() {
        return t;
    }

}
