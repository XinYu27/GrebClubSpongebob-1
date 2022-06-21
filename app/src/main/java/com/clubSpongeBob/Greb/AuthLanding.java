package com.clubSpongeBob.Greb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AuthLanding extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_landing);

        this.getSupportActionBar().hide();
    }

    public void signUp(View v){
//        FirebaseUtils.registerUser(this,"nicolenahwj@gmail.com", "wj","123456", "nicolenahwj@gmail.com");
        Intent intent = new Intent(getApplication(), SignUp.class);
        startActivity(intent);
    }

    public void signIn(View v){
//        FirebaseUtils.loginUser(this, "nicolenahwj@gmail.com","123456");
        Intent intent = new Intent(getApplication(), SignIn.class);
        startActivity(intent);
    }

}