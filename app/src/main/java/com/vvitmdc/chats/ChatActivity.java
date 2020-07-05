package com.vvitmdc.chats;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    LinearLayout layout;
    ImageView sendButton,uploadButton;
    EditText messageArea;
    ScrollView scrollView;
    DatabaseReference reference1, reference2;
    String KEY="AAAApXLLB1Y:APA91bH73e1KgEUOibtykwFz-dF5kHv8gLVA45cUx3Ed9ZBgWlCrfa2tzyKb9gzpmgNiEasagF82uGvmDoF3qKbZ-9DpF37YV18xR6dYbR_CUc_0JVQHFQEjP3UpNBWyRCA8Q3T9Ud_6";
    public static final int PICK_IMAGE_REQUEST=1;
    Uri mImageUri;
    StorageReference mStorageRef;
    DatabaseReference mDataRef;
    static Boolean isImage=false;
    String downUrl=" ";
    RequestQueue rq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        layout = findViewById(R.id.layout1);
        sendButton = findViewById(R.id.sendButton);
        uploadButton=findViewById(R.id.imageUpload);
        messageArea = findViewById(R.id.messageArea);
        scrollView = findViewById(R.id.scrollView);
        rq= Volley.newRequestQueue(this);
        FirebaseMessaging.getInstance().subscribeToTopic(UserData.username);
        mStorageRef= FirebaseStorage.getInstance().getReference("uploads");
        mDataRef=FirebaseDatabase.getInstance().getReference("uploads");
        String url="https://chat-82696.firebaseio.com/messages/" + UserData.username + "_" + UserData.chatWith;
        String url2="https://chat-82696.firebaseio.com/messages/" + UserData.chatWith + "_" + UserData.username;
        reference1 = FirebaseDatabase.getInstance().getReferenceFromUrl(url);
        reference2 = FirebaseDatabase.getInstance().getReferenceFromUrl(url2);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();
                messageArea.setText("");
                if(!messageText.equals("")){
                    sendNotification(messageText);
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", UserData.username);
                    if(mImageUri!=null)
                    {
                        isImage=true;
                        final StorageReference fileref=mStorageRef.child(System.currentTimeMillis()
                        +"."+getExtension(mImageUri));
                        fileref.putFile(mImageUri)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Toast.makeText(ChatActivity.this, "Successfull", Toast.LENGTH_SHORT).show();
                                   fileref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                       @Override
                                       public void onSuccess(Uri uri) {
                                           downUrl=uri.toString();
                                           //String uid=mDataRef.push().getKey();
                                           //mDataRef.child(uid).setValue(uri.toString());
                                       }
                                   });

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ChatActivity.this, "Failed to upload", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                    map.put("image",downUrl);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                }
            }
        });
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String ,String> map = (Map<String,String>)dataSnapshot.getValue();
                String message = map.get("message").toString();
                String userName = map.get("user").toString();
                String image = map.get("image").toString();
                //Toast.makeText(ChatActivity.this, "Event Triggered "+userName, Toast.LENGTH_SHORT).show();

                if(userName.equals(UserData.username)){
                    addMessageBox("You:-" , message, 1);
                    if(!image.equals(" "))
                        addImageBox(image,1);
                }
                else{
                    addMessageBox(UserData.chatWith , message, 2);
                    if(!image.equals(" "))
                        addImageBox(image,2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void sendNotification(String message) {
        JSONObject main=new JSONObject();
        try {
            main.put("to","/topics/"+UserData.chatWith);
            JSONObject notifi=new JSONObject();
            notifi.put("title","Message From "+UserData.chatWith);
            notifi.put("body",message);
            JSONObject extra=new JSONObject();
            extra.put("sender",UserData.username);
            extra.put("receiver",UserData.chatWith);
            main.put("notification",notifi);
            main.put("data",extra);

            String url="https://fcm.googleapis.com/fcm/send";
            JsonObjectRequest request=new JsonObjectRequest(Request.Method.POST, url,
                    main, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    Toast.makeText(ChatActivity.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(ChatActivity.this,"error"+error, Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                   Map<String,String> header=new HashMap<>();
                   header.put("content-type","application/json");
                   header.put("authorization","key="+KEY);
                    return header;
                }
            };
            rq.add(request);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    private String getExtension(Uri uri)
    {
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void openFileChooser() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK
        &&data!=null&&data.getData()!=null)
        {
            mImageUri=data.getData();
        }
    }
    private void addImageBox(String image, int i)
    {
        ImageView imageView=new ImageView(ChatActivity.this);
        Picasso.with(ChatActivity.this).load(image).into(imageView);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 10);
        if(i == 1) {
            lp.gravity= Gravity.RIGHT;
            imageView.setLayoutParams(lp);
        }
        else{
            lp.gravity= Gravity.LEFT;
            imageView.setLayoutParams(lp);
        }
        layout.addView(imageView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }

    public void addMessageBox(String userid,String message, int type){
        LayoutInflater vi=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=vi.inflate(R.layout.comment_item,null);
        TextView textViewx=v.findViewById(R.id.userid);
        textViewx.setText(userid);
        TextView textViewy=v.findViewById(R.id.comment_text);
        textViewy.setText(message);
        textViewx.setText(userid);
        /*TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);*/
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 10);
        //textView.setTextSize(18);
        textViewx.setLayoutParams(lp);
        if(type == 1) {
            lp.gravity= Gravity.RIGHT;
            //textViewx.setGravity(Gravity.CENTER);
            //textViewx.setBackgroundResource(R.drawable.blue_button_background);
            //textViewx.setLayoutParams(lp);
            v.setLayoutParams(lp);

        }
        else{
            lp.gravity= Gravity.LEFT;
            //textViewx.setGravity(Gravity.CENTER);
           // textViewx.setBackgroundResource(R.drawable.blue_button_background);
           // textViewx.setLayoutParams(lp);
            v.setLayoutParams(lp);
        }
       // layout.addView(textView);
        layout.addView(v);
        scrollView.fullScroll(View.FOCUS_DOWN);

    }
}
