package com.example.foodhub;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import com.example.foodhub.Common.Firm;
import com.example.foodhub.Customer.BrowseFirmsAdapter;
import com.example.foodhub.Customer.BrowseFirmsFragment;
import com.example.foodhub.server.Call;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.platform.app.InstrumentationRegistry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BrowseFirmsFragmentTest {

    ArrayList<Firm> firms;
    String firstFirmName;
    ArrayList<Firm> fakeFirms;

    @Mock
    FirmTester AnotherFakeFirm;

    @Mock
    com.example.foodhub.BrowseFirmsFragment fakeBrowseFirmsFragment;

    @Before
    public void setUp() {
       // Call.get("general-get-firms", this::listFirm, null);
    }


    @Test
    public void TestBrowseFirmsFragmentUsername()  {
        BrowseFirmsFragment RealBrowseFirmsFragment = new BrowseFirmsFragment("e", "e");
        String username = RealBrowseFirmsFragment.returnUsername();

        when(fakeBrowseFirmsFragment.returnUsername())
                .thenReturn("e");
        assertThat(username, is(  fakeBrowseFirmsFragment.returnUsername() ));
    }


    @Test
    public void TestBrowseFirmsFragmentPassword()  {
        BrowseFirmsFragment RealBrowseFirmsFragment = new BrowseFirmsFragment("e", "e");
        String password = RealBrowseFirmsFragment.returnPassword();

        when(fakeBrowseFirmsFragment.returnPassword())
                .thenReturn("e");
        assertThat(password, is(  fakeBrowseFirmsFragment.returnPassword() ));
    }

//    @Test
//    public void TestRefreshMethod() throws JSONException {
//        when(FakeCall.get("general-get-firms", this::listFirms, null))
//                .thenReturn(fakeFirms);
//        assertThat(firstFirmName, is(FakeCall.get("general-get-firms", this::listFirms, null).get(0).getName()   ));
//    }
//
//    private void listFirms(JSONObject arr) {
//        firms = new ArrayList<>();
//        for (int i = 0; i < arr.length(); i++) {
//            try{firms.add(new Firm(arr.getJSONObject(i)));
//            } catch (JSONException e) {e.printStackTrace();}
//        }
//
//        Firm ActualFirm = new Firm(firms.get(0));
//        firstFirmName = ActualFirm.getName();
//
//        fakeFirms = new ArrayList<>();
//        fakeFirms.add(new Firm(0001, "arvid", "Taco House", "Ames", "USA", 8, 12, 34));
//        fakeFirms.add(new Firm(00002, "ekimara", "Food Palace", "Ames", "USA", 8, 12, 34));
//        fakeFirms.add(new Firm(213232, "Chipole", "CHIPOLE", "Ames", "USA", 8, 12, 34));
//
//    }

//    public void listFirm(JSONArray arr) {
//        firms = new ArrayList<>();
//        for (int i = 0; i < arr.length(); i++) {
//            try{firms.add(new Firm(arr.getJSONObject(i)));
//            } catch (JSONException e) {e.printStackTrace();}
//        }
//    }
}
