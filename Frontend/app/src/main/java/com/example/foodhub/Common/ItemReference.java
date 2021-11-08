package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * Formats the qualities of an item as it would appear on an order
 * @author Arvid Gustafson
 */
public class ItemReference {

    private Item item;
    private int quantity;
    private String notes;

    /**
     * Constructs an ItemReference given a JSONObject
     * @param obj A JSONObject with the same information format
     */
    public ItemReference(JSONObject obj) throws JSONException {
        item = new Item(-1, (String)obj.get("title"), (String)obj.get("description"), obj.getDouble("price"));
        this.quantity = obj.getInt("quantity");
        this.notes = "";
    }

    /**
     * Constructs an ItemReference given enumerated information
     * @param item The qualities of the referenced item
     * @param quantiy The quantity thereof ordered
     * @param notes The notes regarding the item
     */
    public ItemReference(Item item, int quantity, String notes) {
        this.item = item;
        this.quantity = quantity;
        this.notes = notes;
    }

    /**
     * A getter method for the item field
     * @return The item of the ItemReference
     */
    public Item getItem() {
        return item;
    }

    /**
     * A getter method for the quantity field
     * @return The quantity of the ItemReference
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * A getter method for the notes field
     * @return The notes of the ItemReference
     */
    public String getNotes() {
        return notes;
    }

}
