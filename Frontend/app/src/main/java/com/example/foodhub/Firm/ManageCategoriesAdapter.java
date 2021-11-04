package com.example.foodhub.Firm;

import com.example.foodhub.Common.Category;

import java.util.ArrayList;

public class ManageCategoriesAdapter {

    private long firmId;
    private String username;
    private String password;

    private ManageCategoriesFragment fragment;

    private ArrayList<Category> categories;

    public ManageCategoriesAdapter(long firmId, String username, String password,
            ManageCategoriesFragment fragment, ArrayList<Category> categories) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.categories = categories;
    }

}
