package com.example.foodhub.server;

import org.json.JSONArray;

/**
 * A (redundant) interface; a model for responses from the Call class that accepts a JSONArray
 * @author Arvid Gustafson
 */
public interface ArrayResponse {

    /**
     * A class that is called upon a successful call that receives a JSONArray
     * @param arr The "output" of the server
     */
    void respond(JSONArray arr);

}