import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;


/**
 * Change Log:
 * 	11 Feb 2023 - Add custom payment methods in testTask1Bonus() method and modify expected array
 * @author MUICT
 *
 */
public class TestBonus {

	@Test
	@Order(1)
	void testTask1Bonus() {
		String customerConfig = "SPA";
		String customerPayment = "DMC";	// Added custom payment method 
		String[][] foodStallConfig = {
				{"Bamboo Shop", "NDMSB"}
				,{"N Only", "N"}
				,{"Dessert Only", "D"}
				,{"M&S&B Only", "MSB"}
				,{"N&D&B Only", "NDB"}
		};
		CanteenICT canteen = new CanteenICT("bonus1");
		canteen.setCustomers(customerConfig, customerPayment);
		canteen.setFoodStalls(foodStallConfig);
		canteen.setTables(1);
		
		/*
		 * <timer> <# customers waiting to enter> <# customers waiting in food stalls’ queues> 
		 *	<# customers waiting to be seated> <# of customers sitting at tables> 
		 *	<# done customers>
		 */

		int[][] expected = {
				{0,3,0,0,0,0},
				{1,2,1,0,0,0},
				{2,1,2,0,0,0},
				{3,0,3,0,0,0},
				{4,0,3,0,0,0},
				{5,0,3,0,0,0},
				{6,0,3,0,0,0},
				{7,0,3,0,0,0},
				{8,0,3,0,0,0},
				{9,0,2,1,0,0},
				{10,0,2,0,1,0},
				{11,0,2,0,1,0},
				{12,0,1,1,1,0},
				{13,0,1,0,2,0},
				{14,0,1,0,2,0},
				{15,0,1,0,2,0},
				{16,0,1,0,2,0},
				{17,0,1,0,2,0},
				{18,0,1,0,2,0},
				{19,0,1,0,1,1},
				{20,0,0,1,1,1},
				{21,0,0,0,2,1},
				{22,0,0,0,2,1},
				{23,0,0,0,2,1},
				{24,0,0,0,2,1},
				{25,0,0,0,2,1},
				{26,0,0,0,2,1},
				{27,0,0,0,2,1},
				{28,0,0,0,2,1},
				{29,0,0,0,2,1},
				{30,0,0,0,2,1},
				{31,0,0,0,2,1},
				{32,0,0,0,2,1},
				{33,0,0,0,2,1},
				{34,0,0,0,2,1},
				{35,0,0,0,2,1},
				{36,0,0,0,2,1},
				{37,0,0,0,2,1},
				{38,0,0,0,2,1},
				{39,0,0,0,1,2},
				{40,0,0,0,1,2},
				{41,0,0,0,1,2},
				{42,0,0,0,1,2},
				{43,0,0,0,1,2},
				{44,0,0,0,1,2},
				{45,0,0,0,1,2},
				{46,0,0,0,1,2},
				{47,0,0,0,1,2},
				{48,0,0,0,1,2},
				{49,0,0,0,1,2},
				{50,0,0,0,1,2},
				{51,0,0,0,1,2},
				{52,0,0,0,1,2},
				{53,0,0,0,1,2},
				{54,0,0,0,1,2},
				{55,0,0,0,1,2},
				{56,0,0,0,1,2},
				{57,0,0,0,1,2},
				{58,0,0,0,1,2},
				{59,0,0,0,0,3},


		};

		// start simulation
		canteen.simulate();
		List<Integer[]> log = canteen.getLogState();

		assertTrue(canteen.isFinished(), "must be finished");
		assertEquals(expected.length, log.size(), "must finish when T=59");
		
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

	}
	
