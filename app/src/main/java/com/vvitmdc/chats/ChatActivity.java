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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseError;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    LinearLayout layout;
    ImageView sendButton,uploadButton;
    EditText messageArea;
    ScrollView scrollView;
    DatabaseReference reference1, reference2;
    public static final int PICK_IMAGE_REQUEST=1;
    Uri mImageUri;
    StorageReference mStorageRef;
    DatabaseReference mDataRef;
    static Boolean isImage=false;
    String downUrl=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        layout = findViewById(R.id.layout1);
        sendButton = findViewById(R.id.sendButton);
        uploadButton=findViewById(R.id.imageUpload);
        messageArea = findViewById(R.id.messageArea);
        scrollView = findViewById(R.id.scrollView);
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
                    addMessageBox("You:-\n" + message, 1);
                    if(!image.equals(" "))
                        addImageBox(image,1);
                }
                else{
                    addMessageBox(UserData.chatWith + ":-\n" + message, 2);
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

    public void addMessageBox(String message, int type){
        TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 10);
        textView.setTextSize(18);
        textView.setLayoutParams(lp);

        if(type == 1) {
            lp.gravity= Gravity.RIGHT;
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.blue_button_background);
            textView.setLayoutParams(lp);

        }
        else{
            lp.gravity= Gravity.LEFT;
            textView.setGravity(Gravity.CENTER);
            textView.setBackgroundResource(R.drawable.blue_button_background);
            textView.setLayoutParams(lp);
        }
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);

    }
}
