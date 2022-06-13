package com.clubSpongeBob.Greb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class DriverComing extends AppCompatActivity {
    String location;
    String destination;
    String eat;
    final private Driver driver = CommonUtils.getSelectedDriver();

    TextView driverNameText;
    TextView carPlateText;
    TextView carModelText;
    RatingBar ratingBar;
    Button emergencyDriverComingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_coming);
        driverNameText = this.findViewById(R.id.driverNameText);
        carPlateText = this.findViewById(R.id.carPlateText);
        carModelText = this.findViewById(R.id.carModelText);
        ratingBar = this.findViewById(R.id.ratingBar);
        emergencyDriverComingBtn = this.findViewById(R.id.emergencyDriverComingBtn);

        driverNameText.setText(driver.getName());
        carPlateText.setText(driver.getCarPlate());
        carModelText.setText(driver.getCarModel());
        ratingBar.setRating(driver.getRating());

    }

    public void sendEmergency(View v){
        System.out.println("emergency button");
    }

    public void cancel(View v){
        System.out.println("back");
        super.finish();
    }

    private void setLocationDestinationEat(String location, String destination, String eat){
        final TextView currentLocationText = findViewById(R.id.currentLocationText);
        final TextView destinationText = findViewById(R.id.destinationText);
        final TextView eatText = findViewById(R.id.eatDriverComing);

        currentLocationText.setText(location);
        destinationText.setText(destination);
        eatText.setText(eat);
    }
}