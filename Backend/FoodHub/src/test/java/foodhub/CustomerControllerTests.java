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
import foodhub.controllers.DebugController;
import foodhub.controllers.GeneralController;
import foodhub.ioObjects.Authentication;
import foodhub.ioObjects.CustomerInfo;
import foodhub.ioObjects.Message;

@SpringBootTest
public class CustomerControllerTests {
	
	@InjectMocks
	CustomerController cc;
	
	@InjectMocks
	DebugController dc;
	
	@InjectMocks
	GeneralController gc;
	
	@Mock
	CustomerRepository customerRepository;

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
		List<Customer> list = dc.listCustomers();
		assertEquals(0, list.size());
		verify(customerRepository, times(0)).save((Customer)any(Customer.class));
		verify(customerRepository, times(0)).findByUsername((String)any(String.class));
		verify(customerRepository, times(1)).findAll();
	}
	
	@Test
	public void createCustomerTest1() {
		Message response;
		CustomerInfo c1 = new CustomerInfo("andrew@gmail.com","andrew123","Andrew","andrew blv");
		response = gc.createCustomer(c1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		List<Customer> list = dc.listCustomers();
		assertEquals(1, list.size());
		assertEquals("Andrew", list.get(0).getName());
		assertEquals("andrew@gmail.com", list.get(0).getUsername());
		assertEquals("andrew123", list.get(0).getPassword());
		assertEquals("andrew blv", list.get(0).getLocation());
		verify(customerRepository, times(1)).save((Customer)any(Customer.class));
		verify(customerRepository, times(1)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(1)).findByUsername((String)any(String.class));
		verify(customerRepository, times(1)).findAll();
	}
	
	@Test
	public void createCustomerTest2() {
		Message response;
		CustomerInfo c1 = new CustomerInfo("andrew@gmail.com","andrew123","Andrew","andrew blv");
		response = gc.createCustomer(c1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = gc.createCustomer(c1);
		assertEquals("failure", response.getMessage());
		assertEquals("username taken", response.getError());
		List<Customer> list = dc.listCustomers();
		assertEquals(1, list.size());
		assertEquals("Andrew", list.get(0).getName());
		assertEquals("andrew@gmail.com", list.get(0).getUsername());
		assertEquals("andrew123", list.get(0).getPassword());
		assertEquals("andrew blv", list.get(0).getLocation());
		verify(customerRepository, times(1)).save((Customer)any(Customer.class));
		verify(customerRepository, times(2)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(2)).findByUsername((String)any(String.class));
		verify(customerRepository, times(1)).findAll();
	}
	
	@Test
	public void createCustomerTest3() {
		Message response;
		CustomerInfo c1 = new CustomerInfo("andrew@gmail.com","andrew123","Andrew","andrew blv");
		CustomerInfo c2 = new CustomerInfo("john@gmail.com","john123","John","john st");
		CustomerInfo c3 = new CustomerInfo("marry@gmail.com","mary123","Marry","marry dr");
		CustomerInfo c4 = new CustomerInfo("john@gmail.com","john456","John II","john court");
		response = gc.createCustomer(c1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = gc.createCustomer(c2);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = gc.createCustomer(c3);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = gc.createCustomer(c4);
		assertEquals("failure", response.getMessage());
		assertEquals("username taken", response.getError());
		List<Customer> list = dc.listCustomers();
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
		verify(customerRepository, times(3)).save((Customer)any(Customer.class));
		verify(customerRepository, times(1)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(2)).findByUsername("john@gmail.com");
		verify(customerRepository, times(1)).findByUsername("marry@gmail.com");
		verify(customerRepository, times(4)).findByUsername((String)any(String.class));
		verify(customerRepository, times(1)).findAll();
	}
	
	@Test
	public void authenticateCustomerTest0() {
		Message response;
		CustomerInfo c1 = new CustomerInfo("andrew@gmail.com","andrew123","Andrew","andrew blv");
		Authentication b1 = new Authentication(c1.getUsername(), c1.getPassword());
		response = cc.authenticateCustomer(b1);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong username", response.getError());
		verify(customerRepository, times(0)).save((Customer)any(Customer.class));
		verify(customerRepository, times(1)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(1)).findByUsername((String)any(String.class));
	}
	
	@Test
	public void authenticateCustomerTest1() {
		Message response;
		CustomerInfo c1 = new CustomerInfo("andrew@gmail.com","andrew123","Andrew","andrew blv");
		Authentication b1 = new Authentication(c1.getUsername(), c1.getPassword());
		response = gc.createCustomer(c1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = cc.authenticateCustomer(b1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		verify(customerRepository, times(2)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(2)).findByUsername((String)any(String.class));
	}
	
	@Test
	public void authenticateCustomerTest2() {
		Message response;
		CustomerInfo c1 = new CustomerInfo("andrew@gmail.com","andrew123","Andrew","andrew blv");
		Authentication b1 = new Authentication(c1.getUsername(), c1.getPassword());
		Authentication b2 = new Authentication("Andyeet",        c1.getPassword());
		Authentication b3 = new Authentication(c1.getUsername(), "Yeeticus The Yeety");
		response = gc.createCustomer(c1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = gc.createCustomer(c1);
		assertEquals("failure", response.getMessage());
		assertEquals("username taken", response.getError());
		response = cc.authenticateCustomer(b1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = cc.authenticateCustomer(b2);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong username", response.getError());
		response = cc.authenticateCustomer(b3);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong password", response.getError());
		List<Customer> list = dc.listCustomers();
		assertEquals(1, list.size());
		assertEquals("Andrew", list.get(0).getName());
		assertEquals("andrew@gmail.com", list.get(0).getUsername());
		assertEquals("andrew123", list.get(0).getPassword());
		assertEquals("andrew blv", list.get(0).getLocation());
		verify(customerRepository, times(1)).save((Customer)any(Customer.class));
		verify(customerRepository, times(4)).findByUsername("andrew@gmail.com");
		verify(customerRepository, times(1)).findByUsername("Andyeet");
		verify(customerRepository, times(5)).findByUsername((String)any(String.class));
		verify(customerRepository, times(1)).findAll();
	}
	
	@Test
	public void authenticateCustomerTest3() {
		Message response;
		CustomerInfo c1 = new CustomerInfo("andrew@gmail.com","andrew123","Andrew","andrew blv");
		CustomerInfo c2 = new CustomerInfo("john@gmail.com","john123","John","john st");
		CustomerInfo c3 = new CustomerInfo("marry@gmail.com","mary123","Marry","marry dr");
		CustomerInfo c4 = new CustomerInfo("john@gmail.com","john456","John II","john court");
		Authentication b1 = new Authentication(c1.getUsername(), c1.getPassword());
		Authentication b2 = new Authentication(c2.getUsername(), c2.getPassword());
		Authentication b3 = new Authentication(c3.getUsername(), c3.getPassword());
		Authentication b4 = new Authentication(c4.getUsername(), c4.getPassword());
		Authentication f1 = new Authentication("Yeeticus", "The Yeety");
		Authentication f2 = new Authentication("Yeetus", "Skeetus");
		Authentication f3 = new Authentication("Yeetus", "Maximus");
		response = gc.createCustomer(c1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = gc.createCustomer(c2);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = gc.createCustomer(c3);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = gc.createCustomer(c4);
		assertEquals("failure", response.getMessage());
		assertEquals("username taken", response.getError());
		response = cc.authenticateCustomer(b1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = cc.authenticateCustomer(b2);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = cc.authenticateCustomer(b3);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = cc.authenticateCustomer(b4);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong password", response.getError());
		response = cc.authenticateCustomer(f1);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong username", response.getError());
		response = cc.authenticateCustomer(f2);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong username", response.getError());
		response = cc.authenticateCustomer(f3);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong username", response.getError());
		List<Customer> list = dc.listCustomers();
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
