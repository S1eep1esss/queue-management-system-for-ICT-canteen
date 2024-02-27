// File: Customer.java
// Description: Action of customer by state
// Project: 1
//
// ID: 6588034
// Name: Jakkaphat Jumratboonsom
// Section: 1
//
// On my honor, Jakkaphat Jumratboonsom, this project assignment is my own work
// and I have not provided this code to any other students.


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Customer {
	
	//*********************** DO NOT MODIFY ****************************//
	public static enum CustomerType{DEFAULT, STUDENT, PROFESSOR, ATHLETE, ICTSTUDENT};	//Different types of customers 
	private static int customerRunningNumber = 1;	//static variable for assigning a unique ID to a customer
	private CanteenICT canteen = null;	//reference to the CanteenICT object
	private int customerID = -1;		//this customer's ID
	protected CustomerType customerType = CustomerType.DEFAULT;	//the type of this customer, initialized with a DEFAULT customer.
	protected List<FoodStall.Menu> requiredDishes = new ArrayList<FoodStall.Menu> ();	//List of required dishes
	
	public static enum Payment{DEFAULT, CASH, MOBILE};
	public static final int[] PAYMENT_TIME = {3, 2, 1};
	protected Payment payment = Payment.DEFAULT;
	
	protected int state = 0; // 0 wait-to-enter, 1 wait-to-order, 2 ordering, 
							 // 3 making payment, 4 wait-to-seat, 5 siting, 
							 // 6 eating, 7 done
	//*****************************************************************//
	
	
	/**
	 * Constructor. Initialize canteen reference, default customer type, and default payment method. 
	 * 				Initialize other values as needed
	 * @param _canteen
	 */
	public Customer(CanteenICT _canteen)
	{
		this.canteen = _canteen;
		this.customerType = CustomerType.DEFAULT;
		this.payment = getPayment();
		this.customerID = customerRunningNumber;
		customerRunningNumber++;
		this.requiredDishes.add(FoodStall.Menu.NOODLES);
		this.requiredDishes.add(FoodStall.Menu.DESSERT);
		this.requiredDishes.add(FoodStall.Menu.MEAT);
		this.requiredDishes.add(FoodStall.Menu.SALAD);
		this.requiredDishes.add(FoodStall.Menu.BEVERAGE);
		
		//*****************************************************
	}
	
	/**
	 * Constructor. Initialize canteen reference, default customer type, and specific payment method.
	 * 				Initialize other values as needed 
	 * @param _canteen
	 * @param payment
	 */
			
	public Customer(CanteenICT _canteen, Payment payment)	
	{
		this.canteen = _canteen;
		this.payment = payment;
		this.customerID+=customerRunningNumber;
		this.customerType = CustomerType.DEFAULT;
		this.payment = getPayment();
		this.customerID = customerRunningNumber;
		customerRunningNumber++;
		this.requiredDishes.add(FoodStall.Menu.NOODLES);
		this.requiredDishes.add(FoodStall.Menu.DESSERT);
		this.requiredDishes.add(FoodStall.Menu.MEAT);
		this.requiredDishes.add(FoodStall.Menu.SALAD);
		this.requiredDishes.add(FoodStall.Menu.BEVERAGE);
		
		//*****************************************************
	}
	
	
	
	/**
	 * Depends on the current state of the customer, different action will be taken
	 * @return true if the customer has to move to the next queue, otherwise return false
	 */
	public boolean takeAction(){	
		FoodStall cl = canteen.getFS().get(0);
		for(FoodStall i : canteen.getFS()) {
			if(i.getCustomerQueue().size()<cl.getCustomerQueue().size() && i.getMenu().size() == 5) {
				cl = i;
			}
		}
		
		if(this.state == 0) {
			if(canteen.getWaitToEnterQueue().size() > 0 && this.customerID == canteen.getWaitToEnterQueue().get(0).getCustomerID()) {
				if(cl.getCustomerQueue().size()<FoodStall.MAX_QUEUE) {
					this.state++;
					jot("@" + getCode() + "-" + this.state + " queues up at " + cl.getName() + ", and waiting to order.");
					cl.getCustomerQueue().add(this);
					canteen.getCloneQ().add(this);
					Customer.enterFS = true;
					return true;
				}
			}
		}
		
		else if(this.state == 1) {
			for(int i=0;i<canteen.getFS().size();i++)  {
				if(canteen.getFS().get(i).getCustomerQueue().size() != 0) {
					if(canteen.getFS().get(i).getCustomerQueue().get(0).equals(this) && canteen.getFS().get(i).isWaitingForOrder()) {
						canteen.getFS().get(i).takeOrder(this.requiredDishes);
						this.state++;
						jot("@" + getCode() + "-" + this.state + " orders from " + canteen.getFS().get(i).getName() + ", and will need to wait for 9 periods to cook.");
						return false;
					}
				}
			}
		}
		
		else if(this.state == 2) {
			for(int i=0;i<canteen.getFS().size();i++)  {
				if(canteen.getFS().get(i).getCustomerQueue().size() != 0) {
					if(canteen.getFS().get(i).getCustomerQueue().get(0).equals(this) && canteen.getFS().get(i).isReadyToServe()) {
						canteen.getFS().get(i).takePayment(this.payment);
						this.state++;
						jot("@" + getCode() + "-" + this.state + " pays at " + canteen.getFS().get(i).getName() + " using " + this.payment + " payment which requires 3 period(s) to process payment.");
						return false;
					}
				}
			}
		}
		
		else if(this.state == 3) {
			for(int i=0;i<canteen.getFS().size();i++) {
				if(canteen.getFS().get(i).getCustomerQueue().size() != 0) {
					if(canteen.getFS().get(i).getCustomerQueue().get(0).equals(this) && canteen.getFS().get(i).isPaid()) {
						canteen.getFS().get(i).serve();
						canteen.getWaitToSeatQueue().add(this); // add canteen.getFS()
						this.state++;
						jot("@" + getCode() + "-" + this.state + " retrieves food from " + canteen.getFS().get(i).getName() + ", and goes to Waiting-to-Seat Queue.");
						return true;
					}
				}
			}
		}
		
		else if(this.state == 4) {
			if(canteen.getWaitToSeatQueue().size() > 0 && Customer.enterFS == false){
				if(canteen.getWaitToSeatQueue().get(0).equals(this)) {
					int j = 0;
					for(Table i : canteen.getTB()) {
						if(!i.isFull()) {
							i.getSeatedCustomers().add(canteen.getWaitToSeatQueue().get(0));
							canteen.getWaitToSeatQueue().remove(0);
							this.state++;
							jot("@" + getCode() + "-" + this.state + " sits at Table " + (j+1) + ".");
							Customer.enterFS = true;
							return true;
							}
						j++;
						}
					}
					return false;
				}
			}
		
		else if(this.state == 5) {
			if(!isEating()) {
				takeEat();
				this.state++;
				jot("@" + getCode() + "-" + this.state + " eats at the table, and will need 28 periods to eat his/her meal.");
				return true;
			}
		}
		
		else if(this.state == 6) {
			if(AlreadyEat()) {
				for(int i=0;i<canteen.getTB().size();i++) {
					if(canteen.getTB().get(i).getSeatedCustomers().size() != 0) {
						canteen.getDoneQueue().add(this);
						this.state++;
						jot("@" + getCode() + "-" + this.state + " is done eating.");
						return true;
					}	
				}
			}
		}
		return false;
	}
	
	
	//******************************************** YOUR ADDITIONAL CODE HERE (IF ANY) *******************************//
	public static boolean checkOutFS = true;
	public static boolean enterFS;
	protected int startEat = 0;
	protected int Eattime = 0;
	public boolean isEating() {
		return (canteen.getCurrentTime() - this.startEat) < this.Eattime;
	}
	public boolean AlreadyEat() {
		return !this.isEating() && this.startEat > 0;
	}
	public void takeEat() {
		if(this.isEating());
		if(this.AlreadyEat());
		this.startEat = canteen.getCurrentTime();
		this.Eattime = 0;
		for(FoodStall.Menu dish: this.requiredDishes)
		{
			this.Eattime += FoodStall.EAT_TIME[dish.ordinal()];
		}
	}
	

	//***************For hashing, equality checking, and general purposes. DO NOT MODIFY **************************//	
	
	public CustomerType getCustomerType()
	{
		return this.customerType;
	}
	
	public int getCustomerID()
	{
		return this.customerID;
	}
	
	public Payment getPayment()
	{
		return this.payment;
	}
	
	public List<FoodStall.Menu> getRequiredFood()
	{
		return this.requiredDishes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + customerID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (customerID != other.customerID)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerType=" + customerType +", payment="+payment.name()+"]";
	}

	public String getCode()
	{
		return this.customerType.toString().charAt(0)+""+this.customerID;
	}
	
	/**
	 * print something out if VERBOSE is true 
	 * @param str
	 */
	public void jot(String str)
	{
		if(CanteenICT.VERBOSE) System.out.println(str);
		
		if(CanteenICT.WRITELOG) CanteenICT.append(str, canteen.name+"_state.log");
	}


	//*************************************************************************************************//
	
}
