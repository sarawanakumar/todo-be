package com.sarawanak.todobe.serdes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.sarawanak.todobe.helper.PriorityHelper;

import java.io.IOException;

public class PriorityDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String priorityText = p.getText().toLowerCase();
        return PriorityHelper.getCodeForPriority(priorityText);
    }
}
