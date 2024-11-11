package ricciliao.bsm.common;

import ricciliao.common.component.response.ResponseVoCode;

public enum BsmResponseCode implements ResponseVoCode {

    POST_SIGN_UP_SEND_POST_EXISTED_EMAIL(50, "This Email has been registered, please use another Email or you can sign in directly.");

    BsmResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private final long code;
    private final String message;

    @Override
    public long getId() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
