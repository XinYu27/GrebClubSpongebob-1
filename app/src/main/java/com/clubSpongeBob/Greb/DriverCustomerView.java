package com.clubSpongeBob.Greb;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DriverCustomerView extends AppCompatActivity {
    private static String TAG = "DriverCustomerView";
    private ArrayList<Driver> dList=CommonUtils.getDriverArrayList();
    public static customerdriverlist_RecyclerAdapter myAdapter;
    RecyclerView recyclerView;

    public DriverCustomerView(){
        //Required empty public constructor
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_customer_view);

        CommonUtils.setFirstTimeWaiting(false);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        View view = getSupportActionBar().getCustomView();
        ImageView imageView = view.findViewById(R.id.backNavigation);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CommonUtils.getSelf().clearCustomerOrder();
                FirebaseUtils.resetCustomer();
                CommonUtils.setWaitingPageListening(false);
                startActivity(new Intent(view.getContext(), CustomerLanding.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

        TextView name = view.findViewById(R.id.name);
        name.setText("Available Drivers");

        recyclerView=this.findViewById(R.id.driverRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);

        myAdapter=new customerdriverlist_RecyclerAdapter(this, dList, new customerdriverlist_RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Driver driver = dList.get(position);
                Customer customer = CommonUtils.getSelf();
                Log.i(TAG, "Clicked: "+driver.getName());
                driver.setCustomer(customer.getName());
                driver.setStatus(0);
                customer.setStatus(2);
                CommonUtils.setSelectedDriver(driver);
                FirebaseUtils.updateCustomer(customer);
                FirebaseUtils.updateDriver(driver);
                startActivity(new Intent(getApplicationContext(), DriverComing.class));
                finish();
            }
        });
        recyclerView.setAdapter(myAdapter);
        long[] i = {0};

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                while (!isInterrupted()) {
                    try {
                        Thread.sleep(1000*60);
                        i[0] += 1;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            for(Driver driver:dList){
                                Thread thread1= new Thread(){
                                    @Override
                                    public void run(){
                                        Log.i(TAG, "Total Duration: "+String.valueOf(driver.getTotalDuration()));
                                        Log.i(TAG, "Passed: "+String.valueOf(i[0]));
                                        long time=TimeHelper.calculateEAT(driver.getTotalDuration()+i[0]*60,true);
                                        driver.setEat(String.format("%04d", time));
//                                        tempList.add(driver);
//                                        dList=tempList;
                                    }
                                };
                                thread1.start();
                            }
                            myAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        };
        thread2.start();

    }


}





