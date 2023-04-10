package storage;

import model.Product;

import java.util.List;

public interface ProductStorage {
    void saveProduct(Product product);

    void editProductById(Product product);

    Product getById(int id);

    List<Product> getAll();

    void deleteProductById(int id);

    void printSumOfProducts();

    void printMaxOfPriceProducts();

    void printMinOfPriceProducts();

    void printAVGOfPriceProducts();
}
