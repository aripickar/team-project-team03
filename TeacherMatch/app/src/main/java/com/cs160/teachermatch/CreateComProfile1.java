package com.cs160.teachermatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CreateComProfile1 extends AppCompatActivity {

    private User user;
    private EditText firstName, lastName, school;
    private ImageView profilePicture;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_comm_profile1);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
////        FloatingActionButton fab = findViewById(R.id.fab);
////        fab.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
////            }
////        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        school = findViewById(R.id.occupation);
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
            Toast.makeText(this, "First name is required!",
                    Toast.LENGTH_LONG).show();

            firstName.setError( "First name is required" );

        }
        else if( TextUtils.isEmpty(lastName.getText())){
            /**
             *   You can Toast a message here that the Username is Empty
             **/
            Toast.makeText(this, "Last name is required!",
                    Toast.LENGTH_LONG).show();

            lastName.setError( "Last name is required" );

        }
        else if( TextUtils.isEmpty(school.getText())){
            /**
             *   You can Toast a message here that the Username is Empty
             **/
            Toast.makeText(this, "Occupation is required!",
                    Toast.LENGTH_LONG).show();

            school.setError( "Occupation is required" );

        }
        else {
//            user.setFirstName(firstName.getText().toString());
//            user.setLastName(lastName.getText().toString());
            //user.setSchool()
            //user.setProfilePicture()
            user.setFirstName(firstName.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setSchoolName(school.getText().toString());
            Intent intent = new Intent(this, CreateComProfile2.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }

    }

}
