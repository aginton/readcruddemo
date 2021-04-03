package com.example.demo.model;

import java.util.Arrays;
import java.util.List;

public class CONSTANTS {
    private final static List<String> daysOfWeek = Arrays.asList("sunday", "monday", "tuesday", "wednesday", "thursday", "friday", "saturday");

    public static List<String> getDaysOfWeek() {
        return daysOfWeek;
    }
}
