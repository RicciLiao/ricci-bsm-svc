package ricciliao.bsm.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ricciliao.bsm.cache.pojo.ChallengeVerificationDto;
import ricciliao.bsm.cache.pojo.SignInLogDto;
import ricciliao.bsm.cache.pojo.SignUpLockDto;
import ricciliao.x.starter.mcp.McpConsumerRestService;

@Component("cacheProvider")
public class CacheProvider {

    private McpConsumerRestService<ChallengeVerificationDto> challengeConsumerRestService;
    private McpConsumerRestService<SignUpLockDto> signUpLockConsumerRestService;
    private McpConsumerRestService<SignInLogDto> signInLogConsumerRestService;

    @Qualifier("signUpLockMcpConsumerRestService")
    @Autowired
    public void setSignUpLockConsumerRestService(McpConsumerRestService<SignUpLockDto> signUpLockConsumerRestService) {
        this.signUpLockConsumerRestService = signUpLockConsumerRestService;
    }

    @Qualifier("challengeMcpConsumerRestService")
    @Autowired
    public void setChallengeConsumerRestService(McpConsumerRestService<ChallengeVerificationDto> challengeConsumerRestService) {
        this.challengeConsumerRestService = challengeConsumerRestService;
    }

    @Qualifier("signInLogMcpConsumerRestService")
    @Autowired
    public void setSignInLogConsumerRestService(McpConsumerRestService<SignInLogDto> signInLogConsumerRestService) {
        this.signInLogConsumerRestService = signInLogConsumerRestService;
    }

    public McpConsumerRestService<ChallengeVerificationDto> challenge() {

        return challengeConsumerRestService;
    }

    public McpConsumerRestService<SignUpLockDto> signUpLock() {

        return signUpLockConsumerRestService;
    }

    public McpConsumerRestService<SignInLogDto> signInLog() {

        return signInLogConsumerRestService;
    }

}
