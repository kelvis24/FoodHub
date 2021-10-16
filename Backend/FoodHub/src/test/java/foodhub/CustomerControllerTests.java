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
import foodhub.ioObjects.LoginInput;

@SpringBootTest
public class CustomerControllerTests {
	
	@InjectMocks
	CustomerController cc;
	
	@Mock
	CustomerRepository customerRepository;
	
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";

	private List<Customer> l;
	
	@BeforeEach
	public void init() {
		l = new ArrayList<Customer>();
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
	public void createCustomerTest0() {
		List<Customer> list = cc.listCustomers();
		assertEquals(0, list.size());
		verify(customerRepository, times(0)).save((Customer)any(Customer.class));
		verify(customerRepository, times(0)).findByUsername((String)any(String.class));
		verify(customerRepository, times(1)).findAll();
	}
	
	@Test
	public void createCustomerTest1() {
		String response;
		Customer c1 = new Customer("Andrew","andrew@gmail.com","andrew123","andrew blv");
		response = cc.createCustomer(c1);
		assertEquals(success, response);
		List<Customer> list = cc.listCustomers();
		assertEquals(1, list.size());
		assertEquals("Andrew", list.get(0).getName());
		assertEquals("andrew@gmail.com", list.get(0).getUsername());
		assertEquals("andrew123", list.get(0).getPassword());
		assertEquals("andrew blv", list.get(0).getLocation());
		verify(customerRepository, times(1)).save(c1);
		verify(customerRepository, times(1)).save((Customer)any(Customer.class));
		verify(customerRepository, times(1)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(1)).findByUsername((String)any(String.class));
		verify(customerRepository, times(1)).findAll();
	}
	
	@Test
	public void createCustomerTest2() {
		String response;
		Customer c1 = new Customer("Andrew","andrew@gmail.com","andrew123","andrew blv");
		response = cc.createCustomer(c1);
		assertEquals(success, response);
		response = cc.createCustomer(c1);
		assertEquals(failure, response);
		List<Customer> list = cc.listCustomers();
		assertEquals(1, list.size());
		assertEquals("Andrew", list.get(0).getName());
		assertEquals("andrew@gmail.com", list.get(0).getUsername());
		assertEquals("andrew123", list.get(0).getPassword());
		assertEquals("andrew blv", list.get(0).getLocation());
		verify(customerRepository, times(1)).save(c1);
		verify(customerRepository, times(1)).save((Customer)any(Customer.class));
		verify(customerRepository, times(2)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(2)).findByUsername((String)any(String.class));
		verify(customerRepository, times(1)).findAll();
	}
	
	@Test
	public void createCustomerTest3() {
		String response;
		Customer c1 = new Customer("Andrew","andrew@gmail.com","andrew123","andrew blv");
		Customer c2 = new Customer("John","john@gmail.com","john123","john st");
		Customer c3 = new Customer("Marry","marry@gmail.com","mary123","marry dr");
		Customer c4 = new Customer("John II","john@gmail.com","john456","john court");
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
	
	@Test
	public void loginCustomerTest0() {
		String response;
		Customer c1 = new Customer("Andrew","andrew@gmail.com","andrew123","andrew blv");
		LoginInput b1 = new LoginInput(c1.getUsername(), c1.getPassword());
		response = cc.loginCustomer(b1);
		assertEquals(failure, response);
		verify(customerRepository, times(0)).save((Customer)any(Customer.class));
		verify(customerRepository, times(1)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(1)).findByUsername((String)any(String.class));
	}
	
	@Test
	public void loginCustomerTest1() {
		String response;
		Customer c1 = new Customer("Andrew","andrew@gmail.com","andrew123","andrew blv");
		LoginInput b1 = new LoginInput(c1.getUsername(), c1.getPassword());
		response = cc.createCustomer(c1);
		assertEquals(success, response);
		response = cc.loginCustomer(b1);
		assertEquals(success, response);
		verify(customerRepository, times(2)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(2)).findByUsername((String)any(String.class));
	}
	
	@Test
	public void loginCustomerTest2() {
		String response;
		Customer c1 = new Customer("Andrew","andrew@gmail.com","andrew123","andrew blv");
		LoginInput b1 = new LoginInput(c1.getUsername(), c1.getPassword());
		LoginInput b2 = new LoginInput("Andyeet",        c1.getPassword());
		LoginInput b3 = new LoginInput(c1.getUsername(), "Yeeticus The Yeety");
		response = cc.createCustomer(c1);
		assertEquals(success, response);
		response = cc.createCustomer(c1);
		assertEquals(failure, response);
		response = cc.loginCustomer(b1);
		assertEquals(success, response);
		response = cc.loginCustomer(b2);
		assertEquals(failure, response);
		response = cc.loginCustomer(b3);
		assertEquals(failure, response);
		List<Customer> list = cc.listCustomers();
		assertEquals(1, list.size());
		assertEquals("Andrew", list.get(0).getName());
		assertEquals("andrew@gmail.com", list.get(0).getUsername());
		assertEquals("andrew123", list.get(0).getPassword());
		assertEquals("andrew blv", list.get(0).getLocation());
		verify(customerRepository, times(1)).save(c1);
		verify(customerRepository, times(1)).save((Customer)any(Customer.class));
		verify(customerRepository, times(4)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(1)).findByUsername("Andyeet");
		verify(customerRepository, times(5)).findByUsername((String)any(String.class));
		verify(customerRepository, times(1)).findAll();
	}
	
	@Test
	public void loginCustomerTest3() {
		String response;
		Customer c1 = new Customer("Andrew","andrew@gmail.com","andrew123","andrew blv");
		Customer c2 = new Customer("John","john@gmail.com","john123","john st");
		Customer c3 = new Customer("Marry","marry@gmail.com","mary123","marry dr");
		Customer c4 = new Customer("John II","john@gmail.com","john456","john court");
		LoginInput b1 = new LoginInput(c1.getUsername(), c1.getPassword());
		LoginInput b2 = new LoginInput(c2.getUsername(), c2.getPassword());
		LoginInput b3 = new LoginInput(c3.getUsername(), c3.getPassword());
		LoginInput b4 = new LoginInput(c4.getUsername(), c4.getPassword());
		LoginInput f1 = new LoginInput("Yeeticus", "The Yeety");
		LoginInput f2 = new LoginInput("Yeetus", "Skeetus");
		LoginInput f3 = new LoginInput("Yeetus", "Maximus");
		response = cc.createCustomer(c1);
		assertEquals(success, response);
		response = cc.createCustomer(c2);
		assertEquals(success, response);
		response = cc.createCustomer(c3);
		assertEquals(success, response);
		response = cc.createCustomer(c4);
		assertEquals(failure, response);
		response = cc.loginCustomer(b1);
		assertEquals(success, response);
		response = cc.loginCustomer(b2);
		assertEquals(success, response);
		response = cc.loginCustomer(b3);
		assertEquals(success, response);
		response = cc.loginCustomer(b4);
		assertEquals(failure, response);
		response = cc.loginCustomer(f1);
		assertEquals(failure, response);
		response = cc.loginCustomer(f2);
		assertEquals(failure, response);
		response = cc.loginCustomer(f3);
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
		verify(customerRepository, times(2)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(4)).findByUsername("john@gmail.com");
		verify(customerRepository, times(2)).findByUsername("marry@gmail.com");
		verify(customerRepository, times(1)).findByUsername("Yeeticus");
		verify(customerRepository, times(2)).findByUsername("Yeetus");
		verify(customerRepository, times(11)).findByUsername((String)any(String.class));
		verify(customerRepository, times(1)).findAll();
	}
	
}