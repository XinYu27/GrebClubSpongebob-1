package com.clubSpongeBob.Greb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddDriver extends AppCompatActivity {
    Button addButton;
    EditText driver_Name, car_Modal,car_Capacity,car_Plate,car_Colour,driver_Location;
    DatabaseReference databaseadddrivers;

    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);

        addButton = findViewById(R.id.addButton);
        driver_Name = (EditText) findViewById(R.id.driver_Name);
        car_Modal = findViewById(R.id.car_Model);
        car_Capacity = findViewById(R.id.car_Capacity);
        driver_Location = findViewById(R.id.driver_Location);
        car_Colour = findViewById(R.id.car_Colour);
        databaseadddrivers = FirebaseDatabase.getInstance().getReference();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Adddriver();
            }
        });

    }

    private void Adddriver() {
        String drivername = driver_name
    }
}