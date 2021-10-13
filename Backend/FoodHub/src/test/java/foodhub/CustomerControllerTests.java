package foodhub;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import foodhub.database.Customer;
import foodhub.database.CustomerRepository;
import foodhub.controllers.CustomerController;

@SpringBootTest
public class CustomerControllerTests {
	
	@InjectMocks
	CustomerController cc;
	
	@Mock
	CustomerRepository customerRepository;
	
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";

	private List<Customer> l = new ArrayList<Customer>();
	
	@Before
	public void init() {
	}
	
	@Test
	public void createCustomerTest1() {
		when(customerRepository.findAll()).thenReturn(l);
		when(customerRepository.findByUsername((String)any(String.class)))
		.thenAnswer(x-> {
			String username = x.getArgument(0);
			Iterator<Customer> itr = l.iterator();
			while (itr.hasNext()) {
				Customer c = itr.next();
				if (username.equals(c.getUsername()))
					return c;
			}
			return null;
		});
		when(customerRepository.save((Customer)any(Customer.class)))
		.thenAnswer(x -> {
			Customer c = x.getArgument(0);
			l.add(c);
			return null;
		});
		String response;
		Customer c1 = new Customer("Andrew","andrew@gmail.com","andrew123","andrew blv");
		Customer c2 = new Customer("John","john@gmail.com","john123","john st");
		Customer c3 = new Customer("Marry","marry@gmail.com","mary123","marry dr");
		response = cc.createCustomer(c1);
		assertEquals(success, response);
		response = cc.createCustomer(c2);
		assertEquals(success, response);
		response = cc.createCustomer(c3);
		assertEquals(success, response);
		List<Customer> list = cc.listCustomers();
		assertEquals(3, list.size());
		assertEquals("Andrew", list.get(0).getName());
		assertEquals("andrew@gmail.com", list.get(0).getUsername());
		assertEquals("andrew123", list.get(0).getPassword());
		assertEquals("andrew blv", list.get(0).getLocation());
	}
	
}
