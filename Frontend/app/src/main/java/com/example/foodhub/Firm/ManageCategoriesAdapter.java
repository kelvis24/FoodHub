package com.example.foodhub.Firm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Admin.ManageAdminsAdapter;
import com.example.foodhub.Admin.ManageAdminsFragment;
import com.example.foodhub.Common.Category;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManageCategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long firmId;
    private String username;
    private String password;

    private ManageCategoriesFragment fragment;

    private ArrayList<Category> categories;

    public ManageCategoriesAdapter(long firmId, String username, String password,
            ManageCategoriesFragment fragment, ArrayList<Category> categories) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.categories = categories;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_edit_category, parent, false);
        return new CategoryHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        CategoryHolder categoryHolder = (CategoryHolder) holder;
        categoryHolder.usernameText.setText(categories.get(index).getTitle());
        DeleteCategory response = new DeleteCategory(categories.get(index).getId(), fragment);
        categoryHolder.deleteButton.setOnClickListener(response);
    }

    @Override public int getItemViewType(int index) {
        return categories.get(index) == null ? -1 : 0;
    }

    @Override public int getItemCount() {
        return categories.size();
    }

    class CategoryHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        Button deleteButton;
        public CategoryHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.edit_category_textview);
            deleteButton = view.findViewById(R.id.edit_category_button);
        }
    }

    class DeleteCategory implements View.OnClickListener, ObjectResponse {
        private long categoryId;
        private ManageCategoriesFragment fragment;

        public DeleteCategory(long categoryId, ManageCategoriesFragment fragment) {
            this.categoryId = categoryId;
            this.fragment = fragment;
        }

        public void onClick(View v) {
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            JSONObject obj = new JSONObject(map);
            try{obj.put("id", categoryId);
            } catch (JSONException e) {e.printStackTrace();}
            Call.post("firms-remove-category", obj, this, null);
        }

        public void respond(JSONObject response) {
            try{System.out.println(response.toString());
                if (response.get("message").equals("success")) {
                fragment.refresh();
            }} catch (Exception e) {Log.d("response", e.toString());}
        }

    }

}
