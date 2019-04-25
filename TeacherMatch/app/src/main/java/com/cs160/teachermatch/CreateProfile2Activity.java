package com.cs160.teachermatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateProfile2Activity extends AppCompatActivity {

    private User user;
    private EditText classSize, classDescription, classSubject;
    private ImageView classPictures;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile_2);

        final Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");

        classSize = findViewById(R.id.numHint);
        classSubject = findViewById(R.id.subjectsHint);
        classDescription = findViewById(R.id.aboutText);

        /*
        * Need to create a teacher subclass of User and hook stuff up
        * here? We can figure that out.
        *
        * */

        next = findViewById(R.id.nextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateProfile2Activity.this, PostFeedActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });
    }
}
