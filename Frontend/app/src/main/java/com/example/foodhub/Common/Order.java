package com.example.foodhub.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Formats the qualities of an order
 * @author Arvid Gustafson
 */
public class Order {

    private long id;
    private String firm;
    private String customer;
    private String location;
    private int status;
    private double total;
    private ArrayList<ItemReference> list;

    /**
     * Constructs an Order given a JSONObject
     * @param obj A JSONObject with the same information format
     */
    public Order(JSONObject obj) throws JSONException {
        this.id = obj.getLong("id");
        this.firm = (String)obj.get("firm");
        this.customer = (String)obj.get("customer");
        this.location = (String)obj.get("location");
        this.status = obj.getInt("status");
        this.total = obj.getDouble("total");
        JSONArray arr = obj.getJSONArray("orderList");
        list = new ArrayList<ItemReference>();
        for (int i = 0; i < arr.length(); i++) {
            list.add(new ItemReference(arr.getJSONObject(i)));
        }
    }

    /**
     * A getter method for the id field
     * @return The id of the order
     */
    public long getId() {
        return id;
    }

    /**
     * A getter method for the firm field
     * @return The firm of the order
     */
    public String getFirm() {
        return firm;
    }

    /**
     * A getter method for the id field
     * @return The id of the order
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * A getter method for the id field
     * @return The id of the order
     */
    public String getLocation() {
        return location;
    }

    /**
     * A getter method for the id field
     * @return The id of the order
     */
    public int getStatus() {
        return status;
    }

    /**
     * A getter method for the id field
     * @return The id of the order
     */
    public double getTotal() {
        return total;
    }

    /**
     * A getter method for the id field
     * @return The id of the order
     */
    public ArrayList<ItemReference> getList() {
        return list;
    }

}
