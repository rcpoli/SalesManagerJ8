package manager;

public class Sale {

	private Product product;

	private int quantitySold;

	public Sale(Product product, int quantitySold) {
		this.product = product;
		this.quantitySold = quantitySold;
	}

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
