package manager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

	public static void processSalesmenFiles() {
		// TODO: lógica para leer los archivos
	}

	public static void generateSalesmenReport(List<Salesman> salesmen) throws IOException {
		try (FileWriter writer = new FileWriter("salesmen_report.txt")) {
			salesmen.sort(Comparator.comparing(Salesman::calculateTotalSales).reversed());
			for (Salesman salesman : salesmen) {
				writer.write(salesman.getFirstName() + " " + salesman.getLastName() + "; "
						+ salesman.calculateTotalSales() + "\n");
			}
		}
	}

	public static void generateProductsReport(List<Product> products) throws IOException {
		try (FileWriter writer = new FileWriter("products_report.txt")) {
			products.sort(Comparator.comparing(Product::getPrice).reversed());
			for (Product product : products) {
				writer.write(product.getName() + "; " + product.getPrice() + "\n");
			}
		}
	}

	public static void main(String[] args) {

		processSalesmenFiles();

		List<Salesman> salesmen = new ArrayList<>(); 
		List<Product> products = new ArrayList<>(); 

		try {
			generateSalesmenReport(salesmen);
			generateProductsReport(products);
		} catch (IOException e) {
			System.err.println("Error generating reports: " + e.getMessage());
		}
	}
}
