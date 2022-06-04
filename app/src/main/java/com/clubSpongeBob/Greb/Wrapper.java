package com.clubSpongeBob.Greb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class Wrapper extends AppCompatActivity{
    final String TAG = "Wrapper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrapper);

        final Intent intent;
        if(FirebaseUtils.isLogin(getApplicationContext())){
            intent = new Intent(getApplication(), AdminLanding.class);
            Log.i(TAG, "Authenticated");
        } else{
            intent = new Intent(getApplication(), AuthLanding.class);
            Log.i(TAG, "Not Authenticated");
        }
        startActivity(intent);
    }
}
