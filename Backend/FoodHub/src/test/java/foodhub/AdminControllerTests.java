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
import foodhub.controllers.DebugController;
import foodhub.controllers.FirmController;
import foodhub.ioObjects.AdminInput;
import foodhub.ioObjects.FirmInput;

@SpringBootTest
public class AdminControllerTests {
	
	@InjectMocks
	AdminController ac;
	
	@InjectMocks
	DebugController dc;
	
	@InjectMocks
	FirmController fc;
	
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
		List<Admin> list = dc.listAdmins();
		assertEquals(1, list.size());
		assertEquals(owner.getName(), list.get(0).getName());
		assertEquals(owner.getUsername(), list.get(0).getUsername());
		assertEquals(owner.getPassword(), list.get(0).getPassword());
		assertEquals(1, list.get(0).getType());
		verify(adminRepository, times(1)).save(owner);
		verify(adminRepository, times(1)).save((Admin)any(Admin.class));
		verify(adminRepository, times(0)).findByUsername((String)any(String.class));
		verify(adminRepository, times(1)).findAll();
		verify(firmRepository, times(0)).save((Firm)any(Firm.class));
		verify(firmRepository, times(0)).findByUsername((String)any(String.class));
		verify(firmRepository, times(0)).findAll();
	}
	
	@Test
	public void createAdminTest1() {
		String response;
		Admin a1 = new Admin("Andy","andy@gmail.com","andy123",0);
		AdminInput b1 = new AdminInput(owner.getUsername(),owner.getPassword(),a1);
		response = ac.createAdmin(b1);
		assertEquals(success, response);
		List<Admin> list = dc.listAdmins();
		assertEquals(2, list.size());
		assertEquals(owner.getName(), list.get(0).getName());
		assertEquals(owner.getUsername(), list.get(0).getUsername());
		assertEquals(owner.getPassword(), list.get(0).getPassword());
		assertEquals(1, list.get(0).getType());
		assertEquals(a1.getName(), list.get(1).getName());
		assertEquals(a1.getUsername(), list.get(1).getUsername());
		assertEquals(a1.getPassword(), list.get(1).getPassword());
		assertEquals(0, list.get(1).getType());
		verify(adminRepository, times(1)).save(owner);
		verify(adminRepository, times(1)).save(a1);
		verify(adminRepository, times(2)).save((Admin)any(Admin.class));
		verify(adminRepository, times(1)).findByUsername(owner.getUsername());
		verify(adminRepository, times(1)).findByUsername(a1.getUsername());
		verify(adminRepository, times(2)).findByUsername((String)any(String.class));
		verify(adminRepository, times(1)).findAll();
		verify(firmRepository, times(0)).save((Firm)any(Firm.class));
		verify(firmRepository, times(0)).findByUsername((String)any(String.class));
		verify(firmRepository, times(0)).findAll();
	}
	
	@Test
	public void createAdminTest2() {
		String response;
		Admin a1 = new Admin("Andy","andy@gmail.com","andy123",0);
		AdminInput b1 = new AdminInput(owner.getUsername(),owner.getPassword(),a1);
		response = ac.createAdmin(b1);
		assertEquals(success, response);
		response = ac.createAdmin(b1);
		assertEquals(failure, response);
		List<Admin> list = dc.listAdmins();
		assertEquals(2, list.size());
		assertEquals(owner.getName(), list.get(0).getName());
		assertEquals(owner.getUsername(), list.get(0).getUsername());
		assertEquals(owner.getPassword(), list.get(0).getPassword());
		assertEquals(1, list.get(0).getType());
		assertEquals(a1.getName(), list.get(1).getName());
		assertEquals(a1.getUsername(), list.get(1).getUsername());
		assertEquals(a1.getPassword(), list.get(1).getPassword());
		assertEquals(0, list.get(1).getType());
		verify(adminRepository, times(1)).save(owner);
		verify(adminRepository, times(1)).save(a1);
		verify(adminRepository, times(2)).save((Admin)any(Admin.class));
		verify(adminRepository, times(2)).findByUsername(owner.getUsername());
		verify(adminRepository, times(2)).findByUsername(a1.getUsername());
		verify(adminRepository, times(4)).findByUsername((String)any(String.class));
		verify(adminRepository, times(1)).findAll();
		verify(firmRepository, times(0)).save((Firm)any(Firm.class));
		verify(firmRepository, times(0)).findByUsername((String)any(String.class));
		verify(firmRepository, times(0)).findAll();
	}
	
	@Test
	public void createAdminTest3() {
		String response;
		Admin a1 = new Admin("Andy","andy@gmail.com","andy123",0);
		Admin a2 = new Admin("Will","will@gmail.com","will123",1);
		AdminInput b1 = new AdminInput(owner.getUsername(),owner.getPassword(),a1);
		AdminInput b2 = new AdminInput(owner.getUsername(),owner.getPassword(),a2);
		response = ac.createAdmin(b1);
		assertEquals(success, response);
		response = ac.createAdmin(b2);
		assertEquals(success, response);
		List<Admin> list = dc.listAdmins();
		assertEquals(3, list.size());
		assertEquals(owner.getName(),     list.get(0).getName());
		assertEquals(owner.getUsername(), list.get(0).getUsername());
		assertEquals(owner.getPassword(), list.get(0).getPassword());
		assertEquals(1,                   list.get(0).getType());
		assertEquals(a1.getName(),     list.get(1).getName());
		assertEquals(a1.getUsername(), list.get(1).getUsername());
		assertEquals(a1.getPassword(), list.get(1).getPassword());
		assertEquals(0,                list.get(1).getType());
		assertEquals(a2.getName(),     list.get(2).getName());
		assertEquals(a2.getUsername(), list.get(2).getUsername());
		assertEquals(a2.getPassword(), list.get(2).getPassword());
		assertEquals(0,                list.get(2).getType());
		verify(adminRepository, times(1)).save(owner);
		verify(adminRepository, times(1)).save(a1);
		verify(adminRepository, times(1)).save(a2);
		verify(adminRepository, times(3)).save((Admin)any(Admin.class));
		verify(adminRepository, times(2)).findByUsername(owner.getUsername());
		verify(adminRepository, times(1)).findByUsername(a1.getUsername());
		verify(adminRepository, times(1)).findByUsername(a2.getUsername());
		verify(adminRepository, times(4)).findByUsername((String)any(String.class));
		verify(adminRepository, times(1)).findAll();
		verify(firmRepository, times(0)).save((Firm)any(Firm.class));
		verify(firmRepository, times(0)).findByUsername((String)any(String.class));
		verify(firmRepository, times(0)).findAll();
	}
	
	@Test
	public void createAdminTest4() {
		String response;
		Admin a1 = new Admin("Andy","andy@gmail.com","andy123",0);
		Admin a2 = new Admin("Will","will@gmail.com","will123",1);
		Admin a3 = new Admin("Bill","bill@gmail.com","bill123",0);
		AdminInput b1 = new AdminInput(owner.getUsername(),owner.getPassword(),a1);
		AdminInput b2 = new AdminInput(owner.getUsername(),owner.getPassword(),a2);
		AdminInput b3 = new AdminInput(a3.getUsername(),a3.getPassword(),a3);
		AdminInput b4 = new AdminInput(owner.getUsername(),a3.getPassword(),a3);
		AdminInput b5 = new AdminInput(a3.getUsername(),owner.getPassword(),a3);
		AdminInput b6 = new AdminInput(a2.getUsername(),a2.getPassword(),a3);
		response = ac.createAdmin(b1);
		assertEquals(success, response);
		response = ac.createAdmin(b2);
		assertEquals(success, response);
		response = ac.createAdmin(b3);
		assertEquals(failure, response);
		response = ac.createAdmin(b4);
		assertEquals(failure, response);
		response = ac.createAdmin(b5);
		assertEquals(failure, response);
		response = ac.createAdmin(b6);
		assertEquals(failure, response);
		List<Admin> list = dc.listAdmins();
		assertEquals(3, list.size());
		assertEquals(owner.getName(),     list.get(0).getName());
		assertEquals(owner.getUsername(), list.get(0).getUsername());
		assertEquals(owner.getPassword(), list.get(0).getPassword());
		assertEquals(1,                   list.get(0).getType());
		assertEquals(a1.getName(),     list.get(1).getName());
		assertEquals(a1.getUsername(), list.get(1).getUsername());
		assertEquals(a1.getPassword(), list.get(1).getPassword());
		assertEquals(0,                list.get(1).getType());
		assertEquals(a2.getName(),     list.get(2).getName());
		assertEquals(a2.getUsername(), list.get(2).getUsername());
		assertEquals(a2.getPassword(), list.get(2).getPassword());
		assertEquals(0,                list.get(2).getType());
		verify(adminRepository, times(1)).save(owner);
		verify(adminRepository, times(1)).save(a1);
		verify(adminRepository, times(1)).save(a2);
		verify(adminRepository, times(3)).save((Admin)any(Admin.class));
		verify(adminRepository, times(3)).findByUsername(owner.getUsername());
		verify(adminRepository, times(1)).findByUsername(a1.getUsername());
		verify(adminRepository, times(2)).findByUsername(a2.getUsername());
		verify(adminRepository, times(2)).findByUsername(a3.getUsername());
		verify(adminRepository, times(8)).findByUsername((String)any(String.class));
		verify(adminRepository, times(1)).findAll();
		verify(firmRepository, times(0)).save((Firm)any(Firm.class));
		verify(firmRepository, times(0)).findByUsername((String)any(String.class));
		verify(firmRepository, times(0)).findAll();
	}
	
	@Test
	public void createFirmTest0() {
		String response;
		Firm f1 = new Firm("Taco House","tacohouse@gmail.com","taco123","taco town","tacos",1,2,3);
		FirmInput b1 = new FirmInput(owner.getUsername(),owner.getPassword(),f1);
		response = ac.createFirm(b1);
		assertEquals(success, response);
		List<Admin> admins = dc.listAdmins();
		List<Firm> firms = dc.listFirms();
		assertEquals(1, admins.size());
		assertEquals(owner.getName(),     admins.get(0).getName());
		assertEquals(owner.getUsername(), admins.get(0).getUsername());
		assertEquals(owner.getPassword(), admins.get(0).getPassword());
		assertEquals(1,                   admins.get(0).getType());
		verify(adminRepository, times(1)).save(owner);
		verify(adminRepository, times(1)).save((Admin)any(Admin.class));
		verify(adminRepository, times(1)).findByUsername(owner.getUsername());
		verify(adminRepository, times(1)).findByUsername((String)any(String.class));
		verify(adminRepository, times(1)).findAll();
		assertEquals(1, firms.size());
		assertEquals(f1.getName(),           firms.get(0).getName());
		assertEquals(f1.getUsername(),       firms.get(0).getUsername());
		assertEquals(f1.getPassword(),       firms.get(0).getPassword());
		assertEquals(f1.getLocation(),       firms.get(0).getLocation());
		assertEquals(f1.getCuisine(),        firms.get(0).getCuisine());
		assertEquals(f1.getOpen_time(),      firms.get(0).getOpen_time());
		assertEquals(f1.getClose_time(),     firms.get(0).getClose_time());
		assertEquals(f1.getEmployee_count(), firms.get(0).getEmployee_count());
		verify(firmRepository, times(1)).save(f1);
		verify(firmRepository, times(1)).save((Firm)any(Firm.class));
		verify(firmRepository, times(1)).findByUsername(f1.getUsername());
		verify(firmRepository, times(1)).findByUsername((String)any(String.class));
		verify(firmRepository, times(1)).findAll();
	}
	
	@Test
	public void createFirmTest1() {
		String response;
		Firm f1 = new Firm("Taco House","tacohouse@gmail.com","taco123","taco town","tacos",1,2,3);
		Firm f2 = new Firm("Taco Place","tacoplace@gmail.com","taco123","taco town","tacos",1,2,3);
		FirmInput b1 = new FirmInput(owner.getUsername(),owner.getPassword(),f1);
		FirmInput b2 = new FirmInput(owner.getUsername(),owner.getPassword(),f2);
		response = ac.createFirm(b1);
		assertEquals(success, response);
		response = ac.createFirm(b2);
		assertEquals(success, response);
		response = ac.createFirm(b2);
		assertEquals(failure, response);
		List<Admin> admins = dc.listAdmins();
		List<Firm> firms = dc.listFirms();
		assertEquals(1, admins.size());
		assertEquals(owner.getName(),     admins.get(0).getName());
		assertEquals(owner.getUsername(), admins.get(0).getUsername());
		assertEquals(owner.getPassword(), admins.get(0).getPassword());
		assertEquals(1,                   admins.get(0).getType());
		verify(adminRepository, times(1)).save(owner);
		verify(adminRepository, times(1)).save((Admin)any(Admin.class));
		verify(adminRepository, times(3)).findByUsername(owner.getUsername());
		verify(adminRepository, times(3)).findByUsername((String)any(String.class));
		verify(adminRepository, times(1)).findAll();
		assertEquals(2, firms.size());
		assertEquals(f1.getName(),           firms.get(0).getName());
		assertEquals(f1.getUsername(),       firms.get(0).getUsername());
		assertEquals(f1.getPassword(),       firms.get(0).getPassword());
		assertEquals(f1.getLocation(),       firms.get(0).getLocation());
		assertEquals(f1.getCuisine(),        firms.get(0).getCuisine());
		assertEquals(f1.getOpen_time(),      firms.get(0).getOpen_time());
		assertEquals(f1.getClose_time(),     firms.get(0).getClose_time());
		assertEquals(f1.getEmployee_count(), firms.get(0).getEmployee_count());
		assertEquals(f2.getName(),           firms.get(1).getName());
		assertEquals(f2.getUsername(),       firms.get(1).getUsername());
		assertEquals(f2.getPassword(),       firms.get(1).getPassword());
		assertEquals(f2.getLocation(),       firms.get(1).getLocation());
		assertEquals(f2.getCuisine(),        firms.get(1).getCuisine());
		assertEquals(f2.getOpen_time(),      firms.get(1).getOpen_time());
		assertEquals(f2.getClose_time(),     firms.get(1).getClose_time());
		assertEquals(f2.getEmployee_count(), firms.get(1).getEmployee_count());
		verify(firmRepository, times(1)).save(f1);
		verify(firmRepository, times(1)).save(f2);
		verify(firmRepository, times(2)).save((Firm)any(Firm.class));
		verify(firmRepository, times(1)).findByUsername(f1.getUsername());
		verify(firmRepository, times(2)).findByUsername(f2.getUsername());
		verify(firmRepository, times(3)).findByUsername((String)any(String.class));
		verify(firmRepository, times(1)).findAll();
	}
	
	@Test
	public void createFirmTest2() {
		String response;
		Admin a1 = new Admin("George","george@gmail.com","george123",0);
		AdminInput ab = new AdminInput(owner.getUsername(),owner.getPassword(),a1);
		response = ac.createAdmin(ab);
		assertEquals(success, response);
		Firm f1 = new Firm("Taco House","tacohouse@gmail.com","taco123","taco town","tacos",1,2,3);
		Firm f2 = new Firm("Taco Place","tacoplace@gmail.com","taco123","taco town","tacos",1,2,3);
		Firm f3 = new Firm("Taco Fort","tacofort@gmail.com","taco123","taco town","tacos",1,2,3);
		FirmInput b1 = new FirmInput(a1.getUsername(),a1.getPassword(),f1);
		FirmInput b2 = new FirmInput(a1.getUsername(),a1.getPassword(),f2);
		FirmInput b3 = new FirmInput(owner.getUsername(),a1.getPassword(),f3);
		FirmInput b4 = new FirmInput(a1.getUsername(),owner.getPassword(),f3);
		response = ac.createFirm(b1);
		assertEquals(success, response);
		response = ac.createFirm(b2);
		assertEquals(success, response);
		response = ac.createFirm(b3);
		assertEquals(failure, response);
		response = ac.createFirm(b4);
		assertEquals(failure, response);
		response = ac.createFirm(b1);
		assertEquals(failure, response);
		List<Admin> admins = dc.listAdmins();
		List<Firm> firms = dc.listFirms();
		assertEquals(2, admins.size());
		assertEquals(owner.getName(),     admins.get(0).getName());
		assertEquals(owner.getUsername(), admins.get(0).getUsername());
		assertEquals(owner.getPassword(), admins.get(0).getPassword());
		assertEquals(1,                   admins.get(0).getType());
		assertEquals(a1.getName(),        admins.get(1).getName());
		assertEquals(a1.getUsername(),    admins.get(1).getUsername());
		assertEquals(a1.getPassword(),    admins.get(1).getPassword());
		assertEquals(0,                   admins.get(1).getType());
		verify(adminRepository, times(1)).save(owner);
		verify(adminRepository, times(1)).save(a1);
		verify(adminRepository, times(2)).save((Admin)any(Admin.class));
		verify(adminRepository, times(2)).findByUsername(owner.getUsername());
		verify(adminRepository, times(5)).findByUsername(a1.getUsername());
		verify(adminRepository, times(7)).findByUsername((String)any(String.class));
		verify(adminRepository, times(1)).findAll();
		assertEquals(2, firms.size());
		assertEquals(f1.getName(),           firms.get(0).getName());
		assertEquals(f1.getUsername(),       firms.get(0).getUsername());
		assertEquals(f1.getPassword(),       firms.get(0).getPassword());
		assertEquals(f1.getLocation(),       firms.get(0).getLocation());
		assertEquals(f1.getCuisine(),        firms.get(0).getCuisine());
		assertEquals(f1.getOpen_time(),      firms.get(0).getOpen_time());
		assertEquals(f1.getClose_time(),     firms.get(0).getClose_time());
		assertEquals(f1.getEmployee_count(), firms.get(0).getEmployee_count());
		assertEquals(f2.getName(),           firms.get(1).getName());
		assertEquals(f2.getUsername(),       firms.get(1).getUsername());
		assertEquals(f2.getPassword(),       firms.get(1).getPassword());
		assertEquals(f2.getLocation(),       firms.get(1).getLocation());
		assertEquals(f2.getCuisine(),        firms.get(1).getCuisine());
		assertEquals(f2.getOpen_time(),      firms.get(1).getOpen_time());
		assertEquals(f2.getClose_time(),     firms.get(1).getClose_time());
		assertEquals(f2.getEmployee_count(), firms.get(1).getEmployee_count());
		verify(firmRepository, times(1)).save(f1);
		verify(firmRepository, times(1)).save(f2);
		verify(firmRepository, times(2)).save((Firm)any(Firm.class));
		verify(firmRepository, times(2)).findByUsername(f1.getUsername());
		verify(firmRepository, times(1)).findByUsername(f2.getUsername());
		verify(firmRepository, times(3)).findByUsername((String)any(String.class));
		verify(firmRepository, times(1)).findAll();
	}
	
}
