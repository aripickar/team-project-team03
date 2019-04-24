package com.cs160.teachermatch;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailText;
    private String email;
    private EditText passwordText;
    private String password;
    private Button createAccountButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailText = findViewById(R.id.enter_username);
        passwordText = findViewById(R.id.enter_password);
        createAccountButton = findViewById(R.id.create_account_button);
        createAccountButton.setOnClickListener(this);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.create_account_button:
                email = emailText.getText().toString();
                password = passwordText.getText().toString();
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d("Account Creation", "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    login();
                                    //updateUI(user);
                                } else {
                                    Log.w("Account Creation", "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }
                             }
                        });

        }
    }

    private void login() {
        Intent intent = new Intent(this, PostFeedActivity.class);
        startActivity(intent);
    }
}
