/*
 * ProfileUI
 * 
 * Displays either the Nutritional or Expense profile of a user
 * as a pop-up box
 * 
 * Created by Mason Bruce
 */

package campusCard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

public class ProfileUI extends JFrame {
	/* Private data members */
	private CampusCard user;
	private DecimalFormat formatter;
	
	/*
	 * ONLY CONSTRUCTOR -- NO DEFAULT 
	 */
	public ProfileUI(Boolean isNutritional, CampusCard user) {
		super((isNutritional ? "Nutritional":"Expense") +"Profile for "+user.getName());
		
		formatter = new DecimalFormat("0.00");
		
		this.user = user;
		this.setLayout(new BorderLayout());
		if (isNutritional) {
			this.getContentPane().add(this.nutritionalReport(), BorderLayout.CENTER);
		} else {
			this.getContentPane().add(this.expenseReport(), BorderLayout.CENTER);
		}

		ImageIcon image = new ImageIcon("resources/images/profile.png");
		Image scaledImage = ProfileUI.getScaledImage(image.getImage(), 75, 75);
		image.setImage(scaledImage);
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		JLabel dateLabel = new JLabel(dateFormat.format(date));
		this.getContentPane().add(dateLabel, BorderLayout.PAGE_START);
		JLabel picLabel = new JLabel(image);
		this.getContentPane().add(picLabel, BorderLayout.WEST);
		picLabel.setPreferredSize(new Dimension(75, 75));
		
		JButton doneButton = new JButton("Done");
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				dispose();
			}
		});
		
		JPanel actionPanel = new JPanel();
		actionPanel.setLayout(new BorderLayout());
		actionPanel.add(doneButton, BorderLayout.EAST);
		this.add(actionPanel, BorderLayout.AFTER_LAST_LINE);
		this.setSize(300,150);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		setResizable(false);
	}
	
	/*
	 * Create the expense report
	 */
	private JPanel expenseReport() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Date date = new Date();
		
		JLabel infoLabel = new JLabel("<html><br />"+"<b>Funds set for "+ new SimpleDateFormat("MM/yy").format(date) +":</b> $"
		+formatter.format(user.getSpendingLimit())+"<br />"
		+ "<b>Spent so far: </b>$"+formatter.format(user.getAmountSpent())+"<br />"
		+ "<b>Remaining: </b>$"+ formatter.format((user.getSpendingLimit()-user.getAmountSpent()))
		+"</html>");
		
		
		panel.add(infoLabel, BorderLayout.CENTER);
		return panel;
	}
	
	/*
	 * Create the nutritional
	 */
	private JPanel nutritionalReport() {
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel infoLabel = new JLabel("<html><br />"+"<b>Calories set :</b> "+user.getCalorieLimit()+"cal<br />"
		+ "<b>Consumed so far: </b>"+user.getCaloriesConsumed()+"cal<br />"
		+ "<b>Remaining: </b>"+ (user.getCalorieLimit()-user.getCaloriesConsumed()) + "cal"
		+"</html>");
		
		panel.add(infoLabel, BorderLayout.CENTER);
		return panel;
	}


	/* STATIC
	 * Image helper method
	 * Scale an input image to a given width and height
	 */
	public static Image getScaledImage(Image srcImg, int w, int h){
	    BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = resizedImg.createGraphics();

	    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	    g2.drawImage(srcImg, 0, 0, w, h, null);
	    g2.dispose();

	    return resizedImg;
	}
}
