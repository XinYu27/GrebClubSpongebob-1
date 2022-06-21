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

import java.util.ArrayList;


public class customerdriverlist_RecyclerAdapter extends RecyclerView.Adapter<customerdriverlist_RecyclerAdapter.MyViewHolder>{
    private Context context;
    private ArrayList<Driver> dList;
    private String TAG = "CustomerDriverListRecyclerAdapter";
    private static FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private customerdriverlist_RecyclerAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public customerdriverlist_RecyclerAdapter(Context context, ArrayList<Driver> dList, customerdriverlist_RecyclerAdapter.OnItemClickListener listener){
        this.context=context;
        this.dList=dList;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater= LayoutInflater.from(context);
        View view= inflater.inflate(R.layout.driver_list_customer,parent,false);
        return new MyViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,int position) {
        Driver driver=dList.get(position);
        Log.i(TAG, "Driver: "+driver.getName());
        holder.driverName.setText(driver.getName());
        holder.driverPlate.setText(driver.getCarPlate());
        holder.driverCar.setText(driver.getCarModel());
        holder.driverRating.setRating(driver.getRating());
        holder.driverEat.setText("Estimated Arrival Time: "+driver.getEat());
    }


    @Override
    public int getItemCount(){
        System.out.println(dList.size()+"abcdefg");
        return dList.size();}

    public void setData(ArrayList<Driver>list){
        if(dList!=null) dList.clear();
        if(dList==null) dList=new ArrayList<>();
        dList.addAll(list);
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView driverName,driverPlate,driverCar,driverEat;
        RatingBar driverRating;
        public MyViewHolder(@NonNull View itemView,OnItemClickListener listener){
            super(itemView);
            driverName=itemView.findViewById(R.id.driverName);
            driverPlate=itemView.findViewById(R.id.driverPlate);
            driverCar=itemView.findViewById(R.id.driverCar);
            driverRating=itemView.findViewById(R.id.driverRating);
            driverEat=itemView.findViewById(R.id.driverEat);

            itemView.setOnClickListener(v->{
                if (listener != null){
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        listener.onItemClick(position);
                    }
                }
            });

        }


    }
}
