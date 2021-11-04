package com.example.foodhub.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.R;

import java.util.ArrayList;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyViewHolder> {

    ArrayList<Firm> data = new ArrayList<>();
    public VerticalAdapter(ArrayList<Firm> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public VerticalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_single_row, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText((data.get(position).getName()));
        holder.description.setText("description");
        holder.location.setText((data.get(position).getLocation()));
        holder.cuisine.setText((data.get(position).getCuisine()));
        // holder.postImage.setImageResource((data.get(position).getPostImage()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView postImage;
        TextView name;
        TextView description;
        TextView location;
        TextView cuisine;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            postImage = itemView.findViewById(R.id.ivPost);
            name = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.message);
            location = itemView.findViewById(R.id.cuisine);
            cuisine = itemView.findViewById(R.id.location);
        }
    }
}
