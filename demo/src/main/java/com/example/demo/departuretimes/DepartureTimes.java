package com.example.demo.departuretimes;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepartureTimes {

    private static int randomHour() {

        return (int) (Math.random() * 24);
    }

    private static int randomMinute() {

        return (int) (Math.random() * 60);
    }

    // Generates list of 10 random departure times
    public static List<String> generateDepartureTimes() {

        List<String> departureTimes = new ArrayList<>();
        List<Integer> hour = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            int randomHour = randomHour();

            if (!hour.contains(randomHour)) {
                hour.add(randomHour);
                DecimalFormat formatter = new DecimalFormat("00");
                String time = formatter.format(randomHour) + ":" + formatter.format(randomMinute());
                departureTimes.add(time);
            } else {
                i--;
            }
        }

        Collections.sort(departureTimes);

        return departureTimes;
    }
}
