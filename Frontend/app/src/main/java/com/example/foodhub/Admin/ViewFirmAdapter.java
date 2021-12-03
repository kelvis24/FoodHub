package com.example.foodhub.Admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Category;
import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.R;

import java.util.ArrayList;

/*

    @author Marcus
 */

public class ViewFirmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long firmId;
    private String username;
    private String password;

    private ViewFirmFragment fragment;

    private ArrayList<Category> categories;

    public ViewFirmAdapter(long firmId, String username, String password,
                           ViewFirmFragment fragment) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.fragment = fragment;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_browse_category, parent, false);
        return new CategoryHolder(view);
    }

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
    //Set up brows firsms and sets
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
