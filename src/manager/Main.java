package manager;

import java.io.*;
import java.util.*;

/**
 * Main class for processing salesmen files and their sales.
 */
public class Main {

	/**
	 * Folder name for input files.
	 */
	private static final String INPUT_FOLDER = "input";

	/**
	 * Main method to execute the salesmen report generation process.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		try {
			// Step 1: Read the salesmen info file
			File salesmenInfoFile = new File(INPUT_FOLDER + "/salesmen_info.txt");

			// Step 2: Read the product file
			File productFile = new File(INPUT_FOLDER + "/products.txt");

			// Step 3: Read the sales files (all files in the folder that match the pattern)
			File[] salesFiles = getSalesFiles(INPUT_FOLDER);

			// Step 4: Process the files and generate the salesmen report
			Map<String, Product> products = loadProducts(productFile); // Load product details
			List<Salesman> salesmen = processSalesFiles(salesFiles, salesmenInfoFile, products); // Process sales
			generateSalesmenReport(salesmen);

			System.out.println("Salesmen report generated successfully.");
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}

	/**
	 * Retrieves sales files from the specified folder.
	 * 
	 * @param folder The folder to search for sales files.
	 * @return An array of sales files.
	 */
	public static File[] getSalesFiles(String folder) {
		File dir = new File(folder);

		return dir.listFiles((d, name) -> name.startsWith("salesman_") && name.endsWith(".txt"));
	}

	/**
	 * Loads product details from the specified product file.
	 * 
	 * @param productFile The file containing product details.
	 * @return A map of product IDs to Product objects.
	 * @throws IOException If an I/O error occurs.
	 */
	public static Map<String, Product> loadProducts(File productFile) throws IOException {
		Map<String, Product> products = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(productFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(";");
				String productId = data[0];
				String productName = data[1];
				double pricePerUnit = Double.parseDouble(data[2]);
				products.put(productId, new Product(productId, pricePerUnit, productName));
			}
		}
		return products;
	}

	/**
	 * Processes sales files and associates sales with salesmen.
	 * 
	 * @param salesFiles       The array of sales files.
	 * @param salesmenInfoFile The file containing salesmen information.
	 * @param products         The map of product IDs to Product objects.
	 * @return A list of Salesman objects with their sales.
	 * @throws IOException If an I/O error occurs.
	 */
	public static List<Salesman> processSalesFiles(File[] salesFiles, File salesmenInfoFile,
			Map<String, Product> products) throws IOException {
		List<Salesman> salesmen = new ArrayList<>();

		// Load salesman info into a map (keyed by document number)
		Map<Long, Salesman> salesmanInfoMap = loadSalesmanInfo(salesmenInfoFile);

		// Process each sales file
		for (File file : salesFiles) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				List<Sale> sales = new ArrayList<>();
				String line;

				while ((line = br.readLine()) != null) {
					String[] data = line.split(";");
					String productId = data[0];
					int quantitySold = Integer.parseInt(data[1]);
					Product product = products.get(productId); // Fetch the product from the map
					sales.add(new Sale(product, quantitySold));
				}

				// Extracting salesman ID from the file name (formatted as "salesman_<id>.txt")
				String fileName = file.getName();
				long salesmanId = Long.parseLong(fileName.split("_|\\.")[1]);

				// Get the corresponding Salesman from the salesmanInfoMap
				Salesman salesman = salesmanInfoMap.get(salesmanId);
				if (salesman != null) {
					salesman.setSales(sales); // Set the sales for the salesman
					salesmen.add(salesman);
				} else {
					System.err.println("Salesman ID " + salesmanId + " not found in salesmen info.");
				}
			}
		}

		return salesmen;
	}

	/**
	 * Loads salesmen information from the specified file.
	 * 
	 * @param salesmenInfoFile The file containing salesmen information.
	 * @return A map of document numbers to Salesman objects.
	 * @throws IOException If an I/O error occurs.
	 */
	public static Map<Long, Salesman> loadSalesmanInfo(File salesmenInfoFile) throws IOException {
		Map<Long, Salesman> salesmanInfoMap = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(salesmenInfoFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] data = line.split(";");
				DocumentType documentType = parseDocumentType(data[0]); // Parse the document type
				long documentNumber = Long.parseLong(data[1]);
				String firstName = data[2];
				String lastName = data[3];
				salesmanInfoMap.put(documentNumber,
						new Salesman(documentType, documentNumber, firstName, lastName, new ArrayList<>()));
			}
		}
		return salesmanInfoMap;
	}

	/**
	 * Parses the document type from a string.
	 * 
	 * @param docTypeStr The string representation of the document type.
	 * @return The DocumentType enum value.
	 */
	private static DocumentType parseDocumentType(String docTypeStr) {
		try {
			return DocumentType.valueOf(docTypeStr.toUpperCase());
		} catch (IllegalArgumentException e) {
			return DocumentType.UNKNOWN;
		}
	}

	/**
	 * Generates a report of salesmen and their total sales.
	 * 
	 * @param salesmen The list of salesmen.
	 * @throws IOException If an I/O error occurs.
	 */
	public static void generateSalesmenReport(List<Salesman> salesmen) throws IOException {
		try (FileWriter writer = new FileWriter("salesmen_report.txt")) {
			salesmen.sort((s1, s2) -> Double.compare(s2.calculateTotalSales(), s1.calculateTotalSales()));
			for (Salesman salesman : salesmen) {
				writer.write(salesman.getFirstName() + " " + salesman.getLastName() + "; "
						+ salesman.calculateTotalSales() + "\n");
			}
		}
	}
}
