package com.apress.prospring5.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date toDate(int year, int month, int dayOfMonth) {
        return toDate(LocalDate.of(year, month, dayOfMonth));
    }

    public static java.sql.Date toSqlDate(int year, int month, int dayOfMonth) {
        return java.sql.Date.valueOf(LocalDate.of(year, month, dayOfMonth));
    }

}
