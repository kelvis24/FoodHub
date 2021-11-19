package com.example.foodhub.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodhub.Login.InitialActivity;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A controller for the R.layout.fragment_customer_account view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class CustomerAccountFragment extends Fragment {

    private String username;
    private String email;
    private String location;
    private String password;
    private View view;


    public CustomerAccountFragment(String username, String email, String location, String password) {
        this.username = username;
        this.email = email;
        this.location = location;
        this.password = password;
    }

    public CustomerAccountFragment() {
        this.username = null;
        this.email = null;
        this.location = null;
        this.password = null;
    }

    /**
     * Does bookkeeping with the onCreate method
     * @param savedInstanceState a bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
//            email = getArguments().getString("email");
//            location = getArguments().getString("location");
            password = getArguments().getString("password");

//            setArguments(savedInstanceState);
        }
    }

    /**
     * Ties the "logout" button to its function upon creation of the view
     * @param inflater a layout inflater
     * @param container the larger container of the view (the layout view that contains the fragment)
     * @param savedInstanceState a bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_account, container, false);
        Button btn = view.findViewById(R.id.customer_logout_button);
        btn.setOnClickListener(this::goToSignIn);
        Button btn1 = view.findViewById(R.id.editCustomerAccountButton);
        btn1.setOnClickListener(this::clickToSeeEditCustomerPages);
        this.view = view;
        getCustomerDetails();
        return view;
    }

    /**
     * Goes to the initial activity upon clicking the logout button
     * @param view The "logout" button
     */
    public void goToSignIn(View view) {
        Intent I = new Intent(getContext(), InitialActivity.class);
        startActivity(I);
    }

    public void clickToSeeEditCustomerPages(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new  EditCustomerFragment(username, email, location, password));
        ft.commit();
    }


    public void getCustomerDetails() {
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        Call.post("customers-get-info", obj, this::injectCustomerDetails, null);
    }

    public void injectCustomerDetails(JSONObject response) {
        try{
            email = response.get("name").toString();
            location = response.get("location").toString();

            TextView usernameTextView = view.findViewById(R.id.customerName1);
            usernameTextView.setText(username);
            TextView emailTextView = view.findViewById(R.id.customerEmail);
            emailTextView.setText(email);
            TextView locationTextView = view.findViewById(R.id.customerLocation);
            locationTextView.setText(location);
        } catch (Exception e) {
            Log.d("debug", e.toString());return;}
    }
}
