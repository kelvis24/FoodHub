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
 * The class responsible allowing the user to look through their firms.
 * @author 1_CW_2
*/
public class BrowseFirmsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private String username;
    private String password;

    private BrowseFirmsFragment fragment;

    private ArrayList<Firm> firms;
    /**
    * Constructor taking in the firm username, password, fragment and firm list.
    * @param username Firm username
    * @param password Firm Password
    * @param fragment BrowseFirmsFragment constructor
    * @param firms ArrayList of given firms
    */
    public BrowseFirmsAdapter(String username, String password,
            BrowseFirmsFragment fragment, ArrayList<Firm> firms) {
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.firms = firms;
    }
    /** 
    * Creates a recycle view on the creation of the method
    * @param viewType Int to designate the type of view
    * @param parent A ViewGroup that allows the program to remember the last layout
    * @return FirmHolder(view)
    * @see FirmHolder
    */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_browse_firm, parent, false);
        return new FirmHolder(view);
    }
    /** 
    * Instigates the recycled view of firms
    * @param index Int to designate the type of view
    * @param holder A ViewGroup that allows the program to remember the last layout
    */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        FirmHolder firmHolder = (FirmHolder) holder;
        firmHolder.usernameText.setText(firms.get(index).getName());
        GoToBrowseCategories goToBrowseCategories = new GoToBrowseCategories(firms.get(index).getId(), fragment);
        firmHolder.usernameText.setOnClickListener(goToBrowseCategories);
    }
    /** 
    * Method to get the item view type
    * @param index Int to check the view type
    * @return firm view type
    */
    @Override public int getItemViewType(int index) {
        return firms.get(index) == null ? -1 : 0;
    }
     /** 
    * Method to get the item count
    * @return firm size
    */
    @Override public int getItemCount() {
        return firms.size();
    }
    /** 
    * Class creator to check information on the firm
    */

    class FirmHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        public FirmHolder(@NonNull View view) {
            super(view);
            usernameText = view.findViewById(R.id.browse_firm_textview);
        }
    }
    /** 
    * Class to browse the different firm catagories
    */

    class GoToBrowseCategories implements View.OnClickListener {
        private long firmId;
        private BrowseFirmsFragment fragment;
        /** 
        * Method to browse given catagories dependant on ID and fragment
        * @param firmID long number for the firm ID
        * @param fragment BroseFirmFragment constructor
        */
        public GoToBrowseCategories(long firmId, BrowseFirmsFragment fragment) {
            this.firmId = firmId;
            this.fragment = fragment;
        }
        /** 
        * Method to activate class on click
        */
        public void onClick(View v) {
            ArrayList<ItemReference> order = new ArrayList<>();
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new BrowseCategoriesFragment(firmId, username, password, order));
            ft.commit();
        }
    }

}
