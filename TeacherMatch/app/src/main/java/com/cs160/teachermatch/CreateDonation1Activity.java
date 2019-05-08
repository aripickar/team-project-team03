package com.cs160.teachermatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateDonation1Activity extends AppCompatActivity {

    public Button next;
    private User user;
    private Post newPost;
    private EditText title;
    private EditText items;
    private EditText cost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_donation1);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        final Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");

        next = findViewById(R.id.next);
        title = findViewById(R.id.input_title);
        items = findViewById(R.id.input_items);
        cost = findViewById(R.id.input_link);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newPost = new Post(title.getText().toString(), user, "", "");
                newPost.setPrice(Integer.parseInt(cost.getText().toString()));
                Intent intent = new Intent(CreateDonation1Activity.this, CreateDonation2Activity.class);
                intent.putExtra("user", user);
                intent.putExtra("post", newPost);
                startActivity(intent);
            }
        });


    }

}