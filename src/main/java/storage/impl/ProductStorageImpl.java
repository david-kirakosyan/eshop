package storage.impl;

import db.DBConnectionProvider;
import model.Product;
import storage.CategoryStorage;
import storage.ProductStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductStorageImpl implements ProductStorage {

    private static Connection connection = DBConnectionProvider.getInstance().getConnection();

    private static CategoryStorage categoryStorage = new CategoryStorageImpl();


    @Override
    public void saveProduct(Product product) {
        String sql = "INSERT INTO product(name,description,price,quantity,category_id) VALUES(?,?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editProductById(Product product) {
        String sql = "UPDATE product SET name = ?, description = ?, price = ?, quantity = ?, category_id = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setInt(4, product.getQuantity());
            ps.setInt(5, product.getCategory().getId());
            ps.setInt(6, product.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Product getById(int id) {
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM product WHERE id = ?")) {
            ps.setInt(1,id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Product product = getProduct(id, resultSet);
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM product")){
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Product product = getProduct(resultSet.getInt("id"), resultSet);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }


    @Override
    public void deleteProductById(int id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM product WHERE id = ?")) {
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void printSumOfProducts() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT COUNT(quantity) FROM product")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int quantity = resultSet.getInt(1);
                System.out.println("Sum of quantity products: " +quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void printMaxOfPriceProducts() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT MAX(price) AS max_price FROM product ")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int maxPrice = resultSet.getInt("max_price");
                System.out.println("The price of the most expensive product: " + maxPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void printMinOfPriceProducts() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT MIN(price) AS min_price FROM product ")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int minPrice = resultSet.getInt("min_price");
                System.out.println("The cheapest price of the product: " + minPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void printAVGOfPriceProducts() {
        try (PreparedStatement statement = connection.prepareStatement("SELECT AVG(price) AS avg_price FROM product ")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int avgPrice = resultSet.getInt("avg_price");
                System.out.println("average product price: " + avgPrice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Product getProduct(int resultSet, ResultSet resultSet1) throws SQLException {
        Product product = new Product();
        product.setId(resultSet);
        product.setName(resultSet1.getString("name"));
        product.setDescription(resultSet1.getString("description"));
        product.setPrice(resultSet1.getDouble("price"));
        product.setQuantity(resultSet1.getInt("quantity"));
        int categoryId = resultSet1.getInt("category_id");
        product.setCategory(categoryStorage.getById(categoryId));
        return product;
    }
}
