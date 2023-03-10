package com.example.foodhub.Firm;

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

import com.example.foodhub.Common.Item;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A class for controlling the R.layout.view_edit_item view, placing it in a recycler
 * @author Arvid Gustafson
 * @see RecyclerView.Adapter
 */
public class ManageItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final long firmId;
    private final long categoryId;
    private final String username;
    private final String password;

    private ManageItemsFragment fragment;

    private ArrayList<Item> items;

    /**
     * Constructs a ManageItemsAdapter object given enumerated information
     * @param categoryId The category of the items here
     * @param username The username of the current user
     * @param password The password of the current user
     * @param fragment The fragment that contains the recycler
     * @param items The list information about items, which will be listed in the recycler
     */
    public ManageItemsAdapter(long firmId, long categoryId, String username, String password,
            ManageItemsFragment fragment, ArrayList<Item> items) {
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.fragment = fragment;
        this.items = items;
    }

    /**
     * Creates a ViewHolder given a view
     * @param parent The parent view of the recycler
     * @param viewType The type of view; it should always be 0
     * @return The ViewHolder that is created
     */
    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_edit_item, parent, false);
        return new ItemHolder(view);
    }

    /**
     * Binds a ViewHolder to the recycler; it sets the text and binds the button to the right method
     * @param holder The ViewHolder about to be bound
     * @param index The index of the information of ViewHolder in the items array
     */
    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.usernameText.setText(items.get(index).getTitle());
        EditItem editItem = new EditItem(items.get(index));
        itemHolder.editButton.setOnClickListener(editItem);
        DeleteItem deleteItem = new DeleteItem(items.get(index).getId());
        itemHolder.deleteButton.setOnClickListener(deleteItem);
        SetImage setImage = new SetImage(itemHolder.imageView);
        JSONObject obj = new JSONObject();
        try{obj.put("id",items.get(index).getId());
            Call.post("download-item-image", obj, setImage, null);
        } catch (JSONException e) {e.printStackTrace();}
    }

    /**
     * Retrieves the type of view given its index in the items array
     * @param index The index of the view in question
     * @return The type of view; it should always be 0
     */
    @Override public int getItemViewType(int index) {
        return items.get(index) == null ? -1 : 0;
    }

    /**
     * Retrieves the number of views to be in the recycler
     * @return The number of views to be in the recycler
     */
    @Override public int getItemCount() {
        return items.size();
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

    private class ItemHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView usernameText;
        Button editButton;
        Button deleteButton;
        public ItemHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.edit_item_imageview);
            usernameText = view.findViewById(R.id.edit_item_textview);
            editButton = view.findViewById(R.id.edit_item_edit_button);
            deleteButton = view.findViewById(R.id.edit_item_delete_button);
        }
    }

    private class EditItem implements View.OnClickListener {
        private Item item;
        public EditItem(Item item) {
            this.item = item;
        }
        public void onClick(View v) {
            final FragmentTransaction ft = fragment.getFragmentManager().beginTransaction();
            ft.replace(R.id.firm_fragment_main, new AddItemFragment(firmId, categoryId, username, password, item));
            ft.commit();
        }
    }

    private class DeleteItem implements View.OnClickListener, ObjectResponse {
        private long itemId;
        public DeleteItem(long itemId) {
            this.itemId = itemId;
        }
        public void onClick(View v) {
            Map<String, String> map = new HashMap<>();
            map.put("username", username);
            map.put("password", password);
            JSONObject obj = new JSONObject(map);
            try{obj.put("id", itemId);
            } catch (JSONException e) {e.printStackTrace();}
            Call.post("firms-remove-item", obj, this, null);
        }
        public void respond(JSONObject response) {
            try{if (response.get("message").equals("success")) {
                    fragment.refresh();
            }} catch (Exception e) {Log.d("response", e.toString());}
        }
    }

}
