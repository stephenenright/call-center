package com.github.stephenenright.callcenter.common.validation;

import org.springframework.util.StringUtils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public abstract class ValidationUtils {

    public static boolean requiredStr(String value) {
        return StringUtils.hasText(value);
    }

    public static boolean required(Object value) {
        return value != null;
    }

    public static boolean isIsoDate(String value) {
        try {
            ZonedDateTime.parse(value, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
