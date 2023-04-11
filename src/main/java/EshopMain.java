import model.Category;
import model.Product;
import storage.CategoryStorage;
import storage.ProductStorage;
import storage.impl.CategoryStorageImpl;
import storage.impl.ProductStorageImpl;

import java.util.List;
import java.util.Scanner;

public class EshopMain implements Commands {

    private static Scanner scanner = new Scanner(System.in);

    private static CategoryStorage categoryStorage = new CategoryStorageImpl();
    private static ProductStorage productStorage = new ProductStorageImpl();

    public static void main(String[] args) {
        boolean isRun = true;

        while (isRun) {
            Commands.printEshop();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }

            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_CATEGORY:
                    addCategory();
                    break;
                case EDIT_CATEGORY_BY_ID:
                    editCategoryById();
                    break;
                case DELETE_CATEGORY_BY_ID:
                    deleteCategoryById();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case EDIT_PRODUCT_BY_ID:
                    editProductByID();
                    break;
                case DELETE_PRODUCT_BY_ID:
                    deleteProductById();
                    break;
                case PRINT_SUM_OF_PRODUCTS:
                    productStorage.printSumOfProducts();
                    break;
                case PRINT_MAX_OF_PRICE_PRODUCT:
                    productStorage.printMaxOfPriceProducts();
                    break;
                case PRINT_MIN_OF_PRICE_PRODUCT:
                    productStorage.printMinOfPriceProducts();
                    break;
                case PRINT_AVG_OF_PRICE_PRODUCT:
                    productStorage.printAVGOfPriceProducts();
                    break;
                default:
                    System.err.println("Wrong command");
            }
        }


    }

    private static void deleteProductById() {
        List<Product> productList = productStorage.getAll();
        for (Product product : productList) {
            System.out.println(product);
        }
        try {
            System.out.println("Please input Category id");
            int id = Integer.parseInt(scanner.nextLine());
            productStorage.deleteProductById(id);
            System.out.println("Category deleted");
        } catch (NumberFormatException e){
            System.err.println(e.getMessage());
        }

    }

    private static void editProductByID() {
        List<Product> productList = productStorage.getAll();
        for (Product product : productList) {
            System.out.println(product);
        }
        try {
            System.out.println("Please input Category id");
            int id = Integer.parseInt(scanner.nextLine());
            if (productStorage.getById(id) != null) {
                System.out.println("Please input Product name, description, price, quantity, category_id");
                String productStr = scanner.nextLine();
                String[] productData = productStr.split(",");
                Product product = new Product();
                product.setId(id);
                product.setName(productData[0]);
                product.setDescription(productData[1]);
                product.setPrice(Double.parseDouble(productData[2]));
                product.setQuantity(Integer.parseInt(productData[3]));
                int categoryId = Integer.parseInt(productData[4]);
                Category categoryById = categoryStorage.getById(categoryId);
                if (categoryById != null) {
                    product.setCategory(categoryById);
                    productStorage.editProductById(product);
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }

    }

    private static void addProduct() {
        List<Category> categoryList = categoryStorage.getAll();
        for (Category category : categoryList) {
            System.out.println(category);
        }
        try {
            System.out.println("Please input Category id");
            int id = Integer.parseInt(scanner.nextLine());
            Category categoryId = categoryStorage.getById(id);
            if (categoryId != null) {
                System.out.println("Please input Product name, description, price, quantity");
                String productStr = scanner.nextLine();
                String[] productData = productStr.split(",");
                Product product = new Product();
                product.setCategory(categoryId);
                product.setName(productData[0]);
                product.setDescription(productData[1]);
                product.setPrice(Double.parseDouble(productData[2]));
                product.setQuantity(Integer.parseInt(productData[3]));
                productStorage.saveProduct(product);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void deleteCategoryById() {
        List<Category> categoryList = categoryStorage.getAll();
        for (Category category : categoryList) {
            System.out.println(category);
        }
        try {
            System.out.println("Please input Category id");
            int id = Integer.parseInt(scanner.nextLine());
            categoryStorage.deleteCategoryById(id);
            System.out.println("Category deleted");
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void editCategoryById() {
        List<Category> categoryList = categoryStorage.getAll();
        for (Category category : categoryList) {
            System.out.println(category);
        }
        try {
            System.out.println("Please input Category id");
            int id = Integer.parseInt(scanner.nextLine());
            if (categoryStorage.getById(id) != null) {
                System.out.println("Please input Category name");
                String name = scanner.nextLine();
                Category category = new Category();
                category.setId(id);
                category.setName(name);
                categoryStorage.editCategoryById(category);
                System.out.println("Category was edit");
            } else {
                System.out.println("Category doesn't exist");
            }
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void addCategory() {
        System.out.println("Please input Category name");
        String name = scanner.nextLine();
        Category category = new Category();
        category.setName(name);
        categoryStorage.saveCategory(category);
    }
}
