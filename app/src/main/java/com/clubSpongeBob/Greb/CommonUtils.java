package com.clubSpongeBob.Greb;
import android.util.Patterns;

public class CommonUtils {
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
