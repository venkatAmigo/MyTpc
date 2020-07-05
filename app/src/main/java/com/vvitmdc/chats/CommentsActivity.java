package com.vvitmdc.chats;
        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.cardview.widget.CardView;
        import androidx.core.widget.NestedScrollView;

        import android.app.ProgressDialog;
        import android.content.Context;
        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

        import com.google.android.material.appbar.CollapsingToolbarLayout;
        import com.google.firebase.database.ChildEventListener;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.squareup.picasso.Callback;
        import com.squareup.picasso.Picasso;
        import com.vvitmdc.chats.model.Comment;

public class CommentsActivity extends AppCompatActivity {

    private DatabaseReference reference;
    private EditText commentData;
    private Button send;
    private ImageView collapseIv;
    private String comments;
    private String pid;
    private String img;
    private NestedScrollView scrollView;
    private LinearLayout layout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private String question="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        commentData=findViewById(R.id.commentArea);
        send=findViewById(R.id.commentButton);
        layout = findViewById(R.id.layout2);
        scrollView = findViewById(R.id.scrollView2);
        collapseIv=findViewById(R.id.collapse);
        collapsingToolbarLayout=findViewById(R.id.toolbar_layout);
        toolbar=findViewById(R.id.toolbar);
        img=getIntent().getStringExtra("img");
        question=getIntent().getStringExtra("question");
        final ProgressDialog p=new ProgressDialog(this);
        if(!UserData.imageUrl.isEmpty()){
            p.setTitle("Image");
            p.setMessage("Loading Image...");
            p.show();
            Picasso.with(CommentsActivity.this).load(UserData.imageUrl).into(collapseIv, new Callback() {
                @Override
                public void onSuccess() {
                    p.dismiss();
                }
                @Override
                public void onError() {
                    p.dismiss();
                }
            });}
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        collapsingToolbarLayout.setTitle(question);
        pid=getIntent().getStringExtra("id");
        String url="https://chat-82696.firebaseio.com/comments/"+pid;
        //String url=getString(R.string.COMMENTS_URL)+pid;
        reference = FirebaseDatabase.getInstance().getReferenceFromUrl(url);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comments=commentData.getText().toString();
                if(comments.equals(""))
                {
                    commentData.setError("Enter Your Regestration ID");
                }
                else{
                    commentData.getText().clear();
                    sendDetails();
                }
            }
        });
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Comment comment=dataSnapshot.getValue(Comment.class);
                addMessageBox(comment.getUid(),comment.getComment());

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void sendDetails() {
        reference= FirebaseDatabase.getInstance().getReference("comments");
        Comment comment=new Comment(pid,UserData.username,comments);
        reference.child(pid).push().setValue(comment);
    }
    private void addMessageBox(String userid,String message){
        LayoutInflater vi=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=vi.inflate(R.layout.comment_item,null);
        TextView textViewx=v.findViewById(R.id.userid);
        textViewx.setText(userid);
        TextView textViewy=v.findViewById(R.id.comment_text);
        textViewy.setText(message);
        textViewx.setText(userid);
        layout.addView(v);
        scrollView.fullScroll(View.FOCUS_DOWN);

    }
}
