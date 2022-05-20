package com.clubSpongeBob.Greb;

import android.content.Context;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
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
                            Map map = new HashMap();
                            map.put("email", email);
                            map.put("name", name);
                            map.put("emergency", emergency);
                            map.put("status", 0);
                            map.put("capacity", 0);
                            map.put("destination", "");
                            map.put("location", "");
                            db.getReference("customers")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
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
                            System.out.println(snapshot);
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
        Toast.makeText(context, "Sign Out Successfully", Toast.LENGTH_LONG).show();
    }
}
