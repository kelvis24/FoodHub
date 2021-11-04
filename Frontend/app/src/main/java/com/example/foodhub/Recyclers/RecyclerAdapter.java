package com.example.foodhub.Recyclers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Company;
import com.example.foodhub.Common.SmallCompany;
import com.example.foodhub.Customer.Home.CustomerHomeActivity;
import com.example.foodhub.R;
import com.example.foodhub.adapter.HorizontalAdapter;
import com.example.foodhub.adapter.VerticalAdapter;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Object> arrayList;
    private final int VERTICAL = 1;
    private final int HORIZONTAL = 2;
    private Context context;


    public RecyclerAdapter(Context context, ArrayList<Object> arrayList) {
        this.arrayList  = arrayList;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        RecyclerView.ViewHolder holder;

        switch (viewType) {
            case VERTICAL:
                view = inflater.inflate(R.layout.vertical, parent,false);
                holder = new VerticalViewHolder(view);
                break;
            case HORIZONTAL:
                view = inflater.inflate(R.layout.horizontal, parent,false);
                holder = new HorizontalViewHolder(view);
                break;
            default:
                view = inflater.inflate(R.layout.vertical, parent,false);
                holder = new VerticalViewHolder(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder.getItemViewType() == VERTICAL)
            verticalView((VerticalViewHolder) holder);
        else if (holder.getItemViewType() == HORIZONTAL)
            horizontalView((HorizontalViewHolder) holder);
    }


    private void verticalView(VerticalViewHolder holder) {
        VerticalAdapter adapter1 = new VerticalAdapter(CustomerHomeActivity.getVerticalData());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(adapter1);
    }

    private void horizontalView(HorizontalViewHolder holder) {
        HorizontalAdapter adapter = new HorizontalAdapter(CustomerHomeActivity.getHorizontalData());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemViewType (int position) {
        if (arrayList.get(position) instanceof Company) {
            return VERTICAL;
        }
        else if (arrayList.get(position) instanceof SmallCompany){
            return HORIZONTAL;
        }

        return -1;
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public HorizontalViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = (RecyclerView) itemView.findViewById((R.id.inner_recyclerView));

       }
    }

    public class VerticalViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public VerticalViewHolder(@NonNull View itemView) {
            super(itemView);


            recyclerView = (RecyclerView) itemView.findViewById((R.id.inner_recyclerView));

        }
    }


}
