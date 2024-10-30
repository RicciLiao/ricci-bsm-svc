package ricciliao.bsm.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import ricciliao.bsm.common.CommonHelper;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDate2TimestampSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(CommonHelper.localDate2Long(localDate));
    }

    @Override
    public Class<LocalDate> handledType() {

        return LocalDate.class;
    }
}
