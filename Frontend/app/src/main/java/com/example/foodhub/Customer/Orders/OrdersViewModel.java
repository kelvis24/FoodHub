package com.example.foodhub.Customer.Orders;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.foodhub.Program.ProgramController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;



public class OrdersViewModel extends ViewModel {
    private static final String TAG = "This is a tag" ;
    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<Object>> Orders;

    public OrdersViewModel() {
        mText = new MutableLiveData<>();
        Orders = new MutableLiveData<ArrayList<Object>>();
    }

    //@Override
    public LiveData<ArrayList<Object>> getOrders() {
        if (Orders == null) {
            Orders = new MutableLiveData<ArrayList<Object>>();
            loadOrders();
        }
        return Orders;
    }


    private void loadOrders() {
        // do async operation to fetch users
        Handler myHandler = new Handler();
        myHandler.postDelayed(() -> {

            ArrayList<Object> array = new ArrayList<>();
            array.add(ProgramController.Orders.get(0));

            long seed = System.nanoTime();
            Collections.shuffle(array, new Random(seed));

            Orders.setValue(array);
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

}