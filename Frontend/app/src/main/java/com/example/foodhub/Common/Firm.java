package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

public class Firm {

    private long id;
    private String username;
    private String name;
    private String location;
    private String cuisine;
    private int open_time;
    private int close_time;
    private int employee_count;

    public Firm(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.username = (String)obj.get("username");
        this.name = (String)obj.get("name");
        this.location = (String)obj.get("location");
        this.cuisine = (String)obj.get("cuisine");
        this.open_time = obj.getInt("open_time");
        this.close_time = obj.getInt("close_time");
        this.employee_count = obj.getInt("employee_count");
    }

    public Firm(long id, String username, String name, String location, String cuisine,
                int open_time, int close_time, int employee_count ) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.location = location;
        this.cuisine = cuisine;
        this.open_time = open_time;
        this.close_time= close_time;
        this.employee_count = employee_count;

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

    public String getLocation() {
        return location;
    }

    public String getCuisine() {
        return cuisine;
    }

    public int getOpen_time() {
        return open_time;
    }

    public int getClose_time() {
        return close_time;
    }

    public int getEmployee_count() {
        return employee_count;
    }

}
