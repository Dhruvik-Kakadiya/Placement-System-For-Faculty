package com.example.dduplacementadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Add extends AppCompatActivity {


    EditText c_name, c_tech, c_role, c_package, c_bond,c_lastDate, c_comeDate,c_cgpi;
    TextView c_type;
    Button btn_add;
    private Spinner type_spinner;
    int maxid = 1;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        c_name = (EditText)findViewById(R.id.edit_company_name);
        c_tech = (EditText)findViewById(R.id.edit_tech);
        c_role = (EditText)findViewById(R.id.edit_role);
        c_package = (EditText)findViewById(R.id.edit_package);
        c_bond = (EditText)findViewById(R.id.edit_bond);
        c_lastDate = (EditText)findViewById(R.id.edit_last_date);
        c_comeDate = (EditText)findViewById(R.id.edit_ComeToCampus);
        c_cgpi = (EditText)findViewById(R.id.edit_cgpi);



        c_type = (TextView)findViewById(R.id.type_select);
        btn_add = (Button) findViewById(R.id.btn_add);
        type_spinner = findViewById(R.id.type_spinner);



        List<String> categories =new ArrayList<>();
        //  categories.add(0," Not Selected");
        categories.add("Only Training Company");
        categories.add("Only Placement Company");
        categories.add("Training & Placement Company");
        categories.add("Performance Base Company");

        ArrayAdapter<String> typeAdapter;
        typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,categories);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_spinner.setAdapter(typeAdapter);
        type_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Not Selected"))
                {
                }
                else
                {
                    c_type.setText(parent.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_add_company);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.menu_home:
                        startActivity(new Intent(getApplicationContext(),Home.class));
                        overridePendingTransition(0,0);
                        return  true;

                    case R.id.menu_profile:
                        startActivity(new Intent(getApplicationContext(),Profile.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_add_company:
                        return true;

                    case R.id.menu_std_details:
                        startActivity(new Intent(getApplicationContext(),Std_Details.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.menu_logout:
                        firebaseAuth.getInstance().signOut();

                        Intent intent = new Intent(Add.this, SignIn_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Companies");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    maxid = (int) snapshot.getChildrenCount();
                }
                else
                {
                    maxid = (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = c_name.getText().toString().trim();
                String type = c_type.getText().toString().trim();
                String tech = c_tech.getText().toString().trim();
                String pac = c_package.getText().toString().trim();
                String role = c_role.getText().toString().trim();
                String bond = c_bond.getText().toString().trim();
                String last = c_lastDate.getText().toString().trim();
                String come = c_comeDate.getText().toString().trim();
                String cgpi = c_cgpi.getText().toString().trim();


                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(Add.this, "Please Enter Company Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(type)) {
                    Toast.makeText(Add.this, "Please Enter Company Type", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(tech)) {
                    Toast.makeText(Add.this, "Please Enter Technology", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pac)) {
                    Toast.makeText(Add.this, "Please Enter Package", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(role)) {
                    Toast.makeText(Add.this, "Please Enter Role", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(bond)) {
                    Toast.makeText(Add.this, "Please Enter Bond", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cgpi)) {
                    Toast.makeText(Add.this, "Please Enter CGPI =<", Toast.LENGTH_SHORT).show();
                    return;
                }


                Company_Helper company_info = new Company_Helper(name,type,tech,role,pac,bond,last,come,cgpi);


                databaseReference.child(String.valueOf(maxid+2)).setValue(company_info).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(Add.this, "Company Details Added Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Home.class));
                        }
                        else
                        {
                            Toast.makeText(Add.this, "Company Details Added Not Successfully, Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                });





            }
        });




    }
}