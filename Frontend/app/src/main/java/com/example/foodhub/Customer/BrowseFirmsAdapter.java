package com.example.foodhub.Customer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.R;

import java.util.ArrayList;

/**
 * Class that's the Adapter for the recycler views
 */
public class BrowseFirmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;
    private BrowseFirmsFragment fragment;
    private ArrayList<Firm> firms;

    /**
     * Method responsible for the initailizing the class
     */
    public BrowseFirmsAdapter(String username, String password,
            BrowseFirmsFragment fragment, ArrayList<Firm> firms) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.firms = firms;
    }

    /**
     * Method responsible for returning the view
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_browse_firm, parent, false);
        return new FirmHolder(view);
    }

    /**
     * Method responsible for binding the views
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        FirmHolder firmHolder = (FirmHolder) holder;
        firmHolder.usernameText.setText(firms.get(index).getName());
        GoToBrowseCategories goToBrowseCategories = new GoToBrowseCategories(firms.get(index).getId(), fragment);
        firmHolder.usernameText.setOnClickListener(goToBrowseCategories);
    }

    /**
     * Method responsible for the item view type
     */
    @Override public int getItemViewType(int index) {
        return firms.get(index) == null ? -1 : 0;
    }
    /**
     * Method responsible for the returning size
     */
    @Override public int getItemCount() {
        return firms.size();
    }

    private class FirmHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        public FirmHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.browse_firm_textview);
        }
    }

    private class GoToBrowseCategories implements View.OnClickListener {
        private long firmId;
        private BrowseFirmsFragment fragment;

        public GoToBrowseCategories(long firmId, BrowseFirmsFragment fragment) {
            this.firmId = firmId;
            this.fragment = fragment;
        }

        public void onClick(View v) {
            ArrayList<ItemReference> order = new ArrayList<>();
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new BrowseCategoriesFragment(firmId, username, password, order));
            ft.commit();
        }
    }

}
