package com.sarawanak.todobe.serdes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class StatusDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String statusText = p.getText().toLowerCase();

        switch (statusText) {
            case "pending":
                return 0;
            case "completed":
                return 1;
            default:
                return -1;
        }
    }
}
