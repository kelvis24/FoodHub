package com.example.textbubble;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.textbubble.app.AppController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(this::send);
    }

    public void send(View v) {
        EditText input = findViewById(R.id.input);
        String txt = input.getText().toString();
        TextView output = findViewById(R.id.output);

        final String TAG = "custom";

        String tag_string_obj = "string_req";

        String url = "http://10.0.2.2:8000/postText";

        ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                output.setText(response);
                Log.d(TAG, response);
                pDialog.hide();
            }
        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                output.setText(error.getMessage());
                VolleyLog.d(TAG,"Error: " + error.getMessage());
                pDialog.hide();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("text",txt);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(strReq, tag_string_obj);

    }

}