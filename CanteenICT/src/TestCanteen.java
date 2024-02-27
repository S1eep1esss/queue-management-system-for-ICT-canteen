import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class TestCanteen {

	
	CanteenICT canteen;
	
	@BeforeEach
	void initailize() {
		canteen = new CanteenICT("ICT-Kanteen");
		
	}
	
	@Test
	@Order(1)
	void testDefaultCustomer() {
		
		Customer c = new Customer(canteen);
		assertEquals(1, c.getCustomerID());
		assertEquals(Customer.CustomerType.DEFAULT, c.getCustomerType());
		assertEquals(Customer.Payment.DEFAULT, c.payment);
		assertEquals(Arrays.asList(FoodStall.Menu.NOODLES, FoodStall.Menu.DESSERT, 
				FoodStall.Menu.MEAT, FoodStall.Menu.SALAD, FoodStall.Menu.BEVERAGE), c.getRequiredFood());
		
	}
	
	@Test
	@Order(2)
	void testCustomerMobilePayment() {
		
		Customer c = new Customer(canteen, Customer.Payment.MOBILE);
		assertEquals(2, c.getCustomerID());
		assertEquals(Customer.CustomerType.DEFAULT, c.getCustomerType());
		assertEquals(Customer.Payment.MOBILE, c.payment);
		assertEquals(Arrays.asList(FoodStall.Menu.NOODLES, FoodStall.Menu.DESSERT, 
				FoodStall.Menu.MEAT, FoodStall.Menu.SALAD, FoodStall.Menu.BEVERAGE), c.getRequiredFood());
		
	}
	
	@Test
	@Order(3)
	void testFoodStallBasic() {
		FoodStall f = new FoodStall("ShopA", canteen, 
				Arrays.asList(FoodStall.Menu.DESSERT, FoodStall.Menu.NOODLES));
		assertEquals("ShopA", f.getName()); 
		assertEquals(Arrays.asList(FoodStall.Menu.DESSERT, FoodStall.Menu.NOODLES), f.getMenu()); 
		assertNotEquals(null, f.getCustomerQueue(), "Customer queue must not be null");
		assertEquals(0, f.getCustomerQueue().size(), "Customer queue is empty");
		
	}
	
	
	@Test
	@Order(4)
	void testFoodStallTakeOrderOne() {
		FoodStall f = new FoodStall("ShopA", canteen, 
				Arrays.asList(FoodStall.Menu.DESSERT, FoodStall.Menu.NOODLES));
		
		// cannot cook
		assertEquals(-1, f.takeOrder(Arrays.asList(FoodStall.Menu.MEAT)), "Cannot cook the order, must returns -1");
		
		// can cook
		assertEquals(2, f.takeOrder(Arrays.asList(FoodStall.Menu.NOODLES)), "Can cook the order, must return cooking time");
		// orderTime = 0, cookingTime = 2
		
		assertFalse(f.isWaitingForOrder());	
		assertTrue(f.isCooking());
		assertFalse(f.isReadyToServe());
		
		// cannot take payment, because of the food is not ready to serve
		assertFalse(f.takePayment(Customer.Payment.CASH));
		assertFalse(f.isPaid());
		
		// order while cooking, cannot take an order
		assertEquals(-1, f.takeOrder(Arrays.asList(FoodStall.Menu.NOODLES)));
	}
	
	@Test
	@Order(5)
	void testFoodStallTakeOrderBatch() {
		FoodStall f = new FoodStall("ShopA", canteen, 
				Arrays.asList(FoodStall.Menu.DESSERT, FoodStall.Menu.NOODLES));
		
		// cook in batch
		assertEquals(5, f.takeOrder(Arrays.asList(FoodStall.Menu.NOODLES, FoodStall.Menu.DESSERT, FoodStall.Menu.NOODLES)));
		assertFalse(f.isWaitingForOrder());	
		assertTrue(f.isCooking());
		assertFalse(f.isReadyToServe());
		assertFalse(f.isPaid());
		
		// cannot take payment, because of the food is not ready to serve
		assertFalse(f.takePayment(Customer.Payment.CASH));
		assertFalse(f.isPaid());
	}
	
	@Test
	@Order(6)
	void testValidationPass() {
		String customerConfig = "DDDDDDDDDD";
		String[][] foodStallConfig = {
				{"ShopA", "NDMSB"}
				,{"ShopB", "NDMSB"}
				};
		CanteenICT canteen = new CanteenICT("GoodSetting");
		canteen.setCustomers(customerConfig, null);
		canteen.setFoodStalls(foodStallConfig);
		canteen.setTables(2);
		
		// passing all canteen's criteria
		assertTrue(canteen.validateCanteen(), 
				"Allright! Good to go.");
	}
	
	@Test
	@Order(7)
	void testValidationExeedBudget() {
		String customerConfig = "DDDDDDDDDD";
		String[][] foodStallConfig = {
				 {"ShopA", "NDMSB"}
				,{"ShopB", "NDMSB"}
				,{"ShopC", "B"}
				,{"ShopD", "NDMSB"}
				,{"ShopE", "NDMSB"}
				,{"ShopF", "BMS"}
				};
		CanteenICT canteen = new CanteenICT("TooExpensive");
		canteen.setCustomers(customerConfig, null);
		canteen.setFoodStalls(foodStallConfig);
		canteen.setTables(2);
		
		// exceed the maximum budget
		assertEquals(80500, canteen.getInstallCost());
		assertFalse(canteen.validateCanteen(), 
				"Installation cost: 80500 baht, while maximum budget is " + CanteenICT.MAX_BUDGET + " :(");
	}
	
	@Test
	@Order(8)
	void testValidationRequiredFood() {
		String customerConfig = "DDDDDDDDDD";
		String[][] foodStallConfig = {
				{"ShopA", "DMSB"}
				,{"ShopB", "NDMB"}
				,{"ShopC", "NS"}
				};
		CanteenICT canteen = new CanteenICT("FoodIsLimited");
		canteen.setCustomers(customerConfig, null);
		canteen.setFoodStalls(foodStallConfig);
		canteen.setTables(2);
		
		// required food is not satisfied
		assertFalse(canteen.validateCanteen(), 
				"There must be at least one food stall that sell all the dishes requried by each customer.");
	}
	
	@Test
	@Order(9)
	void testValidationNoTable() {
		String customerConfig = "DDDDDDDDDD";
		String[][] foodStallConfig = {
				{"ShopA", "NDMSB"}
				,{"ShopB", "NDMSB"}
				};
		CanteenICT canteen = new CanteenICT("NoTable");
		canteen.setCustomers(customerConfig, null);
		canteen.setFoodStalls(foodStallConfig);
		
		// required food is not satisfied
		assertFalse(canteen.validateCanteen(), 
				"Have you added some tables to your canteen where customers can sit and eat their food?");
	}
}
