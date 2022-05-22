package com.clubSpongeBob.Greb;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.timepicker.TimeFormat;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class CustomerLanding extends AppCompatActivity {
    TextView currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_landing);

        currentTime = findViewById(R.id.currentTime);
        Date currTime = Calendar.getInstance().getTime();
        String formatTime = DateFormat.getTimeInstance(TimeFormat.CLOCK_24H).format(currTime);

        currentTime.setText(formatTime);

    }
}
