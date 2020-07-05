package com.vvitmdc.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vvitmdc.chats.model.Item;
import com.vvitmdc.chats.model.Result;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ProfileActivity extends AppCompatActivity {
    Item item;
    List<Result> list;
    CardView ssCard;
    SweetAlertDialog sl;
    TextView reg,name,branch,gen,cat,etest,rank,dob,aa,mo,fa,
            add,pf,pm,pp,email,ssc,inter,ug,cocu,selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ssCard=findViewById(R.id.student_profile_card);
        sl= new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        reg=findViewById(R.id.cc_regid);
        name=findViewById(R.id.cc_name);
        branch=findViewById(R.id.cc_branch);
        gen=findViewById(R.id.cc_gender);
        cat=findViewById(R.id.cc_cat);
        etest=findViewById(R.id.cc_etest);
        rank=findViewById(R.id.cc_rank);
        dob=findViewById(R.id.cc_dob);
        aa=findViewById(R.id.cc_aadhar);
        mo=findViewById(R.id.cc_mother);
        fa=findViewById(R.id.cc_father);
        add=findViewById(R.id.cc_address);
        pf=findViewById(R.id.cc_pfphone);
        pm=findViewById(R.id.cc_pmphone);
        pp=findViewById(R.id.cc_pphone);
        email=findViewById(R.id.cc_email);
        ssc=findViewById(R.id.cc_ssc);
        inter=findViewById(R.id.cc_inter);
        ug=findViewById(R.id.cc_ug);
        cocu=findViewById(R.id.cc_cocubes);
        selected=findViewById(R.id.cc_selected);
        ssCard.setVisibility(View.INVISIBLE);
        sl.setContentText("Searching in The Database..")
                .show();
        getData();
    }
    public void getData()
    {
        StringRequest request = new StringRequest("https://www.creators4u.com/student_api.php?num="
                + UserData.username
                , new Response.Listener<String>()
        {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                item = gson.fromJson(response, Item.class);
                list=item.getResults();
                if(!list.isEmpty()) {
                    ssCard.setVisibility(View.VISIBLE);
                    setData(list);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Log.i("INFO","Error");
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }

    private void setData(List<Result> list) {
        sl.dismissWithAnimation();
        reg.setText(list.get(0).getRegdno());
        name.setText(list.get(0).getName());
        branch.setText(list.get(0).getBranch());
        gen.setText(list.get(0).getGender());
        cat.setText(list.get(0).getCat());
        etest.setText(list.get(0).getEntranceTest());
        rank.setText(list.get(0).getRank());
        dob.setText(list.get(0).getDob());
        aa.setText(list.get(0).getAadhar());
        mo.setText(list.get(0).getMother());
        fa.setText(list.get(0).getFather());
        add.setText(list.get(0).getAddress());
        pf.setText(list.get(0).getPfather());
        pm.setText(list.get(0).getPmother());
        pp.setText(list.get(0).getPpersonal());
        email.setText(list.get(0).getEmail());
        ssc.setText(list.get(0).getSsc());
        inter.setText(list.get(0).getIpe());
        ug.setText(list.get(0).getUg());
        cocu.setText(list.get(0).getCocubesscore());
        String co=list.get(0).getC1()+"\n,"+ list.get(0).getC2()+"\n,"+
                list.get(0).getC3()+"\n,"+ list.get(0).getC4()+"\n,"+
                list.get(0).getC5();
        selected.setText(co);

    }

}
