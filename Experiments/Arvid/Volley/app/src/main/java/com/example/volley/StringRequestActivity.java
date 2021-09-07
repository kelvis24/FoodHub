package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.volley.app.AppController;

public class StringRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string_request);
        Button btn = findViewById(R.id.request_string);
        btn.setOnClickListener(this::get_response);
    }

    public void get_response(View v) {
        TextView view = findViewById(R.id.view_string);

        final String TAG = "custom";

        String tag_string_obj = "string_req";

        String url = "http://api.androidhive.info/volley/string_response.html";

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, response);
                        view.setText(response);
                        pDialog.hide();
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG,"Error: " + error.getMessage());
                        view.setText(error.getMessage());
                        pDialog.hide();
                    }
        });
        AppController.getInstance().addToRequestQueue(strReq, tag_string_obj);
    }

}