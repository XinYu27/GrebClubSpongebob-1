//package com.clubSpongeBob.Greb;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//
//public class AdapterDriver extends FirebaseRecyclerAdapter<ModelforDriver,AdapterDriver.myViewHolder>{
//
//    /**
//     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
//     * {@link FirebaseRecyclerOptions} for configuration options.
//     *
//     * @param options
//     */
//    public AdapterDriver(@NonNull FirebaseRecyclerOptions<ModelforDriver> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull ModelforDriver model) {
//        holder.name.setText(model.getName());
//        holder.carcolour.setText(model.getCarColour());
//        holder.carmodel.setText(model.getCarModel());
//        holder.carplate.setText(model.getCarPlate());
//        holder.capacity.setText(model.getCapacity());
//        holder.locationdriver.setText(model.getLocation());
//        holder.statusdriver.setText(model.getStatus());
//
//    }
//
//    @NonNull
//    @Override
//    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_frame,parent,false);
//        return new myViewHolder(view);
//    }
//
//    class myViewHolder extends RecyclerView.ViewHolder{
//        TextView name,carcolour,carmodel,carplate,capacity,locationdriver,statusdriver;
//
//        public myViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            name = (TextView) itemView.findViewById(R.id.namedriver);
//            carcolour = (TextView)itemView.findViewById(R.id.carcolour);
//            carplate = (TextView) itemView.findViewById(R.id.carplate);
//            carmodel = (TextView) itemView.findViewById(R.id.carmodel);
//            capacity = (TextView) itemView.findViewById(R.id.capacity);
//            locationdriver = (TextView) itemView.findViewById(R.id.locationdriver);
//            statusdriver = (TextView) itemView.findViewById(R.id.statusdriver);
//
//
//        }
//    }
//}
