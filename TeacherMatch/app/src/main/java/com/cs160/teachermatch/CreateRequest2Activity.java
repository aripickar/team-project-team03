package com.cs160.teachermatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateRequest2Activity extends AppCompatActivity {

    public Button next;
    private User user;
    private Post newPost;
    private EditText description;
    private EditText other;

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

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(other.getText().toString().length() != 0){
                    newPost.setOther(other.getText().toString());
                }
                newPost.setDescription(description.getText().toString());
                Intent intent = new Intent(CreateRequest2Activity.this, PostSuccessActivity.class);
                intent.putExtra("user", user);
                intent.putExtra("post", newPost);
                startActivity(intent);
            }
        });


    }
}
