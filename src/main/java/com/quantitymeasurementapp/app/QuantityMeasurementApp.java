package com.quantitymeasurementapp.app;

import com.quantitymeasurementapp.controller.QuantityMeasurementController;
import com.quantitymeasurementapp.entity.QuantityDTO;
import com.quantitymeasurementapp.repository.IQuantityMeasurementRepository;
import com.quantitymeasurementapp.repository.QuantityMeasurementCacheRepository;
import com.quantitymeasurementapp.repository.QuantityMeasurementDatabaseRepository;
import com.quantitymeasurementapp.service.IQuantityMeasurementService;
import com.quantitymeasurementapp.service.QuantityMeasurementServiceImpl;
import com.quantitymeasurementapp.util.ApplicationConfig;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // Step 1: Create repository
    	IQuantityMeasurementRepository repo;

		if (ApplicationConfig.get("repository.type").equals("database")) {

			repo = new QuantityMeasurementDatabaseRepository();

		} else {

			repo = QuantityMeasurementCacheRepository.getInstance();
		}

        // Step 2: Create service
        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repo);

        // Step 3: Create controller
        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        // -------------------------------
        // LENGTH OPERATIONS
        // -------------------------------
        QuantityDTO length1 = new QuantityDTO(2, "FEET", "LENGTH");
        QuantityDTO length2 = new QuantityDTO(24, "INCHES", "LENGTH");

        System.out.println("----- LENGTH OPERATIONS -----");
        System.out.println("Compare 2 FEET and 24 INCHES: "
                + controller.compare(length1, length2));

        System.out.println("Add 2 FEET + 24 INCHES: "
                + controller.add(length1, length2));

        System.out.println("Convert 2 FEET to INCHES: "
                + controller.convert(length1, "INCHES"));

        System.out.println("Subtract 2 FEET - 24 INCHES: "
                + controller.subtract(length1, length2));

        System.out.println("Divide 2 FEET / 24 INCHES: "
                + controller.divide(length1, length2));

        // -------------------------------
        // WEIGHT OPERATIONS
        // -------------------------------
        QuantityDTO weight1 = new QuantityDTO(1, "KILOGRAM", "WEIGHT");
        QuantityDTO weight2 = new QuantityDTO(1000, "GRAM", "WEIGHT");

        System.out.println("\n----- WEIGHT OPERATIONS -----");
        System.out.println("Compare 1 KG and 1000 GRAM: "
                + controller.compare(weight1, weight2));

        System.out.println("Add 1 KG + 1000 GRAM: "
                + controller.add(weight1, weight2));

        System.out.println("Convert 1 KG to GRAM: "
                + controller.convert(weight1, "GRAM"));

        System.out.println("Subtract 1 KG - 1000 GRAM: "
                + controller.subtract(weight1, weight2));

        System.out.println("Divide 1 KG / 1000 GRAM: "
                + controller.divide(weight1, weight2));

        // -------------------------------
        // VOLUME OPERATIONS
        // -------------------------------
        QuantityDTO volume1 = new QuantityDTO(1, "GALLON", "VOLUME");
        QuantityDTO volume2 = new QuantityDTO(3.785, "LITER", "VOLUME");

        System.out.println("\n----- VOLUME OPERATIONS -----");
        System.out.println("Compare 1 GALLON and 3.785 LITER: "
                + controller.compare(volume1, volume2));

        System.out.println("Add 1 GALLON + 3.785 LITER: "
                + controller.add(volume1, volume2));

        System.out.println("Convert 1 GALLON to MILLILITER: "
                + controller.convert(volume1, "MILLILITER"));

        System.out.println("Subtract 1 GALLON - 3.785 LITER: "
                + controller.subtract(volume1, volume2));

        System.out.println("Divide 1 GALLON / 3.785 LITER: "
                + controller.divide(volume1, volume2));

        // -------------------------------
        // TEMPERATURE OPERATIONS
        // -------------------------------
        QuantityDTO temp1 = new QuantityDTO(0, "CELSIUS", "TEMPERATURE");
        QuantityDTO temp2 = new QuantityDTO(32, "FAHRENHEIT", "TEMPERATURE");

        System.out.println("\n----- TEMPERATURE OPERATIONS -----");
        System.out.println("Compare 0 CELSIUS and 32 FAHRENHEIT: "
                + controller.compare(temp1, temp2));

        System.out.println("Convert 0 CELSIUS to FAHRENHEIT: "
                + controller.convert(temp1, "FAHRENHEIT"));

        // Unsupported operations for Temperature
        try {
            QuantityDTO temp3 = new QuantityDTO(100, "CELSIUS", "TEMPERATURE");
            QuantityDTO temp4 = new QuantityDTO(50, "CELSIUS", "TEMPERATURE");

            System.out.println("Add 100 CELSIUS + 50 CELSIUS: "
                    + controller.add(temp3, temp4));
        } catch (Exception e) {
            System.out.println("Temperature Add Error: " + e.getMessage());
        }

        try {
            QuantityDTO temp3 = new QuantityDTO(100, "CELSIUS", "TEMPERATURE");
            QuantityDTO temp4 = new QuantityDTO(50, "CELSIUS", "TEMPERATURE");

            System.out.println("Subtract 100 CELSIUS - 50 CELSIUS: "
                    + controller.subtract(temp3, temp4));
        } catch (Exception e) {
            System.out.println("Temperature Subtract Error: " + e.getMessage());
        }

        try {
            QuantityDTO temp3 = new QuantityDTO(100, "CELSIUS", "TEMPERATURE");
            QuantityDTO temp4 = new QuantityDTO(50, "CELSIUS", "TEMPERATURE");

            System.out.println("Divide 100 CELSIUS / 50 CELSIUS: "
                    + controller.divide(temp3, temp4));
        } catch (Exception e) {
            System.out.println("Temperature Divide Error: " + e.getMessage());
        }
        
        System.out.println("\n----- DATABASE RECORDS -----");

        repo.getAllOperations().forEach(System.out::println);
    }
}