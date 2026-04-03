package ricciliao.bsm.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import ricciliao.x.component.persistence.LogPo;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "bsm_user_log")
@IdClass(BsmUserLogId.class)
public class BsmUserLogPo implements LogPo {
    @Serial
    private static final long serialVersionUID = 2376987929554355514L;

    @Id
    @Column(name = "id")
    private Long id;

    @Id
    @Column(name = "action_dtm")
    private Instant actionDtm;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "last_login_dtm")
    private Instant lastLoginDtm;

    @Column(name = "status_id")
    private Long statusId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Instant getActionDtm() {
        return actionDtm;
    }

    @Override
    public void setActionDtm(Instant actionDtm) {
        this.actionDtm = actionDtm;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Instant getLastLoginDtm() {
        return lastLoginDtm;
    }

    public void setLastLoginDtm(Instant lastLoginDtm) {
        this.lastLoginDtm = lastLoginDtm;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
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

    @Override
    public Character getActionCd() {
        return actionCd;
    }

    @Override
    public void setActionCd(Character actionCd) {
        this.actionCd = actionCd;
    }

    public Long getActionBy() {
        return actionBy;
    }

    public void setActionBy(Long actionBy) {
        this.actionBy = actionBy;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BsmUserLogPo that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getActionDtm(), that.getActionDtm()) && Objects.equals(getLoginName(), that.getLoginName()) && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getUserPassword(), that.getUserPassword()) && Objects.equals(getUserEmail(), that.getUserEmail()) && Objects.equals(getLastLoginDtm(), that.getLastLoginDtm()) && Objects.equals(getStatusId(), that.getStatusId()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion()) && Objects.equals(getActionCd(), that.getActionCd()) && Objects.equals(getActionBy(), that.getActionBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getActionDtm(), getLoginName(), getUserName(), getUserPassword(), getUserEmail(), getLastLoginDtm(), getStatusId(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion(), getActionCd(), getActionBy());
    }
}