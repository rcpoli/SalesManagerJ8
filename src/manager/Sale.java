package manager;

public class Sale {

	private Product product;

	private int quantitySold;

	/**
	 * Sale object constructor
	 * 
	 * @param product      the product related to this sale
	 * @param quantitySold the number of products sold
	 */
	public Sale(Product product, int quantitySold) {
		this.product = product;
		this.quantitySold = quantitySold;
	}

	/**
	 * @return the total calculated value
	 */
	public double calculateSaleValue() {
		return product.getPrice() * quantitySold;
	}

	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * @return the quantitySold
	 */
	public int getQuantitySold() {
		return quantitySold;
	}
}
