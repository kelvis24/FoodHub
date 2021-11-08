package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

/**
* Class responsible for creating and setting up the 'firm' user type
* @author 1_CW_1
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
    * Method constructor to set up an firm user in response to a JSONObject request
    * @param obj JSONObject from the JSONObject request
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
    * Method to input values into the given firm request
    * @param id ID of the firm
    * @param username User firm's username
    * @param name User firm's name
    * @param location User firm's location
    * @param cuisine User firm's cuisine
    * @param open_time User firm's open time
    * @param close_time User firm's close time
    * @param employee_count User firms number of employees
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
    * Method to return the ID of the firm when requested.
    * @return id
    */
    public long getId() {
        return id;
    }
    /**
    * Method to return the ID of the firm when requested.
    * @return username
    */
    public String getUsername() {
        return username;
    }
    /**
    * Method to return the name of the firm when requested.
    * @return name
    */
    public String getName() {
        return name;
    }
    /**
    * Method to return the location of the firm when requested.
    * @return location
    */
    public String getLocation() {
        return location;
    }
    /**
    * Method to return the cuisine of the firm when requested.
    * @return cuisin
    */
    public String getCuisine() {
        return cuisine;
    }
    /**
    * Method to return the open time of the firm when requested.
    * @return open time
    */
    public int getOpen_time() {
        return open_time;
    }
     /**
    * Method to return the close time of the firm when requested.
    * @return close time
    */
    public int getClose_time() {
        return close_time;
    }
     /**
    * Method to return the employee count of the firm when requested.
    * @return employee count
    */
    public int getEmployee_count() {
        return employee_count;
    }

}
