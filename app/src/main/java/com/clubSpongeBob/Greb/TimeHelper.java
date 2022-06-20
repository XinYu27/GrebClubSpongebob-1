package com.clubSpongeBob.Greb;

import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TimeHelper {
    private static String TAG = "TimeHelper";

    public static DateTime convertSystemTimeToRequired(DateTime time){
//        input system time and output required time
        int minutes = time.getMinuteOfHour() % 24;
        int seconds = time.getSecondOfMinute();

        return new DateTime(time.getYear(), time.getMonthOfYear(), time.getDayOfMonth(), minutes, seconds);
    }

    public static DateTime getCurrentRequiredTime(){
        return convertSystemTimeToRequired(DateTime.now());
    }

    public static int calculateEAT(long durationInSec, boolean addMinutes){
        DateTime now = DateTime.now();
        now = now.plusSeconds((int) durationInSec + (addMinutes? 5*60: 0));
        int time = now.getHourOfDay() * 100;
        Log.i(TAG, "CalculatedEAT: "+ String.format("%04d",time + now.getMinuteOfHour()));
        return time + now.getMinuteOfHour();
    }


}
