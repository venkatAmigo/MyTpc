package com.vvitmdc.chats;
//import android.support.v7.app.AppCompatActivity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vvitmdc.chats.model.Item;

public class MainActivity extends AppCompatActivity {
        EditText us,ps;
        String url="";
        String user="",pass="";
        Dialog dialog;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            us= findViewById(R.id.username);
            ps= findViewById(R.id.password);

        }
        public void signin(View view) {
            user=(us.getText().toString());
            pass=ps.getText().toString();
                getData();

        }
    public void getData()
    {
        dialog=new Dialog(this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.choose_dialog);
        dialog.show();
        StringRequest request = new StringRequest(
                "https://www.creators4u.com/login.php?pass="+pass+"&"+"user="+user
                , new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
               if(response.equals("Success"))
               {
                   UserData.username=user;

                   SavePreference.setLogin(getApplicationContext(),true,"student");
                   Intent i=new Intent(MainActivity.this,StudentDashboard.class);
                   startActivity(i);
               }
               else
               {
                   if((user.compareToIgnoreCase("VVITTPO")==0)&&((user.compareToIgnoreCase("VVITTPO")==0)))
                   {
                       UserData.isStudent=false;
                       SavePreference.setLogin(getApplicationContext(),true,"tpo");
                       Intent i=new Intent(MainActivity.this,TPODashboard.class);
                       startActivity(i);
                   }
                   else if((user.compareToIgnoreCase("VVITSTAFF")==0)&&((user.compareToIgnoreCase("VVITSTAFF")==0)))
                   {
                       UserData.isStudent=false;
                       SavePreference.setLogin(getApplicationContext(),true,"staff");
                       Intent i=new Intent(MainActivity.this,TPODashboard.class);
                       startActivity(i);
                   }
                   else{
                       Toast.makeText(MainActivity.this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                   }

               }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                //Toast.makeText(MainActivity.this, "Error "+error.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
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
