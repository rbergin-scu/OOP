
public class CampusCard {
	
	// User Attributes
	private String name;				// User's name
	private String phoneNumber;		// User's contact number
	private int cardNumber;			// Generated card id number
	private String password;			// Password set by user
	private double spendingLimit;	// User set limit to spending
	private double amountSpent;		// Amount user has spent this month
	private int calorieLimit;		// User set limit to calories
	private int caloriesConsumed;	// Calories user has used this month
	
	// TODO maybe this many variables can be broken up into inner classes

	public CampusCard(String name, String phoneNumber, int cardNumber, String password, 
		double spendingLimit, double amountSpent, int calorieLimit, int caloriesConsumed) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.cardNumber = cardNumber;
		this.password = password;
		this.spendingLimit = spendingLimit;
		this.amountSpent = amountSpent;
		this.calorieLimit = calorieLimit;
		this.caloriesConsumed = caloriesConsumed;
	}
	
	// Adding methods
	
	// TODO not sure whose responsibility it should be to check if amount spent would
	// put the user over their limit, but it should probably whatever class is in 
	// charge of making the purchases
	
	public void addToAmountSpent(double amount) {
		amountSpent += amount;
	}
	
	public void addToCaloriesConsumed(int calories) {
		caloriesConsumed += calories;
	}
	
	
	// Getters and Setters
	
	public void setSpendingLimit(double spendingLimit) {
		this.spendingLimit = spendingLimit;
	}

	public void setCalorieLimit(int calorieLimit) {
		this.calorieLimit = calorieLimit;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public String getPassword() {
		return password;
	}

	public double getSpendingLimit() {
		return spendingLimit;
	}

	public double getAmountSpent() {
		return amountSpent;
	}

	public int getCalorieLimit() {
		return calorieLimit;
	}

	public int getCaloriesConsumed() {
		return caloriesConsumed;
	}
	
	
	
}
