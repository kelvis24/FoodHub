package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;
/**
* Class responsible for creating and setting up the 'catagory' type that will be falled throughout the code
* @author 1_CW_1
*/
public class Category {

    private final long id;
    private final String title;
    private final String description;
    /**
    * Method constructor to set up an catagory in response to a JSONObject request
    * @param obj JSONObject from the JSONObject request
    */
    public Category(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.title = (String)obj.get("title");
        this.description = (String)obj.get("description");
    }
    /**
    * Method to input values into the given category request
    * @param id ID of the category
    * @param title Title of the category
    * @param description String describing the category
    */
    public Category(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    /**
    * Method to get the id of the category.
    * @return id
    */
    public long getId() {
        return id;
    }   
    /**
    * Method to get the title of the category.
    * @return title
    */
    public String getTitle() {
        return title;
    }
    /**
    * Method to get the description of the category.
    * @return description
    */
    public String getDescription() {
        return description;
    }

}
