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
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodhub.Common.Item;
import com.example.foodhub.Firm.ManageItemsAdapter;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;
import com.example.foodhub.server.ObjectResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ViewItemsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ViewItemsFragment fragment;

    private ArrayList<Item> items;

    public ViewItemsAdapter(ViewItemsFragment fragment, ArrayList<Item> items) {
        this.fragment = fragment;
        this.items = items;
    }

    @NonNull @Override public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_view_item, parent, false);
        return new ItemHolder(view);
    }

    @Override public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int index) {
        ItemHolder itemHolder = (ItemHolder) holder;
        itemHolder.titleText.setText(items.get(index).getTitle());
        SetImage setImage = new SetImage(itemHolder.imageView);
        JSONObject obj = new JSONObject();
        try{obj.put("id",items.get(index).getId());
            Call.post("download-item-image", obj, setImage, null);
        } catch (JSONException e) {e.printStackTrace();}
    }

    @Override public int getItemViewType(int index) {
        return items.get(index) == null ? -1 : 0;
    }

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
            }} catch (Exception e) {
                Log.d("response", e.toString());}
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        ImageView imageView;
        public ItemHolder(@NonNull View view) {
            super(view);
            titleText = view.findViewById(R.id.view_item_textview);
            imageView = view.findViewById(R.id.view_item_imageview);
        }
    }

}
