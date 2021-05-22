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

public class CompanyAdapter extends FirebaseRecyclerAdapter<Company_modal_for_home,CompanyAdapter.myviewholder> {

    Context context;
    public CompanyAdapter(@NonNull FirebaseRecyclerOptions<Company_modal_for_home> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Company_modal_for_home model) {

        holder.tname.setText(model.getName());
        holder.trole.setText(model.getRole());
        holder.ttech.setText(model.getTech());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,Company_Details_Activity.class);
                intent.putExtra("CompanyId",getRef(position).getKey());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview_company,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView tname,trole,ttech;
        View v;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            tname = (TextView)itemView.findViewById(R.id.company_name_card);
            trole = (TextView)itemView.findViewById(R.id.role_card);
            ttech = (TextView)itemView.findViewById(R.id.tech_card);
            v=itemView;


        }
    }

}
