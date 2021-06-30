package ru.vtb.productprofile.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class LogUtils {
    private static Boolean hidden = true;
    final static String OWA_REGEX = "(TSPReport_.).+(innxxx\\d{4}_IDDBO(.*).zip)";

    public static void setHidden(Boolean hidden) {
        log.info("Logging hidden is: {}", hidden);
        LogUtils.hidden = hidden;
    }

    public static String hidePersonalData(Object value) {
        if (value instanceof UUID) {
            return value.toString();
        }
        if (value == null) {
            return "null";
        }
        String result = value.toString();
        if (hidden) {
            if (result.matches(OWA_REGEX)) {
                return result.replaceAll(OWA_REGEX, "$1***$2");
            }
            String regex = ".";
            String replacement = "*";
            if (result.length() <= 3) {
                result = result.replaceAll(regex, replacement);
            } else if (result.length() <= 7) {
                result = result.substring(0, 2) + result.substring(2).replaceAll(regex, replacement);
            } else {
                result = result.substring(0, 3) + result.substring(3).replaceAll(regex, replacement);
            }
            if (result.length() > 10) {
                result = result.substring(0, 10) + "~" + (result.length() - 10);
            }
        }
        return result;
    }
}
