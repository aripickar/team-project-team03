package com.cs160.teachermatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreatePost1Activity extends AppCompatActivity {

    private User user;
    private EditText firstName, lastName, email, school;
    private ImageView profilePicture;
    private Button next;
    public Button donation;
    public Button request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_post_options);


        donation = findViewById(R.id.donate_items);
        request = findViewById(R.id.teacher);

        final Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
//        email = findViewById(R.id.email_name);
        school = findViewById(R.id.occupation);
        //profilePicture
        next = findViewById(R.id.next);
//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                createProfile2();
//            }
//        });


        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CreatePost1Activity.this, CreateRequest1Activity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

        donation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(getApplicationContext(), "This Feature is Coming Soon!",Toast.LENGTH_LONG);
                toast.show();
                //Intent intent = new Intent(CreatePost1Activity.this, CreateDonation1Activity.class);
                //intent.putExtra("user", user);
                //startActivity(intent);
            }
        });

    }
}
