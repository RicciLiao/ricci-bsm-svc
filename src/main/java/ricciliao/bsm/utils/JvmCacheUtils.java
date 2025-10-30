package ricciliao.bsm.utils;

public class JvmCacheUtils {

    private JvmCacheUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static Long systemUserId = 0L;

    public static Long getSystemUserId() {
        return systemUserId;
    }

    public static void setSystemUserId(Long systemUserId) {
        JvmCacheUtils.systemUserId = systemUserId;
    }
}
