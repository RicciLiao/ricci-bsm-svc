package ricciliao.bsm.pojo.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class BsmCodeDetailDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5189725060913822244L;

    public BsmCodeDetailDto() {
    }

    public BsmCodeDetailDto(Long id, Long bsmCodeId, String code) {
        this.id = id;
        this.bsmCodeId = bsmCodeId;
        this.code = code;
    }

    private Long id;
    private Long bsmCodeId;
    private String code;
    private String description;
    private Boolean active;
    private Long createdBy;
    private Long createdDtm;
    private Long updatedBy;
    private Long updatedDtm;
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBsmCodeId() {
        return bsmCodeId;
    }

    public void setBsmCodeId(Long bsmCodeId) {
        this.bsmCodeId = bsmCodeId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(Long createdDtm) {
        this.createdDtm = createdDtm;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getUpdatedDtm() {
        return updatedDtm;
    }

    public void setUpdatedDtm(Long updatedDtm) {
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
        if (this == o) return true;
        if (!(o instanceof BsmCodeDetailDto that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getBsmCodeId(), that.getBsmCodeId()) && Objects.equals(getCode(), that.getCode()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getActive(), that.getActive()) && Objects.equals(getCreatedBy(), that.getCreatedBy()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedBy(), that.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm()) && Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBsmCodeId(), getCode(), getDescription(), getActive(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}
