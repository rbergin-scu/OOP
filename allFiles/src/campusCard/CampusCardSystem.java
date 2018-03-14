package campusCard;

import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class CampusCardSystem extends JFrame implements ActionListener {
	
	private CampusCard activeUser;
	
	private LoginManager lm = new LoginManager(new DataManager());
	
	private Container contentPane;
	private JPanel welcomePanel;
	private JPanel loginPanel;
	private JPanel activatePanel;
	
	private JTextField cardIDField;
	private JPasswordField passwordField;
	
	private JTextField nameField;
	private JTextField phoneField;
	private JPasswordField passwordField2;
	private JPasswordField confirmField;
	private JTextField spendingField;
	private JTextField calorieField;
	
	
	
	public CampusCardSystem() {
		
		contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		
		welcomePanel = getWelcomePanel();
		contentPane.add(welcomePanel);
		
		loginPanel = getLoginPanel();
		loginPanel.setVisible(false);
		contentPane.add(loginPanel);
		
		activatePanel = getActivatePanel();
		activatePanel.setVisible(false);
		contentPane.add(activatePanel);
		
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2,size.height/2 - getHeight()/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	
	////////////////////////////////////////
	// GENERATING PANELS
	
	private JPanel getWelcomePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JButton loginButton = new JButton("Log In");
		loginButton.addActionListener(this);
		loginButton.setActionCommand("gotologin");
		loginButton.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton activateButton = new JButton("Activate a new Campus Card");
		activateButton.addActionListener(this);
		activateButton.setActionCommand("gotoactivate");
		activateButton.setAlignmentX(CENTER_ALIGNMENT);
		
		panel.add(loginButton, BorderLayout.NORTH);
		panel.add(activateButton, BorderLayout.SOUTH);
		
		return panel;
	}
	
	private JPanel getLoginPanel() {
		JPanel panel = new JPanel();
		
		JLabel cardIDLabel = new JLabel("Card ID:");
		panel.add(cardIDLabel);
		cardIDField = new JTextField(8);
		panel.add(cardIDField);
		JLabel passwordLabel = new JLabel("Password:");
		panel.add(passwordLabel);
		passwordField = new JPasswordField(8);
		panel.add(passwordField);
		
		JButton loginButton = new JButton("LOGIN");
		loginButton.addActionListener(this);
		loginButton.setActionCommand("loginattempt");
		loginButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(loginButton);
		
		JButton backButton = new JButton("BACK");
		backButton.addActionListener(this);
		backButton.setActionCommand("goback");
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(backButton);
		
		return panel;
	}
	
	private JPanel getActivatePanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		JLabel nameLabel = new JLabel("Name:");
		panel.add(nameLabel);
		nameField = new JTextField(8);
		panel.add(nameField);
		
		JLabel phoneLabel = new JLabel("Phone Number:");
		panel.add(phoneLabel);
		phoneField = new JTextField(8);
		panel.add(phoneField);
		
		JLabel passwordLabel = new JLabel("Password:");
		panel.add(passwordLabel);
		passwordField2 = new JPasswordField(8);
		panel.add(passwordField2);
		
		JLabel confirmLabel = new JLabel("Confirm Password:");
		panel.add(confirmLabel);
		confirmField = new JPasswordField(8);
		panel.add(confirmField);
		
		JLabel spendingLabel = new JLabel("Spending Limit:");
		panel.add(spendingLabel);
		spendingField = new JTextField(8);
		panel.add(spendingField);
		
		JLabel calorieLabel = new JLabel("Calorie Limit:");
		panel.add(calorieLabel);
		calorieField = new JTextField(8);
		panel.add(calorieField);
		
		JButton activateButton = new JButton("ACTIVATE");
		activateButton.addActionListener(this);
		activateButton.setActionCommand("activateattempt");
		activateButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(activateButton);
		
		
		JButton backButton = new JButton("BACK");
		backButton.addActionListener(this);
		backButton.setActionCommand("goback");
		backButton.setAlignmentX(CENTER_ALIGNMENT);
		panel.add(backButton);
		
		return panel;
	}
	
	////////////////////////////////////////
	// LOGIN LOGIC
	
	private void logInAttempt() {
		String cardID = cardIDField.getText();
		String pass = new String(passwordField.getPassword());
		
		if (cardID.equals("") || pass.equals("")) {
			JOptionPane.showMessageDialog(this,
				    "Please enter information into every field.",
				    "Empty Fields",
				    JOptionPane.WARNING_MESSAGE);
		} else if (lm.authenticate(Integer.parseInt(cardID),pass)) {
			System.out.println("Logged in Successfully!");
			this.dispose();
			new StoreFrontUI("Store", new VendingMachine(lm.getActiveUser()));
		} else {
			JOptionPane.showMessageDialog(this,
				    "Please enter correct card ID and password.",
				    "Incorrect Login",
				    JOptionPane.WARNING_MESSAGE);
		}
	}
	
	////////////////////////////////////////
	// ACTIVATE LOGIC
	
	//TODO activateUser needs reference to many JTextFields and make a new CampusCard and add it to dataManager
	private void activateUser() {
		String name = nameField.getText();
		String phoneNumber = phoneField.getText();
		String password = new String(passwordField2.getPassword());
		String spendingLimit = spendingField.getText();
		String calorieLimit = calorieField.getText();
		
		// If any field is empty, pop up a dialog box
		if (name.equals("") || phoneNumber.equals("") || password.equals("") || spendingLimit.equals("") || calorieLimit.equals("")) {
			JOptionPane.showMessageDialog(this,
				    "Please enter information into every field.",
				    "Empty Fields",
				    JOptionPane.WARNING_MESSAGE);
		// if password does not match confirm password, pop up a dialog box
		} else if (!password.equals(new String(confirmField.getPassword()))) {
			JOptionPane.showMessageDialog(this,
				    "Please enter correct password into confirm password field.",
				    "Password Mismatch",
				    JOptionPane.WARNING_MESSAGE);
		// if everything is correct, activate this user and go to store
		} else {
			double slimit = Double.parseDouble(spendingLimit);
			int climit = Integer.parseInt(calorieLimit);
			activeUser = lm.activateUser(name,phoneNumber,password,slimit,climit);
			new StoreFrontUI("Store", new VendingMachine(activeUser));
			this.dispose();
			
		}
	}
	
	////////////////////////////////////////
	// GETTERS
	
	public CampusCard getActiveUser() {
		return activeUser;
	}
	
	////////////////////////////////////////
	// MENU NAVIGATION
	
	private void openLogInMenu() {
		welcomePanel.setVisible(false);
		loginPanel.setVisible(true);
		pack();
	}
	
	private void openActivateMenu() {
		welcomePanel.setVisible(false);
		activatePanel.setVisible(true);
		pack();
	}
	
	private void back() {
		loginPanel.setVisible(false);
		activatePanel.setVisible(false);
		welcomePanel.setVisible(true);
		pack();
	}
	
	public void actionPerformed(ActionEvent evt) {
		String command = evt.getActionCommand();
		switch(command) {
		case "gotologin":
			openLogInMenu();
			break;
		case "gotoactivate":
			openActivateMenu();
			break;
		case "goback":
			back();
			break;
		case "loginattempt":
			logInAttempt();
			break;
		case "activateattempt":
			activateUser();
			break;
		}
	}
}
