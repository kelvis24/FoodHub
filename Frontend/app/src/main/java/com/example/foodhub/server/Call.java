package com.example.foodhub.server;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Has methods that add some JSON volley request to the request queue, "making a request;"
 *      an "output" is the parameter of the method called upon success.
 * @author Arvid Gustafson
 */
public class Call {

    /**
     * Makes a get request that "outputs" a JSONObject
     * @param route The route of the request
     * @param success The method called upon a successful call
     * @param error The method called upon an unsuccessful call; a default is used if error is null
     */
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
            }
        };
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }

    /**
     * Makes a get request that "outputs" a JSONArray
     * @param route The route of the request
     * @param success The method called upon a successful call
     * @param error The method called upon an unsuccessful call; a default is used if error is null
     */
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
            }
        };
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }

    /**
     * Makes a post request that accepts a JSONObject "outputs" a JSONObject
     * @param route The route of the request
     * @param obj The JSONObject that is accepted as an input
     * @param success The method called upon a successful call
     * @param error The method called upon an unsuccessful call; a default is used if error is null
     */
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

    /**
     * Makes a post request that accepts a JSONObject "outputs" a JSONArray
     * @param route The route of the request
     * @param obj The JSONObject that is accepted as an input
     * @param success The method called upon a successful call
     * @param error The method called upon an unsuccessful call; a default is used if error is null
     */
    public static void post(String route, JSONObject obj, ArrayResponse success, ErrorResponse error) {
        String str = obj.toString();
        StringRequest request = new StringRequest(POST, Const.URL + route,
            response -> {
                try {success.respond(new JSONArray(response));
                } catch (JSONException e) {e.printStackTrace();}
            }, response -> {
                if (error == null) ErrorResponse.getBasic().respond(response);
                else error.respond(response);
            }) {
            @Override public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
            @Override public byte[] getBody() {
                try {return str.getBytes(StandardCharsets.UTF_8);
                } catch (Exception e) {e.printStackTrace();return null;}
            }
            @Override public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json;charset=utf-8");
                return headers;
            }
        };
        AppController.getInstance().addToRequestQueue(request, "json_obj_req");
    }

}
