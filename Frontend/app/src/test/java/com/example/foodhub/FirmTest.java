package com.example.foodhub;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import com.example.foodhub.Common.Firm;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.json.JSONException;
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
public class FirmTest {

    @Mock
    private Firm Firm;


    @Before
    public void init() {}

    private static final String FAKE_STRING = "HELLO WORLD";
    private static final String FAKE_USERNAME = "Chipole";

    @Mock
    Context mockContext;

    @Mock
    Context FakeFirm;

    @Mock
    FirmTester AnotherFakeFirm;

//    @Test
//    public void TestFirmGetId() throws JSONException {
//        // Given a mocked Context injected into the object under test...
//        when(FakeFirm.getString(0))
//                .thenReturn("213232");
//        Firm ActualFirm = new Firm(213232, "Chipole", "CHIPOLE", "Ames", "USA", 8, 12, 34);
//
//        // ...when the string is returned from the object under test...
//        long result =  ActualFirm.getId();
//
//        // ...then the result should be the expected one.
//        assertThat(result, is(FakeFirm.getString(0)));
//    }

    @Test
    public void TestFirmGetName() throws JSONException {
        // Given a mocked Context injected into the object under test...
        when(FakeFirm.getString(0))
                .thenReturn("CHIPOLE");
        Firm ActualFirm = new Firm(213232, "Chipole", "CHIPOLE", "Ames", "USA", 8, 12, 34);

        // ...when the string is returned from the object under test...
        String result = ActualFirm.getName();

        // ...then the result should be the expected one.
        assertThat(result, is(FakeFirm.getString(0)));
    }

    @Test
    public void TestFirmGetLocation() throws JSONException {
        // Given a mocked Context injected into the object under test...
        when(FakeFirm.getString(0))
                .thenReturn("Ames");
        Firm ActualFirm = new Firm(213232, "Chipole", "CHIPOLE", "Ames", "USA", 8, 12, 34);

        // ...when the string is returned from the object under test...
        String result = ActualFirm.getLocation();

        // ...then the result should be the expected one.
        assertThat(result, is(FakeFirm.getString(0)));
    }

    @Test
    public void TestFirmGetCuisine() throws JSONException {
        // Given a mocked Context injected into the object under test...
        when(FakeFirm.getString(0))
                .thenReturn("USA");
        Firm ActualFirm = new Firm(213232, "Chipole", "CHIPOLE", "Ames", "USA", 8, 12, 34);

        // ...when the string is returned from the object under test...
        String result = ActualFirm.getCuisine();

        // ...then the result should be the expected one.
        assertThat(result, is(FakeFirm.getString(0)));
    }
//
//    @Test
//    public void TestFirmGetOpentime() throws JSONException {
//        // Given a mocked Context injected into the object under test...
//        when(FakeFirm.getString(0))
//                .thenReturn(FAKE_USERNAME);
//        Firm ActualFirm = new Firm(213232, "Chipole", "CHIPOLE", "Ames", "USA", 8, 12, 34);
//
//        // ...when the string is returned from the object under test...
//        int result = ActualFirm.getOpen_time();
//
//        // ...then the result should be the expected one.
//        assertThat(result, is(FakeFirm.getString(0)));
//    }
//
//    @Test
//    public void TestFirmGetClosetime() throws JSONException {
//        // Given a mocked Context injected into the object under test...
//        when(FakeFirm.getString(0))
//                .thenReturn(FAKE_USERNAME);
//        Firm ActualFirm = new Firm(213232, "Chipole", "CHIPOLE", "Ames", "USA", 8, 12, 34);
//
//        // ...when the string is returned from the object under test...
//        int result = ActualFirm.getClose_time();
//
//        // ...then the result should be the expected one.
//        assertThat(result, is(FakeFirm.getString(0)));
//    }

//    @Test
//    public void TestFirmGetEmployeeCount() throws JSONException {
//        // Given a mocked Context injected into the object under test...
//        when(FakeFirm.getString(0))
//                .thenReturn("34");
//        Firm ActualFirm = new Firm(213232, "Chipole", "CHIPOLE", "Ames", "USA", 8, 12, 34);
//
//        // ...when the string is returned from the object under test...
//        int result = ActualFirm.getEmployee_count();
//
//        // ...then the result should be the expected one.
//        assertThat(result, is(FakeFirm.getString(0)));
//    }

    @Test
    public void TestFirmGetUserName() throws JSONException {
        // Given a mocked Context injected into the object under test...
        when(FakeFirm.getString(0))
                .thenReturn(FAKE_USERNAME);
        Firm ActualFirm = new Firm(213232, "Chipole", "CHIPOLE", "Ames", "USA", 8, 12, 34);

        // ...when the string is returned from the object under test...
        String result = ActualFirm.getUsername();

        // ...then the result should be the expected one.
        assertThat(result, is(FakeFirm.getString(0)));
    }


    @Test
    public void getAllFirmsTest() {
        List<Firm> list = new ArrayList<Firm>();
        Firm c1 = new Firm(1322, "John", "1234", "john@gmail.com", "John Drive", 23, 23, 32);
        Firm c2 = new Firm(2332, "Alex", "abcd", "alex@yahoo.com", "John Avenue", 23, 23, 32);
        Firm c3 = new Firm(1232, "Steve", "efgh", "steve@gmail.com", "John Court", 23, 23, 32);

        list.add(c1);
        list.add(c2);
        list.add(c3);

        when(AnotherFakeFirm.findAll()).thenReturn(list);

        List<Firm> FirmList = AnotherFakeFirm.findAll();

        assertEquals(3, FirmList.size());
        verify(AnotherFakeFirm, times(1)).findAll();
    }

}
