package com.vvitmdc.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vvitmdc.chats.adapter.ForumAdapter;
import com.vvitmdc.chats.adapter.StatAdapter;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class StatistcsActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SweetAlertDialog sl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Statistics");
        setContentView(R.layout.activity_statistcs);
        recyclerView=findViewById(R.id.stat_recycle);
        sl= new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        getAllPosts();
    }
    private void getAllPosts()
    {
        String url = "https://chat-82696.firebaseio.com/statistics.json";
        sl.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
        sl.setContentText("Loading..");
        sl.setCancelable(false);
        sl.show();
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {

                try {
                    JSONObject j=new JSONObject(s);
                    ArrayList<String> cnames= new ArrayList<>();
                    ArrayList<String> packs= new ArrayList<>();
                    ArrayList<String> noofsel= new ArrayList<>();
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
                        noofsel.add(j.getJSONObject(a.get(i)).getString("number"));
                        packs.add(j.getJSONObject(a.get(i)).getString("packages"));
                    }
                    StatAdapter statAdapter;
                    statAdapter = new StatAdapter(StatistcsActivity.this,cnames,
                            packs,noofsel);
                    recyclerView.setLayoutManager(new LinearLayoutManager(StatistcsActivity.this));
                    recyclerView.setAdapter(statAdapter);
                    sl.dismiss();
                    //}
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });
        RequestQueue rQueue = Volley.newRequestQueue(StatistcsActivity.this);
        rQueue.add(request);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
