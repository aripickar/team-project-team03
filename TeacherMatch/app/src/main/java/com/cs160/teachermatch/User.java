package com.cs160.teachermatch;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class User {

    private String email;
    private Boolean teacher;
    private String firstName;
    private String lastName;
    private int profilePicture;
    private String primaryloc;
    private ArrayList<Post> posts;
    private String DisplayName;
    private Uri photoUrl;
    //private ArrayList<Message> messages

    public User(String email, String firstName, String lastName, int profilePicture) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;

    }

    public User(FirebaseUser user) {
        this.email = user.getEmail();
        this.DisplayName = user.getDisplayName();
        this.photoUrl = user.getPhotoUrl();

    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return firstName.concat(" " + lastName);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }

    public Uri getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(Uri photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Boolean getTeacher() {
        return teacher;
    }

    public void setTeacher(Boolean teacher) {
        this.teacher = teacher;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getPrimaryloc() {
        return primaryloc;
    }

    public void setPrimaryloc(String primaryloc) {
        this.primaryloc = primaryloc;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }


}
