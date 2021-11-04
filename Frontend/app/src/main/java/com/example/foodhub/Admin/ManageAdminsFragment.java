package com.example.foodhub.Admin;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodhub.R;

public class ManageAdminsFragment extends Fragment {

    private String username;
    private String password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            username = bundle.getString("username");
            password = bundle.getString("password");
        }
        System.out.println(username);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_admins, container, false);
        /*
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            username = bundle.getString("username");
            password = bundle.getString("password");
        }

        */
        System.out.println(username);
        return view;
    }

}