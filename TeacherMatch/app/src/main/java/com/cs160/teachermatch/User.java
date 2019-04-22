package com.cs160.teachermatch;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

public class User {

    private String email;
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
