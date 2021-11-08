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
 * The class responsible allowing the user to look through their orders.
 * @author 1_CW_2
*/


public class ManageCustomerOrdersAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;

    private ManageCustomerOrdersFragment fragment;

    private ArrayList<Order> orders;

    /** 
 * Constructer for the addapter
 * @param username The Users username
 * @param password The Users password
*/
    public ManageCustomerOrdersAdapter(String username, String password,
            ManageCustomerOrdersFragment fragment, ArrayList<Order> orders) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.orders = orders;
    }

    /** 
    * Creates a recycle view on the creation of the method
    * @param viewType Int to designate the type of view
    * @param parent A ViewGroup that allows the program to remember the last layout
    * @return OrderHolder(view)
    * @see OrderHolder
    */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_customer_order, parent, false);
        return new OrderHolder(view);
    }
    /** 
    * Instigates the recycled view
    * @param index Int to designate the type of view
    * @param holder A ViewGroup that allows the program to remember the last layout
    * @return OrderHolder(view)
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
    * Method to get the item view type
    * @param index Int to check the view type
    * @return Item view type
    * @see OrderHolder
    */
    @Override public int getItemViewType(int index) {
        return orders.get(index) == null ? -1 : 0;
    }
    /** 
    * Method to get the item count
    * @return order size
    * @see OrderHolder
    */
    @Override public int getItemCount() {
        return orders.size();
    }

    /** 
    * Class creator to check information on the order holder
    */

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

    /** 
    * Class creator to discard an order 
    */
    class DiscardOrder implements View.OnClickListener, ObjectResponse {
        private long id;
        /** 
    * Method to designate id to be discarded.
    * @param id The Id of the order to be discarded, in long 
    */
        public DiscardOrder(long id) {
            this.id = id;
        }
    /** 
    * Method to discard order on click.
    * @param v View 
    */
        public void onClick(View v) {
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            JSONObject obj = new JSONObject(map);
            try{obj.put("id", id);
            } catch (JSONException e) {e.printStackTrace();}
            Call.post("customers-remove-order", obj, this, null);
        }
        /** 
    * Method ensure communication between frontend and server.
    * @param response JSONObject inputted from request 
    */
        public void respond(JSONObject response) {
            try{if (response.get("message").equals("success")) {
                fragment.refresh();
            }} catch (Exception e) {
                Log.d("response", e.toString());}
        }

    }

}
