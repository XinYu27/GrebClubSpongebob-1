package com.clubSpongeBob.Greb;
import android.app.Application;
import android.content.Context;
import android.util.Patterns;

import org.joda.time.DateTime;

public class CommonUtils {
    private static Application sApplication;
    private static Driver selectedDriver;
    private static DateTime eat;

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
        CommonUtils.eat = null;
    }

    public static boolean orderIsEmpty(){
        return  CommonUtils.selectedDriver == null;
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
}
