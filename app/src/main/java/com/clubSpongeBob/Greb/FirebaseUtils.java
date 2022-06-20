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

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FirebaseUtils {
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference customerRef = db.getReference("customers");
    private static DatabaseReference driverRef = db.getReference("drivers");
    private static String TAG = "firebase utils";
    private static ExecutorService executor
            = Executors.newFixedThreadPool(10);

    public static DatabaseReference getDriverRef(){
        return driverRef;
    }

    public static DatabaseReference getCustomerRef(){
        return customerRef;
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

    public static Customer getOneUser(String uid){
        final Customer[] c = new Customer[1];

        customerRef
                .child(uid)
                .get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()){
                            Log.e(TAG, "Error getting user data", task.getException());
                        } else {
                            c[0] = task.getResult().getValue(Customer.class);
                            Log.d(TAG, "Successfully get data from user: " + c[0].getName());
                        }
                    }
                });

        return c[0];
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
                                                if(c.isAdmin())
                                                    context.startActivity(new Intent(CommonUtils.getsApplication(), AdminLanding.class));
                                                context.startActivity(new Intent(CommonUtils.getsApplication(), CustomerLanding.class));
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
        Toast.makeText(CommonUtils.getSContext(), "Sign out", Toast.LENGTH_LONG).show();
    }


    public static void addDriver(Driver driver){
        DatabaseReference ref = driverRef.child(driver.getUid());
        ref.setValue(driver).addOnCompleteListener(new OnCompleteListener<Void>(){

            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(CommonUtils.getSContext(), "Added new driver", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(CommonUtils.getSContext(), "Failed to add new driver", Toast.LENGTH_LONG).show();
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

    public static void getOneDriver(Context context, String uid){

        Task<DataSnapshot> dataSnapshotTask = driverRef.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
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

    public static void adminGetDriver(){
        driverRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Query query=driverRef.orderByChild("status");
                query.addValueEventListener(new ValueEventListener(){
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<Driver> dList = new ArrayList<>();
                        for (DataSnapshot data : dataSnapshot.getChildren()) {
                            Driver driver = data.getValue(Driver.class);
                            dList.add(driver);
                            System.out.println(driver.getName()); //for test
                        }
                        //do what you want to do with your list
                        //Put in recyclerview (adapter)
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error){
                        Log.d(TAG,"Unavailable to retrieve data");
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG,"Unavailable to retrieve data");
            }
        });

    }


    public static Future<Queue<Driver>> customerGetDriver(int noOfPassenger,String EAT, String origin, String destination){
        Queue<Driver> dQueue = new PriorityQueue<>();

        return executor.submit(()->{
            System.out.println("Enter here1");
            driverRef.orderByChild("status").startAt(1).addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(DataSnapshot dataSnapshot){
                    System.out.println("Enter here2");
                    for(DataSnapshot data: dataSnapshot.getChildren()) {
                        Driver driver = data.getValue(Driver.class);
                        Log.i(TAG, driver.toString());

                        if (driver.getCapacity() > noOfPassenger) {
                            try {
                                long[] distance = new long[3];
                                long[] time = new long[3];
                                long[] durations = new long[2];
                                long totalDuration = 0;

                                Future<long[]> driverToCus = MapService.getDistanceTime(driver.getLocation(), origin);
                                Future<long[]> cusToDest = MapService.getDistanceTime(origin, destination);

                                try {
                                    distance[0] = driverToCus.get()[0];
                                    durations[0] = driverToCus.get()[1];
                                    totalDuration += driverToCus.get()[1];
                                    if (driverToCus.isDone()){
                                        time[0] = TimeHelper.calculateEAT(durations[0], false);
                                    }

                                    distance[1] = cusToDest.get()[0];
                                    durations[1] = cusToDest.get()[1];
                                    totalDuration += cusToDest.get()[1];

                                    if (cusToDest.isDone()){
                                        time[1] = TimeHelper.calculateEAT(durations[0], false);
                                    }

                                    if (driverToCus.isDone() && cusToDest.isDone()){
                                        Log.i(TAG, "TotalDuration: "+ totalDuration);
                                        time[2] = TimeHelper.calculateEAT(totalDuration, true);
                                        Log.i(TAG, "Time[2]: "+String.format("%04d", time[2]));
                                        Log.i(TAG, "EAT: "+ Long.parseLong(EAT));
                                        if (time[2] >= Long.parseLong(EAT)) return;
                                        driver.setEat(String.format("%04d", time[2]));
                                        dQueue.add(driver);
                                        System.out.println("Queue size: "+ dQueue.size());
                                    }

                                } catch (ExecutionException | InterruptedException e) {
                                    e.printStackTrace();
                                }

                            } catch (Exception e) {
                                Log.e(TAG, e.getMessage());
                            }
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error){
                    Log.d(TAG,"Unavailable to retrieve data");
                }
            });
            System.out.println("Enter here3");
            return dQueue;
        });

    }

    public static void getOrder(){
        customerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Query query=customerRef.orderByChild("status");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Customer> cList=new ArrayList<>();
                        for(DataSnapshot data: snapshot.getChildren()){
                            Customer customer=data.getValue(Customer.class);
                            cList.add(customer);
                        }
                        //Do here
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d(TAG,"Unavailable to retrieve data");
                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d(TAG,"Unavailable to retrieve data");
            }
        });
    }

    public static void updateStatus(boolean customer, String uid, int status){
        DatabaseReference ref;
        if (customer){
            ref = customerRef;
        } else {
            ref = driverRef;
        }
        ref.child(uid).child("status").setValue(status).addOnCompleteListener(new OnCompleteListener(){
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Log.d(TAG, "Successfully update status: " + uid);
                }else {
                    Log.e(TAG,"Unable to update status: " + uid);
                }
            }
        });

    }

    public static void updateRating(Driver driver, int rate){
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
