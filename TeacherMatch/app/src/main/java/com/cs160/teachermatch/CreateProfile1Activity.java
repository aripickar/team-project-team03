package com.cs160.teachermatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateProfile1Activity extends AppCompatActivity {

    private User user;
    private EditText firstName, lastName, email, school;
    private ImageView profilePicture;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile_1);

        final Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        email = findViewById(R.id.email_name);
        school = findViewById(R.id.school_name);
        //profilePicture
        next = findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProfile2();
            }
        });

    }

    private void createProfile2(){
        user.setFirstName(firstName.getText().toString());
        user.setLastName(lastName.getText().toString());
        //user.setSchool()
        //user.setProfilePicture()
        Intent intent = new Intent(this, CreateProfile2Activity.class);
        intent.putExtra("user", user);
        startActivity(intent);

    }
}
