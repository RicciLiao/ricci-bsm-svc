package ricciliao.bsm.component;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ricciliao.bsm.common.BsmCommonHelper;

import java.io.IOException;
import java.time.LocalDate;

public class Timestamp2LocalDateDeserializer extends JsonDeserializer<LocalDate> {


    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        return BsmCommonHelper.long2LocalDate(jsonParser.readValueAs(Long.class));
    }

    @Override
    public Class<?> handledType() {

        return LocalDate.class;
    }
}
