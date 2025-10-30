package ricciliao.bsm.pojo.dto.response;


import com.fasterxml.jackson.annotation.JsonIgnore;
import ricciliao.x.component.payload.PayloadData;

import java.io.Serial;
import java.util.Objects;

/**
 * @param k Cache key
 * @param i Challenge base64 image
 * @param t Cache reset time
 * @param c Challenge code
 */
public record GetChallengeDto(String k, String i, Long t, @JsonIgnore String c) implements PayloadData {
    @Serial
    private static final long serialVersionUID = 8830237055676586883L;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GetChallengeDto(String k1, String i1, Long t1, String c1))) return false;
        return Objects.equals(k(), k1) && Objects.equals(i(), i1) && Objects.equals(t(), t1) && Objects.equals(c(), c1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(k(), i(), t(), c());
    }
}
