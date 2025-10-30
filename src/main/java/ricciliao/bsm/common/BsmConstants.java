package ricciliao.bsm.common;

public class BsmConstants {

    public static final Long ACTIVE_STATUS = 10000L;
    public static final Long INITIALIZED_STATUS = 10003L;
    public static final String APP_VERSION_USER = "@%s@";

    private BsmConstants() {
        throw new IllegalStateException("Utility class");
    }

}
