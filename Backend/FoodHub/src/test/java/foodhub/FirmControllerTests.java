package foodhub;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import foodhub.controllers.DebugController;
import foodhub.controllers.FirmController;
import foodhub.database.Category;
import foodhub.database.CategoryRepository;
import foodhub.database.Firm;
import foodhub.database.FirmRepository;
import foodhub.database.Item;
import foodhub.database.ItemRepository;
import foodhub.ioObjects.AddCategoryInput;
import foodhub.ioObjects.AddItemInput;
import foodhub.ioObjects.Authentication;
import foodhub.ioObjects.CategoryInfo;
import foodhub.ioObjects.EditCategoryInput;
import foodhub.ioObjects.ItemInfo;
import foodhub.ioObjects.Message;

@SpringBootTest
public class FirmControllerTests {
	@InjectMocks
	DebugController dc;
	
	@InjectMocks
	FirmController fc;
	
	@Mock
	FirmRepository firmRepository;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Mock
	ItemRepository itemRepository;
	
	Firm initial = new Firm("testfirm@gmail.com", "truePassword", "CyBurger", "Ames", "Borger", 500, 2000,  3);
	Firm second = new Firm("secondTest@yahoo.com", "duplicate", "WcDendys", "Ohio", "Borger", 500, 2000, 3);
	
	private List<Firm> firms;
	private List<Category> categories;
	private List<Item> items;
	
	@BeforeEach
	public void init() {
		firms = new ArrayList<Firm>();
		when(firmRepository.findAll()).thenReturn(firms);
		when(firmRepository.findByUsername((String)any(String.class)))
		.thenAnswer(x-> {
			String username = x.getArgument(0);
			Iterator<Firm> itr = firms.iterator();
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
			firms.add(f);
			return null;
		});
		firmRepository.save(initial);
		firmRepository.save(second);
		categories = new ArrayList<Category>();
		when(categoryRepository.findAll()).thenReturn(categories);
		when(categoryRepository.findByFirmId((Long)any(Long.class)))
		.thenAnswer(x-> {
			List<Category> firmCats = new ArrayList<>();
			Long id = x.getArgument(0);
			Iterator<Category> itr = categories.iterator();
			while (itr.hasNext()) {
				Category c = itr.next();
				if (id.equals(c.getFirmId()))
					firmCats.add(c);
			}
			return firmCats;
		});
		when(categoryRepository.save((Category)any(Category.class)))
		.thenAnswer(x -> {
			Category c = x.getArgument(0);
			categories.add(c);
			return null;
		});
		items = new ArrayList<Item>();
		when(itemRepository.findAll()).thenReturn(items);
		when(itemRepository.findByFirmId((Long)any(Long.class)))
		.thenAnswer(x-> {
			List<Item> firmItems = new ArrayList<>();
			Long id = x.getArgument(0);
			Iterator<Item> itr = items.iterator();
			while (itr.hasNext()) {
				Item i = itr.next();
				if (id.equals(i.getFirmId()))
					firmItems.add(i);
			}
			return firmItems;
		});
		when(itemRepository.save((Item)any(Item.class)))
		.thenAnswer(x -> {
			Item i = x.getArgument(0);
			items.add(i);
			return null;
		});
	}
	
	@Test
	public void generalFirmTest() {
		List<Firm> currFirms = dc.listFirms();
		assertEquals(2, currFirms.size());
		
		assertEquals(initial.getUsername(), currFirms.get(0).getUsername());
		assertEquals(initial.getPassword(), currFirms.get(0).getPassword());
		assertEquals(initial.getName(), currFirms.get(0).getName());
		assertEquals(initial.getLocation(), currFirms.get(0).getLocation());
		assertEquals(initial.getId(), currFirms.get(0).getId());
		assertEquals(initial.getClose_time(), currFirms.get(0).getClose_time());
		assertEquals(initial.getOpen_time(), currFirms.get(0).getOpen_time());
		assertEquals(initial.getCuisine(), currFirms.get(0).getCuisine());
		assertEquals(initial.getEmployee_count(), currFirms.get(0).getEmployee_count());
		
		verify(firmRepository, times(1)).save(initial);
		verify(firmRepository, times(2)).save((Firm)any(Firm.class));
		verify(firmRepository, times(0)).findByUsername((String)any(String.class));
		verify(firmRepository, times(1)).findAll();
		
		verify(categoryRepository, times(0)).save((Category)any(Category.class));
		verify(categoryRepository, times(0)).findByFirmId((Long)any(Long.class));
		verify(categoryRepository, times(0)).findAll();
		
		verify(itemRepository, times(0)).save((Item)any(Item.class));
		verify(itemRepository, times(0)).findByFirmId((Long)any(Long.class));
		verify(itemRepository, times(0)).findAll();
	}
	
