package ricciliao.bsm.cache;

import ricciliao.bsm.cache.pojo.ChallengeVerificationDto;
import ricciliao.bsm.pojo.dto.request.VerifyChallengeDto;
import ricciliao.x.component.challenge.Challenge;
import ricciliao.x.mcp.ConsumerCache;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class ChallengeCacheBuilder {

    private ChallengeCacheBuilder() {
        throw new IllegalStateException("Utility class");
    }

    public static GetChallenge get(Challenge challenge) {

        return new GetChallenge(challenge);
    }

    public static VerifyChallenge verify(VerifyChallengeDto verify) {

        return new VerifyChallenge(verify);
    }

    public static class GetChallenge {
        private final ConsumerCache<ChallengeVerificationDto> verification;
        private Consumer<ConsumerCache<ChallengeVerificationDto>> process;

        public GetChallenge(Challenge challenge) {
            this.verification = ConsumerCache.of(new ChallengeVerificationDto(challenge));
        }

        public Consumer<ConsumerCache<ChallengeVerificationDto>> getProcess() {
            return process;
        }

        public ConsumerCache<ChallengeVerificationDto> getVerification() {
            return verification;
        }

        public GetChallenge process(Consumer<ConsumerCache<ChallengeVerificationDto>> process) {
            this.process = process;

            return this;
        }

        public ConsumerCache<ChallengeVerificationDto> get() {
            if (Objects.nonNull(process)) {
                process.accept(this.verification);
            }

            return this.verification;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof GetChallenge that)) return false;
            return Objects.equals(verification, that.verification) && Objects.equals(process, that.process);
        }

        @Override
        public int hashCode() {
            return Objects.hash(verification, process);
        }
    }

    public static class VerifyChallenge {
        private final VerifyChallengeDto challenge;
        private Function<ChallengeVerificationDto, Boolean> process;

        public VerifyChallenge(VerifyChallengeDto challengeDto) {
            this.challenge = challengeDto;
        }

        public VerifyChallengeDto getChallenge() {
            return challenge;
        }

        public Function<ChallengeVerificationDto, Boolean> getProcess() {
            return process;
        }

        public VerifyChallenge process(Function<ChallengeVerificationDto, Boolean> process) {
            this.process = process;

            return this;
        }

        public boolean apply(ChallengeVerificationDto cache) {

            return Objects.nonNull(process) && !process.apply(cache);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof VerifyChallenge that)) return false;
            return Objects.equals(getChallenge(), that.getChallenge()) && Objects.equals(process, that.process);
        }

        @Override
        public int hashCode() {
            return Objects.hash(getChallenge(), process);
        }
    }

}
