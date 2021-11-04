package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

public class Category {

    private final long id;
    private final String title;
    private final String description;

    public Category(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.title = (String)obj.get("title");
        this.description = (String)obj.get("description");
    }

    public Category(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

}