	//Success case for firm authentication
	@Test
	public void firmAuthenticationTest() {
		Message result;
		Authentication auth = new Authentication(initial.getUsername(), initial.getPassword());
		result = fc.authenticateFirm(auth);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
	}
	
	//Failure cases for firm authentication
	@Test
	public void firmAuthenticationTestFailures() {
		Message result;
		Authentication auth = new Authentication();
		result = fc.authenticateFirm(auth);
		assertEquals("failure", result.getMessage());
		assertEquals("wrong username", result.getError());
		
		auth = new Authentication(initial.getUsername(), "wrongPassword");
		result = fc.authenticateFirm(auth);
		assertEquals("failure", result.getMessage());
		assertEquals("wrong password", result.getError());
	}
	
	//Singular firm with singular category
	@Test
	public void createCategoryTest1() {
		Message result;
		CategoryInfo catInfo = new CategoryInfo("Test Title", "Test Description");
		AddCategoryInput catInput = new AddCategoryInput(initial.getUsername(), initial.getPassword(), catInfo);
		
		result = fc.createCategory(catInput);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		List<Category> currCats = dc.listCategories();
		assertEquals(1, currCats.size());
		
		assertEquals(catInfo.getTitle(), categories.get(0).getTitle());
		assertEquals(catInfo.getDescription(), categories.get(0).getDescription());
	}
	
	//Singular firm with multiple categories
	@Test
	public void createCategoryTest2() {
		Message result;
		CategoryInfo catInfo1 = new CategoryInfo("Test Title", "Test Description");
		AddCategoryInput catInput1 = new AddCategoryInput(initial.getUsername(), initial.getPassword(), catInfo1);
		CategoryInfo catInfo2 = new CategoryInfo("Second Test Title", "Second Test Description");
		AddCategoryInput catInput2 = new AddCategoryInput(initial.getUsername(), initial.getPassword(), catInfo2);
		
		result = fc.createCategory(catInput1);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		result = fc.createCategory(catInput2);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		List<Category> currCats = dc.listCategories();
		assertEquals(2, currCats.size());
		
		assertEquals(catInfo1.getTitle(), categories.get(0).getTitle());
		assertEquals(catInfo1.getDescription(), categories.get(0).getDescription());
		
		assertEquals(catInfo2.getTitle(), categories.get(1).getTitle());
		assertEquals(catInfo2.getDescription(), categories.get(1).getDescription());
	}
	
	/*
	//Multiple firms with singular category each (with same name)
	@Test
	public void createCategoryTest3() {
		Message result;
		CategoryInfo catInfo = new CategoryInfo("Test Title", "Test Description");
		AddCategoryInput catInputInitial = new AddCategoryInput(initial.getUsername(), initial.getPassword(), catInfo);
		AddCategoryInput catInputSecond = new AddCategoryInput(second.getUsername(), second.getPassword(), catInfo);
		
		result = fc.createCategory(catInputInitial);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		//TODO: currently 'failure':'title taken'. 
		result = fc.createCategory(catInputSecond);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
	}*/
	
