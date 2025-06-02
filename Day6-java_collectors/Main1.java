package MayBatchJava.Jun2A;

public class Main1 {
    public static void main(String[] args) {
        ProductManager manager = new ProductManager();

        manager.addProduct(new Product(1, "Laptop", "Electronics", 1200.00));
        manager.addProduct(new Product(2, "Smartphone", "Electronics", 800.00));
        manager.addProduct(new Product(3, "Book", "Education", 20.00));

        boolean added = manager.addProduct(new Product(1, "Another Laptop", "Electronics", 1000.00));
        System.out.println("Add duplicate product (should be false): " + added);


        System.out.println("Product ID 2: " + manager.getProduct(2));


        manager.updateProduct(3, "Notebook", "Stationery", 25.00);


        manager.deleteProduct(2);

        System.out.println("\nAll Products:");
        for (Product p : manager.getAllProducts()) {
            System.out.println(p);
        }

        System.out.println("\nSorted by ID:");
        for (Product p : manager.sortById()) {
            System.out.println(p);
        }

        System.out.println("\nSorted by Name:");
        for (Product p : manager.sortByName()) {
            System.out.println(p);
        }
    }
}

