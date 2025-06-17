package com.daw135.dawFinalProyect.helpers;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class DawUtil {

    public static SimpleDateFormat ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
    public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static String ROLE_PARTICIPANTE = "PARTICIPANTE";
    public static String ROLE_PONENTE = "PONENTE";
    public static String ROLE_ADMIN = "ADMIN";
    public static String ROLE_OTRO = "OTRO";

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                   .atZone(ZoneId.systemDefault())
                   .toLocalDate();
    }

    public static LocalTime stringToLocalTime(String timeString) {
        return LocalTime.parse(timeString); 
    }

    
    public static String aString(Object obj, String defaultValue) {
        if (obj == null) { return defaultValue; }
        return String.valueOf(obj);
    }

    public static String aString(Object obj) {
        return String.valueOf(obj);
    }

    public static Long aLong(Object obj) {
        if (obj == null) { return null; }
        return Long.parseLong(aString(obj));
    }
}
