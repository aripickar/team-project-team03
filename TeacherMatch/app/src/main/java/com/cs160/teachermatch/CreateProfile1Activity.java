package com.cs160.teachermatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateProfile1Activity extends AppCompatActivity {

    private User user;
    private EditText firstName, lastName, school;
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
        if( TextUtils.isEmpty(firstName.getText())){
            /**
             *   You can Toast a message here that the Username is Empty
             **/
            Toast.makeText(CreateProfile1Activity.this, "First name is required!",
                    Toast.LENGTH_LONG).show();

            firstName.setError( "First name is required" );

        }
        else if( TextUtils.isEmpty(lastName.getText())){
            /**
             *   You can Toast a message here that the Username is Empty
             **/
            Toast.makeText(CreateProfile1Activity.this, "Last name is required!",
                    Toast.LENGTH_LONG).show();

            lastName.setError( "Last name is required" );

        }
        else if( TextUtils.isEmpty(school.getText())){
            /**
             *   You can Toast a message here that the Username is Empty
             **/
            Toast.makeText(CreateProfile1Activity.this, "School is required!",
                    Toast.LENGTH_LONG).show();

            school.setError( "School is required" );

        }
        else {
//            user.setFirstName(firstName.getText().toString());
//            user.setLastName(lastName.getText().toString());
            //user.setSchool()
            //user.setProfilePicture()
            user.setFirstName(firstName.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setSchoolName(school.getText().toString());
            Intent intent = new Intent(this, CreateProfile2Activity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }

    }
}
