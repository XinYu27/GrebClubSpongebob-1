package com.clubSpongeBob.Greb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Wrapper extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        if(FirebaseUtils.isLogin(this)){
            intent = new Intent(getApplication(), AdminLanding.class);
        }else{
            intent = new Intent(getApplication(), AuthLanding.class);
        }
        startActivity(intent);
    }
}
