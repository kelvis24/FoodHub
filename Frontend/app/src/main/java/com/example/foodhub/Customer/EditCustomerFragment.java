package com.example.foodhub.Customer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodhub.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditCustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditCustomerFragment extends Fragment {

    private String username;
    private String password;
    private ViewGroup container;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditCustomerFragment(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public EditCustomerFragment() {
        this.username = null;
        this.password = null;
    }
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditCustomerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditCustomerFragment newInstance(String param1, String param2) {
        EditCustomerFragment fragment = new EditCustomerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            username = getArguments().getString("username");
            password = getArguments().getString("password");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      //  this.container = container;

        View view = inflater.inflate(R.layout.fragment_edit_customer, container, false);
        this.container = container;
    //    refresh();
        return view;

        // Inflate the layout for this fragment
    }
}