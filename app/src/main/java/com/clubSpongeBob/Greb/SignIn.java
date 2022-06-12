package com.clubSpongeBob.Greb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {
    String TAG = "Sign In";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.findViewById(R.id.loginBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn(view);
            }
        });
    }

    public void signIn(View v){
        TextView editTextEmail = this.findViewById(R.id.editTextEmail);
        TextView editTextPassword = this.findViewById(R.id.editTextPassword);

        String password = editTextPassword.getText().toString();
        String email = editTextEmail.getText().toString().trim();
        String errorMessage="";

        if (!CommonUtils.emailValidation(email))
            errorMessage = "Invalid email Address!";

        if (errorMessage.isEmpty()){
            if (!CommonUtils.passwordValidation(password))
                errorMessage = "Password must be longer than 5";
        }

        if (errorMessage.isEmpty()){
            Customer c = FirebaseUtils.loginUser(this, email,password);

            Intent intent;
            if (c != null){
                if(c.isAdmin()){
                    intent = new Intent(getApplication(), AdminLanding.class);
                    Log.i(TAG, "User is admin");
                } else{
                    intent = new Intent(getApplication(), CustomerLanding.class);
                    Log.i(TAG, "User is customer");
                }
                startActivity(intent);
            }
        } else{
            Log.w(TAG, "No user found");
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }

    }
}