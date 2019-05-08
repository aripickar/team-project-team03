package com.cs160.teachermatch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class CreateProfile2Activity extends AppCompatActivity {

    private User tempUser;
    private User user;
    private EditText classSize, classDescription, classSubject;
    private ImageView classPictures;
    private Button next;
    private ProgressBar mProgressBar;
    private StorageReference mStorageRef;
    private byte[] byteArray;
    private String mUri;


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
        byteArray = getIntent().getByteArrayExtra("image");
        mStorageRef = FirebaseStorage.getInstance().getReference(tempUser.getUID());

        classSize = findViewById(R.id.numHint);
        classSubject = findViewById(R.id.subjectsHint);
        classDescription = findViewById(R.id.aboutText);
        mProgressBar = findViewById(R.id.progress_bar);

        tempUser.setClassSize(classSize.getText().toString());
        tempUser.setSubject(classSubject.getText().toString());
        tempUser.setDescription(classDescription.getText().toString());

        mUri = "uri";


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
        userRef.child(user.getUID()).setValue(user);
        UploadTask uploadTask = uploadToStorage(byteArray);
            }
        });


    }

    public UploadTask uploadToStorage(byte[] byteArray){

        final UploadTask uploadTask = mStorageRef.putBytes(byteArray);
        Log.d("UploadTask", "uploadTask is uploading");
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.i("whatTheFuck:",e.toString());
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                        double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                        mProgressBar.setProgress((int) progress);
                    }
                })
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful()) {
                            Log.i("problem", task.getException().toString());
                        }

                        return mStorageRef.getDownloadUrl();

                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            Uri downloadUri = task.getResult();
                            Log.d("seeThisUri", downloadUri.toString());// This is the one you should store
                            mUri = downloadUri.toString();
                            user.setProfilePicture(mUri);
                            Intent intent = new Intent(CreateProfile2Activity.this, Post_feedActivity.class);
                            intent.putExtra("user", user);

                            startActivity(intent);

                        } else {
                            Log.d("wentWrong","downloadUri failure");
                    }
                    }
                });
            }
        });

        return uploadTask;
    }

}
