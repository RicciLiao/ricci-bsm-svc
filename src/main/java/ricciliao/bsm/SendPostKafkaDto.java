package ricciliao.bsm;

import ricciliao.x.component.kafka.KafkaMessageDto;

import java.io.Serial;
import java.time.Instant;
import java.util.Objects;

public class SendPostKafkaDto implements KafkaMessageDto {
    @Serial
    private static final long serialVersionUID = -916146796564299665L;
    private String code;
    private Instant expireAt;
    private String to;

    public SendPostKafkaDto() {
    }

    public SendPostKafkaDto(String code, String to, Instant expireAt) {
        this.code = code;
        this.to = to;
        this.expireAt = expireAt;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Instant getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Instant expireAt) {
        this.expireAt = expireAt;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SendPostKafkaDto that)) return false;
        return Objects.equals(getCode(), that.getCode()) && Objects.equals(getExpireAt(), that.getExpireAt()) && Objects.equals(getTo(), that.getTo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode(), getExpireAt(), getTo());
    }
}
