package com.sarawanak.todobe.serdes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class StatusDeserializerTest {

    @Test
    void deserialize() throws IOException {
        JsonParser parser = Mockito.mock(JsonParser.class);
        DeserializationContext ctxt = Mockito.mock(DeserializationContext.class);

        when(parser.getText()).thenReturn("Pending");

        Integer result = new StatusDeserializer().deserialize(parser, ctxt);

        assertEquals(result, 0);
    }
}
