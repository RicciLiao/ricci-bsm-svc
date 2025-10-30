package ricciliao.bsm.utils;

public class JvmCacheUtils {

    private static Long systemUserId = 0L;

    private JvmCacheUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Long getSystemUserId() {
        return systemUserId;
    }

    public static void setSystemUserId(Long systemUserId) {
        JvmCacheUtils.systemUserId = systemUserId;
    }
}
