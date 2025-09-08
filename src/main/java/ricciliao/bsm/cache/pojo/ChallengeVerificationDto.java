package ricciliao.bsm.cache.pojo;

import ricciliao.x.cache.ConsumerCacheData;
import ricciliao.x.component.challenge.Challenge;

import java.io.Serial;
import java.util.Objects;

public class ChallengeVerificationDto extends Challenge implements ConsumerCacheData {
    @Serial
    private static final long serialVersionUID = -182040538870727655L;
    private boolean verified;

    public ChallengeVerificationDto() {
        this.verified = false;
    }

    public ChallengeVerificationDto(Challenge challenge) {
        super();
        this.setCode(challenge.getCode());
        this.setImage(challenge.getImage());
        this.setStrategy(challenge.getStrategy());
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ChallengeVerificationDto that)) return false;
        if (!super.equals(o)) return false;
        return isVerified() == that.isVerified();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isVerified());
    }

    @Override
    public String generateCacheKey() {
        return "";
    }
}
