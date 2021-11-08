package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

/**
* Class responsible for creating and setting up the 'item' type
* @author 1_CW_1
*/
public class Item {

    private final long id;
    private final String title;
    private final String description;
    private final double price;
    /**
    * Method constructor to set up an item in response to a JSONObject request
    * @param obj JSONObject from the JSONObject request
    */
    public Item(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.title = (String)obj.get("title");
        this.description = (String)obj.get("description");
        this.price = obj.getDouble("price");
    }
    /**
    * Method to input values into the given item request
    * @param id ID of the item
    * @param title Title of the item
    * @param description Description of the item
    * @param price Price of the given item
    */
    public Item(long id, String title, String description, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }
    /**
    * Method to return the ID of the item when requested.
    * @return id
    */
    public long getId() {
        return id;
    }
    /**
    * Method to return the title of the item when requested.
    * @return title
    */
    public String getTitle() {
        return title;
    }
    /**
    * Method to return the description of the item when requested.
    * @return description
    */
    public String getDescription() {
        return description;
    }
    /**
    * Method to return the price of the item when requested.
    * @return price
    */
    public double getPrice() {
        return price;
    }

}
