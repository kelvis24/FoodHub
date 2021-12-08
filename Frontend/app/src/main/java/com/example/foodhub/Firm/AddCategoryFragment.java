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

import com.example.foodhub.Common.Category;
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
 * Controls the R.layout.fragment_add_category view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class AddCategoryFragment extends Fragment {

    private final int IMAGE_REQUEST_ID = 1;

    private final long firmId;
    private final String username;
    private final String password;
    private final Category category;

    private String imageString;

    private View page;

    /**
     * Constructs a new AddCategoryFragment given enumerated information
     * @param firmId The id of the current user
     * @param username The username of the current user
     * @param password The password of the current user
     */
    public AddCategoryFragment(long firmId, String username, String password, Category category) {
        super();
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.category = category;
        this.imageString = null;
    }

    /**
     * Constructs a new AddCategoryFragment given enumerated information
     * @param firmId The id of the current user
     * @param username The username of the current user
     * @param password The password of the current user
     */
    public AddCategoryFragment(long firmId, String username, String password) {
        super();
        this.firmId = firmId;
        this.username = username;
        this.password = password;
        this.category = null;
        this.imageString = null;
    }

    /**
     * A default constructor
     */
    public AddCategoryFragment() {
        this.firmId = -1;
        this.username = null;
        this.password = null;
        this.category = null;
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
     * Binds "add category" button to appropriate function when the view is created
     * @param inflater A layout inflater
     * @param container The view that contains this one
     * @param savedInstanceState a bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = inflater.inflate(R.layout.fragment_add_category, container, false);
        Button btn = page.findViewById(R.id.add_category_button2);
        btn.setOnClickListener(this::addCategoryRequest);
        ImageView image = page.findViewById(R.id.add_category_imageview);
        image.setOnClickListener(this::selectImage);
        if (category != null) {
            btn.setText(R.string.Edit);
            ((EditText)page.findViewById(R.id.add_category_title)).setText(category.getTitle());
            ((EditText)page.findViewById(R.id.add_category_description)).setText(category.getDescription());
        }
        return page;
    }

    /**
     * Sends a request to add a category upon clicking the "add category" button
     * @param v the "add category" button
     */
    public void addCategoryRequest(View v) {
        String d_title = ((EditText)page.findViewById(R.id.add_category_title)).getText().toString();
        String d_description = ((EditText)page.findViewById(R.id.add_category_description)).getText().toString();
        ArrayList<String> list = new ArrayList<>();
        list.add(d_title);
        list.add(d_description);
        if (AreInvalidFields(getActivity(), list)) return;
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("title", d_title);
        dataMap.put("description", d_description);
        JSONObject dataObj = new JSONObject(dataMap);
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        JSONObject obj = new JSONObject(map);
        try{obj.put("data", dataObj);
            if (category != null) obj.put("categoryId", category.getId());
        } catch (JSONException e) {e.printStackTrace();}
        if (category == null)
            Call.post("firms-create-category", obj, this::categoryResponse, null);
        else
            Call.post("firms-edit-category", obj, this::categoryResponse, null);
    }

    /**
     * Goes back to the R.layout.fragment_manage_categories view upon successfully adding an category
     * @param response The response from the server as a JSONObject
     */
    public void categoryResponse(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            if (imageString == null) {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.firm_fragment_main, new ManageCategoriesFragment(firmId, username, password));
                ft.commit();
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("username",username);
                map.put("password",password);
                map.put("data",imageString);
                JSONObject obj = new JSONObject(map);
                if (category == null)
                    obj.put("id",response.getLong("id"));
                else
                    obj.put("id",category.getId());
                Call.post("upload-category-image", obj, this::uploadImageResponse, null);
            }
        } else {
            Toast.makeText(getActivity().getApplicationContext(),
                    (String)response.get("error"),Toast.LENGTH_SHORT).show();
        }} catch (Exception e) {Log.d("response", e.toString());}
    }

    public void uploadImageResponse(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.firm_fragment_main, new ManageCategoriesFragment(firmId, username, password));
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
                ImageView imageView = page.findViewById(R.id.add_category_imageview);
                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } catch (FileNotFoundException e) {e.printStackTrace();}
        }
    }

}
