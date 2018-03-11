package campusCard;

import javax.swing.ImageIcon;

enum SnackType {
	FRUIT,
	CANDY,
	DRINK,
	SNACK
}

public class StoreItem {
	private String name;
	private Double price;
	private int calories;
	private SnackType type;
	private ImageIcon image;
	
	public StoreItem(String name, Double price, int calories, SnackType type) {
		this.name = name;
		this.price = price;
		this.calories = calories;
		this.type = type;
		
		
		switch (type) {
		case FRUIT:
			image = new ImageIcon("resources/images/foodTypes/fruit.png");
			break;
		case CANDY:
			image = new ImageIcon("resources/images/foodTypes/candy.png");
			break;
		case DRINK:
			image = new ImageIcon("resources/images/foodTypes/drink.png");
			break;
		case SNACK:
			image = new ImageIcon("resources/images/foodTypes/snack.png");
			break;
		}
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public int getCalories() {
		return calories;
	}
	
	public SnackType getType() {
		return type;
	}

	public ImageIcon getImage() {
		return image;
	}
}
