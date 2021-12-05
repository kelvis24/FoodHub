package com.example.foodhub.Admin;

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

import com.example.foodhub.Common.Firm;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A controller for the R.layout.fragment_add_firm view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class AddFirmFragment extends Fragment {

    private final String username;
    private final String password;
    private final String type;
    private final Firm firm;

    View page;

    /**
     * Constructs an AddFirmFragment given enumerated information; for an edit page
     * @param username The username of the current user
     * @param password The password of the current user
     * @param type The type of user, whether owner or admin
     * @param firm The firm that is to be edited
     */
    public AddFirmFragment(String username, String password, String type, Firm firm) {
        super();
        this.username = username;
        this.password = password;
        this.type = type;
        this.firm = firm;
    }

    /**
     * Constructs an AddFirmFragment given enumerated information; for an add page
     * @param username The username of the current user
     * @param password The password of the current user
     * @param type The type of user, whether owner or admin
     */
    public AddFirmFragment(String username, String password, String type) {
        super();
        this.username = username;
        this.password = password;
        this.type = type;
        this.firm = null;
    }

    /**
     * A Default Constructor
     */
    public AddFirmFragment() {
        this.username = null;
        this.password = null;
        this.type = null;
        this.firm = null;
    }

    /**
     * Does bookkeeping regarding the onCreate method
     * @param savedInstanceState A bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Binds the "add firm" to the appropriate method upon creation of the view
     * @param inflater A layout inflater
     * @param container The view within which this one is contained
     * @param savedInstanceState A bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = inflater.inflate(R.layout.fragment_add_firm, container, false);
        Button btn = page.findViewById(R.id.add_firm_button2);
        btn.setOnClickListener(this::firmRequest);
        if (firm != null) {
            String str;
            btn.setText(R.string.Edit_Firm);
            ((TextView)page.findViewById(R.id.add_firm_username)).setText(firm.getUsername());
            ((TextView)page.findViewById(R.id.add_firm_name)).setText(firm.getName());
            ((TextView)page.findViewById(R.id.add_firm_location)).setText(firm.getLocation());
            ((TextView)page.findViewById(R.id.add_firm_cuisine)).setText(firm.getCuisine());
            str = "" + firm.getOpen_time();
            ((TextView)page.findViewById(R.id.add_firm_open_time)).setText(str);
            str = "" + firm.getClose_time();
            ((TextView)page.findViewById(R.id.add_firm_close_time)).setText(str);
            str = "" + firm.getEmployee_count();
            ((TextView)page.findViewById(R.id.add_firm_employee_count)).setText(str);
        }
        return page;
    }

    /**
     * Sends a request to add a firm upon clicking the "add firm" or "edit firm" button
     * @param view the "add firm" or "edit firm" button
     */
    public void firmRequest(View view) {
        String d_name = ((EditText)page.findViewById(R.id.add_firm_name)).getText().toString();
        String d_username = ((EditText)page.findViewById(R.id.add_firm_username)).getText().toString();
        String d_password = ((EditText)page.findViewById(R.id.add_firm_password)).getText().toString();
        String d_location = ((EditText)page.findViewById(R.id.add_firm_location)).getText().toString();
        String d_cuisine = ((EditText)page.findViewById(R.id.add_firm_cuisine)).getText().toString();
        int d_open_time = Integer.parseInt(((EditText)page.findViewById(R.id.add_firm_open_time)).getText().toString());
        int d_close_time = Integer.parseInt(((EditText)page.findViewById(R.id.add_firm_close_time)).getText().toString());
        int d_employee_count= Integer.parseInt(((EditText)page.findViewById(R.id.add_firm_employee_count)).getText().toString());
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("name", d_name);
        dataMap.put("username", d_username);
        dataMap.put("password", d_password);
        dataMap.put("location", d_location);
        dataMap.put("cuisine", d_cuisine);
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject dataObj = new JSONObject(dataMap);
        JSONObject obj = new JSONObject(map);
        try{dataObj.put("open_time", d_open_time);
            dataObj.put("close_time", d_close_time);
            dataObj.put("employee_count", d_employee_count);
            obj.put("data", dataObj);
            if (firm != null) obj.put("firmId", firm.getId());
        } catch (JSONException e) {e.printStackTrace();}
        if (firm == null)
            Call.post("admins-create-firm", obj, this::addFirmResponse, null);
        else
            Call.post("admins-edit-firm", obj, this::addFirmResponse, null);
    }

    /**
     * Traverses to the appropriate fragment upon a successful request to add a firm
     * @param response The response of the server as a JSONObject
     */
    public void addFirmResponse(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (type.equals("owner"))
                ft.replace(R.id.owner_fragment_main, new ManageFirmsFragment(username, password, type));
            else
                ft.replace(R.id.admin_fragment_main, new ManageFirmsFragment(username, password, type));
            ft.commit();
        }} catch (Exception e) {Log.d("response", e.toString());}
    }

}
