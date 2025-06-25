package ricciliao.bsm.pojo.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;

public class VerifyChallengeDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -5655980823864994419L;

    @NotBlank
    private String c;
    @NotBlank
    private String k;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }
}
