package com.example.dduplacementadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Home extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    RecyclerView recyclerView;

    CompanyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = (RecyclerView)findViewById(R.id.home_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {
               switch(item.getItemId())
               {
                   case R.id.menu_home:
                       return  true;

                   case R.id.menu_profile:
                       startActivity(new Intent(getApplicationContext(),Profile.class));
                       overridePendingTransition(0,0);
                       return true;

                   case R.id.menu_add_company:
                       startActivity(new Intent(getApplicationContext(),Add.class));
                       overridePendingTransition(0,0);
                       return true;

                   case R.id.menu_std_details:
                       startActivity(new Intent(getApplicationContext(),Std_Details.class));
                       overridePendingTransition(0,0);
                       return true;

                   case R.id.menu_logout:
                       firebaseAuth.getInstance().signOut();

                       Intent intent = new Intent(Home.this, SignIn_Activity.class);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                       startActivity(intent);
                      overridePendingTransition(0,0);
                       return true;

               }
               return false;
           }
       });


        FirebaseRecyclerOptions<Company_modal_for_home> options =
                new FirebaseRecyclerOptions.Builder<Company_modal_for_home>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Companies"), Company_modal_for_home.class)
                        .build();

        adapter = new CompanyAdapter(options,getApplicationContext());
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


}