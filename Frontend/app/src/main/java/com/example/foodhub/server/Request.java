package com.example.foodhub.server;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Request {

    public static void getRequest(String route, JSONResponse success, ErrorResponse error) {
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

    public static void postRequest(String route, JSONObject obj, JSONResponse success, ErrorResponse error) {
        JsonObjectRequest request = new JsonObjectRequest(POST, CONST.URL + route, obj, success::respond,
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

}
