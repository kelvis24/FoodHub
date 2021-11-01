package com.example.foodhub.server;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Call {

    public static void get(String route, ObjectResponse success, ErrorResponse error) {
        JsonObjectRequest request = new JsonObjectRequest(GET, Const.URL + route, null, success::respond,
            response -> {
                if (error == null) ErrorResponse.getBasic().respond(response);
                else error.respond(response);
            }) {
            @Override public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=utf-8");
                return headers;
        }};
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }

    public static void get(String route, ArrayResponse success, ErrorResponse error) {
        JsonArrayRequest request = new JsonArrayRequest(GET, Const.URL + route, null, success::respond,
                response -> {
                    if (error == null) ErrorResponse.getBasic().respond(response);
                    else error.respond(response);
                }) {
            @Override public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=utf-8");
                return headers;
            }};
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }

    public static void post(String route, JSONObject obj, ObjectResponse success, ErrorResponse error) {
        JsonObjectRequest request = new JsonObjectRequest(POST, Const.URL + route, obj, success::respond,
            response -> {
                if (error == null) ErrorResponse.getBasic().respond(response);
                else error.respond(response);
            }) {
            @Override public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=utf-8");
                return headers;
        }};
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }

    /*
    public static void post(String route, JSONObject obj, ArrayResponse success, ErrorResponse error) {
        JsonArrayRequest request = new JsonArrayRequest(POST, Const.URL + route, obj, success::respond,
                response -> {
                    if (error == null) ErrorResponse.getBasic().respond(response);
                    else error.respond(response);
                }) {
            @Override public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=utf-8");
                return headers;
            }};
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }
    */

}
