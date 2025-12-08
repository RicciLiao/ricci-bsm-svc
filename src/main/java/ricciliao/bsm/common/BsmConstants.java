package ricciliao.bsm.common;

public class BsmConstants {

    public static final Long DATA_STATUS_ACTIVE = 10000L;
    public static final Long DATA_STATUS_INITIALIZED = 10003L;
    public static final String APP_VERSION_USER = "@%s@";
    public static final Long SIGN_IN_WAY_EMAIL = 10004L;
    public static final Long SIGN_IN_WAY_NAME = 10005L;

    private BsmConstants() {
        throw new IllegalStateException("Utility class");
    }

}
