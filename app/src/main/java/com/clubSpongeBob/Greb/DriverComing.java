package com.clubSpongeBob.Greb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class DriverComing extends AppCompatActivity {
    final String TAG = "DriverComing";
    final private Driver driver = CommonUtils.getSelectedDriver();
    final private Customer customer = CommonUtils.getSelf();
    private int idx = 0;

    private TextView driverNameText;
    private TextView carPlateText;
    private TextView carModelText, currentLocationText, destinationText;
    private TextView eatDriverComing;
    private RatingBar ratingBar;
    private FloatingActionButton emergencyDriverComingBtn;

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
        eatDriverComing = this.findViewById(R.id.eatDriverComing);

        driverNameText.setText(driver.getName());
        carPlateText.setText(driver.getCarPlate());
        carModelText.setText(driver.getCarModel());
        currentLocationText.setText(driver.getLocation());
        destinationText.setText(CommonUtils.getSelf().getLocation());
        ratingBar.setRating(driver.getRating());
        eatDriverComing.setText(driver.getEatArr().get(idx));

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000*10);
                        int now = TimeHelper.getCurrentTime();

                        if (now > Integer.parseInt(driver.getEatArr().get(idx))) {
                            idx += 1;

                            if (idx == 2) {
                                throw new InterruptedException("Reached");
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    eatDriverComing.setText(driver.getEatArr().get(idx));
                                    if (idx == 1) {
                                        eatDriverComing.setText(driver.getEatArr().get(idx));
                                        currentLocationText.setText(CommonUtils.getSelf().getLocation());
                                        destinationText.setText(CommonUtils.getSelf().getDestination());
                                        name.setText("On the Way");

                                        customer.setStatus(3);
                                        FirebaseUtils.updateCustomer(customer);

                                        driver.setLocation(CommonUtils.getSelf().getLocation());
                                        FirebaseUtils.updateDriver(driver);
                                    }
                                }
                            });
                        }
                }

            } catch (InterruptedException e) {
                    e.printStackTrace();
                    Log.i(TAG, "REACHED");

                    customer.setStatus(4);
                    FirebaseUtils.updateCustomer(customer);

                    driver.resetOrder();
                    driver.setLocation(customer.getDestination());
                    FirebaseUtils.updateDriver(driver);
                    startActivity(new Intent(getApplicationContext(), ArrivedPay.class));
                    finish();
                }
            }
        };
        thread.start();
    }

    public void sendEmergency(View v){
        Customer self = CommonUtils.getSelf();
        EmailService.sendEmail(self.getEmergencyContact(),self.getLocation(), self.getDestination(), CommonUtils.getSelectedDriver());
    }

}