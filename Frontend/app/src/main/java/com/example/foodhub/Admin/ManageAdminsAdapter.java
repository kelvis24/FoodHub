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

public class ManageAdminsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;

    private ManageAdminsFragment fragment;

    private ArrayList<Admin> admins;

    public ManageAdminsAdapter(String username, String password,
            ManageAdminsFragment fragment, ArrayList<Admin> admins) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.admins = admins;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_edit_admin, parent, false);
        return new AdminHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        AdminHolder adminHolder = (AdminHolder) holder;
        adminHolder.usernameText.setText(admins.get(index).getUsername());
        DeleteAdmin response = new DeleteAdmin(admins.get(index).getId(), fragment);
        adminHolder.deleteButton.setOnClickListener(response);
    }

    @Override public int getItemViewType(int index) {
        return admins.get(index) == null ? -1 : 0;
    }

    @Override public int getItemCount() {
        return admins.size();
    }

    class AdminHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        Button deleteButton;
        public AdminHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.edit_admin_textview);
            deleteButton = view.findViewById(R.id.edit_admin_button);
        }
    }

    class DeleteAdmin implements View.OnClickListener, ObjectResponse {
        private long id;
        private ManageAdminsFragment fragment;

        public DeleteAdmin(long id, ManageAdminsFragment fragment) {
            this.id = id;
            this.fragment = fragment;
        }

        public void onClick(View v) {
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            JSONObject obj = new JSONObject(map);
            try{obj.put("id", id);
            } catch (JSONException e) {e.printStackTrace();}
            System.out.println(obj.toString());
            Call.post("admins-remove-admin", obj, this, null);
        }

        public void respond(JSONObject response) {
            try{if (response.get("message").equals("success")) {
                fragment.refresh();
            }} catch (Exception e) {Log.d("response", e.toString());}
        }

    }

}
