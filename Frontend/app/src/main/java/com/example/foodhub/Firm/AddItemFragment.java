package com.example.foodhub.Firm;

import static com.example.foodhub.Common.FoodhubUtils.AreInvalidFields;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodhub.Common.Item;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Controls the R.layout.fragment_add_item view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class AddItemFragment extends Fragment {

    private final int IMAGE_REQUEST_ID = 1;

    private final long firmId;
    private final long categoryId;
    private final String username;
    private final String password;
    private final Item item;

    private String imageString;

    View page;

    /**
     * Constructs a new AddItemFragment given enumerated information
     * @param firmId The id of the firm to which the item belongs
     * @param categoryId The id of the category to which the item belongs
     * @param username The username of the current user
     * @param password The password of the current user
     */
    public AddItemFragment(long firmId, long categoryId, String username, String password, Item item) {
        super();
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.item = item;
    }

    /**
     * Constructs a new AddItemFragment given enumerated information
     * @param firmId The id of the firm to which the item belongs
     * @param categoryId The id of the category to which the item belongs
     * @param username The username of the current user
     * @param password The password of the current user
     */
    public AddItemFragment(long firmId, long categoryId, String username, String password) {
        super();
        this.firmId = firmId;
        this.categoryId = categoryId;
        this.username = username;
        this.password = password;
        this.item = null;
        this.imageString = null;
    }

    /**
     * A default constructor
     */
    public AddItemFragment() {
        this.firmId = -1;
        this.categoryId = -1;
        this.username = null;
        this.password = null;
        this.item = null;
        this.imageString = null;
    }

    /**
     * Does bookkeeping for the onCreate method
     * @param savedInstanceState a bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Binds the "add item" button to appropriate function when the view is created
     * @param inflater A layout inflater
     * @param container The view that contains this one
     * @param savedInstanceState a bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = inflater.inflate(R.layout.fragment_add_item, container, false);
        Button btn = page.findViewById(R.id.add_item_button2);
        btn.setOnClickListener(this::addItemRequest);
        ImageView image = page.findViewById(R.id.add_item_imageview);
        image.setOnClickListener(this::selectImage);
        if (item != null) {
            String str;
            btn.setText(R.string.Edit);
            ((EditText)page.findViewById(R.id.add_item_title)).setText(item.getTitle());
            ((EditText)page.findViewById(R.id.add_item_description)).setText(item.getDescription());
            str = "" + item.getPrice();
            ((EditText)page.findViewById(R.id.add_item_price)).setText(str);
        }
        return page;
    }

    /**
     * Sends a request to add an item upon clicking the "add item" button
     * @param view the "add item" button
     */
    public void addItemRequest(View view) {
        String d_title = ((EditText)page.findViewById(R.id.add_item_title)).getText().toString();
        String d_description = ((EditText)page.findViewById(R.id.add_item_description)).getText().toString();
        String s_price = ((EditText)page.findViewById(R.id.add_item_price)).getText().toString();
        ArrayList<String> list = new ArrayList<>();
        list.add(d_title);
        list.add(d_description);
        list.add(s_price);
        if (AreInvalidFields(getActivity(), list)) return;
        double d_price = Double.parseDouble(s_price);
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("title", d_title);
        dataMap.put("description", d_description);
        JSONObject dataObj = new JSONObject(dataMap);
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        try{dataObj.put("price", d_price);
            if (item == null)
                obj.put("categoryId", categoryId);
            else
                obj.put("itemId", item.getId());
            obj.put("data", dataObj);
        } catch (JSONException e) {e.printStackTrace();}
        if (item == null)
            Call.post("firms-create-item", obj, this::itemResponse, null);
        else
            Call.post("firms-edit-item", obj, this::itemResponse, null);
    }

    /**
     * Goes back to the R.layout.fragment_manage_items view upon successfully adding an item
     * @param response The response from the server as a JSONObject
     */
    public void itemResponse(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            if (imageString == null) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.firm_fragment_main, new ManageItemsFragment(firmId, categoryId, username, password));
                ft.commit();
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("username",username);
                map.put("password",password);
                map.put("data",imageString);
                JSONObject obj = new JSONObject(map);
                if (item == null)
                    obj.put("id",response.getLong("id"));
                else
                    obj.put("id",item.getId());
                Call.post("upload-item-image", obj, this::uploadImageResponse, null);
            }
        } else {
            Toast.makeText(getActivity().getApplicationContext(),
                    (String)response.get("error"),Toast.LENGTH_SHORT).show();
        }} catch (Exception e) {Log.d("response", e.toString());}
    }

    public void uploadImageResponse(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.firm_fragment_main, new ManageItemsFragment(firmId, categoryId, username, password));
            ft.commit();
        } else {
            Toast.makeText(getActivity().getApplicationContext(),
                    (String)response.get("error"),Toast.LENGTH_SHORT).show();
        }} catch (Exception e) {Log.d("response", e.toString());}
    }

    public void selectImage(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Pick image"), IMAGE_REQUEST_ID);
    }

    @Override public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMAGE_REQUEST_ID && resultCode == -1) {
            try{InputStream is = getActivity().getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                imageString = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                byte[] bytes = Base64.decode(imageString, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                ImageView imageView = page.findViewById(R.id.add_item_imageview);
                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } catch (FileNotFoundException e) {e.printStackTrace();}
        }
    }

}
