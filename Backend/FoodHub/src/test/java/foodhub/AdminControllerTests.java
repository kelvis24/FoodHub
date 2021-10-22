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
import foodhub.controllers.GeneralController;
import foodhub.ioObjects.AddAdminInput;
import foodhub.ioObjects.AdminInfo;
import foodhub.ioObjects.AddFirmInput;
import foodhub.ioObjects.FirmInfo;
import foodhub.ioObjects.Message;

@SpringBootTest
public class AdminControllerTests {
	
	@InjectMocks
	AdminController ac;
	
	@InjectMocks
	DebugController dc;
	
	@InjectMocks
	FirmController fc;
	
	@InjectMocks
	GeneralController gc;
	
	@Mock
	AdminRepository adminRepository;
	
	@Mock
	FirmRepository firmRepository;

	Admin owner = new Admin("adom@gmail.com","adom123","Adom",1);

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
		Message response;
		AdminInfo a1 = new AdminInfo("andy@gmail.com","andy123","Andy");
		AddAdminInput b1 = new AddAdminInput(owner.getUsername(),owner.getPassword(),a1);
		response = ac.createAdmin(b1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
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
		Message response;
		AdminInfo a1 = new AdminInfo("andy@gmail.com","andy123","Andy");
		AddAdminInput b1 = new AddAdminInput(owner.getUsername(),owner.getPassword(),a1);
		response = ac.createAdmin(b1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = ac.createAdmin(b1);
		assertEquals("failure", response.getMessage());
		assertEquals("username taken", response.getError());
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
		Message response;
		AdminInfo a1 = new AdminInfo("andy@gmail.com","andy123","Andy");
		AdminInfo a2 = new AdminInfo("will@gmail.com","will123","Will");
		AddAdminInput b1 = new AddAdminInput(owner.getUsername(),owner.getPassword(),a1);
		AddAdminInput b2 = new AddAdminInput(owner.getUsername(),owner.getPassword(),a2);
		response = ac.createAdmin(b1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = ac.createAdmin(b2);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
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
		Message response;
		AdminInfo a1 = new AdminInfo("andy@gmail.com","andy123","Andy");
		AdminInfo a2 = new AdminInfo("will@gmail.com","will123","Will");
		AdminInfo a3 = new AdminInfo("bill@gmail.com","bill123","Bill");
		AddAdminInput b1 = new AddAdminInput(owner.getUsername(),owner.getPassword(),a1);
		AddAdminInput b2 = new AddAdminInput(owner.getUsername(),owner.getPassword(),a2);
		AddAdminInput b3 = new AddAdminInput(a3.getUsername(),a3.getPassword(),a3);
		AddAdminInput b4 = new AddAdminInput(owner.getUsername(),a3.getPassword(),a3);
		AddAdminInput b5 = new AddAdminInput(a3.getUsername(),owner.getPassword(),a3);
		AddAdminInput b6 = new AddAdminInput(a2.getUsername(),a2.getPassword(),a3);
		response = ac.createAdmin(b1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = ac.createAdmin(b2);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = ac.createAdmin(b3);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong username", response.getError());
		response = ac.createAdmin(b4);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong password", response.getError());
		response = ac.createAdmin(b5);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong username", response.getError());
		response = ac.createAdmin(b6);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong credentials", response.getError());
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
		Message response;
		FirmInfo f1 = new FirmInfo("tacohouse@gmail.com","taco123","Taco House","taco town","tacos",1,2,3);
		AddFirmInput b1 = new AddFirmInput(owner.getUsername(),owner.getPassword(),f1);
		response = ac.createFirm(b1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		List<Admin> admins = dc.listAdmins();
		List<Firm> firms = dc.listFirms();
		assertEquals(1, admins.size());
		assertEquals(owner.getName(),     admins.get(0).getName());
		assertEquals(owner.getUsername(), admins.get(0).getUsername());
		assertEquals(owner.getPassword(), admins.get(0).getPassword());
		assertEquals(1,                   admins.get(0).getType());
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
		verify(firmRepository, times(1)).save((Firm)any(Firm.class));
		verify(firmRepository, times(1)).findByUsername(f1.getUsername());
		verify(firmRepository, times(1)).findByUsername((String)any(String.class));
		verify(firmRepository, times(1)).findAll();
	}
	
	@Test
	public void createFirmTest1() {
		Message response;
		FirmInfo f1 = new FirmInfo("tacohouse@gmail.com","taco123","Taco House","taco town","tacos",1,2,3);
		FirmInfo f2 = new FirmInfo("tacoplace@gmail.com","taco123","Taco Place","taco town","tacos",1,2,3);
		AddFirmInput b1 = new AddFirmInput(owner.getUsername(),owner.getPassword(),f1);
		AddFirmInput b2 = new AddFirmInput(owner.getUsername(),owner.getPassword(),f2);
		response = ac.createFirm(b1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = ac.createFirm(b2);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = ac.createFirm(b2);
		assertEquals("failure", response.getMessage());
		assertEquals("username taken", response.getError());
		List<Admin> admins = dc.listAdmins();
		List<Firm> firms = dc.listFirms();
		assertEquals(1, admins.size());
		assertEquals(owner.getName(),     admins.get(0).getName());
		assertEquals(owner.getUsername(), admins.get(0).getUsername());
		assertEquals(owner.getPassword(), admins.get(0).getPassword());
		assertEquals(1,                   admins.get(0).getType());
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
		verify(firmRepository, times(2)).save((Firm)any(Firm.class));
		verify(firmRepository, times(1)).findByUsername(f1.getUsername());
		verify(firmRepository, times(2)).findByUsername(f2.getUsername());
		verify(firmRepository, times(3)).findByUsername((String)any(String.class));
		verify(firmRepository, times(1)).findAll();
	}
	
	@Test
	public void createFirmTest2() {
		Message response;
		AdminInfo a1 = new AdminInfo("george@gmail.com","george123","George");
		AddAdminInput ab = new AddAdminInput(owner.getUsername(),owner.getPassword(),a1);
		response = ac.createAdmin(ab);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		FirmInfo f1 = new FirmInfo("tacohouse@gmail.com","taco123","Taco House","taco town","tacos",1,2,3);
		FirmInfo f2 = new FirmInfo("tacoplace@gmail.com","taco123","Taco Place","taco town","tacos",1,2,3);
		FirmInfo f3 = new FirmInfo("tacofort@gmail.com","taco123","Taco Fort","taco town","tacos",1,2,3);
		AddFirmInput b1 = new AddFirmInput(a1.getUsername(),a1.getPassword(),f1);
		AddFirmInput b2 = new AddFirmInput(a1.getUsername(),a1.getPassword(),f2);
		AddFirmInput b3 = new AddFirmInput(owner.getUsername(),a1.getPassword(),f3);
		AddFirmInput b4 = new AddFirmInput(a1.getUsername(),owner.getPassword(),f3);
		response = ac.createFirm(b1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = ac.createFirm(b2);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		response = ac.createFirm(b3);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong password", response.getError());
		response = ac.createFirm(b4);
		assertEquals("failure", response.getMessage());
		assertEquals("wrong password", response.getError());
		response = ac.createFirm(b1);
		assertEquals("failure", response.getMessage());
		assertEquals("username taken", response.getError());
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
		verify(firmRepository, times(2)).save((Firm)any(Firm.class));
		verify(firmRepository, times(2)).findByUsername(f1.getUsername());
		verify(firmRepository, times(1)).findByUsername(f2.getUsername());
		verify(firmRepository, times(3)).findByUsername((String)any(String.class));
		verify(firmRepository, times(1)).findAll();
	}
	
}
