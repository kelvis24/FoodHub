package com.example.foodhub.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Order {

    private long id;
    private String firm;
    private String customer;
    private String location;
    private int status;
    private double total;
    private ArrayList<ItemReference> list;

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

    public long getId() {
        return id;
    }

    public String getFirm() {
        return firm;
    }

    public String getCustomer() {
        return customer;
    }

    public String getLocation() {
        return location;
    }

    public int getStatus() {
        return status;
    }

    public double getTotal() {
        return total;
    }

    public ArrayList<ItemReference> getList() {
        return list;
    }

}
