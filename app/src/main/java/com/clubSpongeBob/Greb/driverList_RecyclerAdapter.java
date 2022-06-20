package com.clubSpongeBob.Greb;

import android.content.Context;
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

    public driverList_RecyclerAdapter(Context context, ArrayList<Driver> drivList){
        this.context = context;
        this.drivList = drivList;

    }


    @NonNull
    @Override
    public driverList_RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.driver_list,parent,false);
        return new driverList_RecyclerAdapter.MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull driverList_RecyclerAdapter.MyViewHolder holder, int position) {
        Driver model = drivList.get(position);
        holder.name.setText(model.getName());
        holder.capacity.setText("Capacity: "+String.valueOf(model.getCapacity()));
        //holder.customer.setText("Customer: "+model);
        holder.location.setText("Location: "+model.getLocation());
        holder.carinfo.setText(model.getCarColour()+" "+model.getCarModel()+" "+model.getCarPlate());
        int status = model.getStatus();

        switch(status){
            case 1:
                holder.driverStatus.setImageResource(R.drawable.ic_baseline_person_24_green);
                break;
            case 2:
                holder.driverStatus.setImageResource(R.drawable.ic_baseline_person_off_24);
                break;
            default:
        }
    }

    @Override
    public int getItemCount() {
        System.out.println(drivList.size());
        return drivList.size();
    }

    public void setData(List<Driver> list) {
        if (drivList != null) drivList.clear();
        if (drivList == null) drivList = new ArrayList<>();
        drivList.addAll(list);
        notifyDataSetChanged();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView driverStatus;
        TextView name,carinfo,customer,capacity,location;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            driverStatus = itemView.findViewById((R.id.driverStatus));
            name = itemView.findViewById((R.id.driverName));
            carinfo= itemView.findViewById((R.id.carInfo));
            customer = itemView.findViewById((R.id.driverCust));
            capacity = itemView.findViewById((R.id.driverCapacity));
            location = itemView.findViewById((R.id.location));

        }
    }

}
