package MayBatchJava.Jun2A;


import java.util.*;

public class ProductManager {
    private Map<Integer, Product> productMap = new HashMap<>();


    public boolean addProduct(Product product) {
        if (productMap.containsKey(product.getProductId())) {
            return false;
        }
        productMap.put(product.getProductId(), product);
        return true;
    }


    public Product getProduct(int productId) {
        return productMap.get(productId);
    }


    public boolean updateProduct(int productId, String name, String category, double price) {
        Product product = productMap.get(productId);
        if (product != null) {
            product.setProductName(name);
            product.setCategory(category);
            product.setPrice(price);
            return true;
        }
        return false;
    }


    public boolean deleteProduct(int productId) {
        return productMap.remove(productId) != null;
    }


    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }


    public List<Product> sortById() {
        List<Product> list = getAllProducts();
        list.sort(Comparator.comparingInt(Product::getProductId));
        return list;
    }


    public List<Product> sortByName() {
        List<Product> list = getAllProducts();
        list.sort(Comparator.comparing(Product::getProductName));
        return list;
    }
}

