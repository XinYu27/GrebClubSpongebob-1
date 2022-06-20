package com.clubSpongeBob.Greb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomersFragment extends Fragment {

    private ArrayList<Customer> customerList;
    RecyclerView recyclerView;
    customerList_RecyclerAdapter cusAdapter;

    public CustomersFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customers, container, false);

        recyclerView = view.findViewById(R.id.customerRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        customerList = new ArrayList<>();
        cusAdapter = new customerList_RecyclerAdapter(this.getContext(), customerList);
        recyclerView.setAdapter(cusAdapter);

        getCustomer();
        return view;
    }


    private void getCustomer() {
        Query query = FirebaseUtils.customerRef;
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                customerList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    System.out.println(snapshot);
                    Customer customer = snapshot.getValue(Customer.class);
                    customerList.add(customer);
                }
                cusAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}