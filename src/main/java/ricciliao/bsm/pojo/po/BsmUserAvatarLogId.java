package ricciliao.bsm.pojo.po;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class BsmUserAvatarLogId implements Serializable {
    @Serial
    private static final long serialVersionUID = 8927628963278604932L;

    private Long bsmUserId;
    private Instant actionDtm;

    public BsmUserAvatarLogId() {
    }

    public BsmUserAvatarLogId(Long bsmUserId, Instant actionDtm) {
        this.bsmUserId = bsmUserId;
        this.actionDtm = actionDtm;
    }

    public Long getBsmUserId() {
        return bsmUserId;
    }

    public void setBsmUserId(Long bsmUserId) {
        this.bsmUserId = bsmUserId;
    }

    public Instant getActionDtm() {
        return actionDtm;
    }

    public void setActionDtm(Instant actionDtm) {
        this.actionDtm = actionDtm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BsmUserAvatarLogId entity = (BsmUserAvatarLogId) o;
        return Objects.equals(this.bsmUserId, entity.bsmUserId) &&
               Objects.equals(this.actionDtm, entity.actionDtm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bsmUserId, actionDtm);
    }
}