package com.cs160.teachermatch;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Post implements Serializable {

    private final String postId;
    private User poster;
    private String posterName;
    private Uri posterPhoto;
    private int intDays;
    private String name;
    private Date timePosted;
    private String school;
    private String title;
    private String description;
    private String details;
    private String other;
    private String amazonLink;
    private Date deadline;
    private int price;
    private boolean request;
    private boolean volunteer;

    public Post(){
        this.postId = UUID.randomUUID().toString();
    }

    public Post(String title, User poster, String school, String description) {
        this.title = title;
        this.poster = poster;
        this.school = school;
        this.description = description;
        this.timePosted = new Date();
        this.postId = UUID.randomUUID().toString();
    }


    public Post(String postId, User poster, String posterName, Uri posterPhoto, int intDays, String name, Date timePosted, String school, String title, String description, String details, String other, String amazonLink, Date deadline, int price, boolean request, boolean volunteer) {
        this.postId = UUID.randomUUID().toString();
        this.poster = poster;
        this.posterName = posterName;
        this.posterPhoto = posterPhoto;
        this.intDays = intDays;
        this.name = name;
        this.timePosted = timePosted;
        this.school = school;
        this.title = title;
        this.description = description;
        this.details = details;
        this.other = other;
        this.amazonLink = amazonLink;
        this.deadline = deadline;
        this.price = price;
        this.request = request;
        this.volunteer = volunteer;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPostId() {
        return postId;
    }

    public User getPoster() {
        return poster;
    }

    public String getPosterName(){
        return poster.getName();
    }

    public String getPosterPicture(){
        return poster.getProfilePicture();
    }

    public boolean getRequest() {
        return true;
    }

    public void setPoster(User poster) {
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public Date getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(Date timePosted) {
        this.timePosted = timePosted;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAmazonLink() {
        return amazonLink;
    }

    public void setAmazonLink(String amazonLink) {
        this.amazonLink = amazonLink;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isVolunteer() {
        return volunteer;
    }

    public void setVolunteer(boolean volunteer) {
        this.volunteer = volunteer;
    }
}
