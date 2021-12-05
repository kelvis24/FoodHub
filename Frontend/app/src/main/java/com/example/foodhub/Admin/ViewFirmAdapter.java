package com.example.foodhub.Admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Category;
import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.R;

import java.util.ArrayList;

/**
 * Sets up the view adapter for Firms
 * @author Marcus
 * @see Fragment
 */

public class ViewFirmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long firmId;
    private String username;
    private String password;

    private ViewFirmFragment fragment;

    private ArrayList<Category> categories;

    /**
     * Constructs a ViewFirmAdapter from enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param firmId The ID of the current firm being used
     */
    public ViewFirmAdapter(long firmId, String username, String password,
                           ViewFirmFragment fragment) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.fragment = fragment;
    }
    /**
     * Creates the view
     * @param parent A Viewgroup
     * @param viewType An int designating the view type
     * @return The view that is created
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_browse_category, parent, false);
        return new CategoryHolder(view);
    }
    /**
     * Repeats the view currently selected
     * @param holder A Viewgroup
     * @param index An int designating the view type
     * @return The view that is created
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        CategoryHolder categoryHolder = (CategoryHolder) holder;
        categoryHolder.usernameText.setText(categories.get(index).getTitle());
        GoToBrowseFirms goToBrowseFirms = new GoToBrowseFirms(categories.get(index).getId(), fragment);
        categoryHolder.usernameText.setOnClickListener(goToBrowseFirms);
    }

    @Override public int getItemViewType(int index) {
        return categories.get(index) == null ? -1 : 0;
    }

    @Override public int getItemCount() {
        return categories.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        public CategoryHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.browse_category_textview);
        }
    }
    /**
     * Sets up the firm and sets for the onclick listener
     */
    class GoToBrowseFirms implements View.OnClickListener {
        private long firmId;
        private ViewFirmFragment fragment;

        public GoToBrowseFirms(long firmId, ViewFirmFragment fragment) {
            this.firmId = firmId;
            this.fragment = fragment;
        }

        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new ViewFirmFragment(firmId, username, password));
            ft.commit();
        }
    }

}
