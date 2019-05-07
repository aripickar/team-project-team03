package com.cs160.teachermatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ChooseProfileType extends AppCompatActivity {


    private User user;
    private EditText firstName, lastName, email, school;
    private ImageView profilePicture;
    private Button next;
    public Button teacher;
    public Button other_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_user);


        teacher = findViewById(R.id.teacher);
        other_user = findViewById(R.id.community_member);

        final Intent passedIntent = getIntent();
//        user = (User)passedIntent.getSerializableExtra("user");
//        firstName = findViewById(R.id.first_name);
//        lastName = findViewById(R.id.last_name);
//        school = findViewById(R.id.school_name);
//        //profilePicture
//        next = findViewById(R.id.next);
////        next.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                createProfile2();
////            }
////        });


        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseProfileType.this, SignUpActivity.class);
                intent.putExtra("type", "teacher");
                startActivity(intent);
            }
        });

        other_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseProfileType.this, SignUpActivity.class);
                intent.putExtra("type", "community");
                startActivity(intent);
            }
        });

    }
}
