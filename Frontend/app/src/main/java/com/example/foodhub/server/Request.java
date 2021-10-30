package com.example.foodhub.server;

import static com.android.volley.Request.Method.GET;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Request {

    public static void getRequest(String url, JSONObject obj, JSONResponse success, ErrorResponse error) {
        JsonObjectRequest request = new JsonObjectRequest(GET, url, null,
        new Response.Listener<JSONObject>() {
            @Override public void onResponse(JSONObject response) {
                success.respond(response);
        }}, new Response.ErrorListener() {
            @Override public void onErrorResponse(VolleyError response) {
                error.respond(response);
        }}) {
            @Override public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json;charset=utf-8");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }

}
