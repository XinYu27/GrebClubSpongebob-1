package com.clubSpongeBob.Greb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class WaitingPage extends AppCompatActivity {
//    private Handler mHandler= new Handler();
    private int noOfPassenger;
    private String eat;
    private String origin;
    private String destination;
    private final String TAG = "WaitingPage";
    Queue<Driver> dQueue;
    ArrayList<Driver> listDriver = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_page);
        Bundle extras = getIntent().getExtras();

        if(extras != null){
            noOfPassenger = extras.getInt("noOfPassenger");
            eat = extras.getString("eat");
            origin = extras.getString("origin");
            destination = extras.getString("destination");
        }

        List<Future<long[]>> list = new ArrayList<>();
        Thread thread1 = new Thread(){
            @Override
            public void run(){
            FirebaseUtils.getDriverRef().orderByChild("status").startAt(1).addValueEventListener(new ValueEventListener(){
                @Override
                public void onDataChange(DataSnapshot dataSnapshot){
                    dQueue = new PriorityQueue<>();
                    System.out.println("Enter here2");
                    for(DataSnapshot data: dataSnapshot.getChildren()) {

                        Driver driver = data.getValue(Driver.class);
                        Log.i(TAG, driver.toString());

                        if (driver.getCapacity() > noOfPassenger) {
                            try {
                                long[] distance = new long[3];
                                long[] time = new long[3];
                                long[] durations = new long[3];
                                durations[2] = 0;

                                Future<long[]> driverToCus = MapService.getDistanceTime(driver.getLocation(), origin);
                                Future<long[]> cusToDest = MapService.getDistanceTime(origin, destination);
                                list.add(driverToCus);
                                list.add(cusToDest);
                                try {
                                    distance[0] = driverToCus.get()[0];
                                    durations[0] = driverToCus.get()[1];
                                    durations[2] += driverToCus.get()[1];
                                    if (driverToCus.isDone()){
                                        time[0] = TimeHelper.calculateEAT(durations[0], false);
                                    }

                                    distance[1] = cusToDest.get()[0];
                                    durations[1] = cusToDest.get()[1];
                                    durations[2] += cusToDest.get()[1];

                                    if (cusToDest.isDone()){
                                        time[1] = TimeHelper.calculateEAT(durations[0], false);
                                    }

                                    if (driverToCus.isDone() && cusToDest.isDone()){
                                        Log.i(TAG, "TotalDuration: "+  durations[2]);
                                        time[2] = TimeHelper.calculateEAT( durations[2], true);
                                        Log.i(TAG, "Time[2]: "+String.format("%04d", time[2]));
                                        Log.i(TAG, "EAT: "+ Long.parseLong(eat));
                                        if (time[2] >= Long.parseLong(eat)) return;
                                        driver.setEat(String.format("%04d", time[2]));
                                        String[] temp = new String[3];
                                        for (int i = 0; i<temp.length; i++){
                                            temp[i] = String.format("%04d", time[i]);
                                        }
                                        driver.setEatArr(temp);
                                        dQueue.add(driver);
                                    }

                                } catch (ExecutionException | InterruptedException e) {
                                    e.printStackTrace();
                                }

                            } catch (Exception e) {
                                Log.e(TAG, e.getMessage());
                            }
                        }
                    }

                    while (true){
                        boolean isFinish = true;
                        for(Future<long[]> f : list){
                            if(!f.isDone()){
                                isFinish = false;
                            }
                        }
                        if (isFinish) {
                            System.out.println("Queue size: "+ dQueue.size());
                            while (!dQueue.isEmpty()){
                                listDriver.add(dQueue.poll());
                            }
                            CommonUtils.resetArrayList();
                            CommonUtils.setDriverArrayList(listDriver);
                            Intent intent=new Intent(WaitingPage.this, DriverCustomerView.class);
                            startActivity(intent);
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error){
                    Log.d(TAG,"Unavailable to retrieve data");
                }
            });
            }
        };
        thread1.start();
    }
}

