package ricciliao.bsm.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import ricciliao.x.component.persistence.LogEntity;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "bsm_user_avatar_log")
@IdClass(BsmUserAvatarLogId.class)
public class BsmUserAvatarLogPo implements LogEntity {
    @Serial
    private static final long serialVersionUID = 3193373043912312547L;
    
    private Long bsmUserId;
    private Instant actionDtm;
    private String fspToken;
    private Long createdBy;
    private Instant createdDtm;
    private Long updatedBy;
    private Instant updatedDtm;
    private Long version;
    private Character actionCd;
    private Long actionBy;

    @Id
    @Column(name = "bsm_user_id")
    public Long getBsmUserId() {
        return bsmUserId;
    }

    public void setBsmUserId(Long bsmUserId) {
        this.bsmUserId = bsmUserId;
    }

    @Id
    @Column(name = "action_dtm")
    public Instant getActionDtm() {
        return actionDtm;
    }

    public void setActionDtm(Instant actionDtm) {
        this.actionDtm = actionDtm;
    }

    @Column(name = "fsp_token")
    public String getFspToken() {
        return fspToken;
    }

    public void setFspToken(String fspToken) {
        this.fspToken = fspToken;
    }

    @Column(name = "created_by")
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Column(name = "created_dtm")
    public Instant getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(Instant createdDtm) {
        this.createdDtm = createdDtm;
    }

    @Column(name = "updated_by")
    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Column(name = "updated_dtm")
    public Instant getUpdatedDtm() {
        return updatedDtm;
    }

    public void setUpdatedDtm(Instant updatedDtm) {
        this.updatedDtm = updatedDtm;
    }

    @Column(name = "version")
    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Column(name = "action_cd")
    public Character getActionCd() {
        return actionCd;
    }

    public void setActionCd(Character actionCd) {
        this.actionCd = actionCd;
    }

    @Column(name = "action_by")
    public Long getActionBy() {
        return actionBy;
    }

    public void setActionBy(Long actionBy) {
        this.actionBy = actionBy;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BsmUserAvatarLogPo logPo)) return false;
        return Objects.equals(getBsmUserId(), logPo.getBsmUserId()) && Objects.equals(getActionDtm(), logPo.getActionDtm()) && Objects.equals(getFspToken(), logPo.getFspToken()) && Objects.equals(getCreatedBy(), logPo.getCreatedBy()) && Objects.equals(getCreatedDtm(), logPo.getCreatedDtm()) && Objects.equals(getUpdatedBy(), logPo.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), logPo.getUpdatedDtm()) && Objects.equals(getVersion(), logPo.getVersion()) && Objects.equals(getActionCd(), logPo.getActionCd()) && Objects.equals(getActionBy(), logPo.getActionBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBsmUserId(), getActionDtm(), getFspToken(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion(), getActionCd(), getActionBy());
    }
}