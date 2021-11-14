package com.example.foodhub.server;

import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

/**
 * A (semi-redundant) interface; a model for responses from the Call class that accepts a VolleyError
 * @author Arvid Gustafson
 */
public interface ErrorResponse {

    /**
     * A class that is called upon an unsuccessful call that receives a VolleyError
     * @param error The error of the call
     */
    void respond(VolleyError error);

    /**
     * Returns a default error response
     * @return A default error response
     */
    static ErrorResponse getBasic() {
        return new BasicErrorResponse();
    }

    /**
     * A class with a default error response method
     */
    class BasicErrorResponse implements ErrorResponse {
        /**
         * A default error response method
         */
        public void respond(VolleyError error) {
            // Toast.makeText(null,"Volley Error", Toast.LENGTH_SHORT).show();
            VolleyLog.d("Error", "Error: " + error.getMessage());
        }
    }
}
