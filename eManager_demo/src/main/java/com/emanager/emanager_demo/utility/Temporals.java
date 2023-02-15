package com.emanager.emanager_demo.utility;

import net.bytebuddy.asm.Advice;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.*;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Temporals {
    public static boolean isWithinWeek(LocalDate terminDatum, int wochenNummer, LocalDate reference,int year) {
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 7);
        LocalDate now = LocalDate.now();
        now = now.withYear(year);

        LocalDate start = now.with(weekFields.weekOfWeekBasedYear(), wochenNummer).with(DayOfWeek.MONDAY);
        LocalDate end = now.with(weekFields.weekOfWeekBasedYear(), wochenNummer).with(DayOfWeek.SUNDAY);
        LocalDate tDate = terminDatum.atStartOfDay().toLocalDate();
        if(tDate.compareTo(start) >= 0 && tDate.compareTo(end) <= 0 && terminDatum.equals(reference)) {
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean isWithinWoche(Date datum){
        Instant i = datum.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        LocalDate d = i.atZone(zone).toLocalDate();
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 7);
        LocalDate now = LocalDate.now();
        int wnr = now.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
        LocalDate start = now.with(weekFields.weekOfWeekBasedYear(), wnr).with(DayOfWeek.MONDAY);
        LocalDate end = now.with(weekFields.weekOfWeekBasedYear(), wnr).with(DayOfWeek.SUNDAY);
        LocalDate tDate = d.atStartOfDay().toLocalDate();
        if(tDate.compareTo(start) >= 0 && tDate.compareTo(end) <= 0) {
            return true;
        }
        else{
            return false;
        }
    }

    public LocalDate heute = LocalDate.now();
    public int wochenNummer = heute.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
    public int jahr = heute.getYear();
}
