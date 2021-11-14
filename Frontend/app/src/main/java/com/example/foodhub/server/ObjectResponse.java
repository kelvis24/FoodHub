package com.example.foodhub.server;

import org.json.JSONObject;

/**
 * A (redundant) interface; a model for responses from the Call class that accepts a JSONObject
 * @author Arvid Gustafson
 */
public interface ObjectResponse {

    /**
     * A class that is called upon a successful call that receives a JSONObject
     * @param obj The "output" of the server
     */
    void respond(JSONObject obj);

}
