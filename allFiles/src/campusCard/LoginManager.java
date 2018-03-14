package campusCard;

import java.util.ArrayList;
import java.util.List;

public class LoginManager {
	
	private DataManager dm;
	private List<CampusCard> users;
	private CampusCard activeUser;
	
	public LoginManager(DataManager datamanager) {
		this.dm = datamanager;
		dm.readFromUsers("resources/data/users.txt");
		users = dm.getUsersList();
	}
	
	public boolean authenticate(int cardID, String password) {
		users = dm.getUsersList();
		for (CampusCard entry : users) {
			if (entry.getCardNumber() == cardID && entry.getPassword().equals(password)) {
				activeUser = entry;
				return true;
			}
		}
		return false;
	}
	
	public CampusCard activateUser(String name, String phoneNumber, String password, double spendingLimit, int calorieLimit) {
		CampusCard newUser = new CampusCard(name, phoneNumber, users.size() + 1, password, spendingLimit, calorieLimit);
		users.add(newUser);
		dm.setUsers(users);
		dm.writeToUsers("resources/data/users.txt");
		activeUser = newUser;
		return newUser;
	}

	public CampusCard getActiveUser() {
		return activeUser;
	}
	
	
}
