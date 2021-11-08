package com.example.foodhub.Common;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodhub.R;

/**
 * Controls the R.layout.fragment_blank view
 * @author Arvid Gustafson
 */
public class BlankFragment extends Fragment {

    /**
     * Does bookkeeping with the onCreate method
     * @param savedInstance A bundle that is passed in
     */
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Does bookkeeping with the onCreateView method
     * @param inflater A layout inflater
     * @param container The view that contains this one
     * @param savedInstanceState a bundle passed in
     * @return The view that is created
     */
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
}