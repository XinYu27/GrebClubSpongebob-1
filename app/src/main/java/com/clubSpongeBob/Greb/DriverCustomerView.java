package com.clubSpongeBob.Greb;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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
        myAdapter=new customerdriverlist_RecyclerAdapter(this,dList);
        recyclerView.setAdapter(myAdapter);
    }
}





