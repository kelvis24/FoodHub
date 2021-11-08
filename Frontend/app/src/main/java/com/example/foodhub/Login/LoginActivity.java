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

/** Main login method for each type of user
 * @author 1_CW_2
 */

public class LoginActivity extends AppCompatActivity {

    private String type;
    private String email;
    private String password;

    /**
     * Creates a specified instance based on the bundle that is inputted.
     * @param savedInstanance State The Bundle that contains the instance state to be shown.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent P = getIntent();
        type = P.getStringExtra("type");
        Button btn = findViewById(R.id.login_button);
        btn.setOnClickListener(this::loginButton);
    }


/**
     * Method for commanding the login button that takes a set view.
     * Checks the inputted email and password and converts to strings.
     * Puts those email and password into a hashmap, then a JSONObject that is tested and authenticated.
     * @param v A View stage
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
     * Method for loggin in the given user should they pass authentication.
     * Ensures that there is no failure in communication between Frontend and server.
     * Checks which type of user is being logged in, puts that type of information into the appropriate creation methods.
     * Puts the correct information into the started intent, and starts the activity (The app) with that given information.
     * @param reponse A JSONObject that was inputted from the logged information
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
