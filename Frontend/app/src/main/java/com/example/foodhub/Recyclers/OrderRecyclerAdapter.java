package com.example.foodhub.Recyclers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.R;

import java.util.ArrayList;

public class OrderRecyclerAdapter extends MenuRecyclerAdapter {
    public OrderRecyclerAdapter(ArrayList<Object> arrayList) {
        super(arrayList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //  View view = inflater.inflate(R.layout.menu_view, parent,false);
        //return new RecyclerView.ViewHolder(view);

        RecyclerView.ViewHolder viewHolder;
        //  LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case MENU:
                View v1 = inflater.inflate(R.layout.order_item, parent,false);
                viewHolder = new ViewHolder1(v1);
                break;
            case IMAGE:
                View v2 =inflater.inflate(R.layout.companypage_picture, parent,false);
                viewHolder = new ViewHolder2(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.order_item, parent,false);
                viewHolder = new ViewHolder1(v);
                break;
        }

        return viewHolder;
    }

}
