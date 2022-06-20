package com.clubSpongeBob.Greb;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DriverCustomerView extends AppCompatActivity {
    private static FirebaseDatabase db = FirebaseDatabase.getInstance();
    private static DatabaseReference driverRef = db.getReference("drivers");
    private static String TAG = "firebase utils";
    private Context context;
    private Activity activity;
    private ArrayList<Driver> dList=CommonUtils.getDriverArrayList();
    public static customerdriverlist_RecyclerAdapter myAdapter;
    RecyclerView recyclerView;

    public DriverCustomerView(){
        //Required empty public constructor
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_customer_view);
        activity=DriverCustomerView.this;
        context=getApplicationContext();
        recyclerView=this.findViewById(R.id.driverRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        myAdapter=new customerdriverlist_RecyclerAdapter(this,dList);
        recyclerView.setAdapter(myAdapter);


//        Query query=FirebaseUtils.driverRef;
//        query.addValueEventListener(new ValueEventListener(){
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot){
//
//                for(DataSnapshot data: dataSnapshot.getChildren()) {
//                    System.out.println("Data: " +data.toString());
//                    Driver driver = data.getValue(Driver.class);
//
//                    System.out.println(driver);
//                    dList.add(driver);
//
//                        }
//
//                        myAdapter.notifyDataSetChanged();
//
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error){
//                        Log.d(TAG,"Unavailable to retrieve data");
//                    }
//                });
            }




    }





