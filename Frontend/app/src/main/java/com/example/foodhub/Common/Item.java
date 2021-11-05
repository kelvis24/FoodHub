package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

public class Item {

    private final long id;
    private final String title;
    private final String description;
    private final double price;

    public Item(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.title = (String)obj.get("title");
        this.description = (String)obj.get("description");
        this.price = obj.getDouble("price");
    }

    public Item(long id, String title, String description, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

}
