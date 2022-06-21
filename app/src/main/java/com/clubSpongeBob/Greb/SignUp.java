package com.clubSpongeBob.Greb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.progressindicator.CircularProgressIndicator;

public class SignUp extends AppCompatActivity {
    private final String TAG = "Sign Up Activity";
    EditText editTextEmail;
    EditText editTextPassword;
    EditText editTextEmergencyEmail;
    EditText editTextName;
    CircularProgressIndicator circularProgressIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.getSupportActionBar().hide();

        editTextEmail = this.findViewById(R.id.editTextEmail);
        editTextPassword = this.findViewById(R.id.editTextPassword);
        editTextEmergencyEmail = this.findViewById(R.id.editTextEmergencyEmail);
        editTextName = this.findViewById(R.id.editTextName);
        circularProgressIndicator = this.findViewById(R.id.circularIndicator);

        this.findViewById(R.id.signUpBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editTextEmail.getText().toString().trim();
                String password = editTextPassword.getText().toString();
                String emergencyEmail = editTextEmergencyEmail.getText().toString().trim();
                String name = editTextName.getText().toString().trim();
                String errorMessage = "";

                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        FirebaseUtils.registerUser(email, name, password, emergencyEmail);
                    }
                };

                circularProgressIndicator.setVisibility(View.VISIBLE);

                if (!CommonUtils.emailValidation(email))
                    errorMessage = "Invalid email Address!";

                if (errorMessage.isEmpty()){
                    if (!CommonUtils.passwordValidation(password))
                        errorMessage = "Password must be longer than 5 characters";
                    else{
                        if (!CommonUtils.emailValidation(emergencyEmail))
                            errorMessage = "Invalid emergency email Address!";
                    }
                }

                if (errorMessage.isEmpty()){
                    thread.start();
                }

                circularProgressIndicator.setVisibility(View.INVISIBLE);
            }
        });

        this.findViewById(R.id.signInBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplication(), SignIn.class));
            }
        });
    }


}