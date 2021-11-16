package com.example.foodhub.Customer;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditCustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditCustomerFragment extends Fragment {
    ArrayList<Customer> customers;
    private Customer customer;
    private String username;
    private String email;
    private String location;
    private String password;
    private ViewGroup container;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditCustomerFragment(String username, String email, String location, String password) {
        this.customer = new Customer();
        this.username = username;
        this.email = email;
        this.location = location;
        this.password = password;
    }

    public EditCustomerFragment() {
        this.customer = null;
        this.username = null;
        this.email = null;
        this.location = null;
        this.password = null;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditCustomerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditCustomerFragment newInstance(String param1, String param2) {
        EditCustomerFragment fragment = new EditCustomerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
            email = getArguments().getString("email");
            location = getArguments().getString("location");
            password = getArguments().getString("password");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       this.container = container;
      //  refresh();
        View view = inflater.inflate(R.layout.fragment_edit_customer, container, false);
       // this.container = container;
       // String word = customer.username;
   //     ArrayList<Customer> cus = customers;
        EditText usernameTextView = view.findViewById(R.id.sign_up_name_field);
        usernameTextView.setText(username, TextView.BufferType.EDITABLE);
        EditText passwordTextView = view.findViewById(R.id.sign_up_password_field);
        passwordTextView.setText(password, TextView.BufferType.EDITABLE);
        EditText emailTextView = view.findViewById(R.id.sign_up_email_field);
        emailTextView.setText(password, TextView.BufferType.EDITABLE);
        EditText locationTextView = view.findViewById(R.id.sign_up_location_field);
        locationTextView.setText(password, TextView.BufferType.EDITABLE);
        EditText confirmPassordTextView = view.findViewById(R.id.sign_up_confirm_password_field);
        confirmPassordTextView.setText(password, TextView.BufferType.EDITABLE);



        Button btn = (Button) view.findViewById(R.id.registerCancel);
        btn.setOnClickListener(this::CancelCustomerAccountEdit);
        Button btn1 = (Button) view.findViewById(R.id.registerSave);
        btn1.setOnClickListener(this::SaveCustomerAccountEdit);
        return view;


        // Inflate the layout for this fragment
    }

    private void CancelCustomerAccountEdit(View view) {
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.customer_fragment_main, new CustomerAccountFragment(username, email, location, password));
        ft.commit();
    }

    public void SaveCustomerAccountEdit(View view) {

        username = ((EditText)view.findViewById(R.id.sign_up_name_field)).getText().toString();
        email = ((EditText)view.findViewById(R.id.sign_up_email_field)).getText().toString();
        location = ((EditText)view.findViewById(R.id.sign_up_location_field)).getText().toString();
        password = ((EditText)view.findViewById(R.id.sign_up_password_field)).getText().toString();
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("email", email);
        map.put("location", location);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        Call.post("customers-edit-customer", obj, this::ReturnToAccountPage, null);
    }

    public void ReturnToAccountPage(JSONObject response) {
        String str;
        try{str = (String)response.get("message");
            if (str.equals("failure")) {
                Log.d("debug", response.toString());
                return;
            }

            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new CustomerAccountFragment(username, email, location, password));
            ft.commit();

        } catch (Exception e) {Log.d("debug", e.toString());return;}
    }

    /**
     * Makes a call to refresh the page
     */
    public void refresh() {
        ArrayList<Customer> cus = null;

//        Map<String, String> map = new HashMap<>();
//        map.put("username", username);
//        map.put("password", password);
//        JSONObject obj = new JSONObject(map);
        Call.get("debug-get-customers", this::CustomerDetails, null);
         cus = customers;

    }

    /**
     * Lists order information upon a successful call to refresh the page
     * @param arr The response from the server as a JSONArray
     */
    public void CustomerDetails(JSONArray arr) {
        customers = new ArrayList<>();
        for (int i = 0; i < arr.length(); i++) {
            try{customers.add(new Customer(arr.getJSONObject(i)));
            } catch (JSONException e) {e.printStackTrace();}
        }
        customer = Customer.SortExactCustomer(username, customers);

//        RecyclerView recyclerView = container.findViewById(R.id.manage_customer_orders_recycler);
//        recyclerView.setAdapter(new ManageCustomerOrdersAdapter(username, password, this, orders));
//        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
    }

}