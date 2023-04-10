package storage;

import model.Category;

import java.util.List;

public interface CategoryStorage {
    void saveCategory(Category category);

    void editCategoryById(Category category);

    Category getById(int id);

    List<Category> getAll();

    void deleteCategoryById(int id);
}
