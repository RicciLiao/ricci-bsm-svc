package ricciliao.bsm.cache.pojo;

import org.springframework.util.DigestUtils;
import ricciliao.x.mcp.ConsumerCacheData;

import java.io.Serial;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class SignUpLockDto implements ConsumerCacheData {
    @Serial
    private static final long serialVersionUID = 8718746710826396880L;

    private String emailAddress;
    private String challengeKey;

    public static String toCacheKey(String emailAddress) {

        return DigestUtils.md5DigestAsHex(emailAddress.getBytes(StandardCharsets.UTF_8));
    }

    public String getChallengeKey() {
        return challengeKey;
    }

    public void setChallengeKey(String challengeKey) {
        this.challengeKey = challengeKey;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof SignUpLockDto that)) return false;
        return Objects.equals(getEmailAddress(), that.getEmailAddress()) && Objects.equals(getChallengeKey(), that.getChallengeKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmailAddress(), getChallengeKey());
    }

    @Override
    public String generateUid() {

        return SignUpLockDto.toCacheKey(emailAddress);
    }
}
