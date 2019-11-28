//stores the individual car and its properties
public class Car {
	private String name; // model of car
	private String price; // cost of base model of car
	private int year; // year of this model

	public Car(String name, String price, int year) {
		this.name = name;
		this.price = price;
		this.year = year;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
