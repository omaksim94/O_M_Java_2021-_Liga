package ru.philosophyit.internship.javabase.time;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Main {
    public static void main(String... args) {
        System.out.println(DateTimeFormatter.ISO_INSTANT.format(Calendar.getInstance().toInstant()));
        // Отобразите календарь текущего месяца в консоли
        currMonthCalendar();
    }

    public static void currMonthCalendar() {
        LocalDate today = LocalDate.now();
        LocalDate thismonth = LocalDate.of(today.getYear(), today.getMonth(), 1);
        int firstDayofMonth = thismonth.getDayOfWeek().getValue();
        System.out.println("       " + thismonth.getMonth() + " " + thismonth.getYear());
        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 0; i < firstDayofMonth - 1; i++) {
            System.out.print("    ");
        }
        for (int i = 1; i < thismonth.lengthOfMonth() + 1; i++){
            System.out.printf("%-4d", i);
            if (((i + firstDayofMonth - 1) % 7 == 0)) {
                System.out.println();
            }
        }
    }
}
