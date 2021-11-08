package com.example.foodhub.Customer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Item;
import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.R;

import java.util.ArrayList;
import java.util.Locale;

/** 
 * The class responsible allowing the user to look through their items.
 * @author 1_CW_2
*/

public class BrowseItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long categoryId;
    private String username;
    private String password;
    private ArrayList<ItemReference> order;

    private BrowseItemsFragment fragment;

    private ArrayList<Item> items;

    /** 
 * Constructer for the addapter
 * @param username The Users username
 * @param password The Users password
 * @param order Arraylist of the users order
 * @param fragment BrowseItemsFragment
 * @param items Arraylist of given items
*/
    public BrowseItemsAdapter(long categoryId, String username, String password, ArrayList<ItemReference> order,
                              BrowseItemsFragment fragment, ArrayList<Item> items) {
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.order = order;
        this.fragment = fragment;
        this.items = items;
    }
    /** 
    * Creates a recycle view on the creation of the method
    * @param viewType Int to designate the type of view
    * @param parent A ViewGroup that allows the program to remember the last layout
    * @return ItemHolder(view)
    * @see ItemHolder
    */

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_browse_item, parent, false);
        return new ItemHolder(view);
    }

    /** 
    * Instigates the recycled view
    * @param index Int to designate the type of view
    * @param holder A ViewGroup that allows the program to remember the last layout
    * @return ItemHolder
    */

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.usernameText.setText(items.get(index).getTitle());
        itemHolder.price.setText(String.format(Locale.ENGLISH, "$%.2f", items.get(index).getPrice()));
        AddItem addItem = new AddItem(items.get(index), fragment);
        itemHolder.addButton.setOnClickListener(addItem);
    }

    /** 
    * Method to get the item view type
    * @param index Int to check the view type
    * @return Item view type
    * @see ItemHolder
    */
    @Override public int getItemViewType(int index) {
        return items.get(index) == null ? -1 : 0;
    }
    /** 
    * Method to get the item count
    * @return order size
    * @see ItemHolder
    */
    @Override public int getItemCount() {
        return items.size();
    }
    /** 
    * Class creator to check information on the item
    */
    class ItemHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        TextView price;
        Button addButton;
        public ItemHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.browse_item_textview);
            price = view.findViewById(R.id.browse_item_price);
            addButton = view.findViewById(R.id.browse_item_button);
        }
    }
    /** 
    * Adds Items on click
    */
    class AddItem implements View.OnClickListener {
        private Item item;
        private BrowseItemsFragment fragment;
        /** 
        * Adds Items
        * @param item Item in question
        * @param fragment BrowseItemsFragment setter
        */
        public AddItem(Item item, BrowseItemsFragment fragment) {
            this.item = item;
            this.fragment = fragment;
        }

        /** 
        * Instigats set of adding on click
        * @param v View
        */
        public void onClick(View v) {
            order.add(new ItemReference(item, 1, ""));
        }

    }

}
