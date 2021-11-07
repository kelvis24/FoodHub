package com.example.foodhub.Login;

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

import java.util.HashMap;
import java.util.Map;

/** 
 * The class responsible for signing up users.
 * @author 1_CW_2
*/

public class SignUpActivity extends AppCompatActivity {

    Intent I;

    /** Creates an initial instance that displays the on screen.
    * @param savedInstanceState bundle of a saved instance brought up on creation
    * @return NULL
    */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Button btn = findViewById(R.id.add_customer_button);
        btn.setOnClickListener(this::signUp);
    }
    /** The main method for creating sign up, letting the user input their name, email, location, password, and letting them
    * confirm said password. It utalizes the Intent I and after making sure that all information meets
    * premade specifications, it adds those pieces of information to a Hashmap to be used with a JSON object request. 
    * @param v A View 
    * @return NULL
    */
    public void signUp(View v) {
        I = new Intent(this, CustomerMainActivity.class);

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

        Call.post("general-create-customer", obj, this::signup, null);
    }

    /** Method for signup, that takes in a JSONObject response and begins the activity should the message response equal success
    * @param response A JSONObject inputted from the signUP class
    * @return NULL
    */
    public void signup(JSONObject response) {
        try{if (response.get("message").equals("success"))
            startActivity(I);
        } catch (Exception e) {Log.d("response", e.toString());}
    }

    /** Method for checking the strings on the signUp method so that the password has a lowercse letter, 
    * an uppercase letter, and a number.
    * @param str a String that is inputted from the password check
    * @param start A char that is the first character of the string str
    * @param end A char that is the last character of the string str
    * @return A True or False
    */
    public boolean notHasBetween(String str, char start, char end) {
        int i;
        for (i = 0; i < str.length(); i++) {
            if (start <= str.charAt(i) && str.charAt(i) <= end)
                return false;
        }
        return true;
    }
    /** Method for checking the strings on the signUp method so that the password has a symbol.
    * @param str a String that is inputted from the password check
    * @return A True or False
    */
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
