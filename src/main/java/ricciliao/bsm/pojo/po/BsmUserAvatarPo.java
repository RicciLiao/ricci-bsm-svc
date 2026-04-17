package ricciliao.bsm.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import ricciliao.x.component.persistence.ModifiableEntity;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "bsm_user_avatar")
public class BsmUserAvatarPo implements ModifiableEntity {
    @Serial
    private static final long serialVersionUID = 6790537579071394797L;
    
    private Long bsmUserId;
    private String fspToken;
    private Long createdBy;
    private Instant createdDtm;
    private Long updatedBy;
    private Instant updatedDtm;
    private Long version;

    @Id
    @Column(name = "bsm_user_id")
    public Long getBsmUserId() {
        return bsmUserId;
    }

    public void setBsmUserId(Long id) {
        this.bsmUserId = id;
    }

    @Column(name = "fsp_token")
    public String getFspToken() {
        return fspToken;
    }

    public void setFspToken(String fspToken) {
        this.fspToken = fspToken;
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
        if (!(o instanceof BsmUserAvatarPo avatarPo)) return false;
        return Objects.equals(getBsmUserId(), avatarPo.getBsmUserId()) && Objects.equals(getFspToken(), avatarPo.getFspToken()) && Objects.equals(getCreatedBy(), avatarPo.getCreatedBy()) && Objects.equals(getCreatedDtm(), avatarPo.getCreatedDtm()) && Objects.equals(getUpdatedBy(), avatarPo.getUpdatedBy()) && Objects.equals(getUpdatedDtm(), avatarPo.getUpdatedDtm()) && Objects.equals(getVersion(), avatarPo.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBsmUserId(), getFspToken(), getCreatedBy(), getCreatedDtm(), getUpdatedBy(), getUpdatedDtm(), getVersion());
    }
}