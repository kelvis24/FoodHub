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
 * Controls the R.layout.fragment_owner_account view
 * @author Arvid Gustafson
 */
public class OwnerAccountFragment extends Fragment {

    /**
     * Does bookkeeping with the onCreate method
     * @param savedInstanceState A bundle that is passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Binds the logout button to its proper method once the view is created
     * @param inflater A layout inflater
     * @param container The container of this view
     * @param savedInstanceState a bundle passed in
     * @return The created view
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_owner_account, container, false);
        Button btn = view.findViewById(R.id.owner_logout_button);
        btn.setOnClickListener(this::goToSignIn);
        return view;
    }

    /**
     * Navigates to the initial activity once the "logout" button is clicked
     * @param view The "logout" button
     */
    public void goToSignIn(View view) {
        Intent I = new Intent(getContext(), InitialActivity.class);
        startActivity(I);
    }

}
