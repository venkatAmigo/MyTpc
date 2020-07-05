package com.vvitmdc.chats;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vvitmdc.chats.adapter.CompaniesAdapter;

import java.util.List;

public class Company extends AppCompatActivity {
        RecyclerView rv;
        CompaniesAdapter adapter;
        Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        setTitle("Company Statistics");
        rv=findViewById(R.id.rvco);
        dialog=new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.choose_dialog);
        dialog.show();
        StringRequest request = new StringRequest("https://www.creators4u.com/api.php ", new Response.Listener<String>()
        {

            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Item item = gson.fromJson(response, Item.class);
                List<Result> list = item.getResults();
                adapter = new CompaniesAdapter( getApplicationContext(),list);
                rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                rv.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                rv.setItemAnimator(new DefaultItemAnimator());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                dialog.dismiss();
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(Company.this);
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
                Log.i("INFO","Error");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

}
