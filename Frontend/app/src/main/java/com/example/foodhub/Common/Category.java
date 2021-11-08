package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Formats the qualities of a category
 * @author Arvid Gustafson
 */
public class Category {

    private final long id;
    private final String title;
    private final String description;

    /**
     * Constructs a Category given a JSONObject
     * @param obj A JSONObject with the same information format
     */
    public Category(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.title = (String)obj.get("title");
        this.description = (String)obj.get("description");
    }

    /**
     * Constructs a Category given enumerated information
     * @param id The id of the category
     * @param title The title of the category
     * @param description The description of the category
     */
    public Category(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    /**
     * A getter for the id field
     * @return The id of the category
     */
    public long getId() {
        return id;
    }

    /**
     * A getter for the title field
     * @return The title of the category
     */
    public String getTitle() {
        return title;
    }

    /**
     * A getter for the description field
     * @return The description of the category
     */
    public String getDescription() {
        return description;
    }

}
