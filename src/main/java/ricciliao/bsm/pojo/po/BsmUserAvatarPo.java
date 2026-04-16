package ricciliao.bsm.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "bsm_user_avatar")
public class BsmUserAvatarPo implements Serializable {
    @Serial
    private static final long serialVersionUID = 4702535455854956283L;

    @Id
    @Column(name = "bsm_user_id")
    private Long bsmUserId;

    @Column(name = "fsp_token")
    private String fspToken;

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

    public Long getBsmUserId() {
        return bsmUserId;
    }

    public void setBsmUserId(Long bsmUserId) {
        this.bsmUserId = bsmUserId;
    }

    public String getFspToken() {
        return fspToken;
    }

    public void setFspToken(String fspToken) {
        this.fspToken = fspToken;
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

}