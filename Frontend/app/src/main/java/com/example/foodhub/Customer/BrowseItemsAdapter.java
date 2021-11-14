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
 * The controller for the R.layout.view_browse_item view, placing them in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
 */
public class BrowseItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long categoryId;
    private String username;
    private String password;
    private ArrayList<ItemReference> order;
    private BrowseItemsFragment fragment;
    private ArrayList<Item> items;

    /**
     * Constructs a BrowseItemsAdapter given enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param fragment The specific fragment within which the recycler lies
     * @param items The list of items retrieved form the backend
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
     * Creates a ViewHolder for a view; called for each view
     * @param parent The parent view of the recycler
     * @param viewType The type of view, which should always be 0
     * @return The tailored ViewHolder for the corresponding view
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_browse_item, parent, false);
        return new ItemHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; sets TextViews and binds buttons
     * @param holder A ViewHolder
     * @param index The index of the ViewHolder in the items arraylist
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.usernameText.setText(items.get(index).getTitle());
        itemHolder.price.setText(String.format(Locale.ENGLISH, "$%.2f", items.get(index).getPrice()));
        AddItem addItem = new AddItem(items.get(index), fragment);
        itemHolder.addButton.setOnClickListener(addItem);
    }

    /**
     * Returns the type of view that will be in the recycler at a specified index
     * @param index The index of the item within the items arraylist
     * @return The type of items in the arraylist, which should always be 0
     */
    @Override public int getItemViewType(int index) {
        return items.get(index) == null ? -1 : 0;
    }

    /**
     * Returns the number of views that will be in the recycler
     * @return The number of views that will be in the recycler
     */
    @Override public int getItemCount() {
        return items.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
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

    private class AddItem implements View.OnClickListener {
        private Item item;
        private BrowseItemsFragment fragment;
        public AddItem(Item item, BrowseItemsFragment fragment) {
            this.item = item;
            this.fragment = fragment;
        }
        public void onClick(View v) {
            order.add(new ItemReference(item, 1, ""));
        }
    }

}
