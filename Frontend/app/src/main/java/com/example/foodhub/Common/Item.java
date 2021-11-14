package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Formats the qualities of an item
 * @author Arvid Gustafson
 */
public class Item {

    private final long id;
    private final String title;
    private final String description;
    private final double price;

    /**
     * Constructs an Item given a JSONObject
     * @param obj A JSONObject with the same information format
     */
    public Item(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.title = (String)obj.get("title");
        this.description = (String)obj.get("description");
        this.price = obj.getDouble("price");
    }

    /**
     * Constructs an Item given enumerated information
     * @param id The id of the item
     * @param title The title of the item
     * @param description The description of the item
     * @param price The price of the item
     */
    public Item(long id, String title, String description, double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
    }

    /**
     * A getter method for the id field
     * @return The id of the item
     */
    public long getId() {
        return id;
    }

    /**
     * A getter method for the title field
     * @return The title of the item
     */
    public String getTitle() {
        return title;
    }

    /**
     * A getter method for the description field
     * @return The description of the item
     */
    public String getDescription() {
        return description;
    }

    /**
     * A getter method for the price field
     * @return The price of the item
     */
    public double getPrice() {
        return price;
    }

}
