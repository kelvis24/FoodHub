package com.example.foodhub.Customer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.R;

import java.util.ArrayList;

/**
 * The controller for the R.layout.view_browse_firm view, placing them in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
 */
public class BrowseFirmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;
    private BrowseFirmsFragment fragment;
    private ArrayList<Firm> firms;

    /**
     * Constructs a BrowseFirmsAdapter given enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param fragment The specific fragment within which the recycler lies
     * @param firms The list of firms retrieved form the backend
     */
    public BrowseFirmsAdapter(String username, String password,
            BrowseFirmsFragment fragment, ArrayList<Firm> firms) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.firms = firms;
    }

    public BrowseFirmsAdapter(String username, String password, ArrayList<Firm> firms) {
        this.username = username;
        this.password = password;
        this.firms = firms;
    }

    /**
     * Creates a ViewHolder for a view; called for each view
     * @param parent The parent view of the recycler
     * @param viewType The type of view, which should always be 0
     * @return The tailored ViewHolder for the corresponding view
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_browse_firm, parent, false);
        return new FirmHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; sets TextViews and binds them to their respective methods
     * @param holder A ViewHolder
     * @param index The index of the ViewHolder in the firms arraylist
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        FirmHolder firmHolder = (FirmHolder) holder;
        firmHolder.usernameText.setText(firms.get(index).getName());
        firmHolder.openTime.setText("Opens: " +Integer.toString(firms.get(index).getOpen_time()));
        firmHolder.closeTime.setText("Closes: "+Integer.toString(firms.get(index).getClose_time()));
        firmHolder.location.setText("Location: "+firms.get(index).getLocation());
        firmHolder.cusine.setText("Cuisine: "+firms.get(index).getCuisine());
        firmHolder.firmPicture.setImageResource(Firm.randomFirmImage());
        GoToBrowseCategories goToBrowseCategories = new GoToBrowseCategories(firms.get(index).getId(), fragment);
        firmHolder.firmPicture.setOnClickListener(goToBrowseCategories);
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
        ImageView firmPicture;
        TextView usernameText;
        TextView openTime;
        TextView closeTime;
        TextView cusine;
        TextView location;
        public FirmHolder(@NonNull View view) {
            super(view);
            firmPicture= view.findViewById(R.id.ivPost);
            usernameText = view.findViewById(R.id.browse_firm_textview);
            openTime = view.findViewById(R.id.firm_open_time);
            closeTime = view.findViewById(R.id.firm_close_time);
            location = view.findViewById(R.id.firm_location);
            cusine = view.findViewById(R.id.firm_cuisine);
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
