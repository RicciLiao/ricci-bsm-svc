package ricciliao.bsm.pojo.bo;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public record BsmCodeDetailBo(String bsmCode, String bsmCodeDetail, String bsmCodeDetailDesc) implements Serializable {
    @Serial
    private static final long serialVersionUID = 2173311105245686625L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BsmCodeDetailBo that)) return false;
        return Objects.equals(bsmCode, that.bsmCode) && Objects.equals(bsmCodeDetail, that.bsmCodeDetail) && Objects.equals(bsmCodeDetailDesc, that.bsmCodeDetailDesc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bsmCode, bsmCodeDetail, bsmCodeDetailDesc);
    }
}
