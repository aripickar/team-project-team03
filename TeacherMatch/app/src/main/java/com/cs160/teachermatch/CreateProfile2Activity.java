package com.cs160.teachermatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfile2Activity extends AppCompatActivity {

    private User tempUser;
    private User user;
    private EditText classSize, classDescription, classSubject;
    private ImageView classPictures;
    private Button next;

    FirebaseDatabase database;
    DatabaseReference userRef;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile_2);

        final Intent passedIntent = getIntent();
        tempUser = (User)passedIntent.getSerializableExtra("user");


        classSize = findViewById(R.id.numHint);
        classSubject = findViewById(R.id.subjectsHint);
        classDescription = findViewById(R.id.aboutText);

        tempUser.setClassSize(classSize.getText().toString());
        tempUser.setSubject(classSubject.getText().toString());
        tempUser.setDescription(classDescription.getText().toString());


        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");

        user = new User(FirebaseAuth.getInstance().getCurrentUser());
        user.portUser(tempUser);



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
                userRef.child(user.getUID()).setValue(user);
                startActivity(intent);
            }
        });
    }
}
