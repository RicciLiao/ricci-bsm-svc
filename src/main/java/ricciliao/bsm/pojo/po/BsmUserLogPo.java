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
@Table(name = "bsm_user_log")
@IdClass(BsmUserLogId.class)
public class BsmUserLogPo implements LogEntity {
    @Serial
    private static final long serialVersionUID = -1630755457870603634L;
    
    private Long id;
    private Instant actionDtm;
    private String loginName;
    private String userName;
    private String userPassword;
    private String userEmail;
    private Instant lastLoginDtm;
    private Long statusId;
    private Long createdBy;
    private Instant createdDtm;
    private Long updatedBy;
    private Instant updatedDtm;
    private Long version;
    private Character actionCd;
    private Long actionBy;

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @Column(name = "action_dtm")
    public Instant getActionDtm() {
        return actionDtm;
    }

    public void setActionDtm(Instant actionDtm) {
        this.actionDtm = actionDtm;
    }

    @Column(name = "login_name")
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(name = "user_password")
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Column(name = "user_email")
    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Column(name = "last_login_dtm")
    public Instant getLastLoginDtm() {
        return lastLoginDtm;
    }

    public void setLastLoginDtm(Instant lastLoginDtm) {
        this.lastLoginDtm = lastLoginDtm;
    }

    @Column(name = "status_id")
    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
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
        if (!(o instanceof BsmUserLogPo that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getActionDtm(), that.getActionDtm()) && Objects.equals(getLoginName(), that.getLoginName()) && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getUserPassword(), that.getUserPassword()) && Objects.equals(getUserEmail(), that.getUserEmail()) && Objects.equals(getLastLoginDtm(), that.getLastLoginDtm()) && Objects.equals(getStatusId(), that.getStatusId()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion()) && Objects.equals(getActionCd(), that.getActionCd()) && Objects.equals(getActionBy(), that.getActionBy());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getActionDtm(), getLoginName(), getUserName(), getUserPassword(), getUserEmail(), getLastLoginDtm(), getStatusId(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion(), getActionCd(), getActionBy());
    }
}