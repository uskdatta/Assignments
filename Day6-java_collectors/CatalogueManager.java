package MayBatchJava.Jun2A;

import java.util.*;

public class CatalogueManager {
    private Map<Product, Integer> catalogue = new HashMap<>();

    public boolean addProduct(Product product, int quantity) {
        if (catalogue.containsKey(product)) {
            return false; // Duplicate product ID
        }
        catalogue.put(product, quantity);
        return true;
    }

    public Integer getQuantityById(int productId) {
        for (Product p : catalogue.keySet()) {
            if (p.getProductId() == productId) {
                return catalogue.get(p);
            }
        }
        return null;
    }

    public boolean updateProduct(int productId, String name, String category, double price, int quantity) {
        for (Product p : catalogue.keySet()) {
            if (p.getProductId() == productId) {
                p.setProductName(name);
                p.setCategory(category);
                p.setPrice(price);
                catalogue.put(p, quantity);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProductById(int productId) {
        Product toRemove = null;
        for (Product p : catalogue.keySet()) {
            if (p.getProductId() == productId) {
                toRemove = p;
                break;
            }
        }
        if (toRemove != null) {
            catalogue.remove(toRemove);
            return true;
        }
        return false;
    }

    public Map<Product, Integer> getAllProducts() {
        return new HashMap<>(catalogue);
    }

    public List<Map.Entry<Product, Integer>> sortById() {
        List<Map.Entry<Product, Integer>> list = new ArrayList<>(catalogue.entrySet());
        list.sort(Comparator.comparing(e -> e.getKey().getProductId()));
        return list;
    }

    public List<Map.Entry<Product, Integer>> sortByName() {
        List<Map.Entry<Product, Integer>> list = new ArrayList<>(catalogue.entrySet());
        list.sort(Comparator.comparing(e -> e.getKey().getProductName()));
        return list;
    }
}
