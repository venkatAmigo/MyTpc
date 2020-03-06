package com.vvitmdc.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TPODashboard extends AppCompatActivity {

    CardView fCard,cCard,uCard,sCard,bSCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpodashboard);
        setTitle("Dashboard");
        Toast.makeText(this, "TPO Dashboard", Toast.LENGTH_SHORT).show();
        fCard=findViewById(R.id.forum_post_card);
        cCard=findViewById(R.id.add_student_card);
       uCard=findViewById(R.id.upload_drive_card);
        sCard=findViewById(R.id.stat_card);
        bSCard=findViewById(R.id.branch_stat_card_tpo);
        fCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TPODashboard.this,ForumActivity.class);
                startActivity(i);
            }
        });
        cCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TPODashboard.this,AddStudentActivity.class);
                startActivity(i);
            }
        });
        uCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TPODashboard.this,UploadSelectionActivity.class);
                startActivity(i);
            }
        });
        sCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TPODashboard.this,StatistcsActivity.class);
                startActivity(i);
            }
        });
        bSCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TPODashboard.this,BranchWiseStatActivity.class);
                startActivity(i);
            }
        });
    }

    public void construction(View view) {
        /*Intent i=new Intent(this,Construction.class);
        startActivity(i);*/
    }
}
