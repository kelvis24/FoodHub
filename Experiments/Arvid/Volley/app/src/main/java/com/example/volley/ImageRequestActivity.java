package com.example.volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.volley.app.AppController;
import com.example.volley.net_utils.Const;

public class ImageRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_request);
        Button btn = findViewById(R.id.request_image);
        btn.setOnClickListener(this::request_image);
    }

    public void request_image(View v) {
        ImageView imageView = findViewById(R.id.view_image);

        String TAG = "custom";

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        imageLoader.get(Const.URL_IMAGE,new ImageLoader.ImageListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    imageView.setImageBitmap(response.getBitmap());
                }
            }
        });
    }

}