package foodhub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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

import foodhub.controllers.CustomerController;
import foodhub.controllers.DebugController;
import foodhub.controllers.FirmController;
import foodhub.database.Category;
import foodhub.database.CategoryRepository;
import foodhub.database.Customer;
import foodhub.database.CustomerRepository;
import foodhub.database.Firm;
import foodhub.database.FirmRepository;
import foodhub.database.Item;
import foodhub.database.ItemRepository;
import foodhub.database.Order;
import foodhub.database.OrderItem;
import foodhub.database.OrderItemRepository;
import foodhub.database.OrderRepository;
import foodhub.ioObjects.AddCategoryInput;
//import foodhub.ioObjects.AddItemInput;
import foodhub.ioObjects.Authentication;
import foodhub.ioObjects.CategoryInfo;
//import foodhub.ioObjects.CompleteOrderInput;
//import foodhub.ioObjects.EditCategoryInput;
//import foodhub.ioObjects.ItemInfo;
import foodhub.ioObjects.Message;
//import foodhub.ioObjects.OrderItemOutput;
//import foodhub.ioObjects.OrderOutput;
//import foodhub.ioObjects.RemoveEntitledInput;
//import foodhub.ioObjects.RemoveItemInput;

@SpringBootTest
public class FirmControllerTests {
	@InjectMocks
	DebugController dc;
	
	@InjectMocks
	FirmController fc;
	
	@InjectMocks
	CustomerController cc;
	
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	FirmRepository firmRepository;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Mock
	ItemRepository itemRepository;
	
	@Mock
	OrderRepository orderRepository;
	
	@Mock
	OrderItemRepository orderItemRepository;
	
	Firm initial = new Firm("testfirm@gmail.com", "truePassword", "CyBurger", "Ames", "Borger", 500, 2000,  3, null);
	
	Customer customer = new Customer("customUser", "customPass", "Custoomer", "Ames");
	
	private List<Firm> firms;
	private List<Customer> customers;
	private List<Category> categories;
	private List<Item> items;
	private List<Order> orders;
	private List<OrderItem> orderItems;
	
	@BeforeEach
	public void init() {
		customers = new ArrayList<Customer>();
		when(customerRepository.findAll()).thenReturn(customers);
		when(customerRepository.findByUsername((String)any(String.class)))
		.thenAnswer(x-> {
			String username = x.getArgument(0);
			Iterator<Customer> itr = customers.iterator();
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
			customers.add(c);
			return null;
		});
		customerRepository.save(customer);
		orders = new ArrayList<Order>();
		when(orderRepository.findAll()).thenReturn(orders);
		when(orderRepository.findByFirmId((Long)any(Long.class)))
		.thenAnswer(x-> {
			List<Order> currOrders = new ArrayList<>();
			Long id = x.getArgument(0);
			Iterator<Order> itr = orders.iterator();
			while (itr.hasNext()) {
				Order o = itr.next();
				if (id.equals(o.getFirmId()))
					currOrders.add(o);
			}
			return currOrders;
		});
		when(orderRepository.save((Order)any(Order.class)))
		.thenAnswer(x -> {
			Order o = x.getArgument(0);
			orders.add(o);
			return null;
		});
		orderItems = new ArrayList<OrderItem>();
		when(orderItemRepository.findAll()).thenReturn(orderItems);
		when(orderItemRepository.findByOrderId((Long)any(Long.class)))
		.thenAnswer(x-> {
			List<OrderItem> currOrderItems = new ArrayList<>();
			Long id = x.getArgument(0);
			Iterator<OrderItem> itr = orderItems.iterator();
			while (itr.hasNext()) {
				OrderItem oi = itr.next();
				if (id.equals(oi.getOrderId()))
					currOrderItems.add(oi);
			}
			return currOrderItems;
		});
		when(orderItemRepository.save((OrderItem)any(OrderItem.class)))
		.thenAnswer(x -> {
			OrderItem oi = x.getArgument(0);
			orderItems.add(oi);
			return null;
		});
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
		categories = new ArrayList<Category>();
		when(categoryRepository.findAll()).thenReturn(categories);
		when(categoryRepository.findById((Long)any(Long.class)))
		.thenAnswer(x-> {
			return categories.get(0);
		});
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
		when(itemRepository.findById((Long)any(Long.class)))
		.thenAnswer(x-> {
			Long id = x.getArgument(0);
			Iterator<Item> itr = items.iterator();
			while (itr.hasNext()) {
				Item i = itr.next();
				if (id.equals(i.getId()))
					return i;
			}
			return null;
		});
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
		when(itemRepository.findByCategoryId((Long)any(Long.class)))
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
		assertEquals(1, currFirms.size());
		
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
		verify(firmRepository, times(1)).save((Firm)any(Firm.class));
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
		
		verify(firmRepository, times(1)).save(initial);
		verify(firmRepository, times(1)).save((Firm)any(Firm.class));
		verify(firmRepository, times(1)).findByUsername((String)any(String.class));
		verify(firmRepository, times(0)).findAll();
	}
	
