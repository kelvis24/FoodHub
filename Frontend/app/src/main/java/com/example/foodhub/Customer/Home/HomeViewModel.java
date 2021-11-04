package com.example.foodhub.Customer.Home;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.Common.SmallCompany;
import com.example.foodhub.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class HomeViewModel extends ViewModel {
    private static final String TAG = "This is a tag" ;
    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<Object>> Companies;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        Companies = new MutableLiveData<ArrayList<Object>>();
    }

    //@Override
    public LiveData<ArrayList<Object>> getCompanies() {
        if (Companies == null) {
            Companies = new MutableLiveData<ArrayList<Object>>();
            loadCompanies();
        }
        return Companies;
    }


    private void loadCompanies() {
        // do async operation to fetch users
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {

            ArrayList<Object> array = new ArrayList<>();
            array.add(getVerticalData().get(0));
            array.add(getHorizontalData().get(0));

            long seed = System.nanoTime();
            Collections.shuffle(array, new Random(seed));

            Companies.setValue(array);
        }, 5000);

    }



    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public static ArrayList<Firm> getVerticalData() {
        ArrayList<Firm> company = new ArrayList<>();
        // company.add( new Firm(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        // company.add( new Firm(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        // company.add( new Firm(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        return company;
    }

    public static ArrayList<SmallCompany> getHorizontalData() {
        ArrayList<SmallCompany> company = new ArrayList<>();
        company.add( new SmallCompany(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        company.add( new SmallCompany(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        company.add( new SmallCompany(R.drawable.litramen, "this is a description", "userame", "password" ,"name" ,"Ames","African", 10, 5, 13 ));
        return company;
    }
}
