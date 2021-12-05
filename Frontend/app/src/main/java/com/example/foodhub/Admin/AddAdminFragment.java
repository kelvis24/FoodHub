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

import com.example.foodhub.Common.Admin;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Controls the R.layout.fragment_add_admin view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class AddAdminFragment extends Fragment {

    private final String username;
    private final String password;
    private final String function;
    private final Admin admin;

    private View page;

    /**
     * Constructs a new AddAdminFragment given enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param function The function of the page: to add or edit
     * @param admin The id of the admin that is to be edited; not used for adding.
     */
    public AddAdminFragment(String username, String password, String function, Admin admin) {
        super();
        this.username = username;
        this.password = password;
        this.function = function;
        this.admin = admin;
    }

    /**
     * A default constructor
     */
    public AddAdminFragment() {
        this.username = null;
        this.password = null;
        this.function = null;
        this.admin = null;
    }

    /**
     * Does bookkeeping for the onCreate method
     * @param savedInstanceState a bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Binds the "add admin" button to appropriate function when the view is created
     * @param inflater A layout inflater
     * @param container The view that contains this one
     * @param savedInstanceState a bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = inflater.inflate(R.layout.fragment_add_admin, container, false);
        Button btn = page.findViewById(R.id.add_admin_button2);
        btn.setOnClickListener(this::adminRequest);
        if (function.equals("add"))
            btn.setText(R.string.Add_Admin);
        else {
            btn.setText(R.string.Edit_Admin);
            ((TextView)page.findViewById(R.id.add_admin_username)).setText(admin.getUsername());
            ((TextView)page.findViewById(R.id.add_admin_name)).setText(admin.getName());
        }
        return page;
    }

    /**
     * Sends a request to add an admin upon clicking the "add admin" button
     * @param view the "add admin" button
     */
    public void adminRequest(View view) {
        String d_name = ((EditText)page.findViewById(R.id.add_admin_name)).getText().toString();
        String d_username = ((EditText)page.findViewById(R.id.add_admin_username)).getText().toString();
        String d_password = ((EditText)page.findViewById(R.id.add_admin_password)).getText().toString();
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("name", d_name);
        dataMap.put("username", d_username);
        dataMap.put("password", d_password);
        JSONObject dataObj = new JSONObject(dataMap);
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        try{obj.put("data", dataObj);
            if (function.equals("edit"))
                obj.put("adminId", admin.getId());
        } catch (JSONException e) {e.printStackTrace();}
        if (function.equals("add"))
            Call.post("admins-create-admin", obj, this::addAdminResponse, null);
        else
            Call.post("admins-edit-admin", obj, this::addAdminResponse, null);
    }

    /**
     * Goes back to the R.layout.fragment_manage_admins view upon successfully adding an admin
     * @param response The response from the server as a JSONObject
     */
    public void addAdminResponse(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.owner_fragment_main, new ManageAdminsFragment(username, password));
            ft.commit();
        }} catch (Exception e) {Log.d("response", e.toString());}
    }

}
