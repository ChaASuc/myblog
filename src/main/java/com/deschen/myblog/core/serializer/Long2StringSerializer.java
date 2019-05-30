package com.deschen.myblog.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Author deschen
 * @Create 2019/5/30
 * @Description 前后端json转换器  Long转换String
 * @Since 1.0.0
 */
public class Long2StringSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(String.valueOf(value));
    }
}
