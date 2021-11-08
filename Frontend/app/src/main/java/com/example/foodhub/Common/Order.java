package com.example.foodhub.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
/**
* Class responsible for creating and setting up the 'order' type
* @author 1_CW_1
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
    * Method constructor to set up an order in response to a JSONObject request
    * @param obj JSONObject from the JSONObject request
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
    * Method to return the ID of the order when requested.
    * @return id
    */
    public long getId() {
        return id;
    }
    /**
    * Method to return the firm of the order when requested.
    * @return firm
    */
    public String getFirm() {
        return firm;
    }
    /**
    * Method to return the customer of the order when requested.
    * @return customer
    */
    public String getCustomer() {
        return customer;
    }
    /**
    * Method to return the location of the order when requested.
    * @return location
    */
    public String getLocation() {
        return location;
    }
    /**
    * Method to return the status of the order when requested.
    * @return status
    */
    public int getStatus() {
        return status;
    }   
    /**
    * Method to return the total of the order when requested.
    * @return total
    */
    public double getTotal() {
        return total;
    }
    /**
    * Method to return the list of item references of the order when requested.
    * @return id
    */
    public ArrayList<ItemReference> getList() {
        return list;
    }

}
