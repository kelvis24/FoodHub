package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

/**
* Class responsible for creating and setting up the 'admin' user type
* @author 1_CW_1
*/
public class Admin {

    private final long id;
    private final String username;
    private final String name;
    private final int type;
    /**
    * Method constructor to set up an admin user in response to a JSONObject request
    * @param obj JSONObject from the JSONObject request
    */
    public Admin(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.username = (String)obj.get("username");
        this.name = (String)obj.get("name");
        this.type = obj.getInt("type");
    }
    /**
    * Method to input values into the given admin request
    * @param id ID of the amin
    * @param username User admin's username
    * @param name User admin's name
    * @param type User admin's type designating them owner or admin
    */
    public Admin(long id, String username, String name, int type) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.type = type;
    }
    /**
    * Method to return the ID of the admin when requested.
    * @return id
    */
    public long getId() {
        return id;
    }
    /**
    * Method to return the username of the admin when requested.
    * @return username
    */
    public String getUsername() {
        return username;
    }
    /**
    * Method to return the name of the admin when requested.
    * @return name
    */
    public String getName() {
        return name;
    }
    /**
    * Method to return the type of the admin when requested.
    * @return TYPE
    */
    public int getType() {
        return type;
    }

}
