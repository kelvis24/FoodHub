package com.example.foodhub.Common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.foodhub.Customer.BrowseItemsAdapter;
import com.example.foodhub.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * The controller for the R.layout.view_item_reference view, placing them in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
 */
public class ItemReferenceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Fragment fragment;

    private ArrayList<ItemReference> order;

    /**
     * Constructs a ManageAdminsAdapter given enumerated information
     * @param fragment The specific fragment within which the recycler lies
     * @param orders The list of orders retrieved form the backend
     */
    public ItemReferenceAdapter(Fragment fragment, ArrayList<ItemReference> order) {
        this.fragment = fragment;
        this.order = order;
    }

    /**
     * Creates a ViewHolder for a view; called for each view
     * @param parent The parent view of the recycler
     * @param viewType The type of view, which should always be 0
     * @return The tailored ViewHolder for the corresponding view
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_reference, parent, false);
        return new ItemReferenceHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; sets the text of some TextViews
     * @param holder A ViewHolder
     * @param index The index of the ViewHolder in the admins arraylist
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        ItemReferenceHolder itemHolder = (ItemReferenceHolder) holder;
        itemHolder.title.setText(order.get(index).getItem().getTitle());
        itemHolder.quantity.setText(String.format(Locale.ENGLISH, "%d", order.get(index).getQuantity()));
        itemHolder.price.setText(String.format(Locale.ENGLISH, "$%.2f", order.get(index).getItem().getPrice()));
    }

    /**
     * Returns the type of view that will be in the recycler at a specified index
     * @param index The index of the admin within the admins arraylist
     * @return The type of admin in the arraylist, which should always be 0
     */
    @Override public int getItemViewType(int index) {
        return order.get(index) == null ? -1 : 0;
    }

    /**
     * Returns the number of views that will be in the recycler
     * @return The number of views that will be in the recycler
     */
    @Override public int getItemCount() {
        return order.size();
    }

    private class ItemReferenceHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView quantity;
        TextView price;
        public ItemReferenceHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.item_reference_title);
            quantity = view.findViewById(R.id.item_reference_quantity);
            price = view.findViewById(R.id.item_reference_price);
        }
    }


}
