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
import android.widget.Toast;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

public class StudentDashboard extends AppCompatActivity
        implements View.OnClickListener ,
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{
    SliderLayout sl;
    CardView fCard,cCard,sCard,bSCard,rCard,cosCard,dashCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);
        setTitle("Home");
        UserData.isStudent=true;
        fCard=findViewById(R.id.forum_card);
        cCard=findViewById(R.id.chat_card);
        sCard=findViewById(R.id.student_stat_card);
        bSCard=findViewById(R.id.branch_stat_card);
        rCard=findViewById(R.id.Refe);
        cosCard=findViewById(R.id.compas);
        dashCard=findViewById(R.id.dash_card);
        Animation a= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
        a.setDuration(1000);
        dashCard.startAnimation(a);
        fCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(StudentDashboard.this,ForumActivity.class);
                startActivity(i);
            }
        });
        cCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(StudentDashboard.this,ExpertsListActivity.class);
                startActivity(i);
            }
        });
        sCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(StudentDashboard.this,StatistcsActivity.class);
                startActivity(i);
            }
        });
        bSCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(StudentDashboard.this,BranchWiseStatActivity.class);
                startActivity(i);
            }
        });
        rCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(StudentDashboard.this,Materials.class);
                startActivity(i);
            }
        });
        cosCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(StudentDashboard.this,Company.class);
                startActivity(i);
            }
        });
        sl=findViewById(R.id.slider_layout);
        HashMap<String,Integer> h=new HashMap<>();
        h.put("img1",R.drawable.tpo_one);
        h.put("img2",R.drawable.tpo_two);
        h.put("img3",R.drawable.tpogroup);
        h.put("img4",R.drawable.tpc_three);
        h.put("img5",R.drawable.tpc_eight);
        h.put("img6",R.drawable.tpc_five);
        h.put("img7",R.drawable.tpc_four);
        h.put("img8",R.drawable.tpc_six);
        h.put("img9",R.drawable.tpc_seven);
        for(String names:h.keySet())
        {
            TextSliderView tv=new TextSliderView(StudentDashboard.this);
            tv.image(h.get(names))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);
            tv.bundle(new Bundle());
            tv.getBundle().putString("extra","IMAGE");
            sl.addSlider(tv);
        }
        sl.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        sl.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sl.setDuration(3000);
        sl.addOnPageChangeListener(this);
    }
    @Override
    protected void onStop() {
        sl.startAutoCycle();
        super.onStop();
    }
    @Override
    public void onClick(View v) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.profile)
        {
            Intent intent=new Intent(this,ProfileActivity.class);
            startActivity(intent);

        }
        else
        {
            SavePreference.setLogin(getApplicationContext(),false,"student");
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i=new Intent(this,StudentDashboard.class);
        startActivity(i);
    }
}
