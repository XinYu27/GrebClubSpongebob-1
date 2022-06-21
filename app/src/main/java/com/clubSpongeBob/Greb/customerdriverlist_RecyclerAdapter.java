package com.clubSpongeBob.Greb;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class customerdriverlist_RecyclerAdapter extends RecyclerView.Adapter<customerdriverlist_RecyclerAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<Driver> dList;
    private String TAG = "CustomerDriverListRecyclerAdapter";
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();


    public customerdriverlist_RecyclerAdapter(Context context, ArrayList<Driver> dList){
        this.context=context;
        this.dList=dList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.driver_list_customer,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        Driver driver=dList.get(position);
        Log.i(TAG, "Driver: "+driver.getName());
        holder.driverName.setText("Name: "+driver.getName());
        holder.driverPlate.setText("Plate: "+driver.getCarPlate());
        holder.driverCar.setText("Car: "+driver.getCarModel());
        holder.driverRating.setRating(driver.getRating());
        holder.driverEat.setText("Estimated Arrival Time: "+driver.getEat());
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View v) {
                CommonUtils.setSelectedDriver(driver);
                FirebaseUtils.updateStatus(false,driver.getUid(),0);
                final FirebaseUser mFirebaseUser=mAuth.getCurrentUser();
                if(mFirebaseUser!=null) {
                    FirebaseUtils.updateStatus(true, mAuth.getCurrentUser().getUid(), 2);
                }
                context.startActivity(new Intent(context,DriverComing.class));

            }
        });
    }


    @Override
    public int getItemCount(){
        System.out.println(dList.size()+"abcdefg");
        return dList.size();}

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView driverName,driverPlate,driverCar,driverEat;
        RatingBar driverRating;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            driverName=itemView.findViewById(R.id.driverName);
            driverPlate=itemView.findViewById(R.id.driverPlate);
            driverCar=itemView.findViewById(R.id.driverCar);
            driverRating=itemView.findViewById(R.id.driverRating);
            driverEat=itemView.findViewById(R.id.driverEat);
        }


    }
}
