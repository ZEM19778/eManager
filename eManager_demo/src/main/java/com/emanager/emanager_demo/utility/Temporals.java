package com.emanager.emanager_demo.utility;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Temporals {
    public static boolean isWithinWeek(LocalDateTime dateTime, LocalDate weekReference) {
        LocalDate startOfWeek = weekReference.with(DayOfWeek.MONDAY);
        LocalDate endOfWeek = weekReference.with(DayOfWeek.SUNDAY);
        return !dateTime.toLocalDate().isBefore(startOfWeek) && !dateTime.toLocalDate().isAfter(endOfWeek);
    }
}
