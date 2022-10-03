package ru.andrianov;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CurseForecast {
    public Currency getRateTomorrow(List<Currency> currencies) {
        int i = 0;
        List<Currency> lastSevenCurses = new ArrayList<>();
        while (i < 7) {
            lastSevenCurses.add(currencies.get(i));
            i++;
        }
        BigDecimal average = average(lastSevenCurses);
        Currency tomorrowCurrency = new Currency(
                lastSevenCurses.get(0).getNominal(),
                lastSevenCurses.get(0).getData().plusDays(1),
                average,
                lastSevenCurses.get(0).getCdx()
        );
        lastSevenCurses.add(0, tomorrowCurrency);
        lastSevenCurses.remove(lastSevenCurses.size() - 1);
        return tomorrowCurrency;
    }

    public List<Currency> getRateToWeek(List<Currency> currencies) {
        List<Currency> nextWeek = new ArrayList<>();
        int i = 0;
        while (i < 7) {
            nextWeek.add(currencies.get(i));
            i++;
        }
        int count = 0;
        while (count < 7) {
            nextWeek.add(0, getRateTomorrow(nextWeek));
            nextWeek.remove(nextWeek.size() - 1);
            count++;
        }
        return nextWeek;
    }

    public static BigDecimal average(List<Currency> list) {
        BigDecimal average = BigDecimal.valueOf(list.stream()
                .mapToDouble(x -> x.getCurs().doubleValue()).average()
                .orElseThrow(ArithmeticException::new));
        return average;
    }
}
