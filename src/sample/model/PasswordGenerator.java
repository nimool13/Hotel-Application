package sample.model;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PasswordGenerator {
    private static final String CHAR_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPERCASE = CHAR_LOWERCASE.toUpperCase();
    private static final String DIGIT = "0123456789";
    private static final String OTHER_PUNCTUATION = "!@#&()–[{}]:;',?/*";
    private static final String OTHER_SYMBOL = "~$^+=<>";
    private static final String OTHER_SPECIAL = OTHER_PUNCTUATION + OTHER_SYMBOL;
    private static final int PASSWORD_LENGTH = 20;


    private static SecureRandom random = new SecureRandom();
    public static String generateStrongPassword() {

        StringBuilder result = new StringBuilder(PASSWORD_LENGTH);

        // at least 2 chars (lowercase)
        String strLowerCase = generateRandomString(CHAR_LOWERCASE, 2);

        result.append(strLowerCase);

        // at least 2 chars (uppercase)
        String strUppercaseCase = generateRandomString(CHAR_UPPERCASE, 2);

        result.append(strUppercaseCase);

        // at least 2 digits
        String strDigit = generateRandomString(DIGIT, 2);

        result.append(strDigit);




        String password = result.toString();


        return shuffleString(password);
    }
    public static String shuffleString(String input) {
        List<String> result = Arrays.asList(input.split(""));
        Collections.shuffle(result);
        // java 8
        return result.stream().collect(Collectors.joining());
    }
    private static String generateRandomString(String input, int size) {

        if (input == null || input.length() <= 0)
            throw new IllegalArgumentException("Invalid input.");
        if (size < 1) throw new IllegalArgumentException("Invalid size.");

        StringBuilder result = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            // produce a random order
            int index = random.nextInt(input.length());
            result.append(input.charAt(index));
        }
        return result.toString();
    }
}
