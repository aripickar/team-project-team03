package com.cs160.teachermatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    public User user;

    RecyclerView recyclerView;
    PostAdapter adapter;

    List<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            user = new User(firebaseUser);
            Toast.makeText(HomeActivity.this, "Authentication Succeeded for: " + user.getEmail(),
                    Toast.LENGTH_SHORT).show();
        }

        posts = new ArrayList<>();
        recyclerView = findViewById(R.id.home_recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        posts.add(
                new Post(
                        "Extra white board markers?",
                        new User("test1@gmail.com", "Eric", "Jones", R.drawable.teacher_headshot),
                        "Martin Luther King Jr High",
                        "Hi, my 8th grade class is short a few math textbooks. Looking for some algebra books to help my students prepare for highschool math!"
                )
        );

        posts.add(
                new Post(
                        "Old math textbooks needed",
                        new User("test1@gmail.com", "Elsa", "David", R.drawable.teacher_headshot2),
                        "Corte Madeira",
                        "Hi, due to budget cuts, our school isn\\'t providing white board markers to teachers anymore! I have been buying them but they are quite expensive, if you have extra would love to..."
                )
        );
        adapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(adapter);
    }
}
