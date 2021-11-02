package com.example.foodhub.Program;


import android.content.Context;
import android.content.res.Configuration;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;

import java.util.List;

public class OrderInitializer implements Initializer<ProgramController.Order> {

//    @Override
//    public WorkManager create(Context context) {
//        Configuration configuration = Configuration.Builder().build();
//        WorkManager.initialize(context, configuration);
//        return WorkManager.getInstance(context);
//    }

    @NonNull
    @Override
    public ProgramController.Order create(@NonNull Context context) {

//        Configuration configuration = Configuration.Builder().build();
//        ProgramController.Order.initialize(context, configuration);
//        return WorkManager.getInstance(context);


        return null;
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return null;
    }

//    @Override
//    public List<Class<Initializer<?>>> dependencies() {
//        // No dependencies on other libraries.
//        return emptyList();
//    }

}