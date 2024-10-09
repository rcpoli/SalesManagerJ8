package manager;

/**
 * Represents a product with an ID, price, and name.
 */
public class Product {

	/**
	 * The unique identifier for the product.
	 */
	private String id;

	/**
	 * The price per unit of the product.
	 */
	private double price;

	/**
	 * The name of the product.
	 */
	private String name;

	/**
	 * Constructs a new Product with the specified ID, price, and name.
	 * 
	 * @param pId          The unique identifier for the product.
	 * @param pricePerUnit The price per unit of the product.
	 * @param pName        The name of the product.
	 */
	public Product(String pId, double pricePerUnit, String pName) {
		id = pId;
		price = pricePerUnit;
		name = pName;
	}

	/**
	 * Returns the unique identifier of the product.
	 * 
	 * @return The product ID.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Returns the price per unit of the product.
	 * 
	 * @return The product price.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Returns the name of the product.
	 * 
	 * @return The product name.
	 */
	public String getName() {
		return name;
	}
}
