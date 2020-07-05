package com.vvitmdc.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Materials extends AppCompatActivity {
    CardView ap,qa,re,co;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Reference Materials");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materials);
        ap=findViewById(R.id.Aptitude);
        qa=findViewById(R.id.Quant);
        re=findViewById(R.id.Reasoning);
        co=findViewById(R.id.Company);
        ap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Materials.this,ViewUploads.class);
                i.putExtra("type","Aptitude");
               // setTitle("aptitude");
                startActivity(i);
            }
        });
        qa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Materials.this,ViewUploads.class);
                i.putExtra("type","Quant");
               // setTitle("Quant");
                startActivity(i);
            }
        });
        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Materials.this,ViewUploads.class);
                i.putExtra("type","Reasoning");
                startActivity(i);
            }
        });
        co.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Materials.this,ViewUploads.class);
                i.putExtra("type","Company Papers or Patterns");
               // setTitle("Company Papers/Patterns");
                startActivity(i);
            }
        });
    }
}
