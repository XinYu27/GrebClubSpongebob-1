package com.clubSpongeBob.Greb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Wrapper extends AppCompatActivity{
    final String TAG = "Wrapper";

    private static Application sApplication;

    public static Application getsApplication(){
        return sApplication;
    }

    public static Context getSContext(){
        return getsApplication().getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sApplication = getApplication();
        setContentView(R.layout.activity_wrapper);

        final Intent intent;
        if(FirebaseUtils.isLogin()){
            intent = new Intent(getApplication(), AdminLanding.class);
            Log.i(TAG, "Authenticated");
        } else{
            intent = new Intent(getApplication(), AuthLanding.class);
            Log.i(TAG, "Not Authenticated");
        }
        startActivity(intent);
        this.finish();
    }
}
