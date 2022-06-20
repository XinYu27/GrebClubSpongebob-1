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
    Queue<Driver> dQueue=new PriorityQueue<>();

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

        try {
            Future<Queue<Driver>> getDrivers = FirebaseUtils.customerGetDriver(noOfPassenger,eat,origin,destination);
            while (!getDrivers.isDone()){
                System.out.println("Waiting");
            }
            if (getDrivers.isDone()){
                dQueue = getDrivers.get();
                System.out.println("Size in waiting page: "+dQueue.size());
                Intent intent=new Intent(WaitingPage.this, DriverCustomerView.class);
                startActivity(intent);
            }

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

//        mHandler.postDelayed(new Runnable(){
//            @Override
//            public void run(){
//                Intent intent=new Intent(WaitingPage.this, DriverCustomerView.class);
//                startActivity(intent);
//                finish();
//                Log.i(TAG, "Finish WaitingPage");
//            }
//        },3000);


    }
}

