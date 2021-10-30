package com.example.foodhub.server;

import com.android.volley.VolleyError;

public interface ErrorResponse {

    public void respond(VolleyError error);

}
