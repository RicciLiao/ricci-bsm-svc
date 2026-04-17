package ricciliao.bsm.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import ricciliao.x.component.persistence.ModifiableEntity;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "bsm_user")
public class BsmUserPo implements ModifiableEntity {
    @Serial
    private static final long serialVersionUID = 1232388334533272926L;
    
    private Long id;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Version
    @Column(name = "version")
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