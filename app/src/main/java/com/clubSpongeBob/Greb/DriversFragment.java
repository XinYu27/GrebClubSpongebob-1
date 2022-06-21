package com.clubSpongeBob.Greb;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DriversFragment extends Fragment {

    private ArrayList<Driver> driverList;
    RecyclerView recyclerView;
    driverList_RecyclerAdapter drivAdapter;
    private static String TAG = "DriversFragment";

    public DriversFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_drivers, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.driverRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        driverList = new ArrayList<>();
        drivAdapter = new driverList_RecyclerAdapter(this.getContext(), driverList, new driverList_RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Driver driver = driverList.get(position);
                Log.i(TAG, "Clicked: "+driver.getName());
                CommonUtils.setSelectedDriver(driver);
                startActivity(new Intent(view.getContext(), EditDriver.class));
            }
        });
        recyclerView.setAdapter(drivAdapter);
        Button rtn= view.findViewById(R.id.addDriverBtn);
        rtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(),AddDriver.class);
                startActivity(intent);
            }
        });

        getDriver();
    }


    private void getDriver() {
        Query query = FirebaseUtils.driverRef;
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                driverList.clear();
                Log.i(TAG, "Reload Driver List");
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Driver driver = snapshot.getValue(Driver.class);
                    driverList.add(driver);
                }
                drivAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}