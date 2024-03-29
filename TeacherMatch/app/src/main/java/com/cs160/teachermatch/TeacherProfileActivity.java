package com.cs160.teachermatch;

import android.content.Intent;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class TeacherProfileActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TeacherProfileAdapter adapter;
    Post post;
    User poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Active Requests");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        recyclerView = findViewById(R.id.post_details_recyclerview);
        Intent intent = getIntent();

        //post = intent.getStringExtra("post");
        post = createPost();
        poster = post.getPoster();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TeacherProfileAdapter(this, poster);
        recyclerView.setAdapter(adapter);
    }

    private Post createPost() {
        User user = new User("test1@gmail.com", "Elsa", "David", "https://firebasestorage.googleapis.com/v0/b/teachermatch-a4a25.appspot.com/o/uploads?alt=media&token=c2f2c038-86c8-4820-97e3-6a0e1409266b", true);
        user.setDescription("I am a math and science teacher at Corte Madera Middle\\nSchool. I have been teaching since I graduated from college with a degree in Biology. This is my 3rd year teaching at Corte Madera, and my 5th year teaching overall... ");
        return new Post(
                "Old math textbooks needed",
                user,
                "Corte Madeira",
                "Hi, due to budget cuts, our school isn\\'t providing white board markers to teachers anymore! I have been buying them but they are quite expensive, if you have extra would love to..."
        );
    }

}
