package com.vvitmdc.chats;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.vvitmdc.chats.model.Post;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ForumPostActivity extends AppCompatActivity implements  ActivityCompat.OnRequestPermissionsResultCallback {

    Button sendButton;
    TextView cnameT,jobT,dateT,qualT,packT,venT;
    String cname,jobrole,date,qual,pack,venue,id;
    DatabaseReference reference;
    String time="";
    StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Job Posts");
        setContentView(R.layout.activity_forum_post);
        sendButton=findViewById(R.id.submit_post_btton);
        cnameT=findViewById(R.id.cname);
        jobT=findViewById(R.id.job_role);
        dateT=findViewById(R.id.interview_date);
        qualT=findViewById(R.id.qualification);
        packT=findViewById(R.id.package_tv);
        venT=findViewById(R.id.location);
        mStorageRef= FirebaseStorage.getInstance().getReference("posts");
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPostDetails();
             /*   if(crop.equals(""))
                {
                    cropText.setError(" మీ పంట పేరును నమోదు చేయండి");
                }
                else if(postData.equals(""))
                {
                    postText.setError("మీ పంటల సమస్యను వివరంగా వ్రాయండి");
                }
                else {*/
                    sendButton.setEnabled(false);
                    generateId();
                    sendPostDetails();
               // }
            }
        });
        /*image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooser();
            }
        });*/

    }

    private void generateId()
    {
        Date c= Calendar.getInstance().getTime();
        SimpleDateFormat df=new SimpleDateFormat("ddMMyyyy_hhmmss");
        id=df.format(c);
    }

    private void sendPostDetails() {
        reference=FirebaseDatabase.getInstance().getReference("Posts");
        Post post;
            post = new Post(id,cname,jobrole,date,qual,pack,venue);
            reference.child(id).setValue(post);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Intent i=new Intent(ForumPostActivity.this,ForumActivity.class);
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
                Toast.makeText(ForumPostActivity.this, ""+databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void getPostDetails() {
        cname=cnameT.getText().toString();
        jobrole=jobT.getText().toString();
        date=dateT.getText().toString();
        qual=qualT.getText().toString();
        pack=packT.getText().toString();
         venue=venT.getText().toString();
        time=""+new Time(System.currentTimeMillis()).getTime();
    }
}
