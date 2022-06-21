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

    public static int getCurrentTime(){
        DateTime now = DateTime.now();
        return now.getHourOfDay()*100 + now.getMinuteOfHour();
    }

    public static int calculateEAT(long durationInSec, boolean addMinutes){
        DateTime now = DateTime.now();
        now = now.plusSeconds((int) durationInSec + (addMinutes? 5*60: 0));
        return Integer.parseInt(now.toString("HHmm"));
    }

    public static boolean isReachable(long durationInSec, String eat){
        DateTime eatDateTime = DateTime.now();
        eat = eatDateTime.getDayOfMonth() + "-" + eatDateTime.getMonthOfYear() +"-"+eatDateTime.getYear()+","+eat;
        eatDateTime = eatDateTime.plusSeconds((int)durationInSec);
        DateTime customerEAT = DateTime.parse(eat, DateTimeFormat.forPattern("dd-MM-yyyy,HHmm"));

        Log.i(TAG, "eatDateTime: "+eatDateTime.toString("dd-MM-yyyy,HHmm"));
        Log.i(TAG, "CustomerEAT: "+customerEAT.toString("dd-MM-yyyy,HHmm"));
        return customerEAT.isAfter(eatDateTime) || customerEAT.isEqual(eatDateTime);
    }


}