	//Failure cases for firm authentication
	@Test
	public void firmAuthenticationTestFailures() {
		Message result;
		Authentication auth = new Authentication("a","b");
		result = fc.authenticateFirm(auth);
		assertEquals("failure", result.getMessage());
		assertEquals("wrong username", result.getError());
		
		auth = new Authentication(initial.getUsername(), "wrongPassword");
		result = fc.authenticateFirm(auth);
		assertEquals("failure", result.getMessage());
		assertEquals("wrong password", result.getError());
		
		verify(firmRepository, times(1)).save(initial);
		verify(firmRepository, times(1)).save((Firm)any(Firm.class));
		verify(firmRepository, times(2)).findByUsername((String)any(String.class));
		verify(firmRepository, times(0)).findAll();
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
		
		verify(firmRepository, times(1)).findByUsername((String)any(String.class));
		
		verify(categoryRepository, times(1)).save((Category)any(Category.class));
		verify(categoryRepository, times(0)).findByFirmId((Long)any(Long.class));
		verify(categoryRepository, times(1)).findAll();
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
		
		verify(firmRepository, times(2)).findByUsername((String)any(String.class));
		
		verify(categoryRepository, times(2)).save((Category)any(Category.class));
		verify(categoryRepository, times(0)).findByFirmId((Long)any(Long.class));
		verify(categoryRepository, times(1)).findAll();
	}
	
	//Show all failures and a success associated with creating category
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
		assertNotEquals("success", result.getMessage());
		assertEquals("wrong password", result.getError());
		
		catInput = new AddCategoryInput(initial.getUsername(), initial.getPassword(), catInfo);
		fc.createCategory(catInput);
		
		CategoryInfo secondCat = new CategoryInfo("Test Title", "another descirption eyeyae");
		AddCategoryInput catInput2 = new AddCategoryInput(initial.getUsername(), initial.getPassword(), secondCat);
		result = fc.createCategory(catInput2);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		verify(firmRepository, times(5)).findByUsername((String)any(String.class));
		
