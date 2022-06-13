package com.clubSpongeBob.Greb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Emergency extends AppCompatActivity {
    final private Driver driver = CommonUtils.getSelectedDriver();

    TextView driverNameText;
    TextView carPlateText;
    TextView carModelText;
    TextView destinationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        driverNameText = this.findViewById(R.id.driverNameText);
        carPlateText = this.findViewById(R.id.carPlateText);
        carModelText = this.findViewById(R.id.carModelText);
        destinationText = this.findViewById(R.id.destinationText);

        driverNameText.setText(driver.getName());
        carPlateText.setText(driver.getCarPlate());
        carModelText.setText(driver.getCarModel());

        this.findViewById(R.id.sendBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}