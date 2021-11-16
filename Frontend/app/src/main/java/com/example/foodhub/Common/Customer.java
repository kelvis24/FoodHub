package com.example.foodhub.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Customer {
    private long id;
    private String username;
    private String name;
    private String location;
    private String password;

    /**
     * Constructs a customer given all information thereon
     * @param username The username of the new customer
     * @param password The password of the new customer
     * @param name The name of the new customer
     * @param location The location of the new customer
     */
    public Customer(String username, String password, String name, String location) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.location = location;
    }

    /**
     * A default constructor
     */
    public Customer() {}

    public Customer(JSONObject obj) throws JSONException {

        this.id = obj.getLong("id");
        this.username = (String)obj.get("username");
        this.password = (String)obj.get("password");
        this.name = (String)obj.get("name");
        this.location = (String)obj.get("location");
    }

    public static Customer SortExactCustomer(String username, ArrayList<Customer> Customers) {
        for (Customer customer: Customers) {
            if (customer.getUsername() == username) {
                return  customer;
            }
        }
        return null;
    }

    /**
     * A getter for the id field
     * @return The id of the customer
     */
    public long getId() {
        return id;
    }

    /**
     * A getter for the username field
     * @return The usernmae of the customer
     */
    public String getUsername() {
        return username;
    }

    /**
     * A getter for the password field
     * @return The password of the customer
     */
    public String getPassword() {
        return password;
    }

    /**
     * A getter for the name of the customer
     * @return The name of the customer
     */
    public String getName() {
        return name;
    }

    /**
     * A getter for the location of the customer
     * @return The location of the customer
     */
    public String getLocation() {
        return location;
    }
}
