package ricciliao.bsm.pojo.po;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "bsm_code_detail")
public class BsmCodeDetailPo implements Serializable {
    @Serial
    private static final long serialVersionUID = 7367342773515638303L;

    private Long id;
    private Long bsmCodeId;
    private String code;
    private String description;
    private Integer isActive;
    private Long createdBy;
    private LocalDateTime createdDtm;
    private Long updatedBy;
    private LocalDateTime updatedDtm;
    private LocalDateTime version;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bsm_code_id")
    public Long getBsmCodeId() {
        return bsmCodeId;
    }

    public void setBsmCodeId(Long bsmCodeId) {
        this.bsmCodeId = bsmCodeId;
    }

    @Basic
    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "is_active")
    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "created_by")
    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "created_dtm")
    public LocalDateTime getCreatedDtm() {
        return createdDtm;
    }

    public void setCreatedDtm(LocalDateTime createdDtm) {
        this.createdDtm = createdDtm;
    }

    @Basic
    @Column(name = "updated_by")
    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Basic
    @Column(name = "updated_dtm")
    public LocalDateTime getUpdatedDtm() {
        return updatedDtm;
    }

    public void setUpdatedDtm(LocalDateTime updatedDtm) {
        this.updatedDtm = updatedDtm;
    }

    @Basic
    @Column(name = "version")
    public LocalDateTime getVersion() {
        return version;
    }

    public void setVersion(LocalDateTime version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BsmCodeDetailPo that = (BsmCodeDetailPo) o;
        return Objects.equals(id, that.id) && Objects.equals(bsmCodeId, that.bsmCodeId) && Objects.equals(code, that.code) && Objects.equals(description, that.description) && Objects.equals(isActive, that.isActive) && Objects.equals(createdBy, that.createdBy) && Objects.equals(createdDtm, that.createdDtm) && Objects.equals(updatedBy, that.updatedBy) && Objects.equals(updatedDtm, that.updatedDtm) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bsmCodeId, code, description, isActive, createdBy, createdDtm, updatedBy, updatedDtm, version);
    }
}
