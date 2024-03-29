package com.cs160.teachermatch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class Post_feedActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public User user;

    RecyclerView recyclerView;
    PostAdapter adapter;
    ImageView mDrawProfileView;
    TextView mNavName;
    TextView mNavTeacherToggle;

    List<Post> posts;
    Toolbar toolbar;

    FirebaseDatabase database;
    DatabaseReference postsRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_feed2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Active Requests");


        final Intent passedIntent = getIntent();
        user = (User)passedIntent.getSerializableExtra("user");

        posts = new ArrayList<>();

        database = FirebaseDatabase.getInstance();
        postsRef = database.getReference("posts");
        database.getReference("users").child(user.getUID()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!user.completeUser()) {
                    user = dataSnapshot.getValue(User.class);
                }
                createFab();
                setSupportActionBar(toolbar);
                layoutDrawer();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    public void createPosts(){
        recyclerView = findViewById(R.id.post_recyclerview);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        mDrawProfileView = findViewById(R.id.profilePicture);
        mNavName = findViewById(R.id.Nav_name);
        mNavTeacherToggle = findViewById(R.id.Nav_teacherToggle);

        mNavName.setText(user.getName());
        if (user.getTeacherID() =="-1") {
            mNavTeacherToggle.setText("COMMUNITY");
            mNavTeacherToggle.setBackgroundColor(getResources().getColor(R.color.yellow ));
        } else {
            mNavTeacherToggle.setText("TEACHER");
            mNavTeacherToggle.setBackgroundColor(getResources().getColor(R.color.colorTeacherMatchPrimary ));
        }
        Picasso.with(getApplicationContext()).load(user.getProfilePicture()).into(mDrawProfileView);

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
            Intent intent = new Intent(Post_feedActivity.this, CreatePost1Activity.class);
            intent.putExtra("user", user);
            startActivity(intent);

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        else if (id == R.id.nav_profile) {
            Intent intent = new Intent(Post_feedActivity.this, ProfilePageActivity.class);
            intent.putExtra("user", user);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void addPosts(List<Post> posts) {
        posts.add(
                new Post(
                        "Extra white board markers?",
                        new User("test1@gmail.com", "Eric", "Jones", "https://firebasestorage.googleapis.com/v0/b/teachermatch-a4a25.appspot.com/o/uploads?alt=media&token=c2f2c038-86c8-4820-97e3-6a0e1409266b", true),
                        "Martin Luther King Jr High",
                        "Hi, my 8th grade class is short a few math textbooks. Looking for some algebra books to help my students prepare for highschool math!"
                )
        );
    }

    public void createFab(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Post_feedActivity.this, CreatePost1Activity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }


    public void layoutDrawer(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        postsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Post post = snapshot.getValue(Post.class);

                        posts.add(post);
                    }
                    Collections.sort(posts);
                    createPosts();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
