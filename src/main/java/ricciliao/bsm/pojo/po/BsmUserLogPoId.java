package ricciliao.bsm.pojo.po;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BsmUserLogPoId implements Serializable {
    private static final long serialVersionUID = -8413886290102893169L;
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "version", nullable = false)
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BsmUserLogPoId entity = (BsmUserLogPoId) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.version, entity.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version);
    }

}