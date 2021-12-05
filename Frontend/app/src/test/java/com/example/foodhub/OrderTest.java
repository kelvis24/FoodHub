package com.example.foodhub;
import static org.mockito.Mockito.when;
import com.example.foodhub.Common.Order;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class OrderTest {
    @Mock
    private org.json.JSONObject jsonObject;

    public Order order;

    @Before
    public void setUp() throws JSONException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetCustomer() throws JSONException {
        when(jsonObject.get("customer")).thenReturn("elvis");
        order = new Order( jsonObject);
        assertEquals(order.getCustomer(),"elvis");
    }

    @Test
    public void testGetLocation() throws JSONException {
        when(jsonObject.get("location")).thenReturn("Ames");
        order = new Order( jsonObject);
        assertEquals(order.getLocation(),"Ames");
    }

    @Test
    public void testGetFirm() throws JSONException {
        when(jsonObject.get("firm")).thenReturn("Taco House");
        order = new Order( jsonObject);
        assertEquals(order.getFirm(),"Taco House");
    }
}
