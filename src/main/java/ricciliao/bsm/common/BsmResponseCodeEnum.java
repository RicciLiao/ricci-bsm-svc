package ricciliao.bsm.common;

import ricciliao.common.component.response.ResponseCode;

public enum BsmResponseCodeEnum implements ResponseCode {

    POST_SIGN_UP_SEND_POST_EXISTED_EMAIL(50, "This Email has been registered, please use another Email or you can sign in directly.");

    BsmResponseCodeEnum(int id, String message) {
        this.id = id;
        this.message = message;
    }

    private final long id;
    private final String message;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
