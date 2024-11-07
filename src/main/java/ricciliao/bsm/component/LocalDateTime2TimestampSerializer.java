package ricciliao.bsm.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ricciliao.bsm.common.BsmCommonHelper;

import java.io.IOException;
import java.time.LocalDateTime;

public class LocalDateTime2TimestampSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(BsmCommonHelper.localDateTime2Long(localDateTime));
    }

    @Override
    public Class<LocalDateTime> handledType() {

        return LocalDateTime.class;
    }
}
