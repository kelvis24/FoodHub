package com.example.foodhub;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.foodhub", appContext.getPackageName());
    }

//
//    @Mock
//    CustomerRepository customerRepository;
//
//    @Before
//    public void init() {}
//
//    @Test
//    public void getCustomerByIdTest() {
//        when(customerRepository.findById(1)).thenReturn(new Customer("Jon1", "Jon2", "Jon3", "Jon4"));
//
//        Customer customer = customerRepository.findById(1);
//
//        assertEquals("Jon1", customer.getUsername());
//        assertEquals("Jon2", customer.getPassword());
//        assertEquals("Jon3", customer.getName());
//        assertEquals("Jon4", customer.getLocation());
//    }
//
//    @Test
//    public void getAllAccountTest() {
//        List<Customer> list = new ArrayList<Customer>();
//        Customer c1 = new Customer("John", "1234", "john@gmail.com", "John Drive");
//        Customer c2 = new Customer("Alex", "abcd", "alex@yahoo.com", "John Avenue");
//        Customer c3 = new Customer("Steve", "efgh", "steve@gmail.com", "John Court");
//
//        list.add(c1);
//        list.add(c2);
//        list.add(c3);
//
//        when(customerRepository.findAll()).thenReturn(list);
//
//        List<Customer> customerList = customerRepository.findAll();
//
//        assertEquals(3, customerList.size());
//        verify(customerRepository, times(1)).findAll();
//
//    }

}