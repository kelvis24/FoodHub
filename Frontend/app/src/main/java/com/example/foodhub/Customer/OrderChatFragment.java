package com.example.foodhub.Customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodhub.R;

public class OrderChatFragment extends Fragment {
    public OrderChatFragment() {
        // Required empty public constructor
    }

    public OrderChatFragment(String firmName, String username, String password) {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_chat, container, false);
    }
}
