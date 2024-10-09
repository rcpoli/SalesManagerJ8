package generator;

import manager.DocumentType;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/**
 * Generates demo Info files to be used to test the application. Outputs files
 * to the demofiles folder in the root of the application.
 */
public class GenerateInfoFiles {
	private static final Random random = new Random();
	private static final String DEMO_FOLDER = "demofiles"; // Folder name
	private static List<String> productIds = new ArrayList<>();
	private static List<String> salesmanIds = new ArrayList<>();

	/**
	 * Creates demo salesmen files
	 * 
	 * @param randomSalesCount the number of sales done by the salesman
	 * @param name the name of the salesman
	 * @param id the id number of the salesman
	 * @throws IOException Exception in case the file errors when creating it
	 */
	public static void createSalesmenFile(int randomSalesCount, String name, long id) throws IOException {
		String fileName = DEMO_FOLDER + "/salesman_" + id + ".txt";
		try (FileWriter writer = new FileWriter(fileName)) {
			for (int i = 0; i < randomSalesCount; i++) {
				// Pick a random product ID from the products list
				String productId = productIds.get(random.nextInt(productIds.size()));
				int quantitySold = random.nextInt(100) + 1; // Random quantity between 1 and 100
				writer.write(productId + ";" + quantitySold + "\n");
			}
		}
		System.out.println("Generated sales file: " + fileName);
	}

	/**
	 * Creates demo product files
	 * @param productsCount the number of products to create
	 * @throws IOException Exception in case the file errors when creating it
	 */
	public static void createProductsFile(int productsCount) throws IOException {
		String fileName = DEMO_FOLDER + "/products.txt";
		try (FileWriter writer = new FileWriter(fileName)) {
			for (int i = 0; i < productsCount; i++) {
				String productId = "Product" + (i + 1);
				String productName = "Product Name " + (i + 1);
				double pricePerUnit = random.nextDouble() * 100; // Random price between 0 and 100
				productIds.add(productId); // Store product ID for later reference
				writer.write(productId + ";" + productName + ";" + pricePerUnit + "\n");
			}
		}
		System.out.println("Generated products file.");
	}

	/**
	 * Creates the salesmen info file
	 * @param salesmanCount the number of salesman to add to the file
	 * @throws IOException Exception in case the file errors when creating it
	 */
	public static void createSalesmanInfoFile(int salesmanCount) throws IOException {
		String fileName = DEMO_FOLDER + "/salesmen_info.txt";
		try (FileWriter writer = new FileWriter(fileName)) {
			for (int i = 0; i < salesmanCount; i++) {
				// Generate random salesman details
				String documentType = randomDocumentType();
				long documentNumber = 100000 + i; // Sequential document numbers starting from 100000
				String firstName = "FirstName" + (i + 1);
				String lastName = "LastName" + (i + 1);

				salesmanIds.add(String.valueOf(documentNumber)); // Store document number for later reference
				writer.write(documentType + ";" + documentNumber + ";" + firstName + ";" + lastName + "\n");
			}
		}
		System.out.println("Generated salesmen info file.");
	}

	/**
	 * Returns a random document type value
	 * @return  a random document type value
	 */
	private static String randomDocumentType() {
		// Randomly select a document type from the enum
		DocumentType[] types = DocumentType.values();
		return types[random.nextInt(types.length)].toString();
	}
	
	/**
	 * Creates the demofolder folder to output the demo files.
	 */
	private static void createDemoFolder() {
		File folder = new File(DEMO_FOLDER);
		if (!folder.exists()) {
			if (folder.mkdir()) {
				System.out.println("Created folder: " + DEMO_FOLDER);
			} else {
				System.err.println("Failed to create folder: " + DEMO_FOLDER);
			}
		}
	}

	/**
	 * Main method to run the generateInfoFiles logic
	 * @param args execution args, no overrides are currently implemented
	 */
	public static void main(String[] args) {
		try {
			int numProducts = 10;
			int numSalesmen = 5;

			// Step 1: Create the demo folder if it doesn't exist
			createDemoFolder();

			// Step 2: Create products
			createProductsFile(numProducts);

			// Step 3: Create salesmen info
			createSalesmanInfoFile(numSalesmen);

			// Step 4: For each salesman, create a sales file
			for (String salesmanId : salesmanIds) {
				createSalesmenFile(5, "Salesman" + salesmanId, Long.parseLong(salesmanId));
			}

			System.out.println("Generated demo files in the folder 'demofiles'.");

		} catch (IOException e) {
			System.err.println("Error generating files: " + e.getMessage());
		}
	}
}
