package com.example.dduplacementadmin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class RegisterdAdapter extends FirebaseRecyclerAdapter<modal_class_for_registerd_std,RegisterdAdapter.myviewholder> {

    public RegisterdAdapter(@NonNull FirebaseRecyclerOptions<modal_class_for_registerd_std> options, Context applicationContext) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull modal_class_for_registerd_std model) {
        holder.collage_id.setText(model.getCollageID());
        holder.email.setText(model.getEmail());

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview_registerd_std,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView collage_id,email;
       // View v;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            collage_id = (TextView)itemView.findViewById(R.id.cid);
            email = (TextView)itemView.findViewById(R.id.cemail);
           // v=itemView;


        }
    }


}
