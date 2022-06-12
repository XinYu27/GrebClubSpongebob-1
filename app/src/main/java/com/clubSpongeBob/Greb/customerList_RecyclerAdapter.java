package com.clubSpongeBob.Greb;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class customerList_RecyclerAdapter extends RecyclerView.Adapter<customerList_RecyclerAdapter.MyViewHolder> {
    Context context;
    ArrayList<customerListModel> list;

    public customerList_RecyclerAdapter(Context context, ArrayList<customerListModel> list){
        this.context = context;
        this.list = list;

    }
    @NonNull
    @Override
    public customerList_RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.customer_list,parent,false);
        return new customerList_RecyclerAdapter.MyViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull customerList_RecyclerAdapter.MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getDriverName());
        holder.capacity.setText(list.get(position).getCarCapacity());
        holder.eat.setText(list.get(position).getEatTime());
        holder.start.setText(list.get(position).getStartingPoint());
        holder.destination.setText(list.get(position).getDestination());
        holder.customerStatus.setImageResource(list.get(position).getDriverStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView customerStatus;
        TextView name,capacity,eat,start,destination;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            customerStatus = itemView.findViewById((R.id.customerStatus));
            name = itemView.findViewById((R.id.driverName));
            capacity= itemView.findViewById((R.id.capacity));
            eat = itemView.findViewById((R.id.eat));
            start = itemView.findViewById((R.id.startingPoint));
            destination = itemView.findViewById((R.id.destination));
        }
    }
}