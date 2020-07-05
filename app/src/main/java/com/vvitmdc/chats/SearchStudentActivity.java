package com.vvitmdc.chats;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.vvitmdc.chats.model.Item;
import com.vvitmdc.chats.model.Result;

import java.util.List;
import java.util.Objects;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class SearchStudentActivity extends AppCompatActivity {
Item item;
    List<Result> list;
    Button getButton;
    TextInputEditText regdEdit;
    String regid="";
    CardView ssCard;
    SweetAlertDialog sl;
    TextView reg,name,branch,gen,cat,etest,rank,dob,aa,mo,fa,
    add,pf,pm,pp,email,ssc,inter,ug,cocu,selected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_student);
        getButton=findViewById(R.id.get_button);
        ssCard=findViewById(R.id.student_search_card);
        regdEdit=findViewById(R.id.regid);
        sl= new SweetAlertDialog(this,SweetAlertDialog.PROGRESS_TYPE);
        reg=findViewById(R.id.c_regid);
        name=findViewById(R.id.c_name);
        branch=findViewById(R.id.c_branch);
        gen=findViewById(R.id.c_gender);
        cat=findViewById(R.id.c_cat);
        etest=findViewById(R.id.c_etest);
        rank=findViewById(R.id.c_rank);
        dob=findViewById(R.id.c_dob);
        aa=findViewById(R.id.c_aadhar);
        mo=findViewById(R.id.c_mother);
        fa=findViewById(R.id.c_father);
        add=findViewById(R.id.c_address);
        pf=findViewById(R.id.c_pfphone);
        pm=findViewById(R.id.c_pmphone);
        pp=findViewById(R.id.c_pphone);
        email=findViewById(R.id.c_email);
       ssc=findViewById(R.id.c_ssc);
        inter=findViewById(R.id.c_inter);
        ug=findViewById(R.id.c_ug);
        cocu=findViewById(R.id.c_cocubes);
        selected=findViewById(R.id.c_selected);
        ssCard.setVisibility(View.INVISIBLE);
        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(regdEdit.getText().toString().isEmpty())
                    regdEdit.setError("Please Enter RegId");
                else {
                    sl.setContentText("Searching in The Database..")
                            .show();
                    getData();
                }
            }
        });
    }
    public void getData()
    {
        StringRequest request = new StringRequest("https://www.creators4u.com/student_api.php?num="
                + regdEdit.getText().toString()
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
        String co=list.get(0).getC1()+"\n"+ list.get(0).getC2()+"\n"+
        list.get(0).getC3()+"\n"+ list.get(0).getC4()+"\n"+
        list.get(0).getC5();
        selected.setText(co);

    }

}
