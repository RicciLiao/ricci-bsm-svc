package ricciliao.bsm.pojo.bo;

import ricciliao.common.component.random.RandomGenerator;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class RedisCatchBo implements Serializable {
    @Serial
    private static final long serialVersionUID = 8938462924539717763L;

    private final String id = RandomGenerator.nextString(12).generate();
    private final LocalDateTime createdDtm = LocalDateTime.now();
    private LocalDateTime updatedDtm = LocalDateTime.now();

    public LocalDateTime getUpdatedDtm() {
        return updatedDtm;
    }

    public void setUpdatedDtm(LocalDateTime updatedDtm) {
        this.updatedDtm = updatedDtm;
    }

    public LocalDateTime getCreatedDtm() {
        return createdDtm;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RedisCatchBo that)) return false;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getCreatedDtm(), that.getCreatedDtm()) && Objects.equals(getUpdatedDtm(), that.getUpdatedDtm());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCreatedDtm(), getUpdatedDtm());
    }
}
