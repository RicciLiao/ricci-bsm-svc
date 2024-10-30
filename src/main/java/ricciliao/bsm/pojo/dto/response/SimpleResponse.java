package ricciliao.bsm.pojo.dto.response;

import ricciliao.common.component.response.ResponseVoData;

public class SimpleResponse implements ResponseVoData {
    public record BooleanResult(boolean result) implements ResponseVoData {
    }
}


