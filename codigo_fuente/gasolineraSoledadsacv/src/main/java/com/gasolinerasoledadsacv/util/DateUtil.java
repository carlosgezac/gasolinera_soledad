package com.gasolinerasoledadsacv.util;

import com.gasolinerasoledadsacv.pojo.RelojPojo;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

    public static final String defaultDatePattern = "dd/MM/yyyy";

    public static boolean isValid(Date date) {
        try {
            Calendar c = toCalendar(date);
            c.getTime();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public static boolean isValid(String pattern, String strDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(pattern);
            dateFormat.parse(strDate);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String formatDefault(Date date) {
        return format(date, defaultDatePattern);
    }

    public static String format(Date date, String pattern) {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static RelojPojo getClockHour(Date date) {
        RelojPojo relojPojo = new RelojPojo();
        String hour = "";
        Calendar calendar = toCalendar(date);
        String ampm = calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        int h = calendar.get(Calendar.HOUR);
        if (h >= 1 && h <= 9) {// 1-9
            hour = "0" + h;
        } else if (h >= 10) {//10-11
            hour = "" + h;
        } else {//0
            hour = "12";
        }
        String minutes = calendar.get(Calendar.MINUTE) > 9 ? "" + calendar.get(Calendar.MINUTE) : "0" + calendar.get(Calendar.MINUTE);
        String seconds = calendar.get(Calendar.SECOND) > 9 ? "" + calendar.get(Calendar.SECOND) : "0" + calendar.get(Calendar.SECOND);
        hour += ":" + minutes + ":" + seconds + " " + ampm;
        relojPojo.setHour(hour);
        relojPojo.setDay(format(date, "dd.MM.yyyy"));
        return relojPojo;
    }

    public static String substract(Date date1, Date date2) {
        Calendar c1 = toCalendar(date1);
        Calendar c2 = toCalendar(date2);
        LocalDate ld1 = LocalDate.of(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH) + 1, c1.get(Calendar.DAY_OF_MONTH));
        LocalDate ld2 = LocalDate.of(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH) + 1, c2.get(Calendar.DAY_OF_MONTH));
        Period p = Period.between(ld1, ld2);
        String result = "";
        if (p.getYears() > 0) {
            result += p.getYears() + " años ";
        }
        if (p.getMonths() > 0) {
            result += p.getMonths() + " meses ";
        }
        if (p.getDays() > 0 || result.isEmpty()) {
            result += p.getDays() + " días";
        }
        return result;
    }
}
