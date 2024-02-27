import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class TestSimAFoodStall {
	
	@Test
	@Order(1)
	void testOneCustomer() {
		String customerConfig = "D";
		String[][] foodStallConfig = {
				{"Bamboo Shop", "NDMSB"}
				};
		CanteenICT canteen = new CanteenICT("sim1");
		canteen.setCustomers(customerConfig, null);
		canteen.setFoodStalls(foodStallConfig);
		canteen.setTables(1);
		
		/*
		 * <timer> <# customers waiting to enter> <# customers waiting in food stalls’ queues> 
		 *	<# customers waiting to be seated> <# of customers sitting at tables> 
		 *	<# done customers>
		 */

		int[][] expected = {
				{0,1,0,0,0,0},
				{1,0,1,0,0,0},
				{2,0,1,0,0,0},
				{3,0,1,0,0,0},
				{4,0,1,0,0,0},
				{5,0,1,0,0,0},
				{6,0,1,0,0,0},
				{7,0,1,0,0,0},
				{8,0,1,0,0,0},
				{9,0,1,0,0,0},
				{10,0,1,0,0,0},
				{11,0,1,0,0,0},
				{12,0,1,0,0,0},
				{13,0,1,0,0,0},
				{14,0,1,0,0,0},
				{15,0,1,0,0,0},
				{16,0,0,1,0,0},
				{17,0,0,0,1,0},
				{18,0,0,0,1,0},
				{19,0,0,0,1,0},
				{20,0,0,0,1,0},
				{21,0,0,0,1,0},
				{22,0,0,0,1,0},
				{23,0,0,0,1,0},
				{24,0,0,0,1,0},
				{25,0,0,0,1,0},
				{26,0,0,0,1,0},
				{27,0,0,0,1,0},
				{28,0,0,0,1,0},
				{29,0,0,0,1,0},
				{30,0,0,0,1,0},
				{31,0,0,0,1,0},
				{32,0,0,0,1,0},
				{33,0,0,0,1,0},
				{34,0,0,0,1,0},
				{35,0,0,0,1,0},
				{36,0,0,0,1,0},
				{37,0,0,0,1,0},
				{38,0,0,0,1,0},
				{39,0,0,0,1,0},
				{40,0,0,0,1,0},
				{41,0,0,0,1,0},
				{42,0,0,0,1,0},
				{43,0,0,0,1,0},
				{44,0,0,0,1,0},
				{45,0,0,0,1,0},
				{46,0,0,0,0,1},

		};

		// start simulation
		canteen.simulate();
		List<Integer[]> log = canteen.getLogState();
		
		// Testing value in each time step
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i][1], log.get(i)[1], 
					"T="+ i + ", waiting to enter queue");
			assertEquals(expected[i][2], log.get(i)[2], 
					"T="+ i + ", customer queue");
			assertEquals(expected[i][3], log.get(i)[3],
					"T="+ i + ", waiting to seat queue");
			assertEquals(expected[i][4], log.get(i)[4],
					"T="+ i + ", seated customer");
			assertEquals(expected[i][5], log.get(i)[5],
					"T="+ i + ", done queue");
		}

		assertTrue(canteen.isFinished(), "must be finished");
	}
	
	@Test
	@Order(2)
	void testOneCustomerMobilePayment() {
		String[][] foodStallConfig = {
				{"Bamboo Shop", "NDMSB"}
				};
		CanteenICT canteen = new CanteenICT("sim2");
		canteen.setCustomers("D", "M");
		canteen.setFoodStalls(foodStallConfig);
		canteen.setTables(1);
		
		/*
		 * <timer> <# customers waiting to enter> <# customers waiting in food stalls’ queues> 
		 *	<# customers waiting to be seated> <# of customers sitting at tables> 
		 *	<# done customers>
		 */

		int[][] expected = {
				{0,1,0,0,0,0},
				{1,0,1,0,0,0},
				{2,0,1,0,0,0},
				{3,0,1,0,0,0},
				{4,0,1,0,0,0},
				{5,0,1,0,0,0},
				{6,0,1,0,0,0},
				{7,0,1,0,0,0},
				{8,0,1,0,0,0},
				{9,0,1,0,0,0},
				{10,0,1,0,0,0},
				{11,0,1,0,0,0},
				{12,0,1,0,0,0},
				{13,0,1,0,0,0},
				{14,0,0,1,0,0},
				{15,0,0,0,1,0},
				{16,0,0,0,1,0},
				{17,0,0,0,1,0},
				{18,0,0,0,1,0},
				{19,0,0,0,1,0},
				{20,0,0,0,1,0},
				{21,0,0,0,1,0},
				{22,0,0,0,1,0},
				{23,0,0,0,1,0},
				{24,0,0,0,1,0},
				{25,0,0,0,1,0},
				{26,0,0,0,1,0},
				{27,0,0,0,1,0},
				{28,0,0,0,1,0},
				{29,0,0,0,1,0},
				{30,0,0,0,1,0},
				{31,0,0,0,1,0},
				{32,0,0,0,1,0},
				{33,0,0,0,1,0},
				{34,0,0,0,1,0},
				{35,0,0,0,1,0},
				{36,0,0,0,1,0},
				{37,0,0,0,1,0},
				{38,0,0,0,1,0},
				{39,0,0,0,1,0},
				{40,0,0,0,1,0},
				{41,0,0,0,1,0},
				{42,0,0,0,1,0},
				{43,0,0,0,1,0},
				{44,0,0,0,0,1},

		};

		// start simulation
		canteen.simulate();
		List<Integer[]> log = canteen.getLogState();
		
		// Testing value in each time step
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i][1], log.get(i)[1], 
					"T="+ i + ", waiting to enter queue");
			assertEquals(expected[i][2], log.get(i)[2], 
					"T="+ i + ", customer queue");
			assertEquals(expected[i][3], log.get(i)[3],
					"T="+ i + ", waiting to seat queue");
			assertEquals(expected[i][4], log.get(i)[4],
					"T="+ i + ", seated customer");
			assertEquals(expected[i][5], log.get(i)[5],
					"T="+ i + ", done queue");
		}

		assertTrue(canteen.isFinished(), "must be finished");
	}
	
	@Test
	@Order(3)
	void testOneFoodStallOneTable() {
		String customerConfig = "DDDDDD";
		String[][] foodStallConfig = {
				{"Bamboo Shop", "NDMSB"}
				};
		CanteenICT canteen = new CanteenICT("sim3");
		canteen.setCustomers(customerConfig, null);
		canteen.setFoodStalls(foodStallConfig);
		canteen.setTables(1);
		
		
		/*
		 * <timer> <# customers waiting to enter> <# customers waiting in food stalls’ queues> 
		 *	<# customers waiting to be seated> <# of customers sitting at tables> 
		 *	<# done customers>
		 */
		

		int[][] expected = {
				{0,6,0,0,0,0},
				{1,5,1,0,0,0},
				{2,4,2,0,0,0},
				{3,3,3,0,0,0},
				{4,2,4,0,0,0},
				{5,1,5,0,0,0},
				{6,1,5,0,0,0},
				{7,1,5,0,0,0},
				{8,1,5,0,0,0},
				{9,1,5,0,0,0},
				{10,1,5,0,0,0},
				{11,1,5,0,0,0},
				{12,1,5,0,0,0},
				{13,1,5,0,0,0},
				{14,1,5,0,0,0},
				{15,1,5,0,0,0},
				{16,1,4,1,0,0},
				{17,0,5,0,1,0},
				{18,0,5,0,1,0},
				{19,0,5,0,1,0},
				{20,0,5,0,1,0},
				{21,0,5,0,1,0},
				{22,0,5,0,1,0},
				{23,0,5,0,1,0},
				{24,0,5,0,1,0},
				{25,0,5,0,1,0},
				{26,0,5,0,1,0},
				{27,0,5,0,1,0},
				{28,0,5,0,1,0},
				{29,0,5,0,1,0},
				{30,0,5,0,1,0},
				{31,0,4,1,1,0},
				{32,0,4,0,2,0},
				{33,0,4,0,2,0},
				{34,0,4,0,2,0},
				{35,0,4,0,2,0},
				{36,0,4,0,2,0},
				{37,0,4,0,2,0},
				{38,0,4,0,2,0},
				{39,0,4,0,2,0},
				{40,0,4,0,2,0},
				{41,0,4,0,2,0},
				{42,0,4,0,2,0},
				{43,0,4,0,2,0},
				{44,0,4,0,2,0},
				{45,0,4,0,2,0},
				{46,0,3,1,1,1},
				{47,0,3,0,2,1},
				{48,0,3,0,2,1},
				{49,0,3,0,2,1},
				{50,0,3,0,2,1},
				{51,0,3,0,2,1},
				{52,0,3,0,2,1},
				{53,0,3,0,2,1},
				{54,0,3,0,2,1},
				{55,0,3,0,2,1},
				{56,0,3,0,2,1},
				{57,0,3,0,2,1},
				{58,0,3,0,2,1},
				{59,0,3,0,2,1},
				{60,0,3,0,2,1},
				{61,0,2,1,1,2},
				{62,0,2,0,2,2},
				{63,0,2,0,2,2},
				{64,0,2,0,2,2},
				{65,0,2,0,2,2},
				{66,0,2,0,2,2},
				{67,0,2,0,2,2},
				{68,0,2,0,2,2},
				{69,0,2,0,2,2},
				{70,0,2,0,2,2},
				{71,0,2,0,2,2},
				{72,0,2,0,2,2},
				{73,0,2,0,2,2},
				{74,0,2,0,2,2},
				{75,0,2,0,2,2},
				{76,0,1,1,1,3},
				{77,0,1,0,2,3},
				{78,0,1,0,2,3},
				{79,0,1,0,2,3},
				{80,0,1,0,2,3},
				{81,0,1,0,2,3},
				{82,0,1,0,2,3},
				{83,0,1,0,2,3},
				{84,0,1,0,2,3},
				{85,0,1,0,2,3},
				{86,0,1,0,2,3},
				{87,0,1,0,2,3},
				{88,0,1,0,2,3},
				{89,0,1,0,2,3},
				{90,0,1,0,2,3},
				{91,0,0,1,1,4},
				{92,0,0,0,2,4},
				{93,0,0,0,2,4},
				{94,0,0,0,2,4},
				{95,0,0,0,2,4},
				{96,0,0,0,2,4},
				{97,0,0,0,2,4},
				{98,0,0,0,2,4},
				{99,0,0,0,2,4},
				{100,0,0,0,2,4},
				{101,0,0,0,2,4},
				{102,0,0,0,2,4},
				{103,0,0,0,2,4},
				{104,0,0,0,2,4},
				{105,0,0,0,2,4},
				{106,0,0,0,1,5},
				{107,0,0,0,1,5},
				{108,0,0,0,1,5},
				{109,0,0,0,1,5},
				{110,0,0,0,1,5},
				{111,0,0,0,1,5},
				{112,0,0,0,1,5},
				{113,0,0,0,1,5},
				{114,0,0,0,1,5},
				{115,0,0,0,1,5},
				{116,0,0,0,1,5},
				{117,0,0,0,1,5},
				{118,0,0,0,1,5},
				{119,0,0,0,1,5},
				{120,0,0,0,1,5},
				{121,0,0,0,0,6},

		};
		
		// start simulation
		canteen.simulate();
		List<Integer[]> log = canteen.getLogState();
		
		// Testing value in each time step
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i][1], log.get(i)[1], 
					"T="+ i + ", waiting to enter queue");
			assertEquals(expected[i][2], log.get(i)[2], 
					"T="+ i + ", customer queue");
			assertEquals(expected[i][3], log.get(i)[3],
					"T="+ i + ", waiting to seat queue");
			assertEquals(expected[i][4], log.get(i)[4],
					"T="+ i + ", seated customer");
			assertEquals(expected[i][5], log.get(i)[5],
					"T="+ i + ", done queue");
		}

		assertTrue(canteen.isFinished(), "must be finished");
		
	}
	
	@Test
	@Order(4)
	void testOneFoodStallOneTableMixPayment() {
		String customerConfig = "DDDDDD";
		String customerPayment = "DCMDCM";
		String[][] foodStallConfig = {
				{"Bamboo Shop", "NDMSB"}
				};
		CanteenICT canteen = new CanteenICT("sim4");
		canteen.setCustomers(customerConfig, customerPayment);
		canteen.setFoodStalls(foodStallConfig);
		canteen.setTables(1);
		
		
		/*
		 * <timer> <# customers waiting to enter> <# customers waiting in food stalls’ queues> 
		 *	<# customers waiting to be seated> <# of customers sitting at tables> 
		 *	<# done customers>
		 */
		

		int[][] expected = {
				{0,6,0,0,0,0},
				{1,5,1,0,0,0},
				{2,4,2,0,0,0},
				{3,3,3,0,0,0},
				{4,2,4,0,0,0},
				{5,1,5,0,0,0},
				{6,1,5,0,0,0},
				{7,1,5,0,0,0},
				{8,1,5,0,0,0},
				{9,1,5,0,0,0},
				{10,1,5,0,0,0},
				{11,1,5,0,0,0},
				{12,1,5,0,0,0},
				{13,1,5,0,0,0},
				{14,1,5,0,0,0},
				{15,1,5,0,0,0},
				{16,1,4,1,0,0},
				{17,0,5,0,1,0},
				{18,0,5,0,1,0},
				{19,0,5,0,1,0},
				{20,0,5,0,1,0},
				{21,0,5,0,1,0},
				{22,0,5,0,1,0},
				{23,0,5,0,1,0},
				{24,0,5,0,1,0},
				{25,0,5,0,1,0},
				{26,0,5,0,1,0},
				{27,0,5,0,1,0},
				{28,0,5,0,1,0},
				{29,0,5,0,1,0},
				{30,0,4,1,1,0},
				{31,0,4,0,2,0},
				{32,0,4,0,2,0},
				{33,0,4,0,2,0},
				{34,0,4,0,2,0},
				{35,0,4,0,2,0},
				{36,0,4,0,2,0},
				{37,0,4,0,2,0},
				{38,0,4,0,2,0},
				{39,0,4,0,2,0},
				{40,0,4,0,2,0},
				{41,0,4,0,2,0},
				{42,0,4,0,2,0},
				{43,0,3,1,2,0},
				{44,0,3,0,3,0},
				{45,0,3,0,3,0},
				{46,0,3,0,2,1},
				{47,0,3,0,2,1},
				{48,0,3,0,2,1},
				{49,0,3,0,2,1},
				{50,0,3,0,2,1},
				{51,0,3,0,2,1},
				{52,0,3,0,2,1},
				{53,0,3,0,2,1},
				{54,0,3,0,2,1},
				{55,0,3,0,2,1},
				{56,0,3,0,2,1},
				{57,0,3,0,2,1},
				{58,0,2,1,2,1},
				{59,0,2,0,3,1},
				{60,0,2,0,2,2},
				{61,0,2,0,2,2},
				{62,0,2,0,2,2},
				{63,0,2,0,2,2},
				{64,0,2,0,2,2},
				{65,0,2,0,2,2},
				{66,0,2,0,2,2},
				{67,0,2,0,2,2},
				{68,0,2,0,2,2},
				{69,0,2,0,2,2},
				{70,0,2,0,2,2},
				{71,0,2,0,2,2},
				{72,0,1,1,2,2},
				{73,0,1,0,2,3},
				{74,0,1,0,2,3},
				{75,0,1,0,2,3},
				{76,0,1,0,2,3},
				{77,0,1,0,2,3},
				{78,0,1,0,2,3},
				{79,0,1,0,2,3},
				{80,0,1,0,2,3},
				{81,0,1,0,2,3},
				{82,0,1,0,2,3},
				{83,0,1,0,2,3},
				{84,0,1,0,2,3},
				{85,0,0,1,2,3},
				{86,0,0,0,3,3},
				{87,0,0,0,3,3},
				{88,0,0,0,2,4},
				{89,0,0,0,2,4},
				{90,0,0,0,2,4},
				{91,0,0,0,2,4},
				{92,0,0,0,2,4},
				{93,0,0,0,2,4},
				{94,0,0,0,2,4},
				{95,0,0,0,2,4},
				{96,0,0,0,2,4},
				{97,0,0,0,2,4},
				{98,0,0,0,2,4},
				{99,0,0,0,2,4},
				{100,0,0,0,2,4},
				{101,0,0,0,2,4},
				{102,0,0,0,1,5},
				{103,0,0,0,1,5},
				{104,0,0,0,1,5},
				{105,0,0,0,1,5},
				{106,0,0,0,1,5},
				{107,0,0,0,1,5},
				{108,0,0,0,1,5},
				{109,0,0,0,1,5},
				{110,0,0,0,1,5},
				{111,0,0,0,1,5},
				{112,0,0,0,1,5},
				{113,0,0,0,1,5},
				{114,0,0,0,1,5},
				{115,0,0,0,0,6},


		};
		
		
		
		// start simulation
		canteen.simulate();
		List<Integer[]> log = canteen.getLogState();
		
		// Testing value in each time step
		for(int i = 0; i < expected.length; i++) {
			assertEquals(expected[i][1], log.get(i)[1], 
					"T="+ i + ", waiting to enter queue");
			assertEquals(expected[i][2], log.get(i)[2], 
					"T="+ i + ", customer queue");
			assertEquals(expected[i][3], log.get(i)[3],
					"T="+ i + ", waiting to seat queue");
			assertEquals(expected[i][4], log.get(i)[4],
					"T="+ i + ", seated customer");
			assertEquals(expected[i][5], log.get(i)[5],
					"T="+ i + ", done queue");
		}

		assertTrue(canteen.isFinished(), "must be finished");

	}
}
