package com.example.dduplacementadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.opencsv.CSVWriter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.*;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Regi_std_details extends AppCompatActivity {

    RecyclerView recyclerView;
    RegisterdAdapter adapter;
    private ImageButton back;
    private Button btn_download_list;


    List<String> collageID_list;
    List<String> emailID_list;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regi_std_details);
        String CompanyId = getIntent().getStringExtra("id");

        recyclerView = (RecyclerView) findViewById(R.id.registerd_std_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        back = (ImageButton)findViewById(R.id.btn_back);
        btn_download_list = (Button)findViewById(R.id.download_list);


        collageID_list = new ArrayList<>();
        emailID_list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Companies").child(CompanyId).child("Registerd Student");
        btn_download_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.exists())
                        {
                            collageID_list.clear();
                            emailID_list.clear();
                            for(DataSnapshot dss:snapshot.getChildren())
                            {
                                String collage = dss.child("CollageID").getValue().toString();
                                collageID_list.add(collage);

                                String email = dss.child("Email").getValue(String.class);
                                emailID_list.add(email);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Companies").child(CompanyId);
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                            fileName =snapshot.child("name").getValue(String.class);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


               Workbook wb = new HSSFWorkbook();
               Cell cell = null;
               CellStyle cellStyle = wb.createCellStyle();
               cellStyle.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);

                Sheet sheet = null;
                sheet =wb.createSheet("List");

                for(int  i=0; i<collageID_list.size(); i++){

                    Row row = sheet.createRow(i);
                    row.createCell(0).setCellValue(collageID_list.get(i));
                    row.createCell(1).setCellValue(emailID_list.get(i));
                }

                sheet.setColumnWidth(0,7000);
                sheet.setColumnWidth(1,10000);



              /*  ValueEventListener valueEventListener =firebaseDatabase.getInstance().getReference().child("Companies")
                        .child(CompanyId).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                fileName = snapshot.child("name").getValue(String.class);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                fileName = fileName +".xls";*/



                File file =new File(getExternalFilesDir(null),fileName+".xls");
                FileOutputStream outputStream = null;

                try {
                    outputStream = new FileOutputStream(file);
                    wb.write(outputStream);
                    Toast.makeText(getApplicationContext(),"List is Downloaded...",Toast.LENGTH_SHORT).show();


                    StringBuilder stringBuilder = new StringBuilder();
                    for(int i=0; i<collageID_list.size();i++)
                    {
                        stringBuilder.append(emailID_list.get(i)+ ", ");
                    }
                    Toast.makeText(getApplicationContext(),stringBuilder.toString(),Toast.LENGTH_SHORT).show();



                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Something Went Wrong..",Toast.LENGTH_SHORT).show();
                    try {
                        outputStream.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

           /* String exStorageState = Environment.getExternalStorageState();

            if(exStorageState.equals(Environment.MEDIA_MOUNTED));
                {

                    Workbook workbook = new XSSFWorkbook();
                    Sheet sheet = workbook.createSheet("List");

                    Row row = sheet.createRow(1);
                    row.createCell(0).setCellValue("Collage ID");
                    row.createCell(1).setCellValue("Email ID");

                    String fileName = "List.xlsx";
                    String extStorageDirectory = Environment.getExternalStorageDirectory()
                            .toString();
                   *//* File folder = new File(extStorageDirectory, "DDUPlacement");
                    folder.mkdir();
                    File file = new File(folder, fileName);*//*

                    File myfile = new File(getExternalFilesDir("DDUPlacement"),"List.xls");

                    try {
                        myfile.createNewFile();// creating the file inside the folder
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Something Went Wrong..",Toast.LENGTH_SHORT).show();
                    }
                    // Toast.makeText(getApplicationContext(),"List is Downloaded...",Toast.LENGTH_SHORT).show();


                    try {
                        FileOutputStream fileOut = new FileOutputStream(myfile); //Opening the file
                        workbook.write(fileOut); //Writing all your row column inside the file
                        fileOut.close(); //closing the file and done
                        Toast.makeText(getApplicationContext(),"List open Downloaded...",Toast.LENGTH_SHORT).show();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }



                }

        */


            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Company_Details_Activity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        FirebaseRecyclerOptions<modal_class_for_registerd_std> options =
                new FirebaseRecyclerOptions.Builder<modal_class_for_registerd_std>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Companies").child(CompanyId).child("Registerd Student"), modal_class_for_registerd_std.class)
                        .build();

        adapter = new RegisterdAdapter(options,getApplicationContext());
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