package com.example.foodhub.Customer.Home;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodhub.Class.Company;
import com.example.foodhub.Class.SmallCompany;
import com.example.foodhub.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HomeViewModel extends ViewModel {
    private static final String TAG = "This is a tag" ;
    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<Object>> Companies;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        Companies = new MutableLiveData<ArrayList<Object>>();

      //  mText.setValue("This is home fragment");
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

//    private ArrayList<Object> getObject() {
//        MutableLiveData<ArrayList<Object>>arrayList = new MutableLiveData<ArrayList<Object>>();
//        arrayList.add(getVerticalData().get(0));
//        arrayList.add(getHorizontalData().get(0));
//        return arrayList;
//    }

    public static ArrayList<Company> getVerticalData() {
        ArrayList<Company> company = new ArrayList<>();
        company.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "dfgfd", "messages appear here"));
        company.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "dfrtd", "messages appear here"));
        company.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "dfdgf", "messages appear here"));
        company.add( new Company(R.drawable.ic_launcher_background, R.drawable.litramen, "dfgfd", "messages appear here"));
        return company;
    }

    public static ArrayList<SmallCompany> getHorizontalData() {
        ArrayList<SmallCompany> company = new ArrayList<>();
        company.add( new SmallCompany(R.drawable.ic_launcher_background, R.drawable.litramen, "dfdew", "messages appear here"));
        company.add( new SmallCompany(R.drawable.ic_launcher_background, R.drawable.litramen, "dfhd", "messages appear here"));
        company.add( new SmallCompany(R.drawable.ic_launcher_background, R.drawable.litramen, "dfkjd", "messages appear here"));
        company.add( new SmallCompany(R.drawable.ic_launcher_background, R.drawable.litramen, "dkjfd", "messages appear here"));
        return company;
    }
}
