package manager;

import java.util.List;

/**
 * Represents a salesman with personal details and a list of sales.
 */
public class Salesman {

	/**
	 * The type of document identifying the salesman.
	 */
	private DocumentType documentType;

	/**
	 * The document number of the salesman.
	 */
	private long documentNumber;

	/**
	 * The first name of the salesman.
	 */
	private String firstName;

	/**
	 * The last name of the salesman.
	 */
	private String lastName;

	/**
	 * The list of sales associated with the salesman.
	 */
	private List<Sale> sales;

	/**
	 * Constructs a new Salesman with the specified details.
	 * 
	 * @param documentType   The type of document identifying the salesman.
	 * @param documentNumber The document number of the salesman.
	 * @param firstName      The first name of the salesman.
	 * @param lastName       The last name of the salesman.
	 * @param sales          The list of sales associated with the salesman.
	 */
	public Salesman(DocumentType documentType, long documentNumber, String firstName, String lastName,
			List<Sale> sales) {
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sales = sales;
	}

	/**
	 * Returns the document type of the salesman.
	 * 
	 * @return The document type.
	 */
	public DocumentType getDocumentType() {
		return documentType;
	}

	/**
	 * Returns the document number of the salesman.
	 * 
	 * @return The document number.
	 */
	public long getDocumentNumber() {
		return documentNumber;
	}

	/**
	 * Returns the first name of the salesman.
	 * 
	 * @return The first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Returns the last name of the salesman.
	 * 
	 * @return The last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Returns the list of sales associated with the salesman.
	 * 
	 * @return The list of sales.
	 */
	public List<Sale> getSales() {
		return sales;
	}

	/**
	 * Sets the list of sales for the salesman.
	 * 
	 * @param newSales The new list of sales.
	 */
	public void setSales(List<Sale> newSales) {
		sales = newSales;
	}

	/**
	 * Calculates the total sales value for the salesman.
	 * 
	 * @return The total sales value.
	 */
	public double calculateTotalSales() {
		return sales.stream().mapToDouble(Sale::calculateSaleValue).sum();
	}
}
