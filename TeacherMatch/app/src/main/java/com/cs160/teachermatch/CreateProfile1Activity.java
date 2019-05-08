package com.cs160.teachermatch;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class CreateProfile1Activity extends AppCompatActivity {

    private static final int requestCode = 1234;
    private User user;

    private EditText firstName, lastName, phoneNumber, school;
    private ImageButton profilePicture;
    private Button next;
    private Bitmap selectedImageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_profile_1);

        final Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");
        firstName = findViewById(R.id.first_name);
        lastName = findViewById(R.id.last_name);
        school = findViewById(R.id.occupation);
        phoneNumber = findViewById(R.id.phoneNumber);
        profilePicture = findViewById(R.id.profile_picture_upload);
        next = findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProfile2();
            }
        });
        View.OnClickListener btnChoosePhotoPressed = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    requestPermissionForReadExtertalStorage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1234;
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
            }
        };
        profilePicture.setOnClickListener(btnChoosePhotoPressed);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1234){
            if(resultCode == RESULT_OK){
                Uri selectedImage = data.getData();


                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex);
                cursor.close();

                this.selectedImageBitmap = getResizedBitmap(BitmapFactory.decodeFile(filePath), 120);
                System.out.println("made it");
                Drawable d = new BitmapDrawable(getResources(), this.selectedImageBitmap);
                profilePicture.setBackground(d);

            }
        }

    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }


    public void requestPermissionForReadExtertalStorage() throws Exception {
        try {
            ActivityCompat.requestPermissions((Activity) this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1212);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
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
        else if( selectedImageBitmap == null){
            /**
             *   You can Toast a message here that the Username is Empty
             **/
            Toast.makeText(CreateProfile1Activity.this, "A Profile Picture is required!",
                    Toast.LENGTH_LONG).show();


        }
        else {
//            user.setFirstName(firstName.getText().toString());
//            user.setLastName(lastName.getText().toString());
            //user.setSchool()
            //user.setProfilePicture()
            user.setFirstName(firstName.getText().toString());
            user.setLastName(lastName.getText().toString());
            user.setSchoolName(school.getText().toString());
            user.setPhoneNumber(phoneNumber.getText().toString());
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            selectedImageBitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            byte[] byteArray = bStream.toByteArray();
            Intent intent = new Intent(this, CreateProfile2Activity.class);
            intent.putExtra("user", user);
            intent.putExtra("image", byteArray);
            startActivity(intent);
        }

    }

}
