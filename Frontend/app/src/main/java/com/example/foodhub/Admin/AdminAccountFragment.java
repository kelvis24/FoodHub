package com.example.foodhub.Admin;

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
 * A controller for the R.layout.fragment_admin_account view
 * @author Arvid Gustafson
 * @see Fragment
 */
public class AdminAccountFragment extends Fragment {

    /**
     * Does bookkeeping with the onCreate method
     * @param savedInstanceState a bundle passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Ties the "logout" button to its function upon creation of the view
     * @param inflater a layout inflater
     * @param container the larger container of the view (the layout view that contains the fragment)
     * @param savedInstanceState a bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_admin_account, container, false);
        Button btn = view.findViewById(R.id.admin_logout_button);
        btn.setOnClickListener(this::goToSignIn);
        return view;
    }

    /**
     * Goes to the initial activity upon clicking the logout button
     * @param v The "logout" button
     */
    public void goToSignIn(View view) {
        Intent I = new Intent(getContext(), InitialActivity.class);
        startActivity(I);
    }

}
