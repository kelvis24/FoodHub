package com.example.foodhub.Customer;

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

/**
 * Class responsible for the browswer Categories
 */
public class BrowseCategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long firmId;
    private String username;
    private String password;
    private ArrayList<ItemReference> order;
    private BrowseCategoriesFragment fragment;
    private ArrayList<Category> categories;

    /**
     * Constructor responsible for initializing what a category would look like.
     */
    public BrowseCategoriesAdapter(long firmId, String username, String password, ArrayList<ItemReference> order,
                                   BrowseCategoriesFragment fragment, ArrayList<Category> categories) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.order = order;
        this.fragment = fragment;
        this.categories = categories;
    }

    /**
     * Method responsible for returning the recycler view
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_browse_category, parent, false);
        return new CategoryHolder(view);
    }

    /**
     * Method responsible for binding the views
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        CategoryHolder categoryHolder = (CategoryHolder) holder;
        categoryHolder.usernameText.setText(categories.get(index).getTitle());
        GoToBrowseItems goToBrowseItems = new GoToBrowseItems(categories.get(index).getId(), fragment);
        categoryHolder.usernameText.setOnClickListener(goToBrowseItems);
    }

    /**
     * Method responsible for returning number of items
     */
    @Override public int getItemViewType(int index) {
        return categories.get(index) == null ? -1 : 0;
    }

    /**
     * Class responsible for returning number of categories
     */
    @Override public int getItemCount() {
        return categories.size();
    }
    /**
     * Class that controls the view
     */
    private class CategoryHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        public CategoryHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.browse_category_textview);
        }
    }
    /**
     * Class responsible for taking you to browsed items
     */
    private class GoToBrowseItems implements View.OnClickListener {
        private long categoryId;
        private BrowseCategoriesFragment fragment;

        /**
         * method responsible for initializing goToBrowse category items
         */
        public GoToBrowseItems(long categoryId, BrowseCategoriesFragment fragment) {
            this.categoryId = categoryId;
            this.fragment = fragment;
        }

        /**
         * method responsible for the onclick when the button is pressed.
         */
        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new BrowseItemsFragment(firmId, categoryId, username, password, order));
            ft.commit();
        }
    }
}
