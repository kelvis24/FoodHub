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
	CostomerRepository costomerRepository;
	
	@Mock
	FirmRepository firmRepository;

	Costomer owner = new Costomer("test@gmail.com","test123","Test",1);

	private List<Admin> al;
    private List<Costomer> cl;
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
		costomerRepository.save(owner);
	}
	
	@Test
	public void frontTest0() {
		List<costomer> list = dc.listCostomer();
		assertEquals(1, list.size());
		assertEquals(owner.getName(), list.get(0).getName());
		assertEquals(owner.getUsername(), list.get(0).getUsername());
		assertEquals(owner.getPassword(), list.get(0).getPassword());
		assertEquals(1, list.get(0).getType());
		verify(costomerRepository, times(1)).save(owner);
		verify(costomerRepository, times(1)).save((costomer)any(costomer.class));
		verify(costomerRepository, times(0)).findByUsername((String)any(String.class));
		verify(costomerRepository, times(1)).findAll();
		verify(firmRepository, times(0)).save((Firm)any(Firm.class));
		verify(firmRepository, times(0)).findByUsername((String)any(String.class));
		verify(firmRepository, times(0)).findAll();
	}
	
	@Test
	public void frontTest1() {
		Message response;
		CostomerInfo a1 = new CostomerInfo("test@gmail.com","test123","Test",1);
		AddCostomerInput b1 = new AddCostomerInput(owner.getUsername(),owner.getPassword(),a1);
		response = ac.createCostomer(b1);
		assertEquals("success", response.getMessage());
		assertEquals("", response.getError());
		List<Costomer> list = dc.listCostomer();
		assertEquals(2, list.size());
		assertEquals(owner.getName(), list.get(0).getName());
		assertEquals(owner.getUsername(), list.get(0).getUsername());
		assertEquals(owner.getPassword(), list.get(0).getPassword());
		assertEquals(1, list.get(0).getType());
		assertEquals(a1.getName(), list.get(1).getName());
		assertEquals(a1.getUsername(), list.get(1).getUsername());
		assertEquals(a1.getPassword(), list.get(1).getPassword());
		assertEquals(0, list.get(1).getType());
	}
	
}
