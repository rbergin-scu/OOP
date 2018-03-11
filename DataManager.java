import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class DataManager {

	private List<StoreItem> items;
	private List<CampusCard> users;
	
	////////////////////////////////////////
	// DATA MANIPULATION
	
	public List<StoreItem> getItemList() {
		return items;
	}
	
	public List<CampusCard> getUsersList() {
		return users;
	}
	
	public void addUser(CampusCard user) {
		users.add(user);
	}
	
	////////////////////////////////////////
	// FILE I/O
	
	// items data file -> items list
	public void readFromItems(String path) {
		StoreItem nextItem;
	    	BufferedReader reader = null;
	    	try {   
	    		File inFile  = new File(path);  
	    		reader = new BufferedReader(new FileReader(inFile));
	
	        	//... Loop as long as there are input lines.
	        	String line = null;
	        	
	        	try {      	
	        		while ((line=reader.readLine()) != null) {
	        			
	            		// split each line into tokens
	            		String [] fields = line.split(";");
	  		
	  				String name = fields[0].trim();
	  				SnackType type = SnackType.valueOf(fields[1].trim().toUpperCase());
	  				double price = Double.parseDouble(fields[2].trim());
	  				int calories = Integer.parseInt(fields[3].trim());
	  			
	  				nextItem = new StoreItem(name,price,calories,type);
	  				items.add(nextItem);
	    			} 		
	    		}
	    		finally{   		
	        		reader.close(); 
	    		}
	    	}
	    	catch (IOException e) {
	        System.err.println(e);
	        System.exit(1);
	    }
	    catch (NumberFormatException e){
	      	System.out.println("NumberFormatException: ");
	      	System.exit(1);
	    	} 
	}
	
	// users data file -> users list
	public void readFromUsers(String path) {
		CampusCard nextUser;
	    	BufferedReader reader = null;
	    	try {   
	    		File inFile  = new File(path);  
	    		reader = new BufferedReader(new FileReader(inFile));
	
	        	//... Loop as long as there are input lines.
	        	String line = null;
	        	
	        	try {      	
	        		while ((line=reader.readLine()) != null) {
	        			
	            		// split each line into tokens
	            		String [] fields = line.split(";");
	  		
	  				String name = fields[0];
	  				String phone = fields[1].trim();
	  				int cardNumber = Integer.parseInt(fields[2].trim());
	  				String password = fields[3];
	  				double spendingLimit = Double.parseDouble(fields[4].trim());
	  				double amountSpent = Double.parseDouble(fields[5].trim());
	  				int calorieLimit = Integer.parseInt(fields[6].trim());
	  				int caloriesSpent = Integer.parseInt(fields[7].trim()); 
	  			
	  				nextUser = new CampusCard(name,phone,cardNumber,password,
	  						spendingLimit,amountSpent,calorieLimit,caloriesSpent);
	  				users.add(nextUser);
	    			} 		
	    		}
	    		finally{   		
	        		reader.close(); 
	    		}
	    	}
	    	catch (IOException e) {
	        System.err.println(e);
	        System.exit(1);
	    }
	    catch (NumberFormatException e){
	      	System.out.println("NumberFormatException: ");
	      	System.exit(1);
	    	}
	}
	
	// items data file <- items list
	public void writeToItems(String path) {
		String toWrite = "";
		for (StoreItem item : items) {
			toWrite += item.getName() + ";" + 
						item.getType() + ";" +
						item.getPrice() + ";" +
						item.getCalories() +"\n";
		}
		
		try {
			File file = new File(path);
			if (file.exists())
			{
			  //delete if exists
			   file.delete();
			}
			FileWriter out = new FileWriter(file); 
			out.write(toWrite);
			out.close();
		}
		catch (IOException e) {
	        System.err.println(e);
	        System.exit(1);
	    }
	}
	
	// users data file <- users list
	public void writeToUsers(String path) {
		String toWrite = "";
		for (CampusCard user : users) {
			toWrite += user.getName() + ";" + 
						user.getPhoneNumber() + ";" +
						user.getCardNumber() + ";" +
						user.getPassword() + ";" +
						user.getSpendingLimit() + ";" +
						user.getAmountSpent() + ";" +
						user.getCalorieLimit() + ";" + 
						user.getCaloriesConsumed() +"\n";
		}
		
		try {
			File file = new File(path);
			if (file.exists())
			{
			  //delete if exists
			   file.delete();
			}
			FileWriter out = new FileWriter(file); 
			out.write(toWrite);
			out.close();
		}
		catch (IOException e) {
	        System.err.println(e);
	        System.exit(1);
	    }
	}
	
}
