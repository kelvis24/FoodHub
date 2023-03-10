package com.example.foodhub.Login;

import static com.example.foodhub.Common.FoodhubUtils.AreInvalidFields;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
import android.widget.Toast;

import com.example.foodhub.Admin.AdminMainActivity;
import com.example.foodhub.Admin.OwnerMainActivity;
import com.example.foodhub.Customer.CustomerMainActivity;
import com.example.foodhub.Firm.FirmMainActivity;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls the R.layout.activity_login view
 * @author Arvid Gustafson, Elvis Kimara.
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
        ArrayList<String> list = new ArrayList<>();
        list.add(email);
        list.add(password);
        if (AreInvalidFields(this, list)) return;
        Map<String, String> map = new HashMap<>();
        map.put("password", password);
        map.put("username", email);
        JSONObject obj = new JSONObject(map);
        Call.post(type+"s-authenticate", obj, this::login, null);

     //   Authenticate(true, obj);
    }

    /**
     * Takes in parameter bool and JSONObject
     * If true, does the authentication with the backend,
     * if false, forces or mimics a log in
     */
    private void Authenticate(boolean bool, JSONObject obj) {
        if (bool){
            Call.post(type+"s-authenticate", obj, this::login, null);
        }else {
            Intent I = new Intent();
            switch (type) {
                case "customer":
                    I = new Intent(this, CustomerMainActivity.class);
                    I.putExtra("username", "e");
                    I.putExtra("password", "e");
                    break;
                case "firm":
                    I = new Intent(this, FirmMainActivity.class);
                    I.putExtra("username", "arvidg@iastate.edu");
                    I.putExtra("password", "aA0/aaaaaaaa");
                    I.putExtra("firmId", 2);
                    break;
                case "admin":
                  //  doesn't work for owner type
                    I = new Intent(this, AdminMainActivity.class);
                    I.putExtra("username", "agustafson");
                    I.putExtra("password", "a");
                    break;
            }
            startActivity(I);
        }
    }

    /**
     * Upon a successful call, if they are verified, it advances to the relevant main activity
     * @param response The response from the server as a JSONObject
     */
    public void login(JSONObject response){
        String str;
        try{str = (String)response.get("message");
            if (str.equals("failure")) {
                Toast.makeText(getApplicationContext(),(String)response.get("error"),Toast.LENGTH_SHORT).show();
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
