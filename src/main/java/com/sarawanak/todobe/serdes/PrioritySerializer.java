package com.sarawanak.todobe.serdes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class PrioritySerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeObject("None");
        }
        switch (value) {
            case 1:
                gen.writeObject("High");
                break;
            case 5:
                gen.writeObject("Medium");
                break;
            case 10:
                gen.writeObject("Low");
                break;
            default:
                gen.writeObject("None");
        }
    }
}
