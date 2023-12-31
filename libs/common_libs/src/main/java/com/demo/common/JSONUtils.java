package com.demo.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;

import java.util.List;
import java.util.Map;

public class JSONUtils {
    public static <T> List<T> getListFromJson(String json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, new TypeReference<>() {
        });
    }

    public static String getRawData(Object json) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(json);
    }

    public static String getRawDataFromGrpc(Message message) throws InvalidProtocolBufferException {
        return JsonFormat.printer().print(message);
    }

    public static Map<String, Object> toMap(Message message) throws JsonProcessingException, InvalidProtocolBufferException {
        String jsonString = JsonFormat.printer().print(message);
        return new ObjectMapper().readValue(jsonString, new TypeReference<>() {
        });
    }

    public static <T> T toObject(Message message, Class<T> clazz) throws JsonProcessingException, InvalidProtocolBufferException {
        String jsonString = JsonFormat.printer().print(message);
        return  new ObjectMapper().readValue(jsonString, clazz);
    }
}
