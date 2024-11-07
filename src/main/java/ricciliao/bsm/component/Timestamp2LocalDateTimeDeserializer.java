package ricciliao.bsm.component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ricciliao.bsm.common.BsmCommonHelper;

import java.io.IOException;
import java.time.LocalDateTime;

public class Timestamp2LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {


    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        return BsmCommonHelper.long2LocalDateTime(jsonParser.readValueAs(Long.class));
    }

    @Override
    public Class<LocalDateTime> handledType() {

        return LocalDateTime.class;
    }
}
