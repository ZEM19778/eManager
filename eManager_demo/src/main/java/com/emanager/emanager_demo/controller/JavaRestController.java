package com.emanager.emanager_demo.controller;

import com.emanager.emanager_demo.model.Termin;
import com.emanager.emanager_demo.service.TermineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;

@RestController
public class JavaRestController {
    @Autowired
    TermineService termineService;
    @GetMapping("/admin/kalender/{wochennummer}")
    public List<Termin> getTermineForPreviousWeek(@PathVariable int wochennummer){
        LocalDate start = LocalDate.now().with(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear(), wochennummer - 1).with(DayOfWeek.MONDAY);
        LocalDate ende = start.plusDays(6);

        return termineService.getTermineInSpan(start, ende);
    }
}
