package com.example.foodhub.server;

import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;

public interface ErrorResponse {

    void respond(VolleyError error);

    static ErrorResponse getBasic() {
        return new BasicErrorResponse();
    }

    class BasicErrorResponse implements ErrorResponse {
        public void respond(VolleyError error) {
            // Toast.makeText(null,"Volley Error", Toast.LENGTH_SHORT).show();
            VolleyLog.d("Error", "Error: " + error.getMessage());
        }
    }

}
