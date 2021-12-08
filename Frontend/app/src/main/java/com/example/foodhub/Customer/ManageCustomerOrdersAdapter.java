package com.example.foodhub.Customer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
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
 * A class for controlling the R.layout.view_customer_order view, placing it in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
 */
public class ManageCustomerOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;
    private BrowseSpecificFirmOrders fragment;
    private ArrayList<Order> orders;

    /**
     * Constructs a ManageFirmOrdersAdapter object given enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param fragment The fragment that contains the recycler
     * @param orders The list information about orders, which will be listed in the recycler
     */
    public ManageCustomerOrdersAdapter(String username, String password,
                                       BrowseSpecificFirmOrders fragment, ArrayList<Order> orders) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_customer_order, parent, false);
        return new OrderHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; it sets up the TextViews and inner recycler
     * @param holder The ViewHolder about to be bound
     * @param index The index of the information of ViewHolder in the orders array
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
                if (orders.size() ==0) {
                    final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
                    ft.replace(R.id.customer_fragment_main, new ManageCustomerOrdersFragment());
                    ft.commit();
                }
            }} catch (Exception e) {
                Log.d("response", e.toString());}
        }

    }

}
