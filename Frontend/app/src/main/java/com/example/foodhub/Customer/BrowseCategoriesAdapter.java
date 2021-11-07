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
 * The class responsible allowing the user to look through their catagories.
 * @author 1_CW_2
*/

public class BrowseCategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long firmId;
    private String username;
    private String password;
    private ArrayList<ItemReference> order;

    private BrowseCategoriesFragment fragment;

    private ArrayList<Category> categories;
    /**
    * Constructor for browsing the given catagories
    * @param username Users Username
    * @param password Users Password
    * @param order ArrayList of the orders
    * @param fragment BrowseCatagoriesFragment
    * @param catagories ArrayList of the catagories
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
    * Creates a recycle view on the creation of the method
    * @param viewType Int to designate the type of view
    * @param parent A ViewGroup that allows the program to remember the last layout
    * @return CategoryHolder(view)
    * @see CategoryHolder
    */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_browse_category, parent, false);
        return new CategoryHolder(view);
    }
    /** 
    * Instigates the recycled view
    * @param index Int to designate the type of view
    * @param holder A ViewGroup that allows the program to remember the last layout
    * @return CategoryHolder
    */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        CategoryHolder categoryHolder = (CategoryHolder) holder;
        categoryHolder.usernameText.setText(categories.get(index).getTitle());
        GoToBrowseItems goToBrowseItems = new GoToBrowseItems(categories.get(index).getId(), fragment);
        categoryHolder.usernameText.setOnClickListener(goToBrowseItems);
    }
    /** 
    * Method to get the item view type
    * @param index Int to check the view type
    * @return Item view type
    * @see CatagoryHolder
    */
    @Override public int getItemViewType(int index) {
        return categories.get(index) == null ? -1 : 0;
    }
    /** 
    * Method to get the item count
    * @return catagory size
    */
    @Override public int getItemCount() {
        return categories.size();
    }
    /** 
    * Class to designate the username of the different catagories currently listed
    */
    class CategoryHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        public CategoryHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.browse_category_textview);
        }
    }
    /** 
    * Class designated to browse through the given items
    */
    class GoToBrowseItems implements View.OnClickListener {
        private long categoryId;
        private BrowseCategoriesFragment fragment;
        /** 
        * Method to go to the given items by id and fragment
        * @param catagoryID Long value to designate the ID of the catagory
        * @param fragment BrowseCategoriesFragment to designate fragment type and set
        */
        public GoToBrowseItems(long categoryId, BrowseCategoriesFragment fragment) {
            this.categoryId = categoryId;
            this.fragment = fragment;
        }
        /** 
        * Method to go to activate class on click
        * @param v The View that was set
        */
        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new BrowseItemsFragment(firmId, categoryId, username, password, order));
            ft.commit();
        }
    }

}
