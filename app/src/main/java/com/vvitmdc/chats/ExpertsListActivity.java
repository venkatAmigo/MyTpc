package com.vvitmdc.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vvitmdc.chats.adapter.ExpertsAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ExpertsListActivity extends AppCompatActivity {

    RecyclerView recyclerView;


    static ArrayList<String> expertList;
    //Button forumButton;
    SweetAlertDialog sl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experts_list);
        setTitle("Ask Tpo");
       /* forumButton=findViewById(R.id.forum_button_experts);
        forumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ExpertsListActivity.this,ForumActivity.class);
                startActivity(i);
            }
        });*/
       sl=new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        recyclerView=findViewById(R.id.experts_recycle);
        expertList=new ArrayList<String>();
        getExpertList();


    }
    void getExpertList()
    {
        sl.setContentText("Loading..");
        sl.setCancelable(false);
        String url = "https://chat-82696.firebaseio.com/experts.json";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {

                try {
                    JSONArray j=new JSONArray(s);
                    ArrayList<String> list=new ArrayList<String>();
                    for(int i=1;i<j.length();i++) {
                       if(j.getJSONObject(i).getString("password")==null)
                            continue;
                       //expertList.add(j.getJSONObject(i).getString("password"));
                        list.add(j.getJSONObject(i).getString("password").toString());
                      // Toast.makeText(ExpertsListActivity.this, ""+expertList.get(0), Toast.LENGTH_SHORT).show();
                    }
                    ExpertsAdapter expertsAdapter=new ExpertsAdapter(list,ExpertsListActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(ExpertsListActivity.this));
                    recyclerView.setAdapter(expertsAdapter);
                    sl.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(ExpertsListActivity.this);
        rQueue.add(request);
    }
}
