package com.cs160.teachermatch;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PostDetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DetailsAdapter adapter;
    String postId;
    User poster;
    FirebaseDatabase database;
    DatabaseReference postsRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);


        recyclerView = findViewById(R.id.post_details_recyclerview);
        Intent intent = getIntent();

        postId = intent.getStringExtra("post");
        database = FirebaseDatabase.getInstance();
        postsRef = database.getReference("posts");
        postsRef.child(postId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Post post = dataSnapshot.getValue(Post.class);
                Log.d("postvalue", post.toString());

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                adapter = new DetailsAdapter(getApplicationContext(), post, post.getPoster());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);


    }

    private Post createPost() {
        User user = new User("test1@gmail.com", "Elsa", "David", " https://firebasestorage.googleapis.com/v0/b/teachermatch-a4a25.appspot.com/o/3iFATArKuhRwbpLe0wHY0URhQrh1?alt=media&token=c5f5fd4c-4461-41da-9eef-8b7566f217d6", true);
        user.setDescription("I am a math and science teacher at Corte Madera Middle\\nSchool. I have been teaching since I graduated from college with a degree in Biology. This is my 3rd year teaching at Corte Madera, and my 5th year teaching overall... ");
        return new Post(
                "Old math textbooks needed",
                user,
                "Corte Madeira",
                "Hi, due to budget cuts, our school isn\\'t providing white board markers to teachers anymore! I have been buying them but they are quite expensive, if you have extra would love to..."
        );
    }
}
