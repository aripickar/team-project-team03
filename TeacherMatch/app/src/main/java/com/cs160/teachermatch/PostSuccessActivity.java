package com.cs160.teachermatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PostSuccessActivity extends AppCompatActivity {
    public Button explore;
    private User user;
    private Post post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_success);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        final Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");
        post = (Post)passedIntent.getSerializableExtra("post");


        explore = findViewById(R.id.explore);

        explore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostSuccessActivity.this, Post_feedActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}
