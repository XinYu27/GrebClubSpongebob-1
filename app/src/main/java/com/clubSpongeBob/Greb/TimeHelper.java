package com.clubSpongeBob.Greb;

import org.joda.time.DateTime;
import java.util.HashMap;
import java.util.Map;

public class TimeHelper {
    public static DateTime convertSystemTimeToRequired(DateTime time){
//        input system time and output required time
        int minutes = time.getMinuteOfHour() % 24;
        int seconds = time.getSecondOfMinute();

        return new DateTime(time.getYear(), time.getMonthOfYear(), time.getDayOfMonth(), minutes, seconds);
    }

    public static DateTime getCurrentRequiredTime(){
        return convertSystemTimeToRequired(DateTime.now());
    }

    public static Map<String, String> getCurrentRequiredTimeMap(){
        return convertSystemTimeToHoursMinutes(getCurrentRequiredTime());
    }

    public static Map<String, String> convertSecondsToRequired(int seconds){
        DateTime now = DateTime.now();
        DateTime newDateTime = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), now.getMinuteOfHour(), seconds);
        return convertSystemTimeToHoursMinutes(newDateTime);
    }

    public static Map<String, String> convertSystemTimeToHoursMinutes(DateTime time){
        Map<String, String> m = new HashMap<>();
        DateTime requiredTime = convertSystemTimeToRequired(time);
        int hours = requiredTime.getHourOfDay();
        m.put("hours", Integer.toString(hours % 12));
        m.put("minutes", Integer.toString(requiredTime.getMinuteOfHour()));
        m.put("zone", hours >= 12?"PM":"AM");

        return m;
    }

}
