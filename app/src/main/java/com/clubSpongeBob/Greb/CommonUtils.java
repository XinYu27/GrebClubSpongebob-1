package com.clubSpongeBob.Greb;
import android.app.Application;
import android.content.Context;
import android.util.Patterns;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class CommonUtils {
    private static Application sApplication;
    private static Driver selectedDriver;
    private static int eat;
    private static Customer self;
    private static ArrayList<Driver> driverArrayList;

    public static Application getsApplication() {
        return sApplication;
    }

    public static Context getSContext(){
        return getsApplication().getApplicationContext();
    }

    public static void setsApplication(Application sApplication) {
        CommonUtils.sApplication = sApplication;
    }

    public static Driver getSelectedDriver() {
        return selectedDriver;
    }

    public static void setSelectedDriver(Driver selectedDriver) {
        CommonUtils.selectedDriver = selectedDriver;
    }

    public static void reset(){
        CommonUtils.selectedDriver = null;
        CommonUtils.eat = 0;
    }

    public static boolean orderIsEmpty(){
        return  CommonUtils.selectedDriver == null;
    }

    public static Customer getSelf() {
        return self;
    }

    public static void setSelf(Customer self) {
        CommonUtils.self = self;
    }

    public static boolean emailValidation(String email){
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return true;
        }
        return false;
    }

    public static boolean passwordValidation(String password){
        if (password.length() < 6)
            return false;
        return true;
    }

    public static ArrayList<Driver> getDriverArrayList() {
        return driverArrayList;
    }

    public static void setDriverArrayList(ArrayList<Driver> driverArrayList) {
        CommonUtils.driverArrayList = driverArrayList;
    }

    public static void resetArrayList(){
        CommonUtils.driverArrayList = new ArrayList<>();
    }
}
