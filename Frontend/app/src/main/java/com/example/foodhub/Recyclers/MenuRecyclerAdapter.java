package com.example.foodhub.Recyclers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Menu;
import com.example.foodhub.R;

import java.util.ArrayList;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> arrayList;
    public MenuRecyclerAdapter(ArrayList<Object> arrayList) {
        this.arrayList  = arrayList;
    }
    protected final int MENU = 0, IMAGE = 1;

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
                View v1 = inflater.inflate(R.layout.menu_view, parent,false);
                viewHolder = new ViewHolder1(v1);
                break;
            case IMAGE:
                View v2 =inflater.inflate(R.layout.companypage_picture, parent,false);
                viewHolder = new ViewHolder2(v2);
                break;
            default:
                View v = inflater.inflate(R.layout.menu_view, parent,false);
                viewHolder = new ViewHolder1(v);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case MENU:
                ViewHolder1 vh1 = (ViewHolder1) holder;
                configureViewHolder1(vh1, position);
                break;
            case IMAGE:
                ViewHolder2 vh2 = (ViewHolder2) holder;
                configureViewHolder2(vh2);
                break;
            default:
                ViewHolder1 vh3 = (ViewHolder1) holder;
                configureViewHolder1(vh3, position);
                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (arrayList.get(position) instanceof Menu) {
            return MENU;
        } else if (arrayList.get(position) instanceof String) {
            return IMAGE;
        }
        return -1;
    }


    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        Menu menu = (Menu) arrayList.get(position);
        if (menu != null) {
            vh1.title.setText((menu.getTitle()));
            vh1.menuImage.setImageResource(R.drawable.litramen);
            vh1.description.setText((menu.getDescription()));
            vh1.price.setText((menu.getPrice()));
        }
    }

    private void configureViewHolder2(ViewHolder2 vh2) {
        vh2.imageView.setImageResource(R.drawable.litramen);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        ImageView menuImage;
        TextView title;
        TextView description;
        TextView price;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            menuImage = itemView.findViewById(R.id.imageView_menuFoodPicture);
            title = itemView.findViewById(R.id.textView_foodname);
            description = itemView.findViewById(R.id.textView_desc);
            price = itemView.findViewById(R.id.textView_price);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {

         ImageView imageView;

        public ViewHolder2(@NonNull View v) {
            super(v);
            imageView =  v.findViewById(R.id.imageView);
        }

        public ImageView getImageView() {
            return imageView;
        }

        public void setImageView(ImageView imageView) {
            this.imageView = imageView;
        }
    }
}
