package MayBatchJava.Jun1;

import java.util.Scanner;

public class CarCustomizer {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Car Manufacturer:\n1. Mahindra\n2. Tata\n3. Maruti");
        String manufacturer = "";
        int manuChoice = scanner.nextInt();
        scanner.nextLine();
        switch (manuChoice) {
            case 1 -> manufacturer = "Mahindra";
            case 2 -> manufacturer = "Tata";
            case 3 -> manufacturer = "Maruti";
            default -> {
                System.out.println("Invalid choice. Exiting.");
                return;
            }
        }

        String model = "";
        if (manufacturer.equals("Mahindra")) {
            System.out.println("Select Model:\n1. Scorpio\n2. Thar\n3. Scorpio N\n4. XUV 700");
            int modelChoice = scanner.nextInt();
            scanner.nextLine();
            switch (modelChoice) {
                case 1 -> model = "Scorpio";
                case 2 -> model = "Thar";
                case 3 -> model = "Scorpio N";
                case 4 -> model = "XUV 700";
                default -> {
                    System.out.println("Invalid choice. Exiting.");
                    return;
                }
            }
        }

        System.out.println("Select Transmission Variant:\n1. Manual\n2. Automatic");
        String transmission = "";
        int transChoice = scanner.nextInt();
        scanner.nextLine();
        switch (transChoice) {
            case 1 -> transmission = "Manual";
            case 2 -> transmission = "Automatic";
            default -> {
                System.out.println("Invalid choice. Exiting.");
                return;
            }
        }

        System.out.println("Select Fuel Type:\n1. Diesel\n2. Petrol\n3. CNG");
        String fuelType = "";
        int fuelChoice = scanner.nextInt();
        scanner.nextLine();
        switch (fuelChoice) {
            case 1 -> fuelType = "Diesel";
            case 2 -> fuelType = "Petrol";
            case 3 -> fuelType = "CNG";
            default -> {
                System.out.println("Invalid choice. Exiting.");
                return;
            }
        }

        System.out.println("Select Color:\n1. Silver\n2. Blue\n3. Yellow");
        String color = "";
        int colorChoice = scanner.nextInt();
        scanner.nextLine();
        switch (colorChoice) {
            case 1 -> color = "Silver";
            case 2 -> color = "Blue";
            case 3 -> color = "Yellow";
            default -> {
                System.out.println("Invalid choice. Exiting.");
                return;
            }
        }

        System.out.println("Select Location:\n1. Delhi\n2. Bangalore\n3. Hyderabad\n4. Chennai");
        String location = "";
        int locChoice = scanner.nextInt();
        scanner.nextLine();
        switch (locChoice) {
            case 1 -> location = "Delhi";
            case 2 -> location = "Bangalore";
            case 3 -> location = "Hyderabad";
            case 4 -> location = "Chennai";
            default -> {
                System.out.println("Invalid choice. Exiting.");
                return;
            }
        }

        System.out.println("\n----- Car Configuration Summary -----");
        System.out.println("Manufacturer: " + manufacturer);
        if (!model.isEmpty()) {
            System.out.println("Model: " + model);
        }
        System.out.println("Transmission: " + transmission);
        System.out.println("Fuel Type: " + fuelType);
        System.out.println("Color: " + color);
        System.out.println("Location: " + location);
    }
}
