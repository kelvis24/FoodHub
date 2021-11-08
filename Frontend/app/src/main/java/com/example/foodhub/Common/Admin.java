package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Keeps track of the qualities of admins for listing them
 * @author Arvid Gustafson
 */
public class Admin {

    private final long id;
    private final String username;
    private final String name;
    private final int type;

    /**
     * Constructs an Admin given a JSONObject
     * @param obj A JSONObject with the same information
     */
    public Admin(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.username = (String)obj.get("username");
        this.name = (String)obj.get("name");
        this.type = obj.getInt("type");
    }

    /**
     * Constructs an Admin given enumerated information
     * @param id The id of the admin
     * @param username The username of the admin
     * @param name The name of the admin
     * @param type The type of the admin, 1 if it is an owner
     */
    public Admin(long id, String username, String name, int type) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.type = type;
    }

    /**
     * A getter method for the id field
     * @return The id of the admin
     */
    public long getId() {
        return id;
    }

    /**
     * A getter method for the username field
     * @return The username of the admin
     */
    public String getUsername() {
        return username;
    }

    /**
     * A getter method for the name field
     * @return The name of the admin
     */
    public String getName() {
        return name;
    }

    /**
     * A getter method for the type field
     * @return The type of the admin: 1 if owner; 0 if admin
     */
    public int getType() {
        return type;
    }

}
