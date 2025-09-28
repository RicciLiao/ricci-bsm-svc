package ricciliao.bsm.cache;

import ricciliao.bsm.cache.pojo.ChallengeVerificationDto;
import ricciliao.bsm.pojo.dto.request.VerifyChallengeDto;
import ricciliao.x.component.challenge.Challenge;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

public class ChallengeOpBuilder {

    private ChallengeOpBuilder() {
        throw new IllegalStateException("Utility class");
    }

    public static GetChallenge get(Challenge challenge) {

        return new GetChallenge(challenge);
    }

    public static VerifyChallenge verify(VerifyChallengeDto verify) {

        return new VerifyChallenge(verify);
    }

    public static class GetChallenge {
        private final ChallengeVerificationDto verification;
        private Consumer<ChallengeVerificationDto> op;

        public GetChallenge(Challenge challenge) {
            this.verification = new ChallengeVerificationDto(challenge);
        }

        public GetChallenge op(Consumer<ChallengeVerificationDto> consumer) {
            this.op = consumer;

            return this;
        }

        public ChallengeVerificationDto get() {
            if (Objects.nonNull(op)) {
                op.accept(this.verification);
            }

            return this.verification;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof GetChallenge that)) return false;
            return Objects.equals(verification, that.verification) && Objects.equals(op, that.op);
        }

        @Override
        public int hashCode() {
            return Objects.hash(verification, op);
        }
    }

    public static class VerifyChallenge {
        private final VerifyChallengeDto verify;
        private Function<ChallengeVerificationDto, Boolean> op;

        public VerifyChallenge(VerifyChallengeDto verify) {
            this.verify = verify;
        }

        public VerifyChallenge op(Function<ChallengeVerificationDto, Boolean> function) {
            this.op = function;

            return this;
        }

        public VerifyChallengeDto getVerify() {
            return verify;
        }

        public boolean get(ChallengeVerificationDto verification) {

            return Objects.nonNull(op) && !op.apply(verification);
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof VerifyChallenge that)) return false;
            return Objects.equals(getVerify(), that.getVerify()) && Objects.equals(op, that.op);
        }

        @Override
        public int hashCode() {
            return Objects.hash(getVerify(), op);
        }
    }

}
