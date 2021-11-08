package foodhub.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import foodhub.database.Customer;
import foodhub.database.CustomerRepository;

/**
 * To my knowledge, this class is unused; please disregard it.
 * It has to do with security.
 * @author Jamie Anderson
 */
@Qualifier("customerSerivce")
@Service
public class CustomerDetailsService implements UserDetailsService{

	@Autowired
	private CustomerRepository customerRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepo.findByUsername(username);
		if (customer == null) {
			throw new UsernameNotFoundException("username not found");
		}
		return new CustomerDetails(customer);
	}
}