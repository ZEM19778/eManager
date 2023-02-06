package com.emanager.emanager_demo.utility;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

public class Temporals {
    public static boolean isWithinWeek(LocalDate terminDatum, int wochenNummer, LocalDate reference) {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate now = LocalDate.now();
        LocalDate start = now.with(weekFields.weekOfWeekBasedYear(), wochenNummer).with(DayOfWeek.MONDAY);
        LocalDate end = now.with(weekFields.weekOfWeekBasedYear(), wochenNummer).with(DayOfWeek.SUNDAY);
        //LocalDate startOfWeek = weekReference.with(DayOfWeek.MONDAY);
        //LocalDate endOfWeek = weekReference.with(DayOfWeek.SUNDAY);
        LocalDate tDate = terminDatum.atStartOfDay().toLocalDate();
        //return tDate.compareTo(startOfWeek) >= 0 && tDate.compareTo(endOfWeek) <= 0 && terminDatum.equals(weekReference);
        if(tDate.isAfter(start) && tDate.isBefore(end) && terminDatum.equals(reference)) {
            return true;
        }
        else{
            return false;
        }
    }

    public LocalDate heute = LocalDate.now();
    public int wochenNummer = heute.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());

    public Calendar now = Calendar.getInstance();
    public int jahr = now.get(Calendar.YEAR);
}
