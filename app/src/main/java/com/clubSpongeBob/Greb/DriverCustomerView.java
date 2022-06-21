package com.clubSpongeBob.Greb;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DriverCustomerView extends AppCompatActivity {
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

        myAdapter=new customerdriverlist_RecyclerAdapter(this, dList, new customerdriverlist_RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Driver driver = dList.get(position);
                Log.i(TAG, "Clicked: "+driver.getName());
                driver.setCustomer(CommonUtils.getSelf().getName());
                driver.setStatus(0);
                CommonUtils.setSelectedDriver(driver);
                FirebaseUtils.updateDriver(driver);
                startActivity(new Intent(context, DriverComing.class));
            }
        });
        recyclerView.setAdapter(myAdapter);
    }
}





