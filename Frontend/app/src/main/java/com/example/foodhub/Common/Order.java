package com.example.foodhub.Common;
import com.example.foodhub.*;

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
        if (arr != null) {
            for (int i = 0; i < arr.length(); i++) {
                list.add(new ItemReference(arr.getJSONObject(i)));
            }
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
     * @return The firm's name of the order
     */
    public String getFirm() {
        return firm;
    }

    /**
     * A getter method for the customer field
     * @return The customer's name of the order
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * A getter method for the location field
     * @return The location of the customer of the order
     */
    public String getLocation() {
        return location;
    }

    /**
     * A getter method for the status field
     * @return The status of the order
     */
    public int getStatus() {
        return status;
    }

    /**
     * A getter method for the total field
     * @return The total of the order
     */
    public double getTotal() {
        return total;
    }

    /**
     * A getter method for the list field
     * @return The list of the ItemReferences of the order
     */
    public ArrayList<ItemReference> getList() {
        return list;
    }

    public static ArrayList<Order> returnOrdersSpecificToAFirm(String firmName, ArrayList<Order> orders) {
        ArrayList<Order> newOrders = new ArrayList<>();
        for (Order order: orders) {
            if(order.getFirm().equals(firmName)) {
                newOrders.add(order);
            }
        }
        return newOrders;
    }

    public static ArrayList<Order> returnSpecificOrderbyId(long id, ArrayList<Order> orders) {
        ArrayList<Order> newOrders = new ArrayList<>();
        for (Order order: orders) {
            if(order.getId()== id) {
                newOrders.add(order);
            }
        }
        return newOrders;
    }
}
