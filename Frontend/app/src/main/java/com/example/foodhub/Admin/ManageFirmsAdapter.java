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

import com.example.foodhub.Common.Firm;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for controlling the R.layout.view_edit_firm view, placing it in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
 */
public class ManageFirmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;
    private String type;

    private ManageFirmsFragment fragment;

    private ArrayList<Firm> firms;

    /**
     * Constructs a ManageFirmsAdapter object given enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param fragment The fragment that contains the recycler
     * @param firms The list information about firms, which will be listed in the recycler
     */
    public ManageFirmsAdapter(String username, String password, String type,
            ManageFirmsFragment fragment, ArrayList<Firm> firms) {
        this.username = username;
        this.password = password;
        this.type = type;
        this.fragment = fragment;
        this.firms = firms;
    }

    /**
     * Creates a ViewHolder given a view
     * @param parent The parent view of the recycler
     * @param viewType The type of view; it should always be 0
     * @return The ViewHolder that is created
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_edit_firm, parent, false);
        return new FirmHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; it sets the text and binds the button to the right method
     * @param holder The ViewHolder about to be bound
     * @param index The index of the information of ViewHolder in the firms array
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        FirmHolder firmHolder = (FirmHolder) holder;
        firmHolder.usernameText.setText(firms.get(index).getUsername());
		GoToViewFirm goToViewFirm = new GoToViewFirm(firms.get(index).getId());
		firmHolder.usernameText.setOnClickListener(goToViewFirm);
        EditFirm editResponse = new EditFirm(firms.get(index));
        firmHolder.editButton.setOnClickListener(editResponse);
        DeleteFirm deleteResponse = new DeleteFirm(firms.get(index).getId());
        firmHolder.deleteButton.setOnClickListener(deleteResponse);
    }

    /**
     * Retrieves the type of view given its index in the firms array
     * @param index The index of the view in question
     * @return The type of view; it should always be 0
     */
    @Override public int getItemViewType(int index) {
        return firms.get(index) == null ? -1 : 0;
    }

    /**
     * Retrieves the number of views to be in the recycler
     * @return The number of views to be in the recycler
     */
    @Override public int getItemCount() {
        return firms.size();
    }

    private class FirmHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        Button editButton;
        Button deleteButton;
        public FirmHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.edit_firm_textview);
            editButton = view.findViewById(R.id.edit_firm_edit_button);
            deleteButton = view.findViewById(R.id.edit_firm_delete_button);
        }
    }
	
	private class GoToViewFirm implements View.OnClickListener {
		private long firmId;
		public GoToViewFirm(long firmId) {
			this.firmId = firmId;
		}
		public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            if (type.equals("owner"))
                ft.replace(R.id.owner_fragment_main, new ViewCategoriesFragment(firmId, username, password, type));
            else
                ft.replace(R.id.admin_fragment_main, new ViewCategoriesFragment(firmId, username, password, type));
            ft.commit();
		}
	}

    private class EditFirm implements View.OnClickListener {
        private Firm firm;
        public EditFirm(Firm firm) {
            this.firm = firm;
        }
        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            if (type.equals("owner"))
                ft.replace(R.id.owner_fragment_main, new AddFirmFragment(username, password, type, firm));
            else
                ft.replace(R.id.admin_fragment_main, new AddFirmFragment(username, password, type, firm));
            ft.commit();
        }
    }

    private class DeleteFirm implements View.OnClickListener, ObjectResponse {
        private long id;
        public DeleteFirm(long id) {
            this.id = id;
        }
        public void onClick(View v) {
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            JSONObject obj = new JSONObject(map);
            try{obj.put("id", id);
            } catch (JSONException e) {e.printStackTrace();}
            Call.post("admins-remove-firm", obj, this, null);
        }
        public void respond(JSONObject response) {
            try{if (response.get("message").equals("success")) {
                fragment.refresh();
            }} catch (Exception e) {Log.d("response", e.toString());}
        }
    }

}
