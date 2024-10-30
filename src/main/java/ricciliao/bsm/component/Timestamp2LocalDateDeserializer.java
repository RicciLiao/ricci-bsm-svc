package ricciliao.bsm.component;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import ricciliao.bsm.common.CommonHelper;

import java.io.IOException;
import java.time.LocalDate;

public class Timestamp2LocalDateDeserializer extends JsonDeserializer<LocalDate> {


    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        return CommonHelper.long2LocalDate(jsonParser.readValueAs(Long.class));
    }

    @Override
    public Class<?> handledType() {

        return LocalDate.class;
    }
}
