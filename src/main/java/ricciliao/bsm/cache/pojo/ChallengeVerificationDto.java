package ricciliao.bsm.cache.pojo;

import ricciliao.x.cache.ConsumerStore;
import ricciliao.x.component.challenge.Challenge;

import java.io.Serial;
import java.util.Objects;

public class ChallengeVerificationDto extends Challenge implements ConsumerStore {
    @Serial
    private static final long serialVersionUID = -182040538870727655L;
    private boolean verified = false;
    private String emailAddress;

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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ChallengeVerificationDto that)) return false;
        if (!super.equals(o)) return false;
        return isVerified() == that.isVerified() && Objects.equals(getEmailAddress(), that.getEmailAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isVerified(), getEmailAddress());
    }

    @Override
    public String generateCacheKey() {
        return "";
    }
}
