package com.vvitmdc.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.concurrent.ConcurrentMap;

public class TPODashboard extends AppCompatActivity {

    CardView fCard,cCard,uCard,sCard,bSCard,sDCard,mCard,rCard,coCard;
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
        sDCard=findViewById(R.id.student_details_card);
        bSCard=findViewById(R.id.branch_stat_card_tpo);
        mCard=findViewById(R.id.messages);
        rCard=findViewById(R.id.reference);
        coCard=findViewById(R.id.compa);
        LinearLayout l=findViewById(R.id.dash_linear);
        UserData.username="vvittpo";
        Animation a= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
        a.setDuration(1000);
        l.startAnimation(a);
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
        sDCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TPODashboard.this,SearchStudentActivity.class);
                startActivity(i);
            }
        });
        mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TPODashboard.this,UserListActivity.class);
                startActivity(i);
            }
        });
        rCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TPODashboard.this,ReferenceMaterials.class);
                startActivity(i);
            }
        });
        coCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(TPODashboard.this, Company.class);
                startActivity(i);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tpo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.logout_tpo)
        {
            SavePreference.setLogin(getApplicationContext(),false,"tpo");
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,TPODashboard.class);
        startActivity(i);
    }
}
