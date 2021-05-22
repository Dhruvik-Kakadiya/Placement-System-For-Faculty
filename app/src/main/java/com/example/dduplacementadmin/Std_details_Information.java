package com.example.dduplacementadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Std_details_Information extends AppCompatActivity {
    DatabaseReference databaseReference;
    private ImageButton back;

    private TextView Merit_rank, Roll_No, Student_ID, Name, Gender, SSC_per, SSC_Board, HSC_per,
            HSC_Board, Diploma_CGPA, Sem1_SPI, Sem2_SPI, Sem3_SPI, Sem4_SPI, Sem5_SPI,
            Sem6_SPI, Sem7_SPI,Sem8_SPI, Avg_SPI, CPI, Category, Admissio_Quota,collage_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_std_details__information);

        collage_id = (TextView)findViewById(R.id.collage_id);

        Student_ID = (TextView)findViewById(R.id.s_collage_id);
        Merit_rank = (TextView)findViewById(R.id.s_Merit_rank);
        Roll_No = (TextView)findViewById(R.id.s_Roll_No);
        Name = (TextView)findViewById(R.id.s_Name);
        Gender = (TextView)findViewById(R.id.s_Gender);
        SSC_per = (TextView)findViewById(R.id.s_SSC_per);
        SSC_Board = (TextView)findViewById(R.id.s_SSC_Board);
        HSC_per = (TextView)findViewById(R.id.s_HSC_per);
        HSC_Board = (TextView)findViewById(R.id.s_HSC_Board);
        Diploma_CGPA = (TextView)findViewById(R.id.s_Diploma_CGPA);
        Sem1_SPI = (TextView)findViewById(R.id.s_Sem1_SPI);
        Sem2_SPI = (TextView)findViewById(R.id.s_Sem2_SPI);
        Sem3_SPI = (TextView)findViewById(R.id.s_Sem3_SPI);
        Sem4_SPI = (TextView)findViewById(R.id.s_Sem4_SPI);
        Sem5_SPI = (TextView)findViewById(R.id.s_Sem5_SPI);
        Sem6_SPI = (TextView)findViewById(R.id.s_Sem6_SPI);
        Sem7_SPI = (TextView)findViewById(R.id.s_Sem7_SPI);
        Sem8_SPI = (TextView)findViewById(R.id.s_Sem8_SPI);
        Avg_SPI = (TextView)findViewById(R.id.s_Avg_SPI);
        CPI = (TextView)findViewById(R.id.s_CPI);
        Category = (TextView)findViewById(R.id.s_Category);
        Admissio_Quota = (TextView)findViewById(R.id.s_Admission_Quota);
        back = (ImageButton)findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Std_Details.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


        databaseReference = FirebaseDatabase.getInstance().getReference().child("2021 batch");


        String id = getIntent().getStringExtra("id");


        databaseReference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists())
                {
                    String StudentID =(String) snapshot.child("Student_ID").getValue().toString();
                    String Merit_rank = (String)snapshot.child("Merit_rank").getValue().toString();
                    String Roll_No =(String) snapshot.child("Roll_No").getValue().toString();
                    String Name = (String)snapshot.child("Name").getValue().toString();
                    String Gender = (String)snapshot.child("Gender").getValue().toString();
                    String SSC_per = (String)snapshot.child("SSC_per").getValue().toString();
                    String SSC_Board = (String)snapshot.child("SSC_Board").getValue().toString();
                    String HSC_per = (String)snapshot.child("HSC_per").getValue().toString();
                    String HSC_Board = (String)snapshot.child("HSC_Board").getValue().toString();
                    String Diploma_CGPA =(String) snapshot.child("Diploma_CGPA").getValue().toString();
                    String Sem1_SPI = (String) snapshot.child("Sem1_SPI").getValue().toString();
                    String Sem2_SPI =(String) snapshot.child("Sem2_SPI").getValue().toString();
                    String Sem3_SPI = (String)snapshot.child("Sem3_SPI").getValue().toString();
                    String Sem4_SPI = (String)snapshot.child("Sem4_SPI").getValue().toString();
                    String Sem5_SPI =(String) snapshot.child("Sem5_SPI").getValue().toString();
                    String Sem6_SPI = (String)snapshot.child("Sem6_SPI").getValue().toString();
                    String Sem7_SPI =(String) snapshot.child("Sem7_SPI").getValue().toString();
                    String Sem8_SPI =(String) snapshot.child("Sem8_SPI").getValue().toString();
                    String Avg_SPI = (String)snapshot.child("Avg_SPI").getValue().toString();
                    String CPI =(String) snapshot.child("CPI").getValue().toString();
                    String Category = (String)snapshot.child("Category").getValue().toString();
                    String Admission_Quota = (String)snapshot.child("Admissio_Quota").getValue().toString();


                    collage_id.setText(StudentID);
                    Std_details_Information.this.Student_ID.setText(StudentID);
                    Std_details_Information.this.Merit_rank.setText(Merit_rank);
                    Std_details_Information.this.Roll_No.setText(Roll_No);
                    Std_details_Information.this.Name.setText(Name);
                    Std_details_Information.this.Gender.setText(Gender);
                    Std_details_Information.this.SSC_per.setText(SSC_per);
                    Std_details_Information.this.SSC_Board.setText(SSC_Board);
                    Std_details_Information.this.HSC_per.setText(HSC_per);
                    Std_details_Information.this.HSC_Board.setText(HSC_Board);
                    Std_details_Information.this.Diploma_CGPA.setText(Diploma_CGPA);
                    Std_details_Information.this.Sem1_SPI.setText(Sem1_SPI);
                    Std_details_Information.this.Sem2_SPI.setText(Sem2_SPI);
                    Std_details_Information.this.Sem3_SPI.setText(Sem3_SPI);
                    Std_details_Information.this.Sem4_SPI.setText(Sem4_SPI);
                    Std_details_Information.this.Sem5_SPI.setText(Sem5_SPI);
                    Std_details_Information.this.Sem6_SPI.setText(Sem6_SPI);
                    Std_details_Information.this.Sem7_SPI.setText(Sem7_SPI);
                    Std_details_Information.this.Sem8_SPI.setText(Sem8_SPI);
                    Std_details_Information.this.Avg_SPI.setText(Avg_SPI);
                    Std_details_Information.this.CPI.setText(CPI);
                    Std_details_Information.this.Category.setText(Category);
                    Std_details_Information.this.Admissio_Quota.setText(Admission_Quota);
                }
                else
                {
                    Toast.makeText(Std_details_Information.this, "Student Details Not Available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Std_details_Information.this, "Something Went Wrong Server Error", Toast.LENGTH_SHORT).show();

            }
        });



    }
}