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
@Table(name = "bsm_code")
public class BsmCodePo implements ModifiableEntity {
    @Serial
    private static final long serialVersionUID = -3097835691153154380L;

    private Long id;
    private String code;
    private String description;
    private Boolean active;
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

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "active")
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        if (!(o instanceof BsmCodePo bsmCodePo)) return false;
        return Objects.equals(getId(), bsmCodePo.getId()) && Objects.equals(getCode(), bsmCodePo.getCode()) && Objects.equals(getDescription(), bsmCodePo.getDescription()) && Objects.equals(getActive(), bsmCodePo.getActive()) && Objects.equals(getCreatedBy(), bsmCodePo.getCreatedBy()) && Objects.equals(getCreatedDtm(), bsmCodePo.getCreatedDtm()) && Objects.equals(getUpdatedBy(), bsmCodePo.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), bsmCodePo.getUpdatedDtm()) && Objects.equals(getVersion(), bsmCodePo.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCode(), getDescription(), getActive(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}