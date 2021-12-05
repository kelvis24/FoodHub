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

    @Mock
    com.example.foodhub.BrowseFirmsFragment fakeBrowseFirmsFragment;

    @Before
    public void setUp() {
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
}
