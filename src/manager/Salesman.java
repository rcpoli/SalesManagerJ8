package manager;

import java.util.List;

public class Salesman {
	private DocumentType documentType;

	private long documentNumber;

	private String firstName;

	private String lastName;

	private List<Sale> sales;

	public Salesman(DocumentType documentType, long documentNumber, String firstName, String lastName,
			List<Sale> sales) {
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sales = sales;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public long getDocumentNumber() {
		return documentNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public List<Sale> getSales() {
		return sales;
	}

	public double calculateTotalSales() {
		return sales.stream().mapToDouble(Sale::calculateSaleValue).sum();
	}

}
