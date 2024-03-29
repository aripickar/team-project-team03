package com.cs160.teachermatch;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText emailText;
    private String email;
    private EditText passwordText;
    private EditText confirmText;
    private String password;
    private Button createAccountButton;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final Intent passedIntent = getIntent();
        type = (String) passedIntent.getSerializableExtra("type");

        emailText = findViewById(R.id.enter_username);
        passwordText = findViewById(R.id.enter_password);
        confirmText = findViewById(R.id.enter_password2);
        createAccountButton = findViewById(R.id.create_account_button);
        createAccountButton.setOnClickListener(this);
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
    }

    public void onClick(View view) {
        if (TextUtils.isEmpty(emailText.getText())){
            Toast.makeText(this, "Email is required!", Toast.LENGTH_LONG).show();
                emailText.setError( "Email is required" );
        }
        else if(type.equals("teacher") && !emailText.getText().toString().contains(".edu")){
            /**
             *   You can Toast a message here that the Username is Empty
             **/
            Toast.makeText(this, "A .edu email is required to sign up as a teacher!",
                    Toast.LENGTH_LONG).show();

            emailText.setError( "A .edu email is required" );

        }
        else if (passwordText.getText().toString().length() < 6){
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_LONG).show();
            passwordText.setError( "At least 6 characters" );
        }
        else if (!passwordText.getText().toString().equals(confirmText.getText().toString())){
            System.out.println("pass: " + passwordText.getText().toString());
            System.out.println("confirm: " + confirmText.getText().toString());
            Toast.makeText(this, "Password must match!", Toast.LENGTH_LONG).show();
            passwordText.setError( "Passwords must match" );
            confirmText.setError( "Passwords must match" );

        }
        else{
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
                                        User user = new User(mAuth.getCurrentUser());
                                        database = FirebaseDatabase.getInstance();
                                        userRef = database.getReference("users");
                                        userRef.child(user.getUID()).setValue(user);
                                        login(user);
                                        //updateUI(user);
                                    } else {
                                        Log.w("Account Creation", "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(SignUpActivity.this, "Email has been taken",
                                                Toast.LENGTH_SHORT).show();
                                        emailText.setError("Email has been taken");
                                        //updateUI(null);
                                    }
                                }
                            });
            }
        }
    }

    private void login(User user) {
        if (type.equals("teacher")) {
            Intent intent = new Intent(this, CreateProfile1Activity.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
        else {
            Intent intent = new Intent(this, CreateComProfile1.class);
            intent.putExtra("user", user);
            startActivity(intent);
        }
    }
}
