package com.cs160.teachermatch;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

public class User {

    private String email;
    private Boolean teacher;

    public String getEmail() {
        return email;
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

    private String DisplayName;
    private Uri photoUrl;


    public User(String email) {
        this.email = email;

    }

    public User(FirebaseUser user) {
        this.email = user.getEmail();
        this.DisplayName = user.getDisplayName();
        this.photoUrl = user.getPhotoUrl();

    }

}
