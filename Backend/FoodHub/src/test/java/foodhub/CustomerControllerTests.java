package foodhub;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
	
	@BeforeEach
	public void init() {
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
	}
	
	@Test
	public void createCustomerTest1() {
		String response;
		Customer c1 = new Customer("Andrew","andrew@gmail.com","andrew123","andrew blv");
		Customer c2 = new Customer("John","john@gmail.com","john123","john st");
		Customer c3 = new Customer("Marry","marry@gmail.com","mary123","marry dr");
		Customer c4 = new Customer("John II","john@gmail.com","mary123","marry dr");
		response = cc.createCustomer(c1);
		assertEquals(success, response);
		response = cc.createCustomer(c2);
		assertEquals(success, response);
		response = cc.createCustomer(c3);
		assertEquals(success, response);
		response = cc.createCustomer(c4);
		assertEquals(failure, response);
		List<Customer> list = cc.listCustomers();
		assertEquals(3, list.size());
		assertEquals("Andrew", list.get(0).getName());
		assertEquals("andrew@gmail.com", list.get(0).getUsername());
		assertEquals("andrew123", list.get(0).getPassword());
		assertEquals("andrew blv", list.get(0).getLocation());
		assertEquals("John", list.get(1).getName());
		assertEquals("john@gmail.com", list.get(1).getUsername());
		assertEquals("john123", list.get(1).getPassword());
		assertEquals("john st", list.get(1).getLocation());
		assertEquals("Marry", list.get(2).getName());
		assertEquals("marry@gmail.com", list.get(2).getUsername());
		assertEquals("mary123", list.get(2).getPassword());
		assertEquals("marry dr", list.get(2).getLocation());
		verify(customerRepository, times(1)).save(c1);
		verify(customerRepository, times(1)).save(c2);
		verify(customerRepository, times(1)).save(c3);
		verify(customerRepository, times(3)).save((Customer)any(Customer.class));
		verify(customerRepository, times(1)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(2)).findByUsername("john@gmail.com");
		verify(customerRepository, times(1)).findByUsername("marry@gmail.com");
		verify(customerRepository, times(4)).findByUsername((String)any(String.class));
		verify(customerRepository, times(1)).findAll();
	}
	
}
