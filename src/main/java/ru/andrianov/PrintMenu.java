package ru.andrianov;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrintMenu {
    public void choiceForecast() throws IOException {
        System.out.println("\n1) Курс валюты на завтра \n2) Курс валюты на 7 дней ");
        List<Currency> currencies = new ArrayList<>();
        CurseForecast curseForecast = new CurseForecast();
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                currencies = new ReaderCSV().readFile(choiceFile());
                System.out.println("Курс " + curseForecast.getRateTomorrow(currencies).getCdx() + " на завтра: ");
                System.out.print(curseForecast.getRateTomorrow(currencies));
                break;
            case "2":
                currencies = new ReaderCSV().readFile(choiceFile());
                System.out.println("Курс " + curseForecast.getRateTomorrow(currencies).getCdx() + " на 7 дней: ");
                for (Currency s : curseForecast.getRateToWeek(currencies)) {
                    System.out.println(s);
                }
                break;
        }
    }

    public static File choiceFile() {
        System.out.println("Выберите тип валюты:\n1 - EUR \n2 - TRY \n3 - USD");
        String eur = "src/main/resources/EUR.csv";
        String tRy = "src/main/resources/TRY.csv";
        String usd = "src/main/resources/USD.csv";
        File csvFile = null;
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1":
                csvFile = new File(eur);
                break;
            case "2":
                csvFile = new File(tRy);
                break;
            case "3":
                csvFile = new File(usd);
                break;
        }
        return csvFile;
    }
}
