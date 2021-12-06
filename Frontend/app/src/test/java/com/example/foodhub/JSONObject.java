package com.example.foodhub;

import com.example.foodhub.Common.ItemReference;

import org.json.JSONArray;

import java.util.ArrayList;

public interface JSONObject {
    String getLong(String id);
    String get(String string);
    String getInt(String string);
    String getDouble(String string);
//
//       this.id = obj.getLong("id");
//        this.firm = (String)obj.get("firm");
//        this.customer = (String)obj.get("customer");
//        this.location = (String)obj.get("location");
//        this.status = obj.getInt("status");
//        this.total = obj.getDouble("total");
//    JSONArray arr = obj.getJSONArray("orderList");
//    list = new ArrayList<ItemReference>();
//        for (int i = 0; i < arr.length(); i++) {
//        list.add(new ItemReference(arr.getJSONObject(i)));
}
