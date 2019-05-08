package com.cs160.teachermatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class CreateRequest2Activity extends AppCompatActivity {

    public Button next;
    private User user;
    private Post newPost;
    private EditText description;
    private EditText other;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_request2);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        final Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");
        newPost = (Post)passedIntent.getSerializableExtra("post");
        next = findViewById(R.id.next);
        description = findViewById(R.id.input_description);
        other = findViewById(R.id.input_other);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("posts");


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if( TextUtils.isEmpty(description.getText())){
                    /**
                     *   You can Toast a message here that the Username is Empty
                     **/
                    Toast.makeText(CreateRequest2Activity.this, "A description about your request is required!",
                            Toast.LENGTH_LONG).show();

                    description.setError( "Description is required" );

                }

                else {
                    if (other.getText().toString().length() != 0) {
                        newPost.setOther(other.getText().toString());
                    }
                    newPost.setPoster(user);
                    newPost.setDescription(description.getText().toString());
                    newPost.setTimePosted(new Date());
                    databaseReference.child(newPost.getPostId()).setValue(newPost);
                    Intent intent = new Intent(CreateRequest2Activity.this, PostSuccessActivity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("post", newPost);
                    startActivity(intent);
                }
            }
        });


    }
}
