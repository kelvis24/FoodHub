package com.example.foodhub.Customer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodhub.Common.Category;
import com.example.foodhub.Common.Firm;
import com.example.foodhub.Common.ItemReference;
import com.example.foodhub.Firm.ManageCategoriesAdapter;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * The controller for the R.layout.view_browse_category view, placing them in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
 */
public class BrowseCategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private long firmId;
    private String username;
    private String password;
    private ArrayList<ItemReference> order;
    private BrowseCategoriesFragment fragment;
    private ArrayList<Category> categories;

    /**
     * Constructs a BrowseCategoryAdapter given enumerated information
     * @param firmId The id of the firm of the categories being browsed
     * @param username The username of the current user
     * @param password The password of the current user
     * @param order The qualities of the ongoing order
     * @param fragment The specific fragment within which the recycler lies
     * @param categories The list of categories retrieved form the backend
     */
    public BrowseCategoriesAdapter(long firmId, String username, String password, ArrayList<ItemReference> order,
                                   BrowseCategoriesFragment fragment, ArrayList<Category> categories) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.order = order;
        this.fragment = fragment;
        this.categories = categories;
    }

    /**
     * Creates a ViewHolder for a view; called for each view
     * @param parent The parent view of the recycler
     * @param viewType The type of view, which should always be 0
     * @return The tailored ViewHolder for the corresponding view
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_browse_category, parent, false);
        return new CategoryHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; sets TextViews and binds it to its proper function
     * @param holder A ViewHolder
     * @param index The index of the ViewHolder in the categories arraylist
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        CategoryHolder categoryHolder = (CategoryHolder) holder;
        categoryHolder.usernameText.setText(categories.get(index).getTitle());
        GoToBrowseItems goToBrowseItems = new GoToBrowseItems(categories.get(index).getId(), fragment);
        categoryHolder.categoryImg.setOnClickListener(goToBrowseItems);
        SetImage setImage = new SetImage(categoryHolder.categoryImg);
        JSONObject obj = new JSONObject();
        try{obj.put("id",categories.get(index).getId());
            Call.post("download-category-image", obj, setImage, null);
        } catch (JSONException e) {e.printStackTrace();}
    }

    /**
     * Returns the type of view that will be in the recycler at a specified index
     * @param index The index of the category within the categories arraylist
     * @return The type of category in the arraylist, which should always be 0
     */
    @Override public int getItemViewType(int index) {
        return categories.get(index) == null ? -1 : 0;
    }

    /**
     * Returns the number of views that will be in the recycler
     * @return The number of views that will be in the recycler
     */
    @Override public int getItemCount() {
        return categories.size();
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
            }} catch (Exception e) {Log.d("response", e.toString());}
        }
    }

    private class CategoryHolder extends RecyclerView.ViewHolder {
        TextView usernameText;
        ImageView categoryImg;
        public CategoryHolder(@NonNull View view) {
            super(view);
            categoryImg  = view.findViewById(R.id.browse_category_imageview);
            usernameText = view.findViewById(R.id.browse_category_textview);
        }
    }

    private class GoToBrowseItems implements View.OnClickListener {
        private long categoryId;
        private BrowseCategoriesFragment fragment;
        public GoToBrowseItems(long categoryId, BrowseCategoriesFragment fragment) {
            this.categoryId = categoryId;
            this.fragment = fragment;
        }
        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.customer_fragment_main, new BrowseItemsFragment(firmId, categoryId, username, password, order));
            ft.commit();
        }
    }

}
