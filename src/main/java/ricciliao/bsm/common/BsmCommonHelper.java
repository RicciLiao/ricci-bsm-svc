package ricciliao.bsm.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

public class BsmCommonHelper {

    private BsmCommonHelper() {
        throw new IllegalStateException("Utility class");
    }

    public static LocalDateTime long2LocalDateTime(long milliseconds) {

        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
    }

    public static Long localDateTime2Long(LocalDateTime localDateTime) {

        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDate long2LocalDate(long milliseconds) {

        return BsmCommonHelper.long2LocalDateTime(milliseconds).toLocalDate();
    }

    public static Long localDate2Long(LocalDate localDate) {

        return BsmCommonHelper.localDateTime2Long(localDate.atTime(LocalTime.MIN));
    }

}
