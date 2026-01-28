package ricciliao.bsm.cache.pojo;

import ricciliao.x.mcp.ConsumerCacheData;

import java.io.Serial;
import java.time.Instant;

public class SignInLogDto implements ConsumerCacheData {
    @Serial
    private static final long serialVersionUID = -1906065287172641192L;

    private Long userId;
    private Instant signInDtm;
    private Long signInWayId;
    private Boolean success = Boolean.FALSE;

    public Instant getSignInDtm() {
        return signInDtm;
    }

    public void setSignInDtm(Instant signInDtm) {
        this.signInDtm = signInDtm;
    }

    public Long getSignInWayId() {
        return signInWayId;
    }

    public void setSignInWayId(Long signInWayId) {
        this.signInWayId = signInWayId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @Override
    public String generateUid() {
        return "";
    }
}
