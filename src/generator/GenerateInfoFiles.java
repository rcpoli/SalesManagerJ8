package generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {
	
	private final static String[] DEMO_FIRST_NAMES = {"Nombre1", "Nombre2", "Nombre3"}; 
	private final static String[] DEMO_LAST_NAMES = {"Apellido1", "Apellido2", "Apellido3"}; 

	private final static String FOLDER_PATH = "sales_reports";
    public static void main(String[] args) {
        // Sets up a directory for the sales reports 
        
        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            if (folder.mkdir()) {
                System.out.println("Created new folder: " + FOLDER_PATH);
            } else {
                System.out.println("Error attempting to save sales reports files at: " + FOLDER_PATH);
                return;
            }
        }

        // Generates 3 pseudorandom file texts.
        for (int i = 1; i <= 3; i++) {
        	createSalesMenFile(FOLDER_PATH + "/sales_report_" + i + ".txt");
        }
    }

    private static void createSalesMen( int randomSalesCount, String name, long id) {
        Random random = new Random();

        // 
        String vendorDocType = "CC";
        String vendorDocNumber = String.format("%08d", random.nextInt(100000000));

        StringBuilder data = new StringBuilder();
        data.append(vendorDocType).append(";").append(vendorDocNumber).append("\n");

        for (int i = 1; i <= 3; i++) {
            String productID = "PROD" + i;
            int quantitySold = random.nextInt(100) + 1;  
            data.append(productID).append(";").append(quantitySold).append("\n");
        }

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(data.toString());
            System.out.println("Archivo generado: " + filePath);
        } catch (IOException e) {
            System.out.println("Ocurrió un error al generar el archivo: " + e.getMessage());
        }
    }
}
