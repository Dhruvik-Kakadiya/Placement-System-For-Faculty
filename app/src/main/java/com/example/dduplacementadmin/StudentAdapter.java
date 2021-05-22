package com.example.dduplacementadmin;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class StudentAdapter extends FirebaseRecyclerAdapter<modal_class_for_std_details,StudentAdapter.myviewholder> {

    Context scontext;
    public StudentAdapter(@NonNull FirebaseRecyclerOptions<modal_class_for_std_details> options, Context context) {
        super(options);
        this.scontext = context;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview_student,parent,false);
        return new myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull modal_class_for_std_details model) {
        holder.collage_id.setText(model.getStudent_ID());


        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(scontext,Std_details_Information.class);
                intent.putExtra("id",getRef(position).getKey());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                scontext.startActivity(intent);

            }
        });
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView collage_id;
        View v;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            collage_id = (TextView)itemView.findViewById(R.id.collage_id_card);
            v=itemView;


        }
    }

}
