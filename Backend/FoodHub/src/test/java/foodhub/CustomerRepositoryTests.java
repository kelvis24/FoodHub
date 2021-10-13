package foodhub;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.mockito.Mock;

import foodhub.database.Customer;
import foodhub.database.CustomerRepository;

@SpringBootTest
public class CustomerRepositoryTests {


	@Mock
	CustomerRepository customerRepository;

	@Before
	public void init() {}

	@Test
	public void getCustomerByIdTest() {
		when(customerRepository.findById(1)).thenReturn(new Customer(1, "Jon1", "Jon2", "Jon3", "Jon4"));

		Customer customer = customerRepository.findById(1);

		assertEquals(1, customer.getId());
		assertEquals("Jon1", customer.getName());
		assertEquals("Jon2", customer.getUsername());
		assertEquals("Jon3", customer.getPassword());
		assertEquals("Jon4", customer.getLocation());
	}

	@Test
	public void getAllAccountTest() {
		List<Customer> list = new ArrayList<Customer>();
		Customer c1 = new Customer(1, "John", "1234", "john@gmail.com", "John Drive");
		Customer c2 = new Customer(2, "Alex", "abcd", "alex@yahoo.com", "John Avenue");
		Customer c3 = new Customer(3, "Steve", "efgh", "steve@gmail.com", "John Court");

		list.add(c1);
		list.add(c2);
		list.add(c3);

		when(customerRepository.findAll()).thenReturn(list);

		List<Customer> customerList = customerRepository.findAll();

		assertEquals(3, customerList.size());
		verify(customerRepository, times(1)).findAll();
		
	}

}
