package com.clubSpongeBob.Greb;

import android.content.Context;
import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FirebaseUtils {
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();

    public static void registerUser(Context context, String email, String name, String password, String emergency){
        // Assuming all input is validated
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task){
                        if(task.isSuccessful()){
                            Customer customer = new Customer(email, name, emergency);
                            db.getReference("customers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(context,"User has been registered successfully!", Toast.LENGTH_LONG).show();
                                            }else{
                                                Toast.makeText(context,"Failed to register! Try again!", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                        }else {
                            Toast.makeText(context, "Failed to register", Toast.LENGTH_LONG).show();
                        }
                    }
        });

//        String[] errorCodes = new String[3];
//        email = email.trim();

//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            errorCodes[0] = "Please provide valid email";
//        }
//
    }

    public static Customer loginUser(Context context, String email, String password){
        Customer c = null;

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>(){

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Task<DataSnapshot> snapshot = db.getReference("customers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .get();
                        }else {
                            Toast.makeText(context, "Failed to register", Toast.LENGTH_LONG).show();
                        }
                    }
                });
        return c;
    }

    public static boolean isLogin(Context context){
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) return false;
        Toast.makeText(context, "Welcome "+ user.getDisplayName(), Toast.LENGTH_LONG);
        return true;
    }

    public static void signOutUser(Context context){
        mAuth.signOut();
        Toast.makeText(context, "Sign out", Toast.LENGTH_LONG).show();
    }

    public static String createUID(){
        return UUID.randomUUID().toString();
    }

    public static void addDriver(Context context, Driver driver){
        DatabaseReference ref = db.getReference("drivers").child(createUID());
        ref.setValue(driver).addOnCompleteListener(new OnCompleteListener<Void>(){

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(context, "Added new driver", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context, "Failed to add new driver", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public static void getOneDriver(Context context, String uid){

        Task<DataSnapshot> dataSnapshotTask = db.getReference("drivers").child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));

                }}
            });
//        DataSnapshot dataSnapshot = dataSnapshotTask.getResult();
//        if (dataSnapshot == null) return null;
//        return new Driver(dataSnapshot.child("name").getValue().toString(), dataSnapshot.child("location").getValue().toString(),
//                Integer.parseInt(dataSnapshot.child("capacity").getValue().toString()), dataSnapshot.child("carPlate").getValue().toString(),
//                dataSnapshot.child("carModel").getValue().toString(), dataSnapshot.child("carColour").getValue().toString(),
//                Integer.parseInt(dataSnapshot.child("rating").getValue().toString()), Integer.parseInt(dataSnapshot.child("numOfRating").getValue().toString()),
//                Integer.parseInt(dataSnapshot.child("status").getValue().toString()), dataSnapshot.child("eat").getValue().toString());
    }


}