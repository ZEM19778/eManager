package com.emanager.emanager_demo.utility;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

public class Temporals {
    public static boolean isWithinWeek(LocalDate terminDatum, int wochenNummer, LocalDate reference) {
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 7);
        LocalDate now = LocalDate.now();
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
    public static boolean isWithinYear(LocalDate termin, int referenceYear){
        int terminJahr = termin.getYear();
        if(terminJahr == referenceYear){
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
