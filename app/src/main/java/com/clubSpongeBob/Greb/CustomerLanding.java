package com.clubSpongeBob.Greb;


import static com.clubSpongeBob.Greb.FirebaseUtils.addOrder;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.timepicker.TimeFormat;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomerLanding extends AppCompatActivity {
    TextView currentTime;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_landing);

        currentTime = findViewById(R.id.currentTimeHour);
        Date currTime = Calendar.getInstance().getTime();
        String formatTime = DateFormat.getTimeInstance(TimeFormat.CLOCK_24H).format(currTime);

        currentTime.setText(formatTime);


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
                int noOfPassenger= Integer.parseInt(passN);

//                EditText mTime=(EditText) findViewById(R.id.inputEAT);
                String currTime=mTime.getText().toString();
//                LocalTime time=LocalTime.parse(currTime);

                addOrder(cLocation,dLocation,noOfPassenger,currTime);

            }
        });


    }
}
