package ricciliao.bsm.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BsmUserInfoDto {

    private Long id;
    @NotBlank
    @Length(min = 5, max = 15)
    private String loginName;
    @Length(min = 1, max = 15)
    private String userName;
    @JsonSerialize(using = NullSerializer.class)
    private String userPassword;
    @NotBlank
    private String userEmail;
    @JsonIgnore
    private Instant lastLoginDtm;
    @JsonIgnore
    private Long statusId;
    @JsonIgnore
    private Long createdBy;
    @JsonIgnore
    private Instant createdDtm;
    @JsonIgnore
    private Long updatedBy;
    @JsonIgnore
    private Instant updatedDtm;
    private Long version;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getLastLoginDtm() {
        return lastLoginDtm;
    }

    public void setLastLoginDtm(Instant lastLoginDtm) {
        this.lastLoginDtm = lastLoginDtm;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
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

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BsmUserInfoDto that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getLoginName(), that.getLoginName()) && Objects.equals(getUserName(), that.getUserName()) && Objects.equals(getUserPassword(), that.getUserPassword()) && Objects.equals(getUserEmail(), that.getUserEmail()) && Objects.equals(getLastLoginDtm(), that.getLastLoginDtm()) && Objects.equals(getStatusId(), that.getStatusId()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLoginName(), getUserName(), getUserPassword(), getUserEmail(), getLastLoginDtm(), getStatusId(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}