package com.clubSpongeBob.Greb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DriverComing extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_coming);
    }

    public void sendEmergency(View v){
        System.out.println("emergency button");
    }

    public void cancel(View v){
        System.out.println("back");
        super.finish();
    }

    protected void setLocationDestinationEat(String location, String destination, String eat){
        final TextView currentLocationText = findViewById(R.id.currentLocationDriverComing);
        final TextView destinationText = findViewById(R.id.destinationDriverComing);
        final TextView eatText = findViewById(R.id.eatDriverComing);

        currentLocationText.setText(location);
        destinationText.setText(destination);
        eatText.setText(eat);
    }
}