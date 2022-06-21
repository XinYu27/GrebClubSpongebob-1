package com.clubSpongeBob.Greb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.progressindicator.CircularProgressIndicator;

public class SignIn extends AppCompatActivity {
    String TAG = "Sign In";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        this.getSupportActionBar().hide();

        this.findViewById(R.id.signInBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(view);
            }
        });
        this.findViewById(R.id.signUpBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SignUp.class));
            }
        });
    }

    public void signIn(View v){
        TextView editTextEmail = this.findViewById(R.id.editTextEmail);
        TextView editTextPassword = this.findViewById(R.id.editTextPassword);
        CircularProgressIndicator circularProgressIndicator = this.findViewById(R.id.circularIndicator);

        String password = editTextPassword.getText().toString();
        String email = editTextEmail.getText().toString().trim();
        String errorMessage="";

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                FirebaseUtils.loginUser(email, password);
            }
        };

        circularProgressIndicator.setVisibility(View.VISIBLE);

        if (!CommonUtils.emailValidation(email))
            errorMessage = "Invalid email Address!";

        if (errorMessage.isEmpty()){
            if (!CommonUtils.passwordValidation(password))
                errorMessage = "Password must be longer than 5";
        }

        if (errorMessage.isEmpty()){
            thread.start();
        } else{
            Log.w(TAG, "No user found");
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
        circularProgressIndicator.setVisibility(View.INVISIBLE);
    }
}