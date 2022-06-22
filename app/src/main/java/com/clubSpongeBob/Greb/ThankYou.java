package com.clubSpongeBob.Greb;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ThankYou extends AppCompatActivity {
    final private String TAG = "ThankYouActivity";
    RatingBar ratingBar;
    final private Driver driver = CommonUtils.getSelectedDriver();
    TextView driverNameText;
    TextView carPlateText;
    TextView carModelText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        this.getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        View view = getSupportActionBar().getCustomView();
        ImageView imageView = view.findViewById(R.id.backNavigation);

        imageView.setVisibility(View.GONE);

        TextView name = view.findViewById(R.id.name);
        name.setText("Thank you");


        driverNameText = this.findViewById(R.id.driverNameText);
        carPlateText = this.findViewById(R.id.carPlateText);
        carModelText = this.findViewById(R.id.carModelText);

        driverNameText.setText(driver.getName());
        carPlateText.setText(driver.getCarPlate());
        carModelText.setText(driver.getCarModel());

        ratingBar = this.findViewById(R.id.ratingBar);

        this.findViewById(R.id.homeButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(false);
            }
        });
        this.findViewById(R.id.rateBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update(true);
            }
        });
    }

    private void update(boolean rate){
        try{
            if(CommonUtils.orderIsEmpty()) throw new Exception("Driver is not set in CommonUtils");
            if(true)
                FirebaseUtils.updateRating(CommonUtils.getSelectedDriver(), ratingBar.getRating());
            CommonUtils.reset();
            startActivity(new Intent(getApplication(), CustomerLanding.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
        } catch (Exception e){
            Log.e(TAG, e.getMessage());
        }
    }
}