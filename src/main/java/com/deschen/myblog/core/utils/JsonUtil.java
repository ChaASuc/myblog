package com.deschen.myblog.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @Author deschen
 * @Create 2019/4/16
 * @Description
 * @Since 1.0.0
 */
public class JsonUtil {

    public static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String obj2string(T object) throws JsonProcessingException {
        return object instanceof String ? (String) object : objectMapper.writeValueAsString(object);
    }

    public static <T> T string2object(String str, Class<T> clazz) throws IOException {
        return clazz.equals(String.class)? (T)str: objectMapper.readValue(str, clazz);
    }
}
