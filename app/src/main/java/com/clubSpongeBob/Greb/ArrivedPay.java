package com.clubSpongeBob.Greb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class ArrivedPay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrived_pay);
    }

    public void payOnClick(View v){
        // push to db
        super.finish();
    }
}