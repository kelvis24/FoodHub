package com.example.foodhub.Class;

public class Company {

    private int profileIcon;
    private int postImage;
    private String title;
    private String message;

    public Company(int profileIcon, int postImage, String title, String message) {
        this.profileIcon = profileIcon;
        this.postImage = postImage;
        this.title = title;
        this.message = message;

    }

    public int getProfileIcon() {
        return profileIcon;
    }

    public void setProfileIcon(int profileIcon) {
        this.profileIcon = profileIcon;
    }


    public int getPostImage() {
        return postImage;
    }

    public void setPostImage(int postImage) {
        this.postImage = postImage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
