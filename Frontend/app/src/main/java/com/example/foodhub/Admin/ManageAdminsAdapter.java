package com.example.foodhub.Admin;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Admin;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** 
 * The class responsible allowing admins to be managed.
 * @author 1_CW_2
*/


public class ManageAdminsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;

    private ManageAdminsFragment fragment;

    private ArrayList<Admin> admins;
    /**
    * Constructor for managing admins
    * @param username Users Username
    * @param password Users Password
    * @param order ArrayList of the orders
    * @param fragment BrowseAdminsFragment
    * @param admins ArrayList of the admins
    */

    public ManageAdminsAdapter(String username, String password,
            ManageAdminsFragment fragment, ArrayList<Admin> admins) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.admins = admins;
    }
    /** 
    * Creates a recycle view on the creation of the method
    * @param viewType Int to designate the type of view
    * @param parent A ViewGroup that allows the program to remember the last layout
    * @return AdminHolder(view)
    * @see AdminHolder
    */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_edit_admin, parent, false);
        return new AdminHolder(view);
    }
    /** 
    * Instigates the recycled view
    * @param index Int to designate the type of view
    * @param holder A ViewGroup that allows the program to remember the last layout
    */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        AdminHolder adminHolder = (AdminHolder) holder;
        adminHolder.usernameText.setText(admins.get(index).getUsername());
        DeleteAdmin response = new DeleteAdmin(admins.get(index).getId(), fragment);
        adminHolder.deleteButton.setOnClickListener(response);
    }
    /** 
    * Method to get the amin item view type
    * @param index Int to check the view type
    * @return admin view type
    */
    @Override public int getItemViewType(int index) {
        return admins.get(index) == null ? -1 : 0;
    }
    /** 
    * Method to get the admin count
    * @return admin size
    */
    @Override public int getItemCount() {
        return admins.size();
    }
    /** 
    * Class designated to browse through/manage the given admins
    */
    class AdminHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        Button deleteButton;
        public AdminHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.edit_admin_textview);
            deleteButton = view.findViewById(R.id.edit_admin_button);
        }
    }
    /** 
    * Class designated to delete an admin
    */
    class DeleteAdmin implements View.OnClickListener, ObjectResponse {
        private long id;
        private ManageAdminsFragment fragment;
        /** 
        * Method for getting the deleted admin information
        * @param id ID of the admin to be deleted
        * @fragment ManageAdminsFragment constructor
        */
        public DeleteAdmin(long id, ManageAdminsFragment fragment) {
            this.id = id;
            this.fragment = fragment;
        }
        /**
        * Method to activate class on click
        */
        public void onClick(View v) {
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            JSONObject obj = new JSONObject(map);
            try{obj.put("id", id);
            } catch (JSONException e) {e.printStackTrace();}
            Call.post("admins-remove-admin", obj, this, null);
        }

        public void respond(JSONObject response) {
            try{if (response.get("message").equals("success")) {
                fragment.refresh();
            }} catch (Exception e) {Log.d("response", e.toString());}
        }

    }

}
