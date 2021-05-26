package com.mia.thankdiary.src.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtil {
    public static String getFormatDate() {
        SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
        return YYYY_MM_DD.format(new Date());
    }

    public static String getFormatDateTime() {
        SimpleDateFormat YYYY_MM_DD_TIME = new SimpleDateFormat("yyyy-MM-dd hh:mm a", Locale.KOREA);
        return YYYY_MM_DD_TIME.format(new Date());
    }

    public static String getYear(String formatDate) {
        return formatDate.split("-")[0];
    }

    public static String getMonth(String formatDate) {
        return formatDate.split("-")[1];
    }

    public static String getDay(String formatDate) {
        return formatDate.split("-")[2];
    }

    public static String getMonthDay(String formatDate) {
        String str = "";
        str += getMonth(formatDate) + "-" + getDay(formatDate);
        return str;
    }
}
