package ricciliao.bsm.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import ricciliao.x.component.persistence.LogPo;

import java.io.Serial;
import java.time.Instant;

@Entity
@Table(name = "bsm_user_avatar_log")
@IdClass(BsmUserAvatarLogId.class)
public class BsmUserAvatarLogPo implements LogPo {
    @Serial
    private static final long serialVersionUID = -8203210924950878134L;

    @Id
    @Column(name = "bsm_user_id")
    private Long bsmUserId;

    @Id
    @Column(name = "action_dtm")
    private Instant actionDtm;

    @Column(name = "fsp_token")
    private String fspToken;

    @Column(name = "created_by")
    private Long createdBy;

    @Column(name = "created_dtm")
    private Instant createdDtm;

    @Column(name = "updated_by")
    private Long updatedBy;

    @Column(name = "updated_dtm")
    private Instant updatedDtm;

    @Column(name = "version")
    private Long version;

    @Column(name = "action_cd")
    private Character actionCd;

    @Column(name = "action_by")
    private Long actionBy;

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

    public String getFspToken() {
        return fspToken;
    }

    public void setFspToken(String fspToken) {
        this.fspToken = fspToken;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(Instant createdDtm) {
        this.createdDtm = createdDtm;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Instant getUpdatedDtm() {
        return updatedDtm;
    }

    public void setUpdatedDtm(Instant updatedDtm) {
        this.updatedDtm = updatedDtm;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Character getActionCd() {
        return actionCd;
    }

    public void setActionCd(Character actionCd) {
        this.actionCd = actionCd;
    }

    public Long getActionBy() {
        return actionBy;
    }

    public void setActionBy(Long actionBy) {
        this.actionBy = actionBy;
    }

}