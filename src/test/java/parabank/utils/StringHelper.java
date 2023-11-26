package parabank.utils;


import java.util.List;
import java.util.stream.Collectors;

public class StringHelper {

    public static String generateUniqueStringOfLettersUsingCurrentData(String prefix, String suffix) {
        String uniqueDate = DateTimeHelper.currentDay("SssmmhhddMMyyyy");
        String result = "";
        List<Character> listOfChar = uniqueDate.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
        //changin each number from date into character starting from A
        for (char ch : listOfChar) {
            result += (char) (ch+49);
        }
        return prefix + result + suffix;
    }
    public static String generateUniqueStringOfLettersUsingCurrentData() {
        return generateUniqueStringOfLettersUsingCurrentData("", "");
    }

    public static String extractTestNumber(String testName) {
        // Szukaj indeksu początkowego liczby po "PB"
        int startIndex = testName.indexOf("PB") + 2;

        // Wyodrębnij tekst liczby po "PB"
        String numberText = testName.substring(startIndex, testName.indexOf(" ", startIndex));

        return numberText;
    }

    public static String extractTestNumberAsString(String testName) {
        String[] parts = testName.split("\\s+");
        for (String part : parts) {
            if (part.startsWith("PB-")) {
                // Usuń "PB-" i zwróć tylko liczbę
                return part.substring(3);
            }
        }
        return "";
    }

}
