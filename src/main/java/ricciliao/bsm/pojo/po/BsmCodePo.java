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
@Table(name = "bsm_code")
public class BsmCodePo implements Serializable {
    @Serial
    private static final long serialVersionUID = -7720481903675357203L;

    private Long id;
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
        BsmCodePo bsmCodePo = (BsmCodePo) o;
        return Objects.equals(id, bsmCodePo.id) && Objects.equals(code, bsmCodePo.code) && Objects.equals(description, bsmCodePo.description) && Objects.equals(isActive, bsmCodePo.isActive) && Objects.equals(createdBy, bsmCodePo.createdBy) && Objects.equals(createdDtm, bsmCodePo.createdDtm) && Objects.equals(updatedBy, bsmCodePo.updatedBy) && Objects.equals(updatedDtm, bsmCodePo.updatedDtm) && Objects.equals(version, bsmCodePo.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, description, isActive, createdBy, createdDtm, updatedBy, updatedDtm, version);
    }
}
