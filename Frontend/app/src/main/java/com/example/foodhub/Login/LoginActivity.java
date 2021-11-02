package com.example.foodhub.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodhub.Customer.Home.Home;
import com.example.foodhub.Customer.Home.HomeActivity;
import com.example.foodhub.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = (Button) findViewById(R.id.login_button);
        btn.setOnClickListener(this::login);
    }

    public void login(View v) {
        Intent I = new Intent(this, HomeActivity.class);
        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
        String password = ((EditText)findViewById(R.id.login_password)).getText().toString();

        I.putExtra("Email", email);
        startActivity(I);

//        String tag_json_obj = "json_obj_req";
//
//        String url = Const.URL + "/customers-login";
//
//        ProgressDialog pDialog = new ProgressDialog(this);
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//
//        Map<String, String> postParams = new HashMap<String, String>();
//        postParams.put("username", email);
//        postParams.put("password", password);
//
//        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
//            url, new JSONObject(postParams), new Response.Listener<JSONObject>() {
//                @Override
//                public void onResponse(JSONObject response) {
//                    Log.d("response", response.toString());
//                    pDialog.hide();
//                    /*
//                    if(response.get("message").equals("failure")){
//                        Toast.makeText(getApplicationContext(),"Email or password not correct, please try again.",Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                     */
//                    try {if (response.get("message").equals("success"))
//                        startActivity(I);
//                    } catch (Exception e) {Log.d("response", e.toString());}
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    VolleyLog.d("response", "Error: " + error.getMessage());
//                    pDialog.hide();
//                }
//        }) {
//                @Override
//                public Map<String, String> getHeaders() throws AuthFailureError {
//                    HashMap<String, String> headers = new HashMap<String, String>();
//                    headers.put("Content-Type", "application/json;charset=utf-8");
//                    return headers;
//                }
//        };
//        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }
}