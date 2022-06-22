package com.clubSpongeBob.Greb;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseUtils {
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    public static FirebaseDatabase db = FirebaseDatabase.getInstance();
    public static DatabaseReference customerRef = db.getReference("customers");
    public static DatabaseReference driverRef = db.getReference("drivers");
    private static String TAG = "firebase utils";


    public static DatabaseReference getDriverRef(){
        return driverRef;
    }

    public static DatabaseReference getCustomerRef(){
        return customerRef;
    }

    public static FirebaseAuth getmAuth() {
        return mAuth;
    }

    public static void registerUser(String email, String name, String password, String emergency){
        // Assuming all input is validated
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            Customer customer = new Customer(email, name, emergency);
                            customerRef
                                    .child(mAuth.getCurrentUser().getUid())
                                    .setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(CommonUtils.getSContext(),"User has been registered successfully!", Toast.LENGTH_LONG).show();
                                                CommonUtils.getSContext().startActivity(new Intent(CommonUtils.getsApplication(), CustomerLanding.class));
                                                Log.i(TAG, "Successfully register user: " + mAuth.getCurrentUser().getUid());
                                            }else{
                                                Toast.makeText(CommonUtils.getSContext(),"Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                                Log.e(TAG, "Failed to register user: " + mAuth.getCurrentUser().getUid());
                                            }
                                        }
                                    });
                        }else {
                            Toast.makeText(CommonUtils.getSContext(), "Failed to register", Toast.LENGTH_LONG).show();
                        }
                    }
        });

    }

    public static void loginUser(String email, String password){
        Context context = CommonUtils.getSContext();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            customerRef
                                    .child(mAuth.getCurrentUser().getUid())
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
                                                    context.startActivity(new Intent(CommonUtils.getsApplication(), AdminLanding.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                                } else {
                                                    context.startActivity(new Intent(CommonUtils.getsApplication(), CustomerLanding.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                                }
                                            }
                                        }
                                    });
                        }else {
                            Toast.makeText(context, "Failed to login", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    public static boolean isLogin(){
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) return false;
        Toast.makeText(CommonUtils.getSContext(), "Welcome "+ user.getDisplayName(), Toast.LENGTH_LONG);
        return true;
    }

    public static void signOutUser(){
        mAuth.signOut();
        Toast.makeText(CommonUtils.getSContext(), "Sign out", Toast.LENGTH_SHORT).show();
    }

    public static void updateDriver(Driver driver, String toastMessage, String failedToastMessage){
        DatabaseReference ref = driverRef.child(driver.getUid());
        ref.setValue(driver).addOnCompleteListener(new OnCompleteListener<Void>(){

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful() && toastMessage != null){
                    Toast.makeText(CommonUtils.getSContext(), toastMessage, Toast.LENGTH_LONG).show();
                }else{
                    if(failedToastMessage != null)
                        Toast.makeText(CommonUtils.getSContext(), "Failed to update driver", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void updateCustomer(Customer customer, String toastMessage, String failedToastMessage){
        DatabaseReference ref = customerRef.child(mAuth.getUid());
        ref.setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>(){

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful() && toastMessage != null){
                    Toast.makeText(CommonUtils.getSContext(), toastMessage, Toast.LENGTH_LONG).show();
                }else{
                    if(failedToastMessage != null)
                        Toast.makeText(CommonUtils.getSContext(), "Failed to update driver", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void addOrder(String currentLoc, String destination, int capacity, String EAT){
        Map<String,Object> values=new HashMap<>();
        values.put("location",currentLoc);
        values.put("destination",destination);
        values.put("capacity",capacity);
        values.put("eat",EAT);
        values.put("status",1);
        customerRef.child(mAuth.getCurrentUser().getUid()).updateChildren(values).addOnCompleteListener(new OnCompleteListener(){
            @Override
            public void onComplete(@NonNull Task task){
                if(task.isSuccessful()){
                    Log.d(TAG,"Successfully add order: "+mAuth.getCurrentUser().getUid());
                }
                else{
                    Log.e(TAG,"Unable to add order: "+mAuth.getCurrentUser().getUid());
                }
            }
        });
    }

    public static void resetCustomer(){
        Customer c = CommonUtils.getSelf();
        customerRef.child(mAuth.getCurrentUser().getUid()).setValue(c).addOnCompleteListener(new OnCompleteListener(){
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Successfully update status: " + c.getName());
                }else {
                    Log.e(TAG,"Unable to update status: " + c.getName());
                }
            }
        });

    }

    public static void updateRating(Driver driver, float rate){
        Map<String, Object> values = new HashMap<>();
        values.put("rating", (rate + driver.getRating()) / (driver.getNumOfRating() + 1));
        values.put("numOfRating", driver.getNumOfRating()+1);
        driverRef.child(driver.getUid()).updateChildren(values).addOnCompleteListener(new OnCompleteListener(){
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Successfully update rating: " + driver.getUid());
                }else {
                    Log.e(TAG,"Unable to update rating: " + driver.getUid());
                }
            }
        });
    }
}
