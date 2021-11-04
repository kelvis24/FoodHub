package com.example.foodhub.Common;

public class Menu {
    private int foodImage;
    private String title;
    private String description;
    private String price;

    public Menu( int foodImage, String title, String decription, String price) {
        this.foodImage =  foodImage;
        this.title =  title;
        this.description =  decription;
        this.price =  price;
    }

    public int getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(int foodImage) {
        this.foodImage = foodImage;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
