package com.sarawanak.todobe.serdes;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PriorityDeserializerTest {

    @Test
    void shouldDeserialize() throws IOException {
        JsonParser parser = Mockito.mock(JsonParser.class);
        DeserializationContext ctxt = Mockito.mock(DeserializationContext.class);

        when(parser.getText()).thenReturn("Medium");

        Integer result = new PriorityDeserializer().deserialize(parser, ctxt);

        assertEquals(result, 5);
    }
}
