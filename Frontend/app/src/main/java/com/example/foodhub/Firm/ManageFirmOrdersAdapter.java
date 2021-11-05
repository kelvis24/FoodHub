package com.example.foodhub.Firm;

import android.util.Log;
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
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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
        CompleteOrder completeOrder = new CompleteOrder(orders.get(index).getId());
        orderHolder.completeButton.setOnClickListener(completeOrder);
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
        Button completeButton;
        RecyclerView recycler;
        public OrderHolder(@NonNull View view) {
            super(view);
            customer = view.findViewById(R.id.firm_order_customer_text);
            id = view.findViewById(R.id.firm_order_id_text);
            location = view.findViewById(R.id.firm_order_location_text);
            total = view.findViewById(R.id.firm_order_total_text);
            completeButton = view.findViewById(R.id.firm_order_complete_button);
            recycler = view.findViewById(R.id.firm_order_recycler);
        }
    }

    class CompleteOrder implements View.OnClickListener, ObjectResponse {
        private long id;

        public CompleteOrder(long id) {
            this.id = id;
        }

        public void onClick(View v) {
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            JSONObject obj = new JSONObject(map);
            try{obj.put("orderId", id);
            } catch (JSONException e) {e.printStackTrace();}
            Call.post("firms-complete-order", obj, this, null);
        }

        public void respond(JSONObject response) {
            try{if (response.get("message").equals("success")) {
                fragment.refresh();
            }} catch (Exception e) {Log.d("response", e.toString());}
        }

    }

}
