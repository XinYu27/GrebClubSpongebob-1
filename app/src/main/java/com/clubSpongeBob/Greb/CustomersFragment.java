package com.clubSpongeBob.Greb;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomersFragment extends Fragment {

    ArrayList<customerListModel> customerListModels = new ArrayList<>();
    int [] customerStatus = {R.drawable.ic_baseline_access_time_filled_24,R.drawable.ic_baseline_check_circle_24,
            R.drawable.ic_baseline_emoji_transportation_24,R.drawable.ic_baseline_incomplete_circle_24,
            R.drawable.ic_baseline_incomplete_circle_24,R.drawable.ic_baseline_access_time_filled_24};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;

    public CustomersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomersFragment.
     */
    // TODO: Rename and change types and number of parameters
//    public static CustomersFragment newInstance(String param1, String param2) {
//        CustomersFragment fragment = new CustomersFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//        RecyclerView recyclerView = getView().findViewById(R.id.customerRecyclerView);
//        setUpcustomerListModels();
//        customerList_RecyclerAdapter adapter = new customerList_RecyclerAdapter(getContext(), customerListModels);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_customers, container, false);
         recyclerView = view.findViewById(R.id.customerRecyclerView);
         recyclerView.setHasFixedSize(true);
         setUpcustomerListModels();
         recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
         recyclerView.setAdapter(new customerList_RecyclerAdapter(getContext(),customerListModels));
         return view;
    }

    private void setUpcustomerListModels(){
        String [] driverNames = getResources().getStringArray(R.array.driverName_txt);
        String [] carCapacities = getResources().getStringArray(R.array.carCapacity_txt);
        String [] estimatedTime = getResources().getStringArray(R.array.eatTime_txt);
        String [] startingPoints = getResources().getStringArray(R.array.startPoint_txt);
        String [] destinations = getResources().getStringArray(R.array.destination_txt);
        System.out.println(driverNames.length);
        for(int i =0;i<driverNames.length;i++){
            customerListModels.add(new customerListModel(driverNames[i],
                    carCapacities[i],estimatedTime[i],startingPoints[i],destinations[i],customerStatus[i]));
        }

    }
}