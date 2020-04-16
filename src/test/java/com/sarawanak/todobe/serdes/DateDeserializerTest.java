package com.sarawanak.todobe.serdes;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DateDeserializerTest {

    @Test
    void shouldDeserialize() throws IOException {
        JsonParser parser = Mockito.mock(JsonParser.class);
        DeserializationContext ctxt = Mockito.mock(DeserializationContext.class);

        when(parser.getText()).thenReturn("14-Apr-2020");

        Date result = new DateDeserializer().deserialize(parser, ctxt);
        Date date = new GregorianCalendar(2020, Calendar.APRIL, 14).getTime();
        assertEquals(result, date);
    }
}
