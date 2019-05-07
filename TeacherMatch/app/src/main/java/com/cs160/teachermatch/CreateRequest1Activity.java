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
        setContentView(R.layout.new_request1);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");

        next = findViewById(R.id.next);
        title = findViewById(R.id.input_title);
        items = findViewById(R.id.input_items);
        cost = findViewById(R.id.Input_cost);
        date = findViewById(R.id.input_date);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( TextUtils.isEmpty(title.getText())){
                    /**
                     *   You can Toast a message here that the Username is Empty
                     **/
                    Toast.makeText(CreateRequest1Activity.this, "First name is required!",
                            Toast.LENGTH_LONG).show();

                    title.setError( "First name is required!" );

                }
                else if( TextUtils.isEmpty(items.getText())){
                    /**
                     *   You can Toast a message here that the Username is Empty
                     **/
                    Toast.makeText(CreateRequest1Activity.this, "Please list the items you are requesting",
                            Toast.LENGTH_LONG).show();

                    items.setError( "Items are required!" );

                }
                else if( TextUtils.isEmpty(cost.getText())){
                    /**
                     *   You can Toast a message here that the Username is Empty
                     **/
                    Toast.makeText(CreateRequest1Activity.this, "Please estimate the cost of your items",
                            Toast.LENGTH_LONG).show();

                    cost.setError( "Estimated cost is required!" );

                }
                else {
                    newPost = new Post(title.getText().toString(), user, "", "");
                    Intent intent = new Intent(CreateRequest1Activity.this, CreateRequest2Activity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("post", newPost);
                    startActivity(intent);
                }
            }
        });


    }

}
