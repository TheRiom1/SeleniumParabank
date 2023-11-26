package parabank.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelper {
    public static String currentDay(String format) {
        DateTimeFormatter dateTimeFormatterNew = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatterNew.format(LocalDateTime.now());
    }
    public static String currentDayPlusDays(int value, String format) {
        DateTimeFormatter dateTimeFormatterNew = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatterNew.format(LocalDateTime.now().plusDays(value));
    }
    public static String yesterday(String format) {
        DateTimeFormatter dateTimeFormatterNew = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatterNew.format(LocalDateTime.now().minusDays(1));
    }
    public static String currentDayMinusDays(int value, String format) {
        DateTimeFormatter dateTimeFormatterNew = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatterNew.format(LocalDateTime.now().minusDays(value));
    }
    public static String currentDayPlusYears(int value, String format) {
        DateTimeFormatter dateTimeFormatterNew = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatterNew.format(LocalDateTime.now().plusYears(value));
    }
    public static String currentDayMinusYears(int value, String format) {
        DateTimeFormatter dateTimeFormatterNew = DateTimeFormatter.ofPattern(format);
        return dateTimeFormatterNew.format(LocalDateTime.now().minusYears(value));
    }

}
