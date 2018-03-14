package campusCard;

import java.util.ArrayList;
import java.util.List;

public class LoginManager {
	
	private DataManager dm;
	private List<CampusCard> users;
	
	public LoginManager(DataManager datamanager) {
		this.dm = datamanager;
		dm.readFromUsers("./src/resources/data/users.txt");
		users = dm.getUsersList();
	}
	
	public boolean authenticate(int cardID, String password) {
		users = dm.getUsersList();
		for (CampusCard entry : users) {
			if (entry.getCardNumber() == cardID && entry.getPassword().equals(password))
				return true;
		}
		return false;
	}
	
	public CampusCard activateUser(String name, String phoneNumber, String password, double spendingLimit, int calorieLimit) {
		CampusCard newUser = new CampusCard(name, phoneNumber, users.size() + 1, password, spendingLimit, calorieLimit);
		users.add(newUser);
		dm.setUsers(users);
		dm.writeToUsers("./src/resources/data/users.txt");
		return newUser;
	}
	
}
