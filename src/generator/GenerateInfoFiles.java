package generator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {
	public static void createSalesmenFile(int randomSalesCount, String name, long id) throws IOException {
		try (FileWriter writer = new FileWriter("salesman_" + id + ".txt")) {
			Random random = new Random();
			for (int i = 0; i < randomSalesCount; i++) {
				writer.write("Product" + (i + 1) + ";" + (random.nextInt(100) + 1) + "\n");
			}
		}
	}

	public static void createProductsFile(int productsCount) throws IOException {
		try (FileWriter writer = new FileWriter("products.txt")) {
			Random random = new Random();
			for (int i = 0; i < productsCount; i++) {
				writer.write(
						"Product" + (i + 1) + ";Product Name " + (i + 1) + ";" + (random.nextDouble() * 100) + "\n");
			}
		}
	}

	public static void createSalesmanInfoFile(int salesmanCount) throws IOException {
		try (FileWriter writer = new FileWriter("salesmen_info.txt")) {
			for (int i = 0; i < salesmanCount; i++) {
				writer.write(
						"ID" + (i + 1) + ";Document" + (i + 1) + ";FirstName" + (i + 1) + ";LastName" + (i + 1) + "\n");
			}
		}
	}

	public static void main(String[] args) {
		try {
			createSalesmenFile(5, "John Doe", 123456);
			createProductsFile(10);
			createSalesmanInfoFile(3);
		} catch (IOException e) {
			System.err.println("Error generating files: " + e.getMessage());
		}
	}
}
