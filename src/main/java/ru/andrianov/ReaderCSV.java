package ru.andrianov;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReaderCSV {
    public List<Currency> readFile(File csvFile) throws IOException {
        CsvMapper mapper = new CsvMapper();

        CsvSchema schema = mapper
                .schemaFor(Currency.class)
                .withSkipFirstDataRow(true)
                .withColumnSeparator(';')
                .withoutQuoteChar();

        MappingIterator<Currency> currencyMappingIterator = mapper
                .readerFor(Currency.class).with(schema)
                .readValues(csvFile);

        return currencyMappingIterator.readAll();
    }
}
