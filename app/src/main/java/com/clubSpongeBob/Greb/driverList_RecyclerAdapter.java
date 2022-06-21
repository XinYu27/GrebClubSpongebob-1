package com.clubSpongeBob.Greb;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class driverList_RecyclerAdapter extends RecyclerView.Adapter<driverList_RecyclerAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<Driver> drivList ;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public driverList_RecyclerAdapter(Context context, ArrayList<Driver> drivList, OnItemClickListener listener){
        this.context = context;
        this.drivList = drivList;
        this.mListener = listener;
    }


    @NonNull
    @Override
    public driverList_RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.driver_list,parent,false);
        return new driverList_RecyclerAdapter.MyViewHolder(view, mListener) ;
    }

    @Override
    public void onBindViewHolder(@NonNull driverList_RecyclerAdapter.MyViewHolder holder, int position) {
        Driver model = drivList.get(position);

        holder.name.setText(model.getName());
        holder.capacity.setText("Capacity: "+String.valueOf(model.getCapacity()));
        //holder.customer.setText("Customer: "+m);
        holder.location.setText("Location: "+model.getLocation());
        holder.carinfo.setText(model.getCarColour()+" "+model.getCarModel()+" "+model.getCarPlate());
        int status = model.getStatus();
        switch (status){
            case 0:
                holder.driverStatus.setImageResource(R.drawable.ic_baseline_person_off_24);
                break;
            case 1:
                holder.driverStatus.setImageResource(R.drawable.ic_baseline_person_24_green);
                break;
            default:
        }

        String cust = model.getCustomer();
        if (cust==null){
            holder.customer.setText("Customer: -");
        }
        else
            holder.customer.setText("Customer: "+cust);
    }

    @Override
    public int getItemCount() {
        return drivList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView driverStatus;
        TextView name,carinfo,customer,capacity,location;

        public MyViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            driverStatus = itemView.findViewById((R.id.driverStatus));
            name = itemView.findViewById((R.id.driverName));
            carinfo= itemView.findViewById((R.id.carInfo));
            customer = itemView.findViewById((R.id.driverCust));
            capacity = itemView.findViewById((R.id.driverCapacity));
            location = itemView.findViewById((R.id.location));

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
