//package com.clubSpongeBob.Greb;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.firebase.database.FirebaseDatabase;
//
//public class driver_status extends AppCompatActivity {
//
//    RecyclerView recyclerView;
//    AdapterDriver AdapterDriver;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_driver_status);
//
//        recyclerView = (RecyclerView)findViewById(R.id.rv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        FirebaseRecyclerOptions<ModelforDriver> options =
//                new FirebaseRecyclerOptions.Builder<ModelforDriver>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("drivers"), ModelforDriver.class)
//                        .build();
//
//        AdapterDriver = new AdapterDriver(options);
//        recyclerView.setAdapter(AdapterDriver);
//
//    }
//}