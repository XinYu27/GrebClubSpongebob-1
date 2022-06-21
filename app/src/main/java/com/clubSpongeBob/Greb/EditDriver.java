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

public class EditDriver extends AppCompatActivity {

    // variable for Edittext view.
    private EditText driver_Name2,car_Model2,car_Plate2,car_Colour2,car_Capacity2,driver_Location2;
    private Driver selectedDriver = CommonUtils.getSelectedDriver();
    Button saveButton,deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_driver);

        // initializing our object class variable.
        driver_Name2 = findViewById(R.id.driver_Name2);
        car_Model2 = findViewById(R.id.car_Model2);
        car_Plate2 = findViewById(R.id.car_Plate2);
        car_Colour2 = findViewById(R.id.car_Colour2);
        car_Capacity2 = findViewById(R.id.car_Capacity2);
        driver_Location2 = findViewById(R.id.driver_Location2);
        saveButton = findViewById(R.id.saveButton);
        deleteButton = findViewById(R.id.deleteButton);

        driver_Name2.setText(selectedDriver.getName());
        car_Model2.setText(selectedDriver.getCarModel());
        car_Plate2.setText(selectedDriver.getCarPlate());
        car_Colour2.setText(selectedDriver.getCarColour());
        car_Capacity2.setText(String.valueOf(selectedDriver.getCapacity()));
        car_Model2.setText(selectedDriver.getLocation());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editthedriver();

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletethedriver();
            }
        });
    }

    private void editthedriver(){
        String drivername = driver_Name2.getText().toString();
        String carmodal = car_Model2.getText().toString();
        String carcapacity = car_Capacity2.getText().toString();
        String carplate = car_Plate2.getText().toString();
        String driverlocation = driver_Location2.getText().toString();
        String carcolour = car_Colour2.getText().toString();
        String id = selectedDriver.getUid();

        int carr_capacity = Integer.parseInt(carcapacity);

        selectedDriver.setName(drivername);
        selectedDriver.setCarColour(carcolour);
        selectedDriver.setCarModel(carmodal);
        selectedDriver.setCarPlate(carplate);
        selectedDriver.setLocation(driverlocation);
        selectedDriver.setCapacity(carr_capacity);


        FirebaseUtils.getDriverRef().child(id).setValue(selectedDriver)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(EditDriver.this, "Driver have been updated", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });
    }

    private void deletethedriver(){
        String id = selectedDriver.getUid();

        FirebaseUtils.getDriverRef().child(id).removeValue()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(EditDriver.this, "Driver " + selectedDriver.getName() +" have been deleted", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                });

    }
}