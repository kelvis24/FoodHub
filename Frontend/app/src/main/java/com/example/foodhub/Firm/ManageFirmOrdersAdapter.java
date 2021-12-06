package com.example.foodhub.Firm;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.ItemReferenceAdapter;
import com.example.foodhub.Common.Order;
import com.example.foodhub.Customer.BrowseMyFirmsAdapter;
import com.example.foodhub.Customer.ManageCustomerOrdersFragment;
import com.example.foodhub.Customer.OrderChatFragment;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * A class for controlling the R.layout.view_firm_order view, placing it in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
 */
public class ManageFirmOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;

    private ManageFirmOrdersFragment fragment;

    private ArrayList<Order> orders;

    /**
     * Constructs a ManageFirmOrdersAdapter object given enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param fragment The fragment that contains the recycler
     * @param orders The list information about orders, which will be listed in the recycler
     */
    public ManageFirmOrdersAdapter(String username, String password,
            ManageFirmOrdersFragment fragment, ArrayList<Order> orders) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.orders = orders;
    }

    /**
     * Creates a ViewHolder given a view
     * @param parent The parent view of the recycler
     * @param viewType The type of view; it should always be 0
     * @return The ViewHolder that is created
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_firm_order, parent, false);
        return new OrderHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; it sets up the TextViews and inner recycler
     * @param holder The ViewHolder about to be bound
     * @param index The index of the information of ViewHolder in the orders array
     */
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
        GoToOrderChat goToOrderChat = new GoToOrderChat(orders.get(index).getCustomer(), this.fragment);
        orderHolder.customer.setOnClickListener(goToOrderChat);


    }

    /**
     * Retrieves the type of view given its index in the orders array
     * @param index The index of the view in question
     * @return The type of view; it should always be 0
     */
    @Override public int getItemViewType(int index) {
        return orders.get(index) == null ? -1 : 0;
    }

    /**
     * Retrieves the number of views to be in the recycler
     * @return The number of views to be in the recycler
     */
    @Override public int getItemCount() {
        return orders.size();
    }

    private class OrderHolder extends RecyclerView.ViewHolder {
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

    private class CompleteOrder implements View.OnClickListener, ObjectResponse {
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

    private class GoToOrderChat implements View.OnClickListener {
        private String customerName;
        private ManageFirmOrdersFragment fragment;
        public GoToOrderChat(String customerName, ManageFirmOrdersFragment fragment) {
            this.customerName = customerName;
            this.fragment = fragment;
        }
        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.firm_fragment_main, new OrderChatFragment(customerName, username, password));
            ft.commit();
        }
    }

}
