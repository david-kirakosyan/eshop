package storage.impl;

import db.DBConnectionProvider;
import model.Category;
import storage.CategoryStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryStorageImpl implements CategoryStorage {

    private static Connection connection = DBConnectionProvider.getInstance().getConnection();

    @Override
    public void saveCategory(Category category) {
        String sql = "INSERT INTO category(name) VALUES(?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editCategoryById(Category category) {
        String sql = "UPDATE category SET name = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category getById(int id) {
        String str = "SELECT * FROM category WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(str)) {
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                Category category = new Category();
                category.setId(id);
                category.setName(resultSet.getString("name"));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM category")) {
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryList;
    }

    @Override
    public void deleteCategoryById(int id) {
        try (PreparedStatement ps = connection.prepareStatement("DELETE FROM category WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
