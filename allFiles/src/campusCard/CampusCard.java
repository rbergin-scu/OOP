package campusCard;

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

	public CampusCard(String name, String phoneNumber, int cardNumber, String password, 
		double spendingLimit, int calorieLimit) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.cardNumber = cardNumber;
		this.password = password;
		this.spendingLimit = spendingLimit;
		this.amountSpent = 0.0;
		this.calorieLimit = calorieLimit;
		this.caloriesConsumed = 0;
	}
	
	////////////////////////////////////////
	// ADDERS
	
	public void addToAmountSpent(double amount) {
		amountSpent += amount;
	}
	
	public void addToCaloriesConsumed(int calories) {
		caloriesConsumed += calories;
	}
	
	
	////////////////////////////////////////
	// GETTERS AND SETTERS
	
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
