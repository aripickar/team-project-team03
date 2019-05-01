package com.cs160.teachermatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Post_feedActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public User user;

    RecyclerView recyclerView;
    PostAdapter adapter;

    List<Post> posts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_feed2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Active Requests");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Post_feedActivity.this, CreatePost1Activity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        posts = new ArrayList<>();
        recyclerView = findViewById(R.id.post_recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addPosts(posts);

        adapter = new PostAdapter(this, posts);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.post_feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_messages) {

        } else if (id == R.id.nav_post) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addPosts(List<Post> posts) {
        posts.add(
                new Post(
                        "Extra white board markers?",
                        new User("test1@gmail.com", "Eric", "Jones", R.drawable.teacher_headshot, true),
                        "Martin Luther King Jr High",
                        "Hi, my 8th grade class is short a few math textbooks. Looking for some algebra books..."
                )
        );

        posts.add(
                new Post(
                        "Old math textbooks needed",
                        new User("test1@gmail.com", "Elsa", "David", R.drawable.teacher_headshot2, true),
                        "Corte Madeira",
                        "Hi, due to budget cuts, our school isn\\'t providing white board markers to teachers..."
                )
        );

        posts.add(
                new Post(
                        "Old math textbooks needed",
                        new User("test1@gmail.com", "Elsa", "David", R.drawable.teacher_headshot2, true),
                        "Corte Madeira",
                        "Hi, due to budget cuts, our school isn\\'t providing white board markers to teachers..."
                )
        );

        posts.add(
                new Post(
                        "Old math textbooks needed",
                        new User("test1@gmail.com", "Elsa", "David", R.drawable.teacher_headshot2, true),
                        "Corte Madera",
                        "Hi, due to budget cuts, our school isn\\'t providing white board markers to teachers..."
                )
        );

        posts.add(
                new Post(
                        "Old math textbooks needed",
                        new User("test1@gmail.com", "Elsa", "David", R.drawable.teacher_headshot2, true),
                        "Corte Madera",
                        "Hi, due to budget cuts, our school isn\\'t providing white board markers to teachers to..."
                )
        );

        posts.add(
                new Post(
                        "Old math textbooks needed",
                        new User("test1@gmail.com", "Elsa", "David", R.drawable.teacher_headshot2, true),
                        "Corte Madera",
                        "Hi, due to budget cuts, our school isn\\'t providing white board markers to teachers ..."
                )
        );
    }
}
