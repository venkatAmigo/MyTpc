package com.vvitmdc.chats;
//import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
        EditText us,ps;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            us= findViewById(R.id.username);
            ps= findViewById(R.id.password);

        }
        public void signin(View view) {
            String user=(us.getText().toString()),pass=ps.getText().toString();

            if((user.compareToIgnoreCase("VVITTPO")==0)&&((user.compareToIgnoreCase("VVITTPO")==0)))
            {
                UserData.isStudent=false;
                Intent i=new Intent(this,TPODashboard.class);
                startActivity(i);

            }

            if(((user.compareToIgnoreCase("16bq1a05k9")==0)
                    ||(user.compareToIgnoreCase("16bq1a05k3")==0)
                    ||(user.compareToIgnoreCase("16bq1a05i3")==0)
                    ||(user.compareToIgnoreCase("17bq5a0505")==0))&&((pass.compareToIgnoreCase("16bq1a05k9")==0)
                    ||(pass.compareToIgnoreCase("16bq1a05k3")==0)
                    ||(pass.compareToIgnoreCase("16bq1a05i3")==0)
                    ||(pass.compareToIgnoreCase("17bq5a0505")==0)))
            {
                UserData.username=user;
                UserData.isStudent=true;
                Toast.makeText(this, "Successfully Logged In", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(this,StudentDashboard.class);
                startActivity(i);
            }
            else
            Toast.makeText(this, "Incorrect Username or password", Toast.LENGTH_SHORT).show();
        }
}