	//Show all failures associated with creating category
	@Test
	public void createCategoryTestFailures() {
		Message result;
		CategoryInfo catInfo = null;
		AddCategoryInput catInput = new AddCategoryInput(initial.getUsername(), initial.getPassword(), catInfo);
		
		result = fc.createCategory(catInput);
		assertEquals("failure", result.getMessage());
		assertEquals("no data", result.getError());
		
		catInfo = new CategoryInfo("Test Title", "Test Description");
		catInput = new AddCategoryInput("fakeUser", initial.getPassword(), catInfo);
		
		result = fc.createCategory(catInput);
		assertEquals("failure", result.getMessage());
		assertEquals("wrong username", result.getError());
		
		catInput = new AddCategoryInput(initial.getUsername(), "wrongPassword", catInfo);
		result = fc.createCategory(catInput);
		assertEquals("failure", result.getMessage());
		assertEquals("wrong password", result.getError());
		
		catInput = new AddCategoryInput(initial.getUsername(), initial.getPassword(), catInfo);
		fc.createCategory(catInput);
		
		CategoryInfo secondCat = new CategoryInfo("Test Title", "another descirption eyeyae");
		AddCategoryInput catInput2 = new AddCategoryInput(initial.getUsername(), initial.getPassword(), secondCat);
		result = fc.createCategory(catInput2);
		assertEquals("failure", result.getMessage());
		assertEquals("title taken", result.getError());
	}
	
	//TODO: Fix. Currently does not change title/description when editted (retains previous form)
	/*
	@Test
	public void editCategoryTest() {
		Message result;
		CategoryInfo catInfo = new CategoryInfo("Test Title", "Test Description");
		AddCategoryInput catInput = new AddCategoryInput(initial.getUsername(), initial.getPassword(), catInfo);
		
		result = fc.createCategory(catInput);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		assertEquals(catInfo.getTitle(), categories.get(0).getTitle());
		assertEquals(catInfo.getDescription(), categories.get(0).getDescription());
		
		CategoryInfo edittedCat = new CategoryInfo("Updated Titile", "Updated Descrirpiption");
		EditCategoryInput editCat = new EditCategoryInput(initial.getUsername(), initial.getPassword(), edittedCat, catInfo.getTitle());
		result = fc.editCategory(editCat);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		assertEquals(edittedCat.getTitle(), dc.listCategories().get(0).getTitle());
	}
	*/
	//Singular firm with singular category with singular item
	@Test
	public void createItemTest1() {
		Message result;
		CategoryInfo catInfo = new CategoryInfo("Test Title", "Test Description");
		AddCategoryInput catInputInitial = new AddCategoryInput(initial.getUsername(), initial.getPassword(), catInfo);
		
		result = fc.createCategory(catInputInitial);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		ItemInfo itemInfo = new ItemInfo("Test Item Title", "Test Item Description", 1.99);
		AddItemInput itemInput = new AddItemInput(initial.getUsername(), initial.getPassword(), dc.listCategories().get(0).getTitle(), itemInfo);
		
		result = fc.createItem(itemInput);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		assertEquals(itemInfo.getTitle(), items.get(0).getTitle());
		assertEquals(itemInfo.getDescription(), items.get(0).getDescription());
		assertEquals(itemInfo.getPrice(), items.get(0).getPrice(), 0);
	}
	
	//Singular firm with singular category with MULTIPLE items
	@Test
	public void createItemTest2() {
		Message result;
		CategoryInfo catInfo = new CategoryInfo("Test Title", "Test Description");
		AddCategoryInput catInputInitial = new AddCategoryInput(initial.getUsername(), initial.getPassword(), catInfo);
		
		result = fc.createCategory(catInputInitial);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		ItemInfo itemInfo = new ItemInfo("Test Item Title", "Test Item Description", 1.99);
		AddItemInput itemInput = new AddItemInput(initial.getUsername(), initial.getPassword(), dc.listCategories().get(0).getTitle(), itemInfo);
		ItemInfo itemInfo1 = new ItemInfo("Second Test Item Title", "Second Test Item Description", 4.20);
		AddItemInput itemInput1 = new AddItemInput(initial.getUsername(), initial.getPassword(), dc.listCategories().get(0).getTitle(), itemInfo1);
		
		result = fc.createItem(itemInput);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		result = fc.createItem(itemInput1);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		assertEquals(itemInfo.getTitle(), items.get(0).getTitle());
		assertEquals(itemInfo.getDescription(), items.get(0).getDescription());
		assertEquals(itemInfo.getPrice(), items.get(0).getPrice(), 0);
		
		assertEquals(itemInfo1.getTitle(), items.get(1).getTitle());
		assertEquals(itemInfo1.getDescription(), items.get(1).getDescription());
		assertEquals(itemInfo1.getPrice(), items.get(1).getPrice(), 0);
	}
}
