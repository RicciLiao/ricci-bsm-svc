package ricciliao.bsm.common;


import ricciliao.x.component.response.code.SecondaryCode;

public enum BsmSecondaryCodeEnum implements SecondaryCode {

    EXISTED_EMAIL(1, "This Email has been registered, you can sign in directly or use another Email to register."),
    MISMATCHED_CAPTCHA(2, "Captcha does not match, please try again."),
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
