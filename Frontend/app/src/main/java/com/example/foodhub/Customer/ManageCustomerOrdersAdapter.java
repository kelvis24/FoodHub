package com.example.foodhub.Customer;

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
import com.example.foodhub.Firm.ManageCategoriesAdapter;
import com.example.foodhub.Firm.ManageCategoriesFragment;
import com.example.foodhub.R;

import java.util.ArrayList;
import java.util.Locale;

public class ManageCustomerOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;

    private ManageCustomerOrdersFragment fragment;

    private ArrayList<Order> orders;

    public ManageCustomerOrdersAdapter(String username, String password,
            ManageCustomerOrdersFragment fragment, ArrayList<Order> orders) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.orders = orders;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_customer_order, parent, false);
        return new OrderHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        OrderHolder orderHolder = (OrderHolder) holder;
        orderHolder.firm.setText(orders.get(index).getFirm());
        orderHolder.id.setText(String.format(Locale.ENGLISH, "%d", (int)orders.get(index).getId()));
        switch (orders.get(index).getStatus()) {
            case 0:
                orderHolder.status.setText(R.string.In_Progress);
                orderHolder.discardButton.setEnabled(false);
                break;
            case 1:
                orderHolder.status.setText(R.string.Complete);
                break;
            case 2:
                orderHolder.status.setText(R.string.Obsolete);
                break;
            case 3:
                orderHolder.status.setText(R.string.Jeopardized);
                break;
            default:
                break;
        }
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
        TextView firm;
        TextView id;
        TextView status;
        TextView total;
        Button discardButton;
        RecyclerView recycler;
        public OrderHolder(@NonNull View view) {
            super(view);
            firm = view.findViewById(R.id.customer_order_firm_text);
            id = view.findViewById(R.id.customer_order_id_text);
            status = view.findViewById(R.id.customer_order_status_text);
            total = view.findViewById(R.id.customer_order_total_text);
            discardButton = view.findViewById(R.id.customer_order_discard_button);
            recycler = view.findViewById(R.id.customer_order_recycler);
        }
    }

}