		verify(categoryRepository, times(2)).save((Category)any(Category.class));
		verify(categoryRepository, times(0)).findByFirmId((Long)any(Long.class));
		verify(categoryRepository, times(0)).findAll();
	}
	
	/*
	
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
		AddItemInput itemInput = new AddItemInput(initial.getUsername(), initial.getPassword(), 0, itemInfo);
		
		result = fc.createItem(itemInput);
		System.out.println(result.getError());
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		assertEquals(itemInfo.getTitle(), items.get(0).getTitle());
		assertEquals(itemInfo.getDescription(), items.get(0).getDescription());
		assertEquals(itemInfo.getPrice(), items.get(0).getPrice(), 0);
		
		verify(firmRepository, times(2)).findByUsername((String)any(String.class));
		
		verify(categoryRepository, times(1)).save((Category)any(Category.class));
		verify(categoryRepository, times(2)).findByFirmId((Long)any(Long.class));
		verify(categoryRepository, times(1)).findAll();
		
		verify(itemRepository, times(1)).save((Item)any(Item.class));
		verify(itemRepository, times(0)).findByFirmId((Long)any(Long.class));
		verify(itemRepository, times(0)).findAll();
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
		AddItemInput itemInput = new AddItemInput(initial.getUsername(), initial.getPassword(), dc.listCategories().get(0).getId(), itemInfo);
		ItemInfo itemInfo1 = new ItemInfo("Second Test Item Title", "Second Test Item Description", 4.20);
		AddItemInput itemInput1 = new AddItemInput(initial.getUsername(), initial.getPassword(), dc.listCategories().get(0).getId(), itemInfo1);
		
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
		
		verify(firmRepository, times(3)).findByUsername((String)any(String.class));
		
		verify(categoryRepository, times(1)).save((Category)any(Category.class));
		verify(categoryRepository, times(3)).findByFirmId((Long)any(Long.class));
		verify(categoryRepository, times(2)).findAll();
		
		verify(itemRepository, times(2)).save((Item)any(Item.class));
		verify(itemRepository, times(0)).findByFirmId((Long)any(Long.class));
		verify(itemRepository, times(0)).findAll();
	}
	
	*/
	
	//---Below this point are methods that are not *yet* working---
	
	//TODO: Fix. Currently continues to see deleted category in dc.listCategories
	/*
	//Remove category with items inside
	@Test
	public void removeCategoryTest() {
		Message result;
		CategoryInfo catInfo = new CategoryInfo("Test Title", "Test Description");
		AddCategoryInput catInput = new AddCategoryInput(initial.getUsername(), initial.getPassword(), catInfo);
		
		result = fc.createCategory(catInput);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		assertEquals(1, dc.listCategories().size());
		
		ItemInfo itemInfo = new ItemInfo("Test Item Title", "Test Item Description", 1.99);
		AddItemInput itemInput = new AddItemInput(initial.getUsername(), initial.getPassword(), dc.listCategories().get(0).getTitle(), itemInfo);
		
		result = fc.createItem(itemInput);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		assertEquals(1, dc.listItems().size());
		
		RemoveEntitledInput removeCat = new RemoveEntitledInput(initial.getUsername(), initial.getPassword(), catInfo.getTitle());
		result = fc.removeCateogry(removeCat);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		assertEquals(0, dc.listCategories().size());
		assertEquals(0, dc.listItems().size());
	}
	*/
	
	//TODO: Fix. Currently both items continue to exist in the dc.listItems after deletion
	/*
	//Removes a single item from a category
	@Test
	public void removeItemTest() {
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
		
		assertEquals(2, dc.listItems().size());
		
		RemoveItemInput removeItem = new RemoveItemInput(initial.getUsername(), initial.getPassword(), catInfo.getTitle(), itemInfo.getTitle());
		result = fc.removeItem(removeItem);
		assertEquals("success", result.getMessage());
		assertEquals("", result.getError());
		
		assertEquals(1, dc.listCategories().size());
		assertEquals(1, dc.listItems().size());
	}
	*/
	
	//TODO: Fix. currently believes item is null
	//Get 1 order for 1 firm
	/*
	@Test
	public void firmGetOrdersTest() {		
		Message result;
		//Quick test that customer is set up properly
		List<Customer> currCustomers = dc.listCustomers();
		assertEquals(1, currCustomers.size());
		assertEquals(customer.getId(), currCustomers.get(0).getId());
		assertEquals(customer.getName(), currCustomers.get(0).getName());
		assertEquals(customer.getUsername(), currCustomers.get(0).getUsername());
		assertEquals(customer.getPassword(), currCustomers.get(0).getPassword());
		
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
		
		Order order = new Order(initial.getId(), customer.getId(), "title?", 0);
		orderRepository.save(order);
		assertEquals(order.getId(), dc.listOrders().get(0).getId());
		assertEquals(order.getCustomerId(), dc.listOrders().get(0).getCustomerId());
		assertEquals(order.getFirmId(), dc.listOrders().get(0).getFirmId());
		assertEquals(order.getStatus(), dc.listOrders().get(0).getStatus());
		
		OrderItem orderItem = new OrderItem(order.getId(), dc.listItems().get(0).getId(), 3, "extra test");
		orderItemRepository.save(orderItem);
		assertEquals(orderItem.getId(), dc.listOrderItems().get(0).getId());
		assertEquals(orderItem.getItemId(), dc.listOrderItems().get(0).getItemId());
		assertEquals(orderItem.getNotes(), dc.listOrderItems().get(0).getNotes());
		assertEquals(orderItem.getOrderId(), dc.listOrderItems().get(0).getOrderId());
		
		List<OrderItemOutput> listOfOrders = new ArrayList<>();
		OrderItemOutput orderItemOutput = new OrderItemOutput(orderItem, dc.listItems().get(0));
		listOfOrders.add(orderItemOutput);
		assertEquals(1, listOfOrders.size());
		
		Authentication currFirm = new Authentication(initial.getUsername(), initial.getPassword());
		List<OrderOutput> orderOutput = new ArrayList<>();
		//TODO:Fix. Believes item is null for some reason?
		//orderOutput = fc.getOrders(currFirm);
		//assertEquals(1, orderOutput.size());
	}
	*/
	//TODO: Fix. Currently does not change title/description when edited (retains previous form)
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
	
	//TODO: Fix. Don't remember why it breaks, but it do
	/*
	@Test
	public void completeOrderTest() {
		Message result;
		Order order = new Order(initial.getId(), customer.getId(), "title?", 0);
		orderRepository.save(order);
		assertEquals(order.getStatus(), 0);
		
		CompleteOrderInput compOrder = new CompleteOrderInput(initial.getUsername(), initial.getPassword(), customer.getUsername(), order.getTitle());
		result = fc.completeOrder(compOrder);
		assertEquals("success", result.getMessage());
		assertEquals(order.getStatus(), 1);
	}*/
}
