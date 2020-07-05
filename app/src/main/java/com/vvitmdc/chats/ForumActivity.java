package com.vvitmdc.chats;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vvitmdc.chats.adapter.ForumAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ForumActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ForumAdapter forumAdapter;
    FloatingActionButton createPost;
    private String location="";
    SweetAlertDialog sl;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);
        recyclerView=findViewById(R.id.forum_recycle);
        createPost=findViewById(R.id.create_post);
        sl= new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        //Comments button on post
        setTitle("JOBS");
      /*  names=new ArrayList<String>();
        crop=new ArrayList<String>();
        postdata=new ArrayList<String>();
        ids=new ArrayList<String>();*/

      if(UserData.isStudent) {
          createPost.hide();
      }
        location=getIntent().getStringExtra("loc");
       // UserData.userLocation=location;
        createPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ForumActivity.this,ForumPostActivity.class);
               // i.putExtra("loc",UserData.userLocation);
                startActivity(i);
            }
        });
        getAllPosts();
        //forumAdapter=new ForumAdapter(this,postdata,names,crop,ids);
        //  recyclerView.setLayoutManager(new LinearLayoutManager(ForumActivity.this));
        // recyclerView.setAdapter(forumAdapter);
    }
    private void getAllPosts()
    {
        String url = "https://chat-82696.firebaseio.com/Posts.json";
        /*sl.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        sl.setContentText("Loading..");
        sl.setCancelable(false);
        sl.setContentView(R.layout.choose_dialog);
        sl.show();*/
        dialog=new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.choose_dialog);
        dialog.show();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                dialog.dismiss();
                try {
                    JSONObject j=new JSONObject(s);
                    ArrayList<String> ids= new ArrayList<>();
                    ArrayList<String> cnames= new ArrayList<>();
                    ArrayList<String> roles= new ArrayList<>();
                    ArrayList<String> dates= new ArrayList<>();
                    ArrayList<String> quals= new ArrayList<>();
                    ArrayList<String> packs= new ArrayList<>();
                    ArrayList<String> venues= new ArrayList<>();
                    ArrayList<String> a= new ArrayList<>();
                    //int i=0;

                    Iterator<String> it=j.keys();
                    while(it.hasNext())
                    {
                        String ss=it.next();
                        a.add(ss);
                    }
                    // while(j.keys().hasNext())
                    for(int i=0;i<a.size();i++)
                    {
                        String key=j.keys().next();
                        cnames.add(j.getJSONObject(a.get(i)).getString("company"));
                       ids.add(j.getJSONObject(a.get(i)).getString("id"));
                        roles.add(j.getJSONObject(a.get(i)).getString("jobrole"));
                        dates.add(j.getJSONObject(a.get(i)).getString("interview_date"));
                       quals.add(j.getJSONObject(a.get(i)).getString("qualification"));
                        packs.add(j.getJSONObject(a.get(i)).getString("packages"));
                        venues.add(j.getJSONObject(a.get(i)).getString("venue"));
                    }
                    /*for(int i=0;i<times.size();i++)
                    {
                        String temp;
                        for(int k=0;k<times.size()-i-1;k++)
                        {
                            if(Long.parseLong(times.get(k))<Long.parseLong(times.get(k+1)))
                            {
                                temp=times.get(k);
                                times.set(k,times.get(k+1));
                                times.set(k+1,temp);
                                temp=crop.get(k);
                                crop.set(k,crop.get(k+1));
                                crop.set(k+1,temp);
                                temp=postdata.get(k);
                                postdata.set(k,postdata.get(k+1));
                                postdata.set(k+1,temp);
                                temp=names.get(k);
                                names.set(k,names.get(k+1));
                                names.set(k+1,temp);
                                temp=ids.get(k);
                                ids.set(k,ids.get(k+1));
                                ids.set(k+1,temp);
                                temp=images.get(k);
                                images.set(k,images.get(k+1));
                                images.set(k+1,temp);
                                temp=locations.get(k);
                                locations.set(k,locations.get(k+1));
                                locations.set(k+1,temp);
                                temp=likes.get(k);
                                likes.set(k,likes.get(k+1));
                                likes.set(k+1,temp);

                            }
                        }
                    }*/
                    ForumAdapter forumAdapter;
                    forumAdapter = new ForumAdapter(ForumActivity.this,ids,cnames,roles,dates,quals,
                            packs,venues);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ForumActivity.this));
                    recyclerView.setAdapter(forumAdapter);
                    sl.dismiss();
                    //}
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                dialog.dismiss();
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(ForumActivity.this);
                alertDialog.setMessage("Please Check Internet Connection..")
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog1=alertDialog.create();
                alertDialog1.setTitle("Information");
                alertDialog1.show();
                System.out.println("" + volleyError);
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(ForumActivity.this);
        rQueue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent i=new Intent(this,DashBoardActivity.class);
        // startActivity(i);
    }
}
