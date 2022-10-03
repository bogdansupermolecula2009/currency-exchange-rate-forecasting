package ru.andrianov;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

class LDDeserializer extends StdDeserializer<LocalDate> {

    private static final long serialVersionUID = 1L;
    private static final String[] DATE_FORMATS = new String[]{
            "MM.dd.yyyy",
            "MM/dd/yyyy",
            "M/dd/yyyy"
    };

    protected LDDeserializer() {
        super(LocalDate.class);
    }

    @Override
    public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {

        JsonNode node = jp.getCodec().readTree(jp);
        final String date = node.textValue();
        for (String DATE_FORMAT : DATE_FORMATS) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ROOT);
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {

            }
        }
        return null;
    }
}