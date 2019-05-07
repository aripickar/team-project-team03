package com.cs160.teachermatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateComProfile2 extends AppCompatActivity {
    private EditText about;
    private Button back;
    private Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_com_profile2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        about = findViewById(R.id.aboutText);
        next = findViewById(R.id.nextButton);
        back = findViewById(R.id.back);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProfile2();
            }
        });

    }
    public void createProfile2(){
        if( TextUtils.isEmpty(about.getText())){
            /**
             *   You can Toast a message here that the Username is Empty
             **/
            Toast.makeText(this, "About description is required!",
                    Toast.LENGTH_LONG).show();

            about.setError( "First name is required" );

        }
        else {
            Intent intent = new Intent(this, Post_feedActivity.class);
            startActivity(intent);
        }
    }

}
