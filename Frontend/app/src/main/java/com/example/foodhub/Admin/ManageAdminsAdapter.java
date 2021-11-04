package com.example.foodhub.Admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Admin;
import com.example.foodhub.R;

import java.util.ArrayList;

public class ManageAdminsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Admin> admins;

    public ManageAdminsAdapter(ArrayList<Admin> admins) {
        this.admins = admins;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_edit_admin, parent, false);
        return new AdminHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        AdminHolder adminHolder = (AdminHolder) holder;
        adminHolder.usernameText.setText(admins.get(index).getUsername());
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

}
