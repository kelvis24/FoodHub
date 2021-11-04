package com.example.foodhub.Admin;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
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

public class ManageFirmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;

    private ManageFirmsFragment fragment;

    private ArrayList<Firm> firms;

    public ManageFirmsAdapter(String username, String password, ManageFirmsFragment fragment, ArrayList<Firm> firms) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.firms = firms;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_edit_firm, parent, false);
        return new FirmHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        FirmHolder firmHolder = (FirmHolder) holder;
        firmHolder.usernameText.setText(firms.get(index).getUsername());
        DeleteFirm response = new DeleteFirm(firms.get(index).getId(), fragment);
        firmHolder.deleteButton.setOnClickListener(response);
    }

    @Override public int getItemViewType(int index) {
        return firms.get(index) == null ? -1 : 0;
    }

    @Override public int getItemCount() {
        return firms.size();
    }

    class FirmHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        Button deleteButton;
        public FirmHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.edit_firm_textview);
            deleteButton = view.findViewById(R.id.edit_firm_button);
        }
    }

    class DeleteFirm implements View.OnClickListener, ObjectResponse {
        private long id;
        private ManageFirmsFragment fragment;

        public DeleteFirm(long id, ManageFirmsFragment fragment) {
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
            Call.post("admins-remove-firm", obj, this, null);
        }

        public void respond(JSONObject response) {
            try{if (response.get("message").equals("success")) {
                fragment.refresh();
            }} catch (Exception e) {Log.d("response", e.toString());}
        }

    }

}
