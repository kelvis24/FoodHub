package com.example.foodhub.Program;

import android.content.Context;
import android.content.res.Configuration;

import com.example.foodhub.Class.Company;
import com.example.foodhub.Class.Menu;

import java.util.ArrayList;

public class ProgramController {

    public Person Person;
    public static ArrayList<Order> Orders = new ArrayList<>();
    public static ArrayList<Menu> MenuOrders = new ArrayList<>();
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

    public static class Order {
        public String itemId;
        public String quantity;
        public String notes;

        public Order(String itemId, String quantity, String notes) {
            this.itemId = itemId;
            this.quantity = quantity;
            this.notes = notes;
        }

        public static ArrayList<Object> orderToObjectArrayConverter(ArrayList<Order> array) {

//            for (item : array
//                 ) {
//                item.itemId =
//            }
            return null;
        }

        public static void initialize(Context context, Configuration configuration) {
        }

        public static void add(Order order) {
            Orders.add(order);
        }
    }
}
