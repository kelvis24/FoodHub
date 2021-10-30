package com.example.foodhub.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodhub.Customer.Home.HomeActivity;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    Intent I;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button btn = findViewById(R.id.add_customer_button);
        btn.setOnClickListener(this::signUp);
    }

    public void signUp(View v) {
        I = new Intent(this, HomeActivity.class);

        String name = ((EditText)findViewById(R.id.sign_up_name_field)).getText().toString();
        String email = ((EditText)findViewById(R.id.sign_up_email_field)).getText().toString();
        String location = ((EditText)findViewById(R.id.sign_up_location_field)).getText().toString();
        String password = ((EditText)findViewById(R.id.sign_up_password_field)).getText().toString();
        String cPassword = ((EditText)findViewById(R.id.sign_up_confirm_password_field)).getText().toString();

        I.putExtra("Name", name);
        I.putExtra("Email", email);
        I.putExtra("Location", location);

        if (name.length() == 0 || email.length() == 0 || location.length() == 0) {
            Toast.makeText(getApplicationContext(),"Please Enter Something In All Fields.",Toast.LENGTH_SHORT).show();
            return;
        }
        if (notHasBetween(password, 'a', 'z')) {
            Toast.makeText(getApplicationContext(),"Password Must Have A Lower Case Letter.",Toast.LENGTH_SHORT).show();
            return;
        }
        if (notHasBetween(password, 'A', 'Z')) {
            Toast.makeText(getApplicationContext(),"Password Must Have A Upper Case Letter.",Toast.LENGTH_SHORT).show();
            return;
        }
        if (notHasBetween(password, '0', '9')) {
            Toast.makeText(getApplicationContext(),"Password Must Have A Number.",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!hasSymbol(password)) {
            Toast.makeText(getApplicationContext(),"Password Must Have A Symbol.",Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 12) {
            Toast.makeText(getApplicationContext(),"Password must have at least 12 characters.",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password.equals(cPassword)) {
            Toast.makeText(getApplicationContext(),"Passwords Do Not Match",Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String,String> map = new HashMap<>();
        map.put("username", email);
        map.put("password", password);
        map.put("name",     name);
        map.put("location", location);
        JSONObject obj = new JSONObject(map);

        Call.post("/general-add-customer", obj, this::signup, null);
    }

    public void signup(JSONObject response) {
        Log.d("response", response.toString());
        try {if (response.get("message").equals("success"))
            startActivity(I);
        } catch (Exception e) {Log.d("response", e.toString());}
    }

    public boolean notHasBetween(String str, char start, char end) {
        int i;
        for (i = 0; i < str.length(); i++) {
            if (start <= str.charAt(i) && str.charAt(i) <= end)
                return false;
        }
        return true;
    }

    public boolean hasSymbol(String str) {
        int i;
        for (i = 0; i < str.length(); i++) {
            if (!('a' <= str.charAt(i) && str.charAt(i) <= 'z') &&
                !('A' <= str.charAt(i) && str.charAt(i) <= 'Z') &&
                !('0' <= str.charAt(i) && str.charAt(i) <= '9'))
                return true;
        }
        return false;
    }

}
