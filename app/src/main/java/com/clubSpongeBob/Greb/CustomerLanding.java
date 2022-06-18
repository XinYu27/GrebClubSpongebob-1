package com.clubSpongeBob.Greb;


import static com.clubSpongeBob.Greb.FirebaseUtils.addOrder;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.Calendar;
import java.util.Date;

public class CustomerLanding extends AppCompatActivity {
    TextView currentTime;
    private Handler mHandler= new Handler();
    public static int noOfPassenger;
    public static String eat;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_landing);

        currentTime = findViewById(R.id.currentTime);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        View view = getSupportActionBar().getCustomView();
        ImageView imageView = view.findViewById(R.id.backNavigation);

        imageView.setVisibility(View.GONE);

        TextView name = view.findViewById(R.id.name);
        name.setText("Customer");

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                updateTime();
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };


        thread.start();

        Button rtn=(Button)findViewById(R.id.letsGoButton);
        EditText currentLoc=(EditText)findViewById(R.id.currentLocation);
        EditText destination=(EditText)findViewById(R.id.destLocation);
        EditText mPassenger=(EditText)findViewById(R.id.numPassengers);
        EditText mTime=(EditText) findViewById(R.id.inputEAT);


        //Get input value from customer
        rtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
//                customerGetDriver();
//                EditText currentLoc=(EditText)findViewById(R.id.currentLocation);
                String cLocation=currentLoc.getText().toString();

//                EditText destination=(EditText)findViewById(R.id.destLocation);
                String dLocation=destination.getText().toString();

//                EditText mPassenger=(EditText)findViewById(R.id.numPassengers);
                String passN=mPassenger.getText().toString();
                noOfPassenger= Integer.parseInt(passN);

//                EditText mTime=(EditText) findViewById(R.id.inputEAT);
                eat=mTime.getText().toString();
//                LocalTime time=LocalTime.parse(currTime);

                addOrder(cLocation,dLocation,noOfPassenger,eat);

                mHandler.postDelayed(new Runnable(){
                    @Override
                    public void run(){
                        Intent intent=new Intent(CustomerLanding.this,WaitingPage.class);
                        startActivity(intent);
                        finish();
                    }
                },1000);
            }
        });


    }
    public static int getCapacity(){
        return noOfPassenger;
    }
    public static String getEat(){
        return eat;
    }

    public void updateTime(){
        Date currTime = Calendar.getInstance().getTime();
        String time="hh:mm:ss aa";
        currentTime.setText(DateFormat.format(time,currTime));
    }
}
