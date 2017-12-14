package com.luxoft.examples;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestDate {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2017, 12, 14);
        System.out.println(date.plusDays(1));
    }
}
