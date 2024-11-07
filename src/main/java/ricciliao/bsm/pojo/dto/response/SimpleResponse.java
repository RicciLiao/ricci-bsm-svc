package ricciliao.bsm.pojo.dto.response;

import ricciliao.common.component.response.ResponseVoData;

import java.io.Serial;

public class SimpleResponse implements ResponseVoData {
    @Serial
    private static final long serialVersionUID = -4274869725993308022L;

    public record BooleanResult(boolean result) implements ResponseVoData {
    }
}


