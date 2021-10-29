package com.example.foodhub;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.foodhub.app.AppController;
import com.example.foodhub.net_utils.Const;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class callset extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = (Button) findViewById(R.id.login_button);
        btn.setOnClickListener(this::login);
    }

    public void login(View v) {
        Intent I = new Intent(this, HomeActivity.class);
        ByPassPasswordCheck(true, I);  //false if you want proof
    }

    private void ByPassPasswordCheck(boolean check, Intent I) {

        if (check == true) {
            startActivity(I);
        }
        else {
//        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
//        String password = ((EditText)findViewById(R.id.login_password)).getText().toString();
//        String firmset = ((EditText)findViewById(R.id.login_firm)).getText().toString();
//
//        I.putExtra("Email", email);
//        I.putExtra("Firm", firmset);
//
//        String tag_json_obj = "json_obj_req";
//
//        Map<String, String> postParams = new HashMap<String, String>();
//        postParams.put("username", email);
//        postParams.put("password", password);
//        postParams.put("Firm", firmset);
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//                url, new JSONObject(postParams), new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                Log.d("response", response.toString());
//                pDialog.hide();
//                try {if (response.get("message").equals("success"))
//                    startActivity(I);
//                } catch (Exception e) {Log.d("response", e.toString());}
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("response", "Error: " + error.getMessage());
//                pDialog.hide();
//            }
//        }) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                HashMap<String, String> headers = new HashMap<String, String>();
//                headers.put("Content-Type", "application/json;charset=utf-8");
//                return headers;
//            }
//        };
//        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
        }
    }
}