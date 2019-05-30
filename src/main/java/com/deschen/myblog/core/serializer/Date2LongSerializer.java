package com.deschen.myblog.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @Author deschen
 * @Create 2019/5/30
 * @Description 前后端json转换器  Date转换Long
 * @Since 1.0.0
 */
public class Date2LongSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeNumber(value.getTime()/1000);
    }
}
