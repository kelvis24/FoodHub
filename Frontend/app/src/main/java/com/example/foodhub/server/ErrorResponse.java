package com.example.foodhub.server;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

public interface ErrorResponse {

    void respond(VolleyError error);

    static ErrorResponse getBasic() {
        return new BasicErrorResponse();
    }

    class BasicErrorResponse implements ErrorResponse {
        public void respond(VolleyError error) {
            VolleyLog.d("Error", "Error: " + error.getMessage());
        }
    }

}
