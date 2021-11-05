package com.example.foodhub.Common;

import org.json.JSONException;
import org.json.JSONObject;

public class ItemReference {

    private Item item;
    private int quantity;
    private String notes;

    public ItemReference(JSONObject obj) throws JSONException {
        item = new Item(-1, (String)obj.get("title"), (String)obj.get("description"), obj.getDouble("price"));
        this.quantity = obj.getInt("quantity");
        this.notes = "";
    }

    public ItemReference(Item item, int quantity, String notes) {
        this.item = item;
        this.quantity = quantity;
        this.notes = notes;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getNotes() {
        return notes;
    }

}
