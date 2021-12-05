package com.example.foodhub;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.server.ErrorResponse;
import com.example.foodhub.server.ObjectResponse;

import java.util.ArrayList;

public interface FakeCall {
    public static ArrayList<Firm> get(String route, ObjectResponse success, ErrorResponse error) {
        return null;
    }
}
