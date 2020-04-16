package com.sarawanak.todobe.serdes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sarawanak.todobe.helper.PriorityHelper;

import java.io.IOException;

public class PrioritySerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(PriorityHelper.getPriorityForCode(value));
    }
}
