package com.example.foodhub.Firm;

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
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodhub.Login.InitialActivity;
import com.example.foodhub.R;
import com.example.foodhub.server.Call;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * A controller for the R.layout.fragment_firm_account view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class FirmAccountFragment extends Fragment {

    private String username;
    private String password;
    private long firmId;

    private String imageString;

    private View page;

    private final int IMAGE_REQUEST_ID = 1;

    /**
     * Does bookkeeping with the onCreate method
     * @param savedInstanceState a bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
            password = getArguments().getString("password");
            firmId = getArguments().getLong("firmId");
        }
        imageString = null;
    }

    /**
     * Ties the "logout" button to its function upon creation of the view
     * @param inflater a layout inflater
     * @param container the larger container of the view (the layout view that contains the fragment)
     * @param savedInstanceState a bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_firm_account, container, false);
        this.page = view;
        ImageView imageView = view.findViewById(R.id.firm_upload_imageview);
        imageView.setOnClickListener(this::selectImage);
        Button btn = view.findViewById(R.id.firm_upload_button);
        btn.setOnClickListener(this::uploadImage);
        btn = view.findViewById(R.id.firm_logout_button);
        btn.setOnClickListener(this::goToSignIn);
        JSONObject obj = new JSONObject();
        try{obj.put("id",firmId);
            Call.post("download-firm-image", obj, this::setImage, null);
        } catch (JSONException e) {e.printStackTrace();}
        return view;
    }

    public void setImage(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            String data = (String)response.get("data");
            byte[] bytes = Base64.decode(data, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            ImageView imageView = ((ImageView)page.findViewById(R.id.firm_upload_imageview));
            imageView.setImageBitmap(bitmap);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
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
            try{
                InputStream is = getActivity().getContentResolver().openInputStream(data.getData());
                Bitmap image = BitmapFactory.decodeStream(is);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 50, outputStream);
                imageString = Base64.encodeToString(outputStream.toByteArray(), Base64.DEFAULT);
                byte[] bytes = Base64.decode(imageString, Base64.DEFAULT);
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                ImageView imageView = page.findViewById(R.id.firm_upload_imageview);
                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } catch (FileNotFoundException e) {e.printStackTrace();}
        }
    }

    public void uploadImage(View view) {
        if (imageString == null) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "No Image Selected",Toast.LENGTH_SHORT).show();
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        map.put("data",imageString);
        JSONObject obj = new JSONObject(map);
        Call.post("upload-firm-image", obj, this::uploadImageResponse, null);
    }

    public void uploadImageResponse(JSONObject response) {
        try{if (response.get("message").equals("success")) {
            Toast.makeText(getActivity().getApplicationContext(),
                    "Success",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getActivity().getApplicationContext(),
                    (String)response.get("error"),Toast.LENGTH_SHORT).show();
        }} catch (Exception e) {Log.d("response", e.toString());}
    }

    /**
     * Goes to the initial activity upon clicking the logout button
     * @param view The "logout" button
     */
    public void goToSignIn(View view) {
        Intent I = new Intent(getContext(), InitialActivity.class);
        startActivity(I);
    }

}
