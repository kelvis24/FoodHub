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

public class ItemReferenceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Fragment fragment;

    private ArrayList<ItemReference> order;

    public ItemReferenceAdapter(Fragment fragment, ArrayList<ItemReference> order) {
        this.fragment = fragment;
        this.order = order;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_reference, parent, false);
        return new ItemReferenceHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        ItemReferenceHolder itemHolder = (ItemReferenceHolder) holder;
        itemHolder.title.setText(order.get(index).getItem().getTitle());
        itemHolder.quantity.setText(String.format(Locale.ENGLISH, "%d", order.get(index).getQuantity()));
        itemHolder.price.setText(String.format(Locale.ENGLISH, "$%.2f", order.get(index).getItem().getPrice()));
    }

    @Override public int getItemViewType(int index) {
        return order.get(index) == null ? -1 : 0;
    }

    @Override public int getItemCount() {
        return order.size();
    }

    class ItemReferenceHolder extends RecyclerView.ViewHolder {
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
