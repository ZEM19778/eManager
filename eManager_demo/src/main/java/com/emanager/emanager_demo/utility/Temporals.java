package com.emanager.emanager_demo.utility;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Temporals {
    public static boolean isWithinWeek(LocalDate terminDatum, LocalDate weekReference) {
        LocalDate startOfWeek = weekReference.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = weekReference.with(DayOfWeek.SUNDAY);
        LocalDate tDate = terminDatum.atStartOfDay().toLocalDate();
        return tDate.compareTo(startOfWeek) >= 0 && tDate.compareTo(endOfWeek) <= 0 && terminDatum.equals(weekReference);
    }
}
