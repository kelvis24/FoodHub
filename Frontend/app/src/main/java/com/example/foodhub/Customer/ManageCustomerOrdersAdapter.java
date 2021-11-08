package com.example.foodhub.Customer;

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
import com.example.foodhub.Firm.ManageCategoriesAdapter;
import com.example.foodhub.Firm.ManageCategoriesFragment;
import com.example.foodhub.Firm.ManageFirmOrdersAdapter;
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
 * Class responsible for creating View
 */
public class ManageCustomerOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;
    private ManageCustomerOrdersFragment fragment;
    private ArrayList<Order> orders;

    /**
     * Method responsible for initializing
     */
    public ManageCustomerOrdersAdapter(String username, String password,
                                       ManageCustomerOrdersFragment fragment, ArrayList<Order> orders) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.orders = orders;
    }

    /**
     * Method responsible for returning View
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_customer_order, parent, false);
        return new OrderHolder(view);
    }

    /**
     * Method responsible for binding View
     */
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
        DiscardOrder discardOrder = new DiscardOrder(orders.get(index).getId());
        orderHolder.discardButton.setOnClickListener(discardOrder);
    }

    /**
     * Method responsible for getting Item View
     */
    @Override public int getItemViewType(int index) {
        return orders.get(index) == null ? -1 : 0;
    }

    /**
     * Method responsible for geting item count
     */
    @Override public int getItemCount() {
        return orders.size();
    }

    private class OrderHolder extends RecyclerView.ViewHolder {
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

    private class DiscardOrder implements View.OnClickListener, ObjectResponse {
        private long id;

        public DiscardOrder(long id) {
            this.id = id;
        }

        public void onClick(View v) {
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            JSONObject obj = new JSONObject(map);
            try{obj.put("id", id);
            } catch (JSONException e) {e.printStackTrace();}
            Call.post("customers-remove-order", obj, this, null);
        }


        public void respond(JSONObject response) {
            try{if (response.get("message").equals("success")) {
                fragment.refresh();
            }} catch (Exception e) {
                Log.d("response", e.toString());}
        }

    }

}
