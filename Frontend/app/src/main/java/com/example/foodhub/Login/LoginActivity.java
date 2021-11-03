package com.example.foodhub.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import com.example.foodhub.Admin.AdminHome.AdminHomeActivity;
import com.example.foodhub.Customer.Home.CustomerHomeActivity;
import com.example.foodhub.Firm.FirmHomeActivity;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private String route;
    private Intent I;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent P = getIntent();
        String type = P.getStringExtra("type");
        Button btn = findViewById(R.id.login_button);
        btn.setOnClickListener(this::loginButton);
        switch (type) {
            case "customer":
                route = "customers-authenticate";
                I = new Intent(this, CustomerHomeActivity.class);
                break;
            case "firm":
                route = "firms-authenticate";
                I = new Intent(this, FirmHomeActivity.class);
                break;
            case "admin":
                route = "admins-authenticate";
                I = new Intent(this, AdminHomeActivity.class);
                break;
        }
    }

    public void loginButton(View v) {
        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
        String password = ((EditText)findViewById(R.id.login_password)).getText().toString();

        I.putExtra("email", email);
        I.putExtra("password", password);

        Map<String, String> map = new HashMap<>();
        map.put("username", email);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);

        Call.post(route, obj, this::login, null);
    }

    public void login(JSONObject response){
        try {
            if (response.get("message").equals("success"))
                startActivity(I);
            else if (response.get("message").equals("admin"))
                startActivity(I);
            else if (response.get("message").equals("owner"))
                startActivity(I);
            else
                Log.d("debug", response.toString());
        } catch (Exception e) {Log.d("response", e.toString());}
    }

}
