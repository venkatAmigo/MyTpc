package com.vvitmdc.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class UserListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static ArrayList<String> expertList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recyclerView=findViewById(R.id.user_recycle);
        expertList=new ArrayList<String>();
        getUsersList();
    }
    void getUsersList()
    {
        String url = "https://chat-82696.firebaseio.com/Users.json";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {

                try {
                    JSONObject j=new JSONObject(s);
                    ArrayList<String> list=new ArrayList<String>();
                    Iterator<String> iterator=j.keys();
                    while(iterator.hasNext())
                    {
                        list.add(iterator.next());
                    }
                    ExpertsAdapter expertsAdapter=new ExpertsAdapter(list,UserListActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(UserListActivity.this));
                    recyclerView.setAdapter(expertsAdapter);
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
        RequestQueue rQueue = Volley.newRequestQueue(UserListActivity.this);
        rQueue.add(request);
    }
}
