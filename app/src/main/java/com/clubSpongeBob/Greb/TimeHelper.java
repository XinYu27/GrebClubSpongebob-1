package com.clubSpongeBob.Greb;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.util.Date;

public class TimeHelper {
    public static DateTime convertSystemTimeToRequired(DateTime time){
//        input system time and output required time
        int minutes = time.getMinuteOfHour() % 24;
        int seconds = time.getSecondOfMinute();

        return new DateTime(time.getYear(), time.getMonthOfYear(), time.getDayOfMonth(), minutes, seconds);
    }

    public static DateTime getCurrentRequiredTime(DateTime time){
        return convertSystemTimeToRequired(DateTime.now());
    }


}
