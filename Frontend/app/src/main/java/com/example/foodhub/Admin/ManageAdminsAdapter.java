package com.example.foodhub.Admin;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
 * The controller for the R.layout.view_edit_admin view, placing them in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
*/
public class ManageAdminsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;

    private ManageAdminsFragment fragment;

    private ArrayList<Admin> admins;

    /**
     * Constructs a ManageAdminsAdapter given enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param fragment The specific fragment within which the recycler lies
     * @param admins The list of admins retrieved form the backend
     */
    public ManageAdminsAdapter(String username, String password,
            ManageAdminsFragment fragment, ArrayList<Admin> admins) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.admins = admins;
    }

    /**
     * Creates a ViewHolder for a view; called for each view
     * @param parent The parent view of the recycler
     * @param viewType The type of view, which should always be 0
     * @return The tailored ViewHolder for the corresponding view
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_edit_admin, parent, false);
        return new AdminHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; sets TextViews and binds buttons
     * @param holder A ViewHolder
     * @param index The index of the ViewHolder in the admins arraylist
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        AdminHolder adminHolder = (AdminHolder) holder;
        adminHolder.usernameText.setText(admins.get(index).getUsername());
        EditAdmin editResponse = new EditAdmin(admins.get(index));
        adminHolder.editButton.setOnClickListener(editResponse);
        DeleteAdmin deleteResponse = new DeleteAdmin(admins.get(index).getId());
        adminHolder.deleteButton.setOnClickListener(deleteResponse);
    }

    /**
     * Returns the type of view that will be in the recycler at a specified index
     * @param index The index of the admin within the admins arraylist
     * @return The type of admin in the arraylist, which should always be 0
     */
    @Override public int getItemViewType(int index) {
        return admins.get(index) == null ? -1 : 0;
    }

    /**
     * Returns the number of views that will be in the recycler
     * @return The number of views that will be in the recycler
     */
    @Override public int getItemCount() {
        return admins.size();
    }

    private class AdminHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        Button editButton;
        Button deleteButton;
        public AdminHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.edit_admin_textview);
            editButton = view.findViewById(R.id.edit_admin_edit_button);
            deleteButton = view.findViewById(R.id.edit_admin_delete_button);
        }
    }
    private class EditAdmin implements View.OnClickListener {
        private Admin admin;
        public EditAdmin(Admin admin) {
            this.admin = admin;
        }
        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.owner_fragment_main, new AddAdminFragment(username, password, "edit", admin));
            ft.commit();
        }
    }


    private class DeleteAdmin implements View.OnClickListener, ObjectResponse {
        private long id;
        public DeleteAdmin(long id) {
            this.id = id;
        }
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
