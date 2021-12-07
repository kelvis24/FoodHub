package com.example.foodhub.Admin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Item;
import com.example.foodhub.R;

import java.util.ArrayList;

public class ViewItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ViewItemsFragment fragment;

    private ArrayList<Item> items;

    public ViewItemsAdapter(ViewItemsFragment fragment, ArrayList<Item> items) {
        this.fragment = fragment;
        this.items = items;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_view_item, parent, false);
        return new ItemHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        ItemHolder categoryHolder = (ItemHolder) holder;
        categoryHolder.titleText.setText(items.get(index).getTitle());
    }

    @Override public int getItemViewType(int index) {
        return items.get(index) == null ? -1 : 0;
    }

    @Override public int getItemCount() {
        return items.size();
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        public ItemHolder(@NonNull View view) {
            super(view);
            titleText = view.findViewById(R.id.view_item_textview);
        }
    }

}
