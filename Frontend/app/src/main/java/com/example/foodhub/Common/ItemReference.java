package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;
/**
* Class responsible for creating and setting up the reference for the item type
* @author 1_CW_1
*/
public class ItemReference {

    private Item item;
    private int quantity;
    private String notes;
    /**
    * Method constructor to set up an item reference in response to a JSONObject request
    * @param obj JSONObject from the JSONObject request
    */
    public ItemReference(JSONObject obj) throws JSONException {
        item = new Item(-1, (String)obj.get("title"), (String)obj.get("description"), obj.getDouble("price"));
        this.quantity = obj.getInt("quantity");
        this.notes = "";
    }
    /**
    * Method to input values into the given admin request
    * @param item The item for the request
    * @param quantity Number of the Item in stock
    * @param notes Notes for the given item
    */
    public ItemReference(Item item, int quantity, String notes) {
        this.item = item;
        this.quantity = quantity;
        this.notes = notes;
    }
    /**
    * Method to return the item of the item request when requested.
    * @return item
    */
    public Item getItem() {
        return item;
    }
    /**
    * Method to return the quantity of the item request when requested.
    * @return quantity
    */
    public int getQuantity() {
        return quantity;
    }
    /**
    * Method to return the notes of the item request when requested.
    * @return notes
    */
    public String getNotes() {
        return notes;
    }

}
