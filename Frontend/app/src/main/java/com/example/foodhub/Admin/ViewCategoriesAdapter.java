package com.example.foodhub.Admin;

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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Category;
import com.example.foodhub.Customer.BrowseCategoriesAdapter;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Sets up the view adapter for Firms
 * @author Marcus Reecy, Arvid Gustafson
 * @see Fragment
 */
public class ViewCategoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final long firmId;
    private final String username;
    private final String password;
    private final String type;

    private ViewCategoriesFragment fragment;

    private ArrayList<Category> categories;

    /**
     * Constructs a ViewFirmAdapter from enumerated information
     * @param username The username of the current user
     * @param password The password of the current user
     * @param firmId The ID of the current firm being used
     */
    public ViewCategoriesAdapter(long firmId, String username, String password, String type,
                                 ViewCategoriesFragment fragment, ArrayList<Category> categories) {
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.type = type;
        this.fragment = fragment;
        this.categories = categories;
    }

    public ViewCategoriesAdapter() {
        this.firmId = 0;
        this.username = null;
        this.password = null;
        this.type = null;
        this.fragment = null;
    }

    /**
     * Creates the view
     * @param parent A Viewgroup
     * @param viewType An int designating the view type
     * @return The view that is created
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_view_category, parent, false);
        return new CategoryHolder(view);
    }

    /**
     * Repeats the view currently selected
     * @param holder A Viewgroup
     * @param index An int designating the view type
     * @return The view that is created
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        CategoryHolder categoryHolder = (CategoryHolder) holder;
        categoryHolder.titleText.setText(categories.get(index).getTitle());
        GoToViewItems goToViewItems = new GoToViewItems(categories.get(index).getId());
        categoryHolder.titleText.setOnClickListener(goToViewItems);
        SetImage setImage = new SetImage(categoryHolder.imageView);
        JSONObject obj = new JSONObject();
        try{obj.put("id",categories.get(index).getId());
            Call.post("download-category-image", obj, setImage, null);
        } catch (JSONException e) {e.printStackTrace();}
    }

    @Override public int getItemViewType(int index) {
        return categories.get(index) == null ? -1 : 0;
    }

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
            }} catch (Exception e) {
                Log.d("response", e.toString());}
        }
    }

    private class CategoryHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        ImageView imageView;
        public CategoryHolder(@NonNull View view) {
            super(view);
            titleText = view.findViewById(R.id.view_category_textview);
            imageView = view.findViewById(R.id.view_category_imageview);
        }
    }

    /**
     * Sets up the firm and sets for the onclick listener
     */
    class GoToViewItems implements View.OnClickListener {
        private long categoryId;
        public GoToViewItems(long firmId) {
            this.categoryId = firmId;
        }
        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            if (type.equals("owner"))
                ft.replace(R.id.owner_fragment_main, new ViewItemsFragment(firmId, categoryId, username, password, type));
            else
                ft.replace(R.id.admin_fragment_main, new ViewItemsFragment(firmId, categoryId, username, password, type));
            ft.commit();
        }
    }

}
