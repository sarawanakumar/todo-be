package com.sarawanak.todobe.serdes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.sarawanak.todobe.helper.StatusHelper;

import java.io.IOException;

public class StatusSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(StatusHelper.getStatusForCode(value));
    }
}
