package manager;

public class Salesman {
	
	private DocumentType documentType;
	
	private int documentNumber;
	
	private String firstName;
	
	private String lastName;
	
	public Salesman (DocumentType pDocumentType, int pDocumentNumber, String pFirstName, String pLastName) {
		documentType = pDocumentType;
		documentNumber = pDocumentNumber;
		firstName = pFirstName;
		lastName = pLastName;
	}
	
	public DocumentType getDocumentType() {
		return documentType;
	}
	
	public int getDocumentNumber() {
		return documentNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
}
