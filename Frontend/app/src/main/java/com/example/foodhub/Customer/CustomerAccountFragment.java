package com.example.foodhub.Customer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.foodhub.Login.InitialActivity;
import com.example.foodhub.R;


/**
 * Class responsible for Customer Account Fragment
 */
public class CustomerAccountFragment extends Fragment {

    /**
     * Method responsible for onCreate
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Method responsible for creating View
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_account, container, false);
        Button btn = view.findViewById(R.id.customer_logout_button);
        btn.setOnClickListener(this::goToSignIn);
        return view;
    }

    /**
     * Method responsible for loading view
     */
    public void goToSignIn(View view) {
        Intent I = new Intent(getContext(), InitialActivity.class);
        startActivity(I);
    }
}
