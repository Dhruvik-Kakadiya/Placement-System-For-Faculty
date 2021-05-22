package com.example.dduplacementadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Company_Details_Activity extends AppCompatActivity {

    private TextView lastDate, comeDate, company_name, type, role, pac, bond, company_name_d, tech,cgpi;
    private Button btn_get_list;
    private ImageButton back;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__details_);

        lastDate = (TextView)findViewById(R.id.d_last_date);
        comeDate = (TextView)findViewById(R.id.d_come_date);
        company_name = (TextView)findViewById(R.id.d_company_name);
        company_name_d = (TextView)findViewById(R.id.d_company_name_d);
        type = (TextView)findViewById(R.id.d_type);
        role = (TextView)findViewById(R.id.d_role);
        pac = (TextView)findViewById(R.id.d_package);
        bond = (TextView)findViewById(R.id.d_bond);
        tech = (TextView)findViewById(R.id.d_tech);
        cgpi = (TextView)findViewById(R.id.d_cgpi);
        btn_get_list = (Button)findViewById(R.id.btn_get_list);
        back = (ImageButton) findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        String CompanyId = getIntent().getStringExtra("CompanyId");

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Companies");
        databaseReference.child(CompanyId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String companyName = snapshot.child("name").getValue().toString();
                    String lastDate = snapshot.child("lastDate").getValue().toString();
                    String comeDate = snapshot.child("comeDate").getValue().toString();
                    String Type = snapshot.child("type").getValue().toString();
                    String Role = snapshot.child("role").getValue().toString();
                    String Pac = snapshot.child("company_package").getValue().toString();
                    String Bond = snapshot.child("bond").getValue().toString();
                    String Tech = snapshot.child("tech").getValue().toString();
                    String cgpi_above= snapshot.child("cgpi_above").getValue().toString();

                    company_name.setText(companyName);
                    company_name_d.setText(companyName);
                    Company_Details_Activity.this.lastDate.setText(lastDate);
                    Company_Details_Activity.this.comeDate.setText(comeDate);
                    type.setText(Type);
                    role.setText(Role);
                    pac.setText(Pac);
                    bond.setText(Bond);
                    tech.setText(Tech);
                    cgpi.setText(cgpi_above);

                }
                else
                {
                    Toast.makeText(Company_Details_Activity.this, "Company Details Not Available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Company_Details_Activity.this, "Something Went Wrong Server Error", Toast.LENGTH_SHORT).show();

            }
        });


        btn_get_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Company_Details_Activity.this,Regi_std_details.class);
                intent.putExtra("id",CompanyId);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        });


    }
}