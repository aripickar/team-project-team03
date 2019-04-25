package com.cs160.teachermatch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChooseUserActivity extends AppCompatActivity {

    private User user;
    public Button teacherButton, communityButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_user);

        final Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");

        teacherButton = (Button)findViewById(R.id.teacherCreateButton);
        communityButton = (Button)findViewById(R.id.communityMemberCreateButton);

        teacherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setTeacher(true);
                Intent goToIntent = new Intent(ChooseUserActivity.this, CreateProfile1Activity.class);
                goToIntent.putExtra("user", user);
                startActivity(goToIntent);
            }
        });

        communityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setTeacher(false);
                Intent goToIntent = new Intent(ChooseUserActivity.this, PostFeedActivity.class);
                goToIntent.putExtra("user", user);
                startActivity(goToIntent);
            }
        });


    }
}
