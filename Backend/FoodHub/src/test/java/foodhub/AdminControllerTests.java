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

import foodhub.database.Admin;
import foodhub.database.Firm;
import foodhub.database.AdminRepository;
import foodhub.database.FirmRepository;
import foodhub.controllers.AdminController;
import foodhub.ioObjects.AdminInput;
import foodhub.ioObjects.FirmInput;

@SpringBootTest
public class AdminControllerTests {
	
	@InjectMocks
	AdminController ac;
	
	@Mock
	AdminRepository adminRepository;
	@Mock
	FirmRepository firmRepository;

	Admin owner = new Admin("Adom", "adom@gmail.com", "adom123", 1);
	
	private String success = "{\"message\":\"success\"}";
	private String failure = "{\"message\":\"failure\"}";

	private List<Admin> al;
	private List<Firm> fl;
	
	@BeforeEach
	public void init() {
		al = new ArrayList<Admin>();
		when(adminRepository.findAll()).thenReturn(al);
		when(adminRepository.findByUsername((String)any(String.class)))
		.thenAnswer(x-> {
			String username = x.getArgument(0);
			Iterator<Admin> itr = al.iterator();
			while (itr.hasNext()) {
				Admin a = itr.next();
				if (username.equals(a.getUsername()))
					return a;
			}
			return null;
		});
		when(adminRepository.save((Admin)any(Admin.class)))
		.thenAnswer(x -> {
			Admin a = x.getArgument(0);
			al.add(a);
			return null;
		});
		fl = new ArrayList<Firm>();
		when(firmRepository.findAll()).thenReturn(fl);
		when(firmRepository.findByUsername((String)any(String.class)))
		.thenAnswer(x-> {
			String username = x.getArgument(0);
			Iterator<Firm> itr = fl.iterator();
			while (itr.hasNext()) {
				Firm f = itr.next();
				if (username.equals(f.getUsername()))
					return f;
			}
			return null;
		});
		when(firmRepository.save((Firm)any(Firm.class)))
		.thenAnswer(x -> {
			Firm f = x.getArgument(0);
			fl.add(f);
			return null;
		});
		adminRepository.save(owner);
	}
	
	@Test
	public void createAdminTest0() {
		List<Admin> list = ac.listAdmins();
		assertEquals(1, list.size());
		assertEquals(owner.getName(), list.get(0).getName());
		assertEquals(owner.getUsername(), list.get(0).getUsername());
		assertEquals(owner.getPassword(), list.get(0).getPassword());
		assertEquals(owner.getType(), list.get(0).getType());
		verify(adminRepository, times(1)).save(owner);
		verify(adminRepository, times(1)).save((Admin)any(Admin.class));
		verify(adminRepository, times(0)).findByUsername((String)any(String.class));
		verify(adminRepository, times(1)).findAll();
		verify(firmRepository, times(0)).save((Firm)any(Firm.class));
		verify(firmRepository, times(0)).findByUsername((String)any(String.class));
		verify(firmRepository, times(0)).findAll();
	}
	
}
