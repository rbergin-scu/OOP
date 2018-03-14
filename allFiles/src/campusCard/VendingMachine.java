/*
 * VendingMachine
 * 
 * Handles the data represented by StoreFrontUI
 * 
 * Created by Mason Bruce
 */
package campusCard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class VendingMachine {
	
	/* Private data members */
	private StoreItem [] items;
	private CampusCard activatedCard;
	
	/*
	 * ONLY CONSTRUCTOR -- NO DEFAULT 
	 */
	public VendingMachine(CampusCard activatedCard) {
		
		DataManager manager = new DataManager();
		manager.readFromItems("resources/data/storeData.txt");
		List<StoreItem> list = manager.getItemList();
		
		items =  new StoreItem[list.size()];
		
		Iterator<StoreItem> it = list.iterator();
		
		for (int i = 0; i <  list.size(); i++) {
			items[i] = it.next();
		}
		
		this.activatedCard = activatedCard;
	}
	
	/****** Setters and getters ******/
	public StoreItem [] getItems() {
		return items;
	}
	
	public void setItems(StoreItem[] items) {
		this.items = items;
	}

	public CampusCard getActivatedCard() {
		return activatedCard;
	}

	public void setActivatedCard(CampusCard activatedCard) {
		this.activatedCard = activatedCard;
	}
	
	/************************************/
	
	/*
	 * Get price of all items in the cart
	 */
	public double getCartPrice() {
		double sum = 0.0;
		for( StoreItem item : items ) {
			sum += item.getPrice();
		}
		
		return sum;
	}
	
	/*
	 * Get total calories from the cart
	 */
	public int getCartCals() {
		int sum = 0;
		for( StoreItem item : items ) {
			sum += item.getCalories();
		}
		
		return sum;
	}
	
	
	
	/*
	 * Charge the activated card for each item in the cart
	 */
	public void purchaseItems() {
		for( StoreItem item : items ) {
			activatedCard.addToAmountSpent(item.getPrice());
			activatedCard.addToCaloriesConsumed(item.getCalories());
			DataManager dm = new DataManager();
			dm.readFromUsers("resources/data/users.txt");
			List<CampusCard> oldList = dm.getUsersList();
			List<CampusCard> newList = new ArrayList<CampusCard>();
			
			for (CampusCard card : oldList) {
				if (card.getCardNumber() == activatedCard.getCardNumber()) {
					newList.add(activatedCard);
				} else {
					newList.add(card);
				}
			}
			
			dm.setUsers(newList);
			dm.writeToUsers("resources/data/users.txt");
		}
	}
}
