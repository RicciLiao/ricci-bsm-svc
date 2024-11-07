package ricciliao.bsm.common;

public class BsmCode {

    public static final String EMAIL_VERIFICATION = "EVT";

    public enum BsmCodeEnum {

        EMAIL_VERIFICATION_FOR_SIGN_UP(EMAIL_VERIFICATION, "EVTSU");

        BsmCodeEnum(String bsmCode, String bsmDetailCode) {
            this.bsmCode = bsmCode;
            this.bsmDetailCode = bsmDetailCode;
        }

        private final String bsmCode;
        private final String bsmDetailCode;

        public String getBsmCode() {
            return bsmCode;
        }

        public String getBsmDetailCode() {
            return bsmDetailCode;
        }
    }

}
