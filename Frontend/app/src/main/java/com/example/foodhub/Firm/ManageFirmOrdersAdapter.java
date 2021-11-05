package com.example.foodhub.Firm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.ItemReferenceAdapter;
import com.example.foodhub.Common.Order;
import com.example.foodhub.Customer.ManageCustomerOrdersAdapter;
import com.example.foodhub.Customer.ManageCustomerOrdersFragment;
import com.example.foodhub.R;

import java.util.ArrayList;
import java.util.Locale;

public class ManageFirmOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;

    private ManageFirmOrdersFragment fragment;

    private ArrayList<Order> orders;

    public ManageFirmOrdersAdapter(String username, String password,
            ManageFirmOrdersFragment fragment, ArrayList<Order> orders) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.orders = orders;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_firm_order, parent, false);
        return new OrderHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        OrderHolder orderHolder = (OrderHolder) holder;
        orderHolder.customer.setText(orders.get(index).getCustomer());
        orderHolder.id.setText(String.format(Locale.ENGLISH, "%d", (int)orders.get(index).getId()));
        orderHolder.location.setText(orders.get(index).getLocation());
        orderHolder.total.setText(String.format(Locale.ENGLISH, "$%.2f", orders.get(index).getTotal()));
        orderHolder.recycler.setAdapter(new ItemReferenceAdapter(fragment, orders.get(index).getList()));
        orderHolder.recycler.setLayoutManager(new LinearLayoutManager(fragment.getContext()));
    }

    @Override public int getItemViewType(int index) {
        return orders.get(index) == null ? -1 : 0;
    }

    @Override public int getItemCount() {
        return orders.size();
    }

    class OrderHolder extends RecyclerView.ViewHolder {
        TextView customer;
        TextView id;
        TextView location;
        TextView total;
        Button discardButton;
        RecyclerView recycler;
        public OrderHolder(@NonNull View view) {
            super(view);
            customer = view.findViewById(R.id.firm_order_customer_text);
            id = view.findViewById(R.id.firm_order_id_text);
            location = view.findViewById(R.id.firm_order_location_text);
            total = view.findViewById(R.id.firm_order_total_text);
            discardButton = view.findViewById(R.id.firm_order_discard_button);
            recycler = view.findViewById(R.id.firm_order_recycler);
        }
    }

}
