package com.example.foodhub.Customer;

import static com.example.foodhub.Common.FoodhubUtils.AreInvalidFields;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodhub.Admin.AdminMainActivity;
import com.example.foodhub.Admin.OwnerMainActivity;
import com.example.foodhub.Common.Customer;
import com.example.foodhub.Common.Order;
import com.example.foodhub.Firm.FirmMainActivity;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditCustomerFragment extends Fragment {
    private String oldusername;
    private String oldpassword;
    private String username;
    private String name;
    private String location;
    private String password;
    private View view;
    private ViewGroup container;

    /**
     * Constructor if values have been passed through
     */
    public EditCustomerFragment(String username, String email, String location, String password) {
        this.username = username;
        this.name = email;
        this.location = location;
        this.password = password;
    }

    /**
     * Constructor if everything is null
     */
    public EditCustomerFragment() {
        this.username = null;
        this.name = null;
        this.location = null;
        this.password = null;
    }

    /**
     * Creates the fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    /**
     * Creates the page and implements all the button onclicks plus other functionality
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_customer, container, false);
        this.container = container;
        this.view = view;
        oldusername = username;
        oldpassword = password;
        setTextViewDetails();
        Button btn = (Button) view.findViewById(R.id.registerCancel);
        btn.setOnClickListener(this::CancelCustomerAccountEdit);
        Button btn1 = (Button) view.findViewById(R.id.registerSave);
        btn1.setOnClickListener(this::SaveCustomerAccountEdit);
        return view;
    }

    /**
     * Switches back to the Customer Account Fragment
     * @param view, takes in the view to control the button
     */
    private void CancelCustomerAccountEdit(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new CustomerAccountFragment(username, name, location, password));
        ft.commit();
    }

    /**
     * Gets the user inut and puts it into a combined jSON before using backend to change the user's details
     * @param v, takes in the view to control the button
     */
    public void SaveCustomerAccountEdit(View v) {
        String username = ((EditText)view.findViewById(R.id.edit_customer_name)).getText().toString();
        String email = ((EditText)view.findViewById(R.id.edit_customer_email)).getText().toString();
        String location = ((EditText)view.findViewById(R.id.edit_customer_location)).getText().toString();
        String password = ((EditText)view.findViewById(R.id.edit_customer_password)).getText().toString();
        String cPassword = ((EditText)view.findViewById(R.id.edit_customer_cpassword)).getText().toString();
        ArrayList<String> list = new ArrayList<>();
        list.add(username);
        list.add(email);
        list.add(location);
        list.add(password);
        list.add(cPassword);
        if (AreInvalidFields(getActivity(), list, password, cPassword)) return;
        this.username = username;
        this.name = email;
        this.location = location;
        this.password = password;
        Map<String, String> OldDetails = new HashMap<>();
        OldDetails.put("username", oldusername);
        OldDetails.put("password", oldpassword);
        Map<String, String> NewDetails = new HashMap<>();
        NewDetails.put("username", username);
        NewDetails.put("name", email);
        NewDetails.put("location", location);
        NewDetails.put("password", password);
        try {
        JSONObject objOldDetails = new JSONObject(OldDetails);
        JSONObject objNewDetails = new JSONObject(NewDetails);
        objOldDetails.put("data", objNewDetails);
        Call.post("customers-edit-customer", objOldDetails, this::ReturnToAccountPage, null);
        } catch (JSONException jsonException) {}
    }

    /**
     * Returns the user back to the Customer account page upon successful implementation to the edit customer
     * @param response
     */
    public void ReturnToAccountPage(JSONObject response) {
        String str;
        try{str = (String)response.get("message");
            if (str.equals("failure")) {
                Toast.makeText(getActivity().getApplicationContext(),
                        (String)response.get("error"),Toast.LENGTH_SHORT).show();
                return;
            }
            //here
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new CustomerAccountFragment(username, name, location, password));
            ft.commit();

        } catch (Exception e) {Log.d("debug", e.toString());return;}
    }

    /**
     * Sets the TextView to have information pulled in from the backend
     */
    public void setTextViewDetails() {
        EditText usernameTextView = view.findViewById(R.id.edit_customer_name);
        usernameTextView.setText(username, TextView.BufferType.EDITABLE);
        EditText passwordTextView = view.findViewById(R.id.edit_customer_password);
        passwordTextView.setText(password, TextView.BufferType.EDITABLE);
        EditText emailTextView = view.findViewById(R.id.edit_customer_email);
        emailTextView.setText(name, TextView.BufferType.EDITABLE);
        EditText locationTextView = view.findViewById(R.id.edit_customer_location);
        locationTextView.setText(location, TextView.BufferType.EDITABLE);
        EditText confirmPassordTextView = view.findViewById(R.id.edit_customer_cpassword);
        confirmPassordTextView.setText(password, TextView.BufferType.EDITABLE);
    }
}