package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

public class Admin {

    private final long id;
    private final String username;
    private final String name;
    private final int type;

    public Admin(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.username = (String)obj.get("username");
        this.name = (String)obj.get("name");
        this.type = obj.getInt("type");
    }

    public Admin(long id, String username, String name, int type) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }

}
