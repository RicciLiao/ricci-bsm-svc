package ricciliao.bsm.common;


import ricciliao.x.component.payload.response.code.SecondaryCode;

public enum BsmSecondaryCodeEnum implements SecondaryCode {

    EXISTED_EMAIL(1, "This Email has been registered, you can sign in directly or use another Email to register."),
    MISMATCHED_CAPTCHA(2, "Captcha does not match, please try again."),
    TIMEOUT(3, "Time out, please try again."),
    SIGN_IN_FAILED(4, "Login failed, please check your input."),
    ;

    private final int id;
    private final String message;

    BsmSecondaryCodeEnum(int id, String message) {
        this.id = id;
        this.message = message;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
