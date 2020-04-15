package com.sarawanak.todobe.serdes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class StatusSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null)
            gen.writeObject("Unknown");

        switch (value) {
            case 0:
                gen.writeObject("Pending");
                break;
            case 1:
                gen.writeObject("Completed");
                break;
            default:
                gen.writeObject("Unknown");
        }
    }
}
