/*
 * StoreFrontUI
 * 
 * The GUI of the store
 * 
 * Created by Mason Bruce
 */

package campusCard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;

public class StoreFrontUI extends JFrame {
	/* Private data members */
	private VendingMachine machine;
	private JLabel currentTotal;
	private ArrayList<StoreItem> cart = new ArrayList<StoreItem>();
	private DecimalFormat formatter;
	private JScrollPane scrollPanel;
	
	/*
	 * ONLY CONSTRUCTOR -- NO DEFAULT 
	 */
	public StoreFrontUI(String storeName, VendingMachine machine) {
		super(storeName);
		this.machine = machine;
		formatter = new DecimalFormat("0.00");
		
		this.setLayout(new BorderLayout());
		this.getContentPane().add(this.foodPanel(), BorderLayout.CENTER);
		this.getContentPane().add(this.purchasePanel(), BorderLayout.EAST);
		
		this.setSize(600,500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	/*
	 * Creates the grid of food items degined to resemble a vending machine
	 */
	private JPanel foodPanel() {
		JPanel panel = new JPanel();
		
	    panel.setLayout(new GridLayout(0, 3));

	    for(StoreItem item : this.machine.getItems()) {
	    		JButton button = new JButton(convertToMultiline("<b>"+item.getName() + "</b>\n$"+formatter.format(item.getPrice())+"\n"+item.getCalories()+"cal", true));
	    		ImageIcon image = item.getImage();
	    		Image scaledImage = ProfileUI.getScaledImage(image.getImage(), 50, 50);
	    		image.setImage(scaledImage);
	    		button.setIcon(image);
	    		button.setFocusPainted(false);
	    		button.addActionListener(new ActionListener() {
	    			public void actionPerformed(ActionEvent e) { 
	    			    updateCart(item);
	    			}
	    		});
	    		panel.add(button);
	    }
	    
	    return panel;  
	}
	
	/*
	 * Creates a sidebar with title, dispaly screen of all the items selected, and buttons with options to
	 * 	- purchase/switch user
	 * 	- display Expense/Dietary profile
	 */
	private JPanel purchasePanel() {
		JPanel panel = new JPanel();
		JPanel buttonPanel = new JPanel();
		JPanel marquee = new JPanel();
		
		JLabel titleLabel = new JLabel("<html><font style='font-size: 20pt;'><center>Welcome to the <br />CampusSmartCafe!<br /></center></font></html>");
		titleLabel.setPreferredSize(new Dimension(200, 100));
		marquee.add(titleLabel);
		
		panel.setLayout(new BorderLayout());

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.PAGE_AXIS));
		
		currentTotal = new JLabel("");
		currentTotal.setVerticalAlignment(SwingConstants.BOTTOM);
		currentTotal.setForeground(Color.WHITE);
		currentTotal.setBackground(Color.BLACK);
		currentTotal.setOpaque(true);
		
		scrollPanel = new JScrollPane(currentTotal, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setPreferredSize(new Dimension(200, 100));
		
		JButton purchaseButton = new JButton("Buy");
		JButton viewPurchaseStats = new JButton("Expense Profile");
		JButton viewDietaryStats = new JButton("Nutritional Profile");
		JButton clearButton = new JButton("Clear");
		clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		clearButton.setFocusPainted(false);
		purchaseButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		purchaseButton.setFocusPainted(false);
		viewPurchaseStats.setAlignmentX(Component.CENTER_ALIGNMENT);
		viewPurchaseStats.setFocusPainted(false);
		viewDietaryStats.setAlignmentX(Component.CENTER_ALIGNMENT);
		viewDietaryStats.setFocusPainted(false);
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			    clearOrder();
			}
		});
		purchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
			    buy(cart.toArray());
			}
		}); 
		
		viewPurchaseStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				viewPurchaseStats();
			}
		}); 

		viewDietaryStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				viewDietStats();
			}
		}); 
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.add(purchaseButton, BorderLayout.CENTER);
		topPanel.add(clearButton, BorderLayout.EAST);
		buttonPanel.add(topPanel);
		buttonPanel.add(viewPurchaseStats);
		buttonPanel.add(viewDietaryStats);
		panel.add(marquee, BorderLayout.NORTH);
		panel.add(scrollPanel, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		return panel;
	}
	
	/*
	 * Called when purchase is pressed
	 * Opens a confirmation UI where the user can switch accounts
	 */
	private void buy(Object [] items) {
		if (items.length == 0)
			return;
		
		StoreItem [] storeItems = new StoreItem[items.length];
		for(int i = 0; i < items.length; i++) {
			storeItems[i] = (StoreItem)items[i];
		}
		
		this.machine.setItems(storeItems);
		
		new PurchaseUI(machine, this);
	}
	
	/*
	 * Open the Nutritional profile UI
	 */
	private void viewDietStats() {
		new ProfileUI(true, machine.getActivatedCard());
		
	}

	/*
	 * Open the Expense profile UI
	 */
	private void viewPurchaseStats() {
		new ProfileUI(false, machine.getActivatedCard());
	}
	
	/*
	 * Clear the cart and refresh the UI
	 */
	public void clearOrder() {
		cart.clear();
		currentTotal.setText("");
	}

	/*
	 * Print message to order screen
	 */
	public void printMessage(String message) {
		currentTotal.setText(currentTotal.getText() + message);
	}
	
	/*
	 * Update the current cart screen in the UI
	 */
	private void updateCart(StoreItem item) {
		cart.add(item);
		int totalCals = 0;
		Double totalPrice = 0.0;
		String allItems = "";
		for(StoreItem storeItem : cart) {
			totalCals += storeItem.getCalories();
			totalPrice += storeItem.getPrice();
			allItems += storeItem.getName() + ": $" +  formatter.format(storeItem.getPrice()) + "| "+storeItem.getCalories() + "cal\n";
		}
		currentTotal.setText(convertToMultiline(allItems 
		+ "----------- \n" + "Total: $" + formatter.format(totalPrice) + " | "+totalCals + "cal", false));
		
		SwingUtilities.invokeLater(new Runnable() { // scroll to the bottom after updating the label
		    public void run() {
		    		JScrollBar vertical = scrollPanel.getVerticalScrollBar();
				vertical.setValue( vertical.getMaximum());
		    }
		  });
		
		
	}
	
	public static String convertToMultiline(String orig, Boolean center) {
	    return "<html>" + (center ? "<center>":"") + orig.replaceAll("\n", "<br />") + (center ? "</center>":"") + "</html>";
	}
}
