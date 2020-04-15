package com.sarawanak.todobe.serdes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class PriorityDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String priorityText = p.getText().toLowerCase();
        if (priorityText == null)
            return -1;

        switch (priorityText) {
            case "high":
                return 1;
            case "medium":
                return 5;
            case "low":
                return 10;
            default:
                return -1;
        }
    }
}
