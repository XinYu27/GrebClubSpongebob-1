package com.clubSpongeBob.Greb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
        car_Plate = findViewById(R.id.car_Plate);
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
        String drivername = driver_Name.getText().toString();
        String carmodal = car_Modal.getText().toString();
        String carcapacity = car_Capacity.getText().toString();
        String carplate = car_Plate.getText().toString();
        String driverlocation = driver_Location.getText().toString();
        String carcolour = car_Colour.getText().toString();
        String id = databaseadddrivers.push().getKey();

        int carr_capacity = Integer.parseInt(carcapacity);

        Driver driver = new Driver(drivername,carmodal,carplate,carcolour,carr_capacity,driverlocation);
        databaseadddrivers.child("drivers").child(id).setValue(driver)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(AddDriver.this, "Driver have been inserted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }
}