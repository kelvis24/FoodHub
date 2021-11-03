package com.example.foodhub.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import com.example.foodhub.Customer.Home.Home;
import com.example.foodhub.Customer.Home.HomeActivity;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private Intent I;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn = (Button) findViewById(R.id.login_button);
        btn.setOnClickListener(this::login);
    }

    public void login(View v) {
        I = new Intent(this, HomeActivity.class);
        String email = ((EditText)findViewById(R.id.login_email_address)).getText().toString();
        String password = ((EditText)findViewById(R.id.login_password)).getText().toString();

        I.putExtra("Email", email);

        HashMap<String, String> mapset = new HashMap<>();
        mapset.put("username", email);
        mapset.put("password", password);
        JSONObject obj = new JSONObject(mapset);

        Call.post("costomers-authenticate", obj, this::loginset, null);
    }

    public void loginset(JSONObject response){
        //Log.d("response", response.toString());
        try {if (response.get("message").equals("success"))
            startActivity(I);
        } catch (Exception e) {Log.d("response", e.toString());}
    }
}
