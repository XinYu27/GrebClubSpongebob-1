package com.clubSpongeBob.Greb;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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


//    public void getOrder(){
//        List<Customer> cList=new ArrayList<>();
//        //ArrayList<customerListModel> customerListModels = new ArrayList<>();
//        customerRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Query query=customerRef.orderByChild("status");
//                query.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(DataSnapshot data: snapshot.getChildren()){
//                            Customer customer=data.getValue(Customer.class);
//                            cList.add(customer);
//                        }
//                        for (int i=0;i<cList.size();i++){
//                            customerList.add(new customerListModel(cList.get(i).getName()
//                                    ,cList.get(i).getCapacity(),cList.get(i).getEat()
//                                    ,cList.get(i).getLocation(),cList.get(i).getDestination()
//                                    ,cList.get(i).getStatus()));
//                        }
//                    }
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//                        System.out.println("db error");
//                    }
//                });
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                System.out.println("db error");
//            }
//        });

}
