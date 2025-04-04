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

    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                   .atZone(ZoneId.systemDefault())
                   .toLocalDate();
    }

     public static LocalTime stringToLocalTime(String timeString) {
        return LocalTime.parse(timeString); 
    }

}
