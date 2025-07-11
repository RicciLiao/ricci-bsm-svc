package ricciliao.bsm.pojo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import ricciliao.x.component.serialisation.LocalDateTimeDeserializer;
import ricciliao.x.component.serialisation.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BsmUserInfoDto {

    private Long id;
    @NotBlank
    @Length(min = 5, max = 15)
    private String loginName;
    @NotBlank
    @Length(min = 1, max = 15)
    private String userName;
    @JsonIgnore
    private String userPassword;
    @NotBlank
    private String userEmail;
    @JsonIgnore
    private LocalDateTime lastLoginDtm;
    @JsonIgnore
    private Long statusId;
    @JsonIgnore
    private Long createdBy;
    @JsonIgnore
    private LocalDateTime createdDtm;
    @JsonIgnore
    private Long updatedBy;
    @JsonIgnore
    private LocalDateTime updatedDtm;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime version;

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

    public LocalDateTime getLastLoginDtm() {
        return lastLoginDtm;
    }

    public void setLastLoginDtm(LocalDateTime lastLoginDtm) {
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

    public LocalDateTime getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(LocalDateTime createdDtm) {
        this.createdDtm = createdDtm;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedDtm() {
        return updatedDtm;
    }

    public void setUpdatedDtm(LocalDateTime updatedDtm) {
        this.updatedDtm = updatedDtm;
    }

    public LocalDateTime getVersion() {
        return version;
    }

    public void setVersion(LocalDateTime version) {
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