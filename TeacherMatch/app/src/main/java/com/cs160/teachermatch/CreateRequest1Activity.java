package com.cs160.teachermatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateRequest1Activity extends AppCompatActivity {

    public Button next;
    private User user;
    private Post newPost;
    private EditText title;
    private EditText items;
    private EditText cost;
    private EditText date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpost1);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        final Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");

        next = findViewById(R.id.next);
        title = findViewById(R.id.input_title);
        items = findViewById(R.id.input_items);
        cost = findViewById(R.id.input_link);
        date = findViewById(R.id.input_date);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPost = new Post(title.getText().toString(), user, user.getSchoolName(), "");
                Intent intent = new Intent(CreateRequest1Activity.this, CreateRequest2Activity.class);
                intent.putExtra("user", user);
                intent.putExtra("post", newPost);
                startActivity(intent);
            }
        });


    }

}
