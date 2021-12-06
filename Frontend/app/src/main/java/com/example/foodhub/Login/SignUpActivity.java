package com.example.foodhub.Login;

import static com.example.foodhub.Common.FoodhubUtils.AreInvalidFields;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodhub.Customer.CustomerMainActivity;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls the R.layout.activity_sign_up view
 * @author Arvid Gustafson
 * @see AppCompatActivity
 */
public class SignUpActivity extends AppCompatActivity {

    Intent I;

    /**
     * Binds the "sign up" button to its respective method
     * @param savedInstanceState A bundle that is passed in
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button btn = findViewById(R.id.add_customer_button);
        btn.setOnClickListener(this::signUp);
    }

    /**
     * If valid, sends a request to sign-up the user upon clicking the "sign up" button
     * @param v The "sign up" button
     */
    public void signUp(View v) {
        I = new Intent(this, CustomerMainActivity.class);
        String name = ((EditText)findViewById(R.id.sign_up_name_field)).getText().toString();
        String email = ((EditText)findViewById(R.id.sign_up_email_field)).getText().toString();
        String location = ((EditText)findViewById(R.id.sign_up_location_field)).getText().toString();
        String password = ((EditText)findViewById(R.id.sign_up_password_field)).getText().toString();
        String cPassword = ((EditText)findViewById(R.id.sign_up_confirm_password_field)).getText().toString();
        ArrayList<String> list = new ArrayList<>();
        list.add(name);
        list.add(email);
        list.add(location);
        list.add(password);
        list.add(cPassword);
        if (AreInvalidFields(this, list, password, cPassword)) return;
        I.putExtra("Name", name);
        I.putExtra("Email", email);
        I.putExtra("Location", location);
        I.putExtra("username", email);
        I.putExtra("password", password);
        Map<String,String> map = new HashMap<>();
        map.put("username", email);
        map.put("password", password);
        map.put("name",     name);
        map.put("location", location);
        JSONObject obj = new JSONObject(map);
        Call.post("general-create-customer", obj, this::signup, null);
    }

    /**
     * Upon a successful call, if sign up succeeds, it advances to the customer's main activity
     * @param response The response from the server as a JSONObject
     */
    public void signup(JSONObject response) {
        try{if (response.get("message").equals("success")) startActivity(I);
            else Toast.makeText(getApplicationContext(),(String)response.get("error"),Toast.LENGTH_SHORT).show();
        } catch (Exception e) {Log.d("response", e.toString());}
    }

}
