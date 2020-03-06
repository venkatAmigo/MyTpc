package com.vvitmdc.chats;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vvitmdc.chats.model.Upload;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UploadSelectionActivity extends AppCompatActivity {

    TextInputEditText cname,pack,nosel;
    Button post;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String id="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_selection);
        cname=findViewById(R.id.company_name);
        pack=findViewById(R.id.package_tv);
        nosel=findViewById(R.id.no_selection_tv);
        post=findViewById(R.id.submit_btton);
        setTitle("Upload Selection Details");
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadDetails();
            }
        });
    }

    private void uploadDetails()
    {
        Upload upload=new Upload(cname.getText().toString(),
                pack.getText().toString(),nosel.getText().toString());
        databaseReference=FirebaseDatabase.getInstance().getReference("statistics");
        generateId();
        databaseReference.child(id).setValue(upload);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Intent i=new Intent(UploadSelectionActivity.this,StatistcsActivity.class);
                startActivity(i);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void generateId()
    {
        Date c= Calendar.getInstance().getTime();
        SimpleDateFormat df=new SimpleDateFormat("ddMMyyyy_hhmmss");
        id=df.format(c);
    }
}
