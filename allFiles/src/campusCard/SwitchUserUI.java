package campusCard;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SwitchUserUI extends JFrame {
	/* Private data members */
	private PurchaseUI ui;
	private JPasswordField passwordField;
	private JTextField userIDField;
	
	/*
	 * ONLY CONSTRUCTOR -- NO DEFAULT 
	 * Initializes the UI
	 */
	public SwitchUserUI(PurchaseUI ui) {
		super("Switch Card");
		this.ui = ui;
		ImageIcon image = new ImageIcon("resources/images/profile.png");
		Image scaledImage = ProfileUI.getScaledImage(image.getImage(), 70, 70);
		image.setImage(scaledImage);
		JLabel picLabel = new JLabel(image);
		this.getContentPane().add(picLabel, BorderLayout.WEST);
		this.getContentPane().add(this.mainPanel(), BorderLayout.CENTER);
		this.setSize(300,200);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		setResizable(false);
	}
	
	/*
	 * Create the main panel
	 */
	private JPanel mainPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		JLabel switchLabel = new JLabel("<html><h3>Switch User</h3></html>");
		
		JPanel namePanel = new JPanel();
		JLabel nameLabel = new JLabel("Card number: ");
		userIDField = new JTextField("", 10);
		namePanel.add(nameLabel);
		namePanel.add(userIDField);
		
		JPanel passwordPanel = new JPanel();
		JLabel passwordLabel = new JLabel("Password: ");
		passwordField = new JPasswordField(10);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		
		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setLayout(new BorderLayout());
		
		textFieldPanel.add(passwordPanel, BorderLayout.CENTER);
		textFieldPanel.add(namePanel, BorderLayout.NORTH);
		 
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				login();
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				cancel();
			}
		});
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(loginButton, BorderLayout.EAST);
		buttonPanel.add(cancelButton, BorderLayout.CENTER);
		
		panel.add(switchLabel, BorderLayout.NORTH);
		panel.add(textFieldPanel, BorderLayout.WEST);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		return panel;
	}
	
	private void login() {
		DataManager dm = new DataManager();
		LoginManager lm = new LoginManager(dm);
		

		String password = new String(this.passwordField.getPassword());
		
		try {
			String id = this.userIDField.getText();
			if (lm.authenticate(Integer.parseInt(id), password)) {
				// logged in 
				this.ui.setCard(lm.getActiveUser());
				
				this.dispose();
			} else {
				this.showLoginError();
			}
		} catch (Exception ex) {
			this.showLoginError();
		}
	}
	
	private void showLoginError() {
		this.userIDField.setText("");
		this.passwordField.setText("");
		JOptionPane.showMessageDialog(this, "Problem logging in: Incorrect Card number or Password");
	}
	
	private void cancel() {
		this.dispose();
	}
	
}
