package com.example.foodhub;

import android.media.Image;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Company> arrayList;
    public RecyclerAdapter(ArrayList<Company> arrayList) {
    this.arrayList  = arrayList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.company_view, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Company company = arrayList.get(position);
        holder.title.setText((company.getTitle()));
        holder.message.setText((company.getMessage()));
        holder.profileImage.setImageResource((company.getProfileIcon()));
        holder.postImage.setImageResource((company.getPostImage()));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView profileImage;
        ImageView postImage;
        TextView title;
        TextView message;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profileImage = itemView.findViewById(R.id.ivPost);
            postImage = itemView.findViewById(R.id.ivPost);
            title = itemView.findViewById(R.id.title);
            message = itemView.findViewById(R.id.message);


       }
    }


}
