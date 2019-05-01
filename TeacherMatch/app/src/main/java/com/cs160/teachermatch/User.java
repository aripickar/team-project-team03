package com.cs160.teachermatch;

import android.net.Uri;

import com.google.firebase.auth.FirebaseUser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

public class User implements Serializable {

    private final String UID;

    private String email;
    private Boolean teacher;
    private String firstName;
    private String lastName;
    private int profilePicture;
    private String primaryloc;
    private ArrayList<Post> posts;
    private String DisplayName;
    private Uri photoUrl;
    private String description;
    private String teacherID;

    private String subject;
    private String about;
    private String schoolName;
    private ArrayList<Uri> classroomPhotos;

    //private ArrayList<Message> messages

    public User(String email, String firstName, String lastName, int profilePicture, Boolean teacher) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.teacherID = "-1";
        this.teacher = teacher;
//        kinda confused what this is supposed to be
        this.UID = "";

    }

    public User(String email, String firstName, String lastName, int profilePicture, boolean teacher, String UID) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.UID = UID;
        if (teacher) {
            this.teacherID = UUID.randomUUID().toString();
        } else {
            this.teacherID = "-1";
        }

    }

    public User(FirebaseUser user) {
        this.email = user.getEmail();
        this.DisplayName = user.getDisplayName();
        this.photoUrl = user.getPhotoUrl();
        this.UID = user.getUid();

    }

    public String getTeacherID() {
        return teacherID;
    }


    public String getUID() {
        return UID;
    }


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (!teacherID.equals("-1")) {
            this.subject = subject;
        }
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        if (!teacherID.equals("-1")) {
            this.about = about;
        }
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        if (!teacherID.equals("-1")) {
            this.schoolName = schoolName;
        }
    }

    public ArrayList<Uri> getClassroomPhotos() {
        return classroomPhotos;
    }

    public void setClassroomPhotos(ArrayList<Uri> classroomPhotos) {
        if (!teacherID.equals("-1")) {
            this.classroomPhotos = classroomPhotos;
        }
    }

    public void addClassroomPhoto(Uri uri) {
        if (!teacherID.equals("-1")) {
            classroomPhotos.add(uri);
        }
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
