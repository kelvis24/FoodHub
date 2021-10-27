package com.example.foodhub.adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Company;
import com.example.foodhub.R;
import com.example.foodhub.SmallCompany;

import java.util.ArrayList;

import javax.xml.transform.dom.DOMLocator;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyViewHolder> {

    ArrayList<Company> data = new ArrayList<>();
    public VerticalAdapter(ArrayList<Company> data) {
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
        holder.title.setText((data.get(position).getTitle()));
        holder.message.setText((data.get(position).getMessage()));
        holder.profileImage.setImageResource((data.get(position).getProfileIcon()));
        holder.postImage.setImageResource((data.get(position).getPostImage()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        ImageView postImage;
        TextView title;
        TextView message;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.ivPost);
            postImage = itemView.findViewById(R.id.ivPost);
            title = itemView.findViewById(R.id.title);
            message = itemView.findViewById(R.id.message);
        }
    }
}