	@Test
	@Order(2)
	void testTask2Bonus() {
		String customerConfig = "AAAAAIIIIII";
		String[][] foodStallConfig = {
				{"Bamboo Shop", "NDMSB"}
				,{"N Only", "N"}
				,{"Dessert Only", "D"}
				,{"M&S&B Only", "MSB"}
				,{"N&D&B Only", "NDB"}
		};
		CanteenICT canteen = new CanteenICT("bonus2");
		canteen.setCustomers(customerConfig, null);
		canteen.setFoodStalls(foodStallConfig);
		canteen.setTables(1);
		
		/*
		 * <timer> <# customers waiting to enter> <# customers waiting in food stalls’ queues> 
		 *	<# customers waiting to be seated> <# of customers sitting at tables> 
		 *	<# done customers>
		 */

		int[][] expected = {
				{0,11,0,0,0,0},
				{1,10,1,0,0,0},
				{2,9,2,0,0,0},
				{3,8,3,0,0,0},
				{4,7,4,0,0,0},
				{5,6,5,0,0,0},
				{6,5,6,0,0,0},
				{7,4,7,0,0,0},
				{8,3,8,0,0,0},
				{9,2,9,0,0,0},
				{10,1,10,0,0,0},
				{11,0,11,0,0,0},
				{12,0,11,0,0,0},
				{13,0,11,0,0,0},
				{14,0,11,0,0,0},
				{15,0,11,0,0,0},
				{16,0,11,0,0,0},
				{17,0,10,1,0,0},
				{18,0,9,1,1,0},
				{19,0,8,1,2,0},
				{20,0,7,1,3,0},
				{21,0,7,0,4,0},
				{22,0,7,0,4,0},
				{23,0,7,0,4,0},
				{24,0,7,0,4,0},
				{25,0,7,0,4,0},
				{26,0,7,0,4,0},
				{27,0,7,0,4,0},
				{28,0,6,1,4,0},
				{29,0,5,2,4,0},
				{30,0,5,2,4,0},
				{31,0,5,2,4,0},
				{32,0,5,2,4,0},
				{33,0,5,2,4,0},
				{34,0,5,2,4,0},
				{35,0,5,2,4,0},
				{36,0,5,2,4,0},
				{37,0,4,3,4,0},
				{38,0,3,4,4,0},
				{39,0,2,5,4,0},
				{40,0,1,6,4,0},
				{41,0,1,6,4,0},
				{42,0,1,6,4,0},
				{43,0,1,6,4,0},
				{44,0,1,6,3,1},
				{45,0,1,5,3,2},
				{46,0,1,4,4,2},
				{47,0,1,4,4,2},
				{48,0,1,4,4,2},
				{49,0,1,4,4,2},
				{50,0,1,4,4,2},
				{51,0,1,4,4,2},
				{52,0,1,4,4,2},
				{53,0,1,4,4,2},
				{54,0,1,4,4,2},
				{55,0,0,5,3,3},
				{56,0,0,4,3,4},
				{57,0,0,3,4,4},
				{58,0,0,3,3,5},
				{59,0,0,2,3,6},
				{60,0,0,1,4,6},
				{61,0,0,1,4,6},
				{62,0,0,1,4,6},
				{63,0,0,1,4,6},
				{64,0,0,1,4,6},
				{65,0,0,1,4,6},
				{66,0,0,1,3,7},
				{67,0,0,0,3,8},
				{68,0,0,0,3,8},
				{69,0,0,0,3,8},
				{70,0,0,0,3,8},
				{71,0,0,0,3,8},
				{72,0,0,0,3,8},
				{73,0,0,0,3,8},
				{74,0,0,0,3,8},
				{75,0,0,0,3,8},
				{76,0,0,0,3,8},
				{77,0,0,0,3,8},
				{78,0,0,0,3,8},
				{79,0,0,0,3,8},
				{80,0,0,0,3,8},
				{81,0,0,0,3,8},
				{82,0,0,0,3,8},
				{83,0,0,0,3,8},
				{84,0,0,0,3,8},
				{85,0,0,0,3,8},
				{86,0,0,0,3,8},
				{87,0,0,0,3,8},
				{88,0,0,0,3,8},
				{89,0,0,0,3,8},
				{90,0,0,0,3,8},
				{91,0,0,0,3,8},
				{92,0,0,0,3,8},
				{93,0,0,0,3,8},
				{94,0,0,0,2,9},
				{95,0,0,0,1,10},
				{96,0,0,0,1,10},
				{97,0,0,0,1,10},
				{98,0,0,0,1,10},
				{99,0,0,0,1,10},
				{100,0,0,0,1,10},
				{101,0,0,0,1,10},
				{102,0,0,0,1,10},
				{103,0,0,0,1,10},
				{104,0,0,0,1,10},
				{105,0,0,0,0,11},

		};

		// start simulation
		canteen.simulate();
		List<Integer[]> log = canteen.getLogState();
		assertTrue(canteen.isFinished(), "must be finished");
		assertEquals(expected.length, log.size(), "must finish when T=105");
		
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

		
	}
}
