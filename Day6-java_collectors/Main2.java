package MayBatchJava.Jun2A;

public class Main {
    public static void main(String[] args) {
        CatalogueManager manager = new CatalogueManager();

        Product p1 = new Product(1, "Laptop", "Electronics", 1200.00);
        Product p2 = new Product(2, "Phone", "Electronics", 700.00);
        Product p3 = new Product(3, "Pen", "Stationery", 1.50);

        manager.addProduct(p1, 10);
        manager.addProduct(p2, 5);
        manager.addProduct(p3, 50);

        boolean added = manager.addProduct(new Product(1, "Laptop", "Electronics", 1200.00), 5);
        System.out.println("Tried adding duplicate (should be false): " + added);

        System.out.println("Quantity of Product ID 2: " + manager.getQuantityById(2));

        manager.updateProduct(3, "Notebook", "Stationery", 2.00, 30);

        manager.deleteProductById(2);

        System.out.println("\nAll Products:");
        for (Map.Entry<Product, Integer> entry : manager.getAllProducts().entrySet()) {
            System.out.println(entry.getKey() + " | Quantity: " + entry.getValue());
        }

        System.out.println("\nSorted by ID:");
        for (Map.Entry<Product, Integer> entry : manager.sortById()) {
            System.out.println(entry.getKey() + " | Quantity: " + entry.getValue());
        }

        System.out.println("\nSorted by Name:");
        for (Map.Entry<Product, Integer> entry : manager.sortByName()) {
            System.out.println(entry.getKey() + " | Quantity: " + entry.getValue());
        }
    }
}
