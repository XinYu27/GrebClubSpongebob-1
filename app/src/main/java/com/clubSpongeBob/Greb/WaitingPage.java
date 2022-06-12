package com.clubSpongeBob.Greb;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class WaitingPage extends AppCompatActivity {
    private Handler mHandler= new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting_page);

        mHandler.postDelayed(new Runnable(){
            @Override
            public void run(){
                Intent intent=new Intent(WaitingPage.this, DriverCustomerView.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}

