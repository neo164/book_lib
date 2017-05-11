package org.predybaylo;

/**
 * Created by ipredybaylo on 20-Apr-17.
 */

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidatorImpl implements DateValidator {

    private static final String DATE_PATTERN
            = "(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])"
            + "\\.((19|20)\\d\\d)";

    private final Pattern pattern;
    private Matcher matcher;

    public DateValidatorImpl() {
        pattern = Pattern.compile(DATE_PATTERN);
    }

    public boolean validate(String date) {

        matcher = pattern.matcher(date);

        if (matcher.matches()) {

            matcher.reset();

            if (matcher.find()) {
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if ("31".equals(day)) {
                    // 31 день может быть только в месяцах
                    // 1, 3, 5, 7, 8, 10, 12
                    return Arrays.asList(new String[]{"1", "01", "3", "03",
                            "5", "05", "7", "07", "8", "08", "10", "12"})
                            .contains(month);

                } else if ("2".equals(month) || "02".equals(month)) {
                    // Проверяем февраль
                    if (year % 4 == 0) {
                        if (year % 100 == 0) {
                            if (year % 400 == 0) {
                                // Високосный год
                                return Integer.parseInt(day) <= 29;
                            }
                            // Невисокосный год
                            return Integer.parseInt(day) <= 28;
                        }
                        // Високосный год
                        return Integer.parseInt(day) <= 29;
                    } else {
                        // Невисокосный год
                        return Integer.parseInt(day) <= 28;
                    }

                } else {
                    return true;
                }
            }
        }

        return false;
    }
}