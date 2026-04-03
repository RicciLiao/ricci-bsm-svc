package ricciliao.bsm.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "bsm_user")
public class BsmUserPo implements Serializable {
    @Serial
    private static final long serialVersionUID = -1686666696193049959L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

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

    @Version
    @Column(name = "version")
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
    public boolean equals(Object o) {
        if (!(o instanceof BsmUserPo bsmUserPo)) return false;
        return Objects.equals(getId(), bsmUserPo.getId()) && Objects.equals(getLoginName(), bsmUserPo.getLoginName()) && Objects.equals(getUserName(), bsmUserPo.getUserName()) && Objects.equals(getUserPassword(), bsmUserPo.getUserPassword()) && Objects.equals(getUserEmail(), bsmUserPo.getUserEmail()) && Objects.equals(getLastLoginDtm(), bsmUserPo.getLastLoginDtm()) && Objects.equals(getStatusId(), bsmUserPo.getStatusId()) && Objects.equals(getCreatedBy(), bsmUserPo.getCreatedBy()) && Objects.equals(getCreatedDtm(), bsmUserPo.getCreatedDtm()) && Objects.equals(getUpdatedBy(), bsmUserPo.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), bsmUserPo.getUpdatedDtm()) && Objects.equals(getVersion(), bsmUserPo.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLoginName(), getUserName(), getUserPassword(), getUserEmail(), getLastLoginDtm(), getStatusId(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}