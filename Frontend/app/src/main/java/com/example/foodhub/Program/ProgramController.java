package com.example.foodhub.Program;

import com.example.foodhub.Class.Company;

import java.util.ArrayList;

public class ProgramController {

    public Person Person;
    public ArrayList<Order> Orders;
    public ArrayList<Company> Firms;

    public ProgramController() {
        Person = new Person();
        Orders = new ArrayList<>();
        Firms = null;
    }

    public class Person {
        public String username;
        public String password;
        public String name;
        public String location;

        public Person() {

        }

        public Person(String username, String password, String name, String location) {
            this.username = username;
            this.password = password;
            this.name = name;
            this.location = location;
        }
    }

    public class Order {
        public String itemId;
        public String quantity;
        public String notes;

        public Order(String itemId, String quantity, String notes) {
            this.itemId = itemId;
            this.quantity = quantity;
            this.notes = notes;
        }

    }
}
