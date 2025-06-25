package ricciliao.bsm.pojo.dto.response;


import ricciliao.x.component.response.ResponseData;

import java.io.Serial;

/**
 * @param k key
 * @param i base64 image
 * @param t reset time
 */
public record GetChallengeDto(String k, String i, Long t) implements ResponseData {
    @Serial
    private static final long serialVersionUID = 8830237055676586883L;

}
