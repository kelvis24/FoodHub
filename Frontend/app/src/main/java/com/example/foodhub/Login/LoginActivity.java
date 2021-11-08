package com.example.foodhub.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import com.example.foodhub.Admin.AdminMainActivity;
import com.example.foodhub.Admin.OwnerMainActivity;
import com.example.foodhub.Customer.CustomerMainActivity;
import com.example.foodhub.Firm.FirmMainActivity;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Controls the R.layout.activity_login view
 * @author Arvid Gustafson
 * @see AppCompatActivity
 */
public class LoginActivity extends AppCompatActivity {

    private String type;
    private String email;
    private String password;

    /**
     * Binds the "login" button to its respective method
     * @param savedInstanceState A bundle that is passed in
     */
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent P = getIntent();
        type = P.getStringExtra("type");
        Button btn = findViewById(R.id.login_button);
        btn.setOnClickListener(this::loginButton);
    }

    /**
     * Sends a request to verify the claim of the user, that they have that username and password,
     *      according to what they claim to be, upon clicking the "login" button
     * @param v The "login" button
     */
    public void loginButton(View v) {
        email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
        password = ((EditText)findViewById(R.id.login_password)).getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("username", email);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        Call.post(type+"s-authenticate", obj, this::login, null);
    }

    /**
     * Upon a successful call, if they are verified, it advances to the relevant main activity
     * @param response The response from the server as a JSONObject
     */
    public void login(JSONObject response){
        String str;
        try{str = (String)response.get("message");
            if (str.equals("failure")) {
                Log.d("debug", response.toString());
                return;
            }
            Intent I = new Intent();
            switch (type) {
                case "customer":
                    I = new Intent(this, CustomerMainActivity.class);
                    break;
                case "firm":
                    I = new Intent(this, FirmMainActivity.class);
                    I.putExtra("firmId", response.getLong("id"));
                    break;
                case "admin":
                    if (str.equals("owner")) I = new Intent(this, OwnerMainActivity.class);
                    else I = new Intent(this, AdminMainActivity.class);
                    break;
            }
            I.putExtra("username", email);
            I.putExtra("password", password);
            startActivity(I);
        } catch (Exception e) {Log.d("debug", e.toString());return;}
    }

}
