package com.example.foodhub.Firm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Category;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for controlling the R.layout.view_edit_category view, placing it in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
 */
public class ManageCategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long firmId;
    private String username;
    private String password;

    private ManageCategoriesFragment fragment;

    private ArrayList<Category> categories;

    /**
     * Constructs a ManageCategoriesAdapter object given enumerated information
     * @param firmId The id of the firm to which the categories belong
     * @param username The username of the current user
     * @param password The password of the current user
     * @param fragment The fragment that contains the recycler
     * @param categories The list information about categories, which will be listed in the recycler
     */
    public ManageCategoriesAdapter(long firmId, String username, String password,
            ManageCategoriesFragment fragment, ArrayList<Category> categories) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.categories = categories;
    }

    /**
     * Creates a ViewHolder given a view
     * @param parent The parent view of the recycler
     * @param viewType The type of view; it should always be 0
     * @return The ViewHolder that is created
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_edit_category, parent, false);
        return new CategoryHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; it sets the text and binds the button to the right method
     * @param holder The ViewHolder about to be bound
     * @param index The index of the information of ViewHolder in the categories array
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        CategoryHolder categoryHolder = (CategoryHolder) holder;
        categoryHolder.usernameText.setText(categories.get(index).getTitle());
        GoToManageItems goToManageItems = new GoToManageItems(categories.get(index).getId());
        categoryHolder.usernameText.setOnClickListener(goToManageItems);
        EditCategory editCategory = new EditCategory(categories.get(index));
        categoryHolder.editButton.setOnClickListener(editCategory);
        DeleteCategory deleteCategory = new DeleteCategory(categories.get(index).getId());
        categoryHolder.deleteButton.setOnClickListener(deleteCategory);
    }

    /**
     * Retrieves the type of view given its index in the categories array
     * @param index The index of the view in question
     * @return The type of view; it should always be 0
     */
    @Override public int getItemViewType(int index) {
        return categories.get(index) == null ? -1 : 0;
    }

    /**
     * Retrieves the number of views to be in the recycler
     * @return The number of views to be in the recycler
     */
    @Override public int getItemCount() {
        return categories.size();
    }

    private class CategoryHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        Button editButton;
        Button deleteButton;
        public CategoryHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.edit_category_textview);
            editButton = view.findViewById(R.id.edit_category_edit_button);
            deleteButton = view.findViewById(R.id.edit_category_delete_button);
        }
    }

    private class EditCategory implements View.OnClickListener {
        private Category category;
        public EditCategory(Category category) {
            this.category = category;
        }
        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.firm_fragment_main, new AddCategoryFragment(firmId, username, password, category));
            ft.commit();
        }
    }

    private class DeleteCategory implements View.OnClickListener, ObjectResponse {
        private long categoryId;
        public DeleteCategory(long categoryId) {
            this.categoryId = categoryId;
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
            try{if (response.get("message").equals("success")) {
                fragment.refresh();
            }} catch (Exception e) {Log.d("response", e.toString());}
        }
    }

    private class GoToManageItems implements View.OnClickListener {
        private long categoryId;
        public GoToManageItems(long categoryId) {
            this.categoryId = categoryId;
        }
        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.firm_fragment_main, new ManageItemsFragment(firmId, categoryId, username, password));
            ft.commit();
        }
    }

}
