package com.clubSpongeBob.Greb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;

public class Wrapper extends AppCompatActivity{
    final String TAG = "Wrapper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CommonUtils.setsApplication(getApplication());
        new EmailService();

        if(FirebaseUtils.isLogin()){
            FirebaseUtils.getCustomerRef()
                    .child(FirebaseUtils.getmAuth().getCurrentUser().getUid())
                    .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (!task.isSuccessful()){
                                Log.e(TAG, "Error getting user data", task.getException());
                            } else {
                                Customer c = task.getResult().getValue(Customer.class);
                                CommonUtils.setSelf(c);
                                Log.d(TAG, "Successfully get data from user: " + c.getName());
                                if(c.isAdmin()){
                                    startActivity(new Intent(getApplication(), AuthLanding.class));
                                    finish();
                                }
                                else{
                                    startActivity(new Intent(getApplication(), CustomerLanding.class));
                                    finish();
                                }

                            }
                        }
                    });

            Log.i(TAG, "Authenticated");
        } else{
            startActivity(new Intent(getApplication(), AuthLanding.class));
            Log.i(TAG, "Not Authenticated");
            this.finish();
        }
    }
}
