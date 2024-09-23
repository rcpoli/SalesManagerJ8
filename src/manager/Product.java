package manager;

public class Product {

	private String id;

	private int price;

	private String name;

	public Product(String pId, int pPrice, String pName) {
		id = pId;
		price = pPrice;
		name = pName;
	}

	public String getId() {
		return id;
	}

	public int getPrice() {
		return price;
	}

	public String getName() {
		return name;
	}
}
