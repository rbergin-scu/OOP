/*
 * PurchaseUI
 * 
 * A UI allowing a user to review there purchase, 
 * it alerts them if they've gone over their limits,
 * allows them to switch cards, and purchase their cart
 * 
 * Created by Mason Bruce
 */

package campusCard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PurchaseUI extends JFrame {
	/* Private data members */
	private VendingMachine machine;
	private JPanel centerPanel;
	private DecimalFormat formatter;
	private JButton doneButton;
	
	/*
	 * ONLY CONSTRUCTOR -- NO DEFAULT 
	 * Initializes the UI
	 */
	public PurchaseUI(VendingMachine machine, StoreFrontUI ui) {
		super("Confirm Purchase");
		this.machine = machine;
		
		formatter = new DecimalFormat("0.00");
		ImageIcon image = new ImageIcon("resources/images/bag.png");
		Image scaledImage = ProfileUI.getScaledImage(image.getImage(), 65, 65);
		image.setImage(scaledImage);
		
		JLabel picLabel = new JLabel(image);
		this.getContentPane().add(picLabel, BorderLayout.WEST);
		picLabel.setPreferredSize(new Dimension(75, 75));
		
		doneButton = new JButton("Confirm");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				machine.purchaseItems();
				ui.clearOrder();
				Double moneyLeft = machine.getActivatedCard().getSpendingLimit() - machine.getActivatedCard().getAmountSpent();
				int calsLeft = machine.getActivatedCard().getCalorieLimit() - machine.getActivatedCard().getCaloriesConsumed();
				ui.printMessage("<html><center><b>Thank you for your purchase!</b></center><br />"
						+ "<b>Current Balance:</b> $" + moneyLeft + "<br />"
						+ "<b>Calories remaining: </b>"+calsLeft +"<br /><br /><br />"
						+ "</html>");
				dispose();
			}
		});
		doneButton.setFocusPainted(false);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				dispose();
			}
		});
		cancelButton.setFocusPainted(false);
		
		JButton switchButton = new JButton("Switch user");
		switchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				switchCard();
				
			}
		});
		switchButton.setFocusPainted(false);
		
		
		JPanel actionPanel = new JPanel();
		centerPanel = this.mainPanel();
		actionPanel.setLayout(new BorderLayout());
		actionPanel.add(doneButton, BorderLayout.EAST);
		actionPanel.add(switchButton, BorderLayout.CENTER);
		actionPanel.add(cancelButton, BorderLayout.WEST);
		this.add(centerPanel, BorderLayout.CENTER);
		this.add(actionPanel, BorderLayout.AFTER_LAST_LINE);
		this.setSize(350,200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		setResizable(false);
	}

	/*
	 * Set a new CampusCard and update the UI
	 */
	public void setCard(CampusCard user) {
		machine.setActivatedCard(user);
		update();
	}
	
	/*
	 * Update the UI to reflect a card switch
	 */
	private void update() {
		remove(centerPanel);
		centerPanel = mainPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.revalidate();
		repaint();
	}
	
	/*
	 * Create the main panel
	 */
	private JPanel mainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		CampusCard card = machine.getActivatedCard();
		
		double moneyLeft = card.getSpendingLimit() - card.getAmountSpent();
		int calsLeft = card.getCalorieLimit() - card.getCaloriesConsumed();
		Boolean insufficientFunds = machine.getCartPrice() > moneyLeft;
		Boolean insufficientCals = machine.getCartCals() > calsLeft;
			
		doneButton.setEnabled(!insufficientFunds); // only allow purchase if the card has enough money
		
		String cartTotal = (insufficientFunds ? "<font style='color: red;'>" : "") + "$"+formatter.format(machine.getCartPrice()) +(insufficientFunds ? "</font>" : "");
		String cartCals = (insufficientCals ? "<font style='color: red;'>" : "")+machine.getCartCals() + "cal" +(insufficientCals ? "</font>" : "");
		String remainingTotal = (insufficientFunds ? "<font style='color: red;'>" : "") + "$"+formatter.format(moneyLeft) +(insufficientFunds ? "</font>" : "");
		String remainingCals = (insufficientCals ? "<font style='color: red;'>" : "")+calsLeft + "cal" +(insufficientCals ? "</font>" : "");
		
		JLabel userLabel = new JLabel("<html><b>Card holder: </b>"+card.getName()+"<br /><b>Remaining on card:</b>"+remainingTotal+" | "
				+remainingCals+"<br /><br /></html>");


		String allItems = "";
		for(StoreItem storeItem : machine.getItems()) {
			allItems += storeItem.getName() + ": $" +  formatter.format(storeItem.getPrice()) + "| "+storeItem.getCalories() + "cal\n";
		}
		allItems = StoreFrontUI.convertToMultiline(allItems, false);
		JLabel cartLabel = new JLabel(allItems);
		
		JScrollPane scrollPanel = new JScrollPane(cartLabel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPanel.setPreferredSize(new Dimension(190, 50));
		scrollPanel.getViewport().setOpaque(false);
		scrollPanel.setBorder(BorderFactory.createTitledBorder("<html>"
				+"<b>Cart total:</b> "+cartTotal+" | "+ cartCals
				
				+"</html>"));
		scrollPanel.setOpaque(false);
		panel.add(userLabel, BorderLayout.NORTH);
		panel.add(scrollPanel,  BorderLayout.CENTER);
		return panel;
	}
	
	/*
	 * Call the login view to switch users
	 */
	private void switchCard() {
		new SwitchUserUI(this);
	}
}
