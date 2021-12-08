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
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.Common.Order;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BrowseMyFirmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;
    private ManageCustomerOrdersFragment fragment;
    private ArrayList<Order> orders;


    /**
     * Constructs a BrowseFirmsAdapter given enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param fragment The specific fragment within which the recycler lies
     */
    public BrowseMyFirmsAdapter(String username, String password,
                                ManageCustomerOrdersFragment fragment,  ArrayList<Order> orders) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.orders = orders;
    }

    /**
     * Creates a ViewHolder for a view; called for each view
     * @param parent The parent view of the recycler
     * @param viewType The type of view, which should always be 0
     * @return The tailored ViewHolder for the corresponding view
     */
    @NonNull
    @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_customer_order_title, parent, false);
        return new FirmHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; sets TextViews and binds them to their respective methods
     * @param holder A ViewHolder
     * @param index The index of the ViewHolder in the firms arraylist
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        FirmHolder firmHolder = (FirmHolder) holder;

        String total = "";
        firmHolder.firmNameText.setText(orders.get(index).getFirm());
        firmHolder.firmPriceText.setText("Total Price: $"+Double.toString(orders.get(index).getTotal()));

        //    firmHolder.firmPriceText.setText((int)orders.get(index).getTotal());
//        firmHolder.firmItemText.setText(orders.get(index).getList().size());
        firmHolder.firmItemText.setText("Item Count: " +Integer.toString(orders.get(index).getList().size()));

//

        GoToManageCustomerOrders goToManageCustomerOrders = new GoToManageCustomerOrders(orders.get(index).getId(), orders.get(index).getFirm(), fragment);
        firmHolder.firmImage.setOnClickListener(goToManageCustomerOrders);
        GoToOrderChat goToOrderChat = new GoToOrderChat(orders.get(index).getId(), orders.get(index).getFirm(), fragment);
        firmHolder.goToChatButton.setOnClickListener(goToOrderChat);
        SetImage setImage = new SetImage(firmHolder.firmImage);
        JSONObject obj = new JSONObject();
        try{obj.put("id",orders.get(index).getId());
            Call.post("download-firm-image", obj, setImage, null);
        } catch (JSONException e) {e.printStackTrace();}
    }

    /**
     * Returns the type of view that will be in the recycler at a specified index
     * @param index The index of the firm within the firm arraylist
     * @return The type of firm in the arraylist, which should always be 0
     */
    @Override public int getItemViewType(int index) {
        return orders.get(index) == null ? -1 : 0;
    }

    /**
     * Returns the number of views that will be in the recycler
     * @return The number of views that will be in the recycler
     */
    @Override public int getItemCount() {
        return orders.size();
    }

    private class SetImage implements ObjectResponse {
        private ImageView imageView;
        public SetImage(ImageView imageView) {
            this.imageView = imageView;
        }
        @Override public void respond(JSONObject response) {
            try{if (response.get("message").equals("success")) {
                String data = (String)response.get("data");
                byte[] bytes = Base64.decode(data, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imageView.setImageBitmap(bitmap);
            }} catch (Exception e) {
                Log.d("response", e.toString());}
        }
    }


    private class FirmHolder extends RecyclerView.ViewHolder {
        ImageView firmImage;
        TextView firmNameText;
        TextView firmPriceText;
        TextView firmItemText;
        Button goToChatButton;
        public FirmHolder(@NonNull View view) {
            super(view);
            firmImage = view.findViewById(R.id.ivPost);
            firmNameText = view.findViewById(R.id.title);
            firmPriceText = view.findViewById(R.id.firm_total_price);
            firmItemText = view.findViewById(R.id.firm_item_count);
            goToChatButton = view.findViewById(R.id.button);
        }
    }

    private class GoToManageCustomerOrders implements View.OnClickListener {
        private long id;
        private String firmName;
        private ManageCustomerOrdersFragment fragment;
        public GoToManageCustomerOrders(long id, String firmName, ManageCustomerOrdersFragment fragment) {
            this.id = id;
            this.firmName = firmName;
            this.fragment = fragment;
        }
        public void onClick(View v) {
            ArrayList<ItemReference> order = new ArrayList<>();
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new BrowseSpecificFirmOrders(id, firmName, username, password));
            ft.commit();
        }
    }

    private class GoToOrderChat implements View.OnClickListener {
        private String firmName;
        private long id;
        private ManageCustomerOrdersFragment fragment;
        public GoToOrderChat(long id, String firmName, ManageCustomerOrdersFragment fragment) {
            this.id = id;
            this.firmName = firmName;
            this.fragment = fragment;
        }

        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new OrderChatFragment(id, username, password, "customer", firmName));
            ft.commit();
        }
    }
}
