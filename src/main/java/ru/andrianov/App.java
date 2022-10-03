package ru.andrianov;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {

        File csvFile = new File("C:/Users/WD40/Desktop/дз/ligaHomework1/src/main/resources/TRY.csv");
        List<Currency> currencies = new ArrayList<>();
        currencies = readFile(csvFile);
        for (Currency cur : currencies) {
            System.out.print(cur);
        }



    }


    public static List<Currency> readFile(File csvFile) throws IOException {
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


