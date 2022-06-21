package com.clubSpongeBob.Greb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DriverComing extends AppCompatActivity {
    String location;
    String destination;
    String eat;
    final private Driver driver = CommonUtils.getSelectedDriver();

    TextView driverNameText;
    TextView carPlateText;
    TextView carModelText, currentLocationText, destinationText;
    RatingBar ratingBar;
    FloatingActionButton emergencyDriverComingBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_coming);
        CommonUtils.setWaitingPageListening(false);
        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        View view = getSupportActionBar().getCustomView();
        ImageView imageView = view.findViewById(R.id.backNavigation);

        imageView.setVisibility(View.GONE);

        TextView name = view.findViewById(R.id.name);
        name.setText("Driver is coming");

        driverNameText = this.findViewById(R.id.driverNameText);
        carPlateText = this.findViewById(R.id.carPlateText);
        carModelText = this.findViewById(R.id.carModelText);
        currentLocationText = this.findViewById(R.id.currentLocationText);
        destinationText = this.findViewById(R.id.destinationText);
        ratingBar = this.findViewById(R.id.ratingBar);
        emergencyDriverComingBtn = this.findViewById(R.id.emergencyDriverComingBtn);

        driverNameText.setText(driver.getName());
        carPlateText.setText(driver.getCarPlate());
        carModelText.setText(driver.getCarModel());
        currentLocationText.setText(driver.getLocation());
        destinationText.setText(CommonUtils.getSelf().getLocation());
        ratingBar.setRating(driver.getRating());


    }

    public void sendEmergency(View v){
        Customer self = CommonUtils.getSelf();
        EmailService.sendEmail(self.getEmergencyContact(),self.getLocation(), self.getDestination());
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