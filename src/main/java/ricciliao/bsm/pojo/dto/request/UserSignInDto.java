package ricciliao.bsm.pojo.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class UserSignInDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 2659596222637938984L;

    @NotBlank
    private String signInName;
    @NotBlank
    private String signInPassword;

    public String getSignInName() {
        return signInName;
    }

    public void setSignInName(String signInName) {
        this.signInName = signInName;
    }

    public String getSignInPassword() {
        return signInPassword;
    }

    public void setSignInPassword(String signInPassword) {
        this.signInPassword = signInPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserSignInDto that)) return false;
        return Objects.equals(getSignInName(), that.getSignInName()) && Objects.equals(getSignInPassword(), that.getSignInPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSignInName(), getSignInPassword());
    }
}
