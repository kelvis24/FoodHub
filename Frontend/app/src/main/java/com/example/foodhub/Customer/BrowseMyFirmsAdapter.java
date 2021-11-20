package com.example.foodhub.Customer;

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
import com.example.foodhub.R;

import java.util.ArrayList;

public class BrowseMyFirmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;
    private ManageCustomerOrdersFragment fragment;
    private ArrayList<Firm> firms;


    /**
     * Constructs a BrowseFirmsAdapter given enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param fragment The specific fragment within which the recycler lies
     * @param firms The list of firms retrieved form the backend
     */
    public BrowseMyFirmsAdapter(String username, String password,
                                ManageCustomerOrdersFragment fragment, ArrayList<Firm> firms) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.firms = firms;
    }

    /**
     * Creates a ViewHolder for a view; called for each view
     * @param parent The parent view of the recycler
     * @param viewType The type of view, which should always be 0
     * @return The tailored ViewHolder for the corresponding view
     */
    @NonNull
    @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_order_firm, parent, false);
        return new FirmHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; sets TextViews and binds them to their respective methods
     * @param holder A ViewHolder
     * @param index The index of the ViewHolder in the firms arraylist
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        FirmHolder firmHolder = (FirmHolder) holder;
        firmHolder.firmImage.setImageResource(Firm.randomFirmImage());
        firmHolder.firmNameText.setText(firms.get(index).getName());
        firmHolder.firmPriceText.setText(firms.get(index).getLocation());
        firmHolder.firmItemText.setText(firms.get(index).getCuisine());
        GoToManageCustomerOrders goToManageCustomerOrders = new GoToManageCustomerOrders(firms.get(index).getName(), fragment);
        firmHolder.firmNameText.setOnClickListener(goToManageCustomerOrders);
        GoToOrderChat goToOrderChat = new GoToOrderChat(firms.get(index).getName(), fragment);
        firmHolder.goToChatButton.setOnClickListener(goToOrderChat);
    }

    /**
     * Returns the type of view that will be in the recycler at a specified index
     * @param index The index of the firm within the firm arraylist
     * @return The type of firm in the arraylist, which should always be 0
     */
    @Override public int getItemViewType(int index) {
        return firms.get(index) == null ? -1 : 0;
    }

    /**
     * Returns the number of views that will be in the recycler
     * @return The number of views that will be in the recycler
     */
    @Override public int getItemCount() {
        return firms.size();
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
        private String firmName;
        private ManageCustomerOrdersFragment fragment;
        public GoToManageCustomerOrders(String firmName, ManageCustomerOrdersFragment fragment) {
            this.firmName = firmName;
            this.fragment = fragment;
        }
        public void onClick(View v) {
            ArrayList<ItemReference> order = new ArrayList<>();
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new BrowseSpecificFirmOrders(firmName, username, password));
            ft.commit();
        }
    }

    private class GoToOrderChat implements View.OnClickListener {
        private String firmName;
        private ManageCustomerOrdersFragment fragment;
        public GoToOrderChat(String firmName, ManageCustomerOrdersFragment fragment) {
            this.firmName = firmName;
            this.fragment = fragment;
        }
        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new OrderChatFragment(firmName, username, password));
            ft.commit();
        }
    }
}