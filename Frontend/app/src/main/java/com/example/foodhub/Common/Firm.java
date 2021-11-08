package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Formats teh qualities of a Firm
 * @author Arvid Gustafson
 */
public class Firm {

    private long id;
    private String username;
    private String name;
    private String location;
    private String cuisine;
    private int open_time;
    private int close_time;
    private int employee_count;

    /**
     * Constructs a Firm given a JSONObject
     * @param obj A JSONObject with the same information format
     */
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

    /**
     * Constructs a Firm given enumerated information
     * @param id The id of the firm
     * @param username The username of the firm
     * @param name The name of the firm
     * @param location The location of the firm
     * @param cuisine The cuisine of the firm
     * @param open_time The time the firm opens, expressed as an int
     * @param close_time The time the firm closes, expressed as an int
     * @param employee_count The number of employees that the firm has
     */
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

    /**
     * A getter method for the id field
     * @return The id of the firm
     */
    public long getId() {
        return id;
    }

    /**
     * A getter method for the username field
     * @return The username of the firm
     */
    public String getUsername() {
        return username;
    }

    /**
     * A getter method for the name field
     * @return The name of the firm
     */
    public String getName() {
        return name;
    }

    /**
     * A getter method for the location field
     * @return The location of the firm
     */
    public String getLocation() {
        return location;
    }

    /**
     * A getter method for the cuisine field
     * @return The cuisine of the firm
     */
    public String getCuisine() {
        return cuisine;
    }

    /**
     * A getter method for the open_time field
     * @return The time the firm opens, expressed by an int
     */
    public int getOpen_time() {
        return open_time;
    }

    /**
     * A getter method for the close_time field
     * @return The time the firm closes, expressed by an int
     */
    public int getClose_time() {
        return close_time;
    }

    /**
     * A getter method for the employee_count field
     * @return The number of employees the firm has
     */
    public int getEmployee_count() {
        return employee_count;
    }

}
