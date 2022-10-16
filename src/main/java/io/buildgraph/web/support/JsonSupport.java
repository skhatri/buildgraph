package io.buildgraph.web.support;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;

public class JsonSupport {
    private final ObjectMapper objectMapper;

    private JsonSupport() {
        this.objectMapper = new ObjectMapper();
    }

    public <T> T getAs(String data, Class<T> cz) {
        try {
            return objectMapper.readValue(data.getBytes(StandardCharsets.UTF_8), cz);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public String toJson(Object sz) {
        try {
            return objectMapper.writeValueAsString(sz);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static JsonSupport getInstance() {
        return INSTANCE;
    }

    public static final JsonSupport INSTANCE = new JsonSupport();
}
