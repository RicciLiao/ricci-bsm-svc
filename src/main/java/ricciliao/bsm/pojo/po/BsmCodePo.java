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
@Table(name = "bsm_code")
public class BsmCodePo implements Serializable {
    @Serial
    private static final long serialVersionUID = 1413891399510483391L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active")
    private Integer isActive;

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

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
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
        if (!(o instanceof BsmCodePo bsmCodePo)) return false;
        return Objects.equals(getId(), bsmCodePo.getId()) && Objects.equals(getCode(), bsmCodePo.getCode()) && Objects.equals(getDescription(), bsmCodePo.getDescription()) && Objects.equals(getIsActive(), bsmCodePo.getIsActive()) && Objects.equals(getCreatedBy(), bsmCodePo.getCreatedBy()) && Objects.equals(getCreatedDtm(), bsmCodePo.getCreatedDtm()) && Objects.equals(getUpdatedBy(), bsmCodePo.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), bsmCodePo.getUpdatedDtm()) && Objects.equals(getVersion(), bsmCodePo.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getDescription(), getIsActive(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}