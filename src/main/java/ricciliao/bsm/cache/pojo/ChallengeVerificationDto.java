package ricciliao.bsm.cache.pojo;

import ricciliao.x.cache.pojo.common.ChallengeCacheDto;
import ricciliao.x.component.challenge.Challenge;

import java.io.Serial;
import java.util.Objects;

public class ChallengeVerificationDto extends ChallengeCacheDto {
    @Serial
    private static final long serialVersionUID = -182040538870727655L;
    private boolean verified;

    public ChallengeVerificationDto() {
    }

    public ChallengeVerificationDto(Challenge challenge) {
        this.setChallenge(challenge);
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
}
