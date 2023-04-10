public interface Commands {

    int EXIT = 0;
    int ADD_CATEGORY = 1;
    int EDIT_CATEGORY_BY_ID = 2;
    int DELETE_CATEGORY_BY_ID = 3;
    int ADD_PRODUCT = 4;
    int EDIT_PRODUCT_BY_ID = 5;
    int DELETE_PRODUCT_BY_ID = 6;
    int PRINT_SUM_OF_PRODUCTS = 7;
    int PRINT_MAX_OF_PRICE_PRODUCT = 8;
    int PRINT_MIN_OF_PRICE_PRODUCT = 9;
    int PRINT_AVG_OF_PRICE_PRODUCT = 10;


    static void printEshop(){
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_CATEGORY + " for ADD_CATEGORY");
        System.out.println("Please input " + EDIT_CATEGORY_BY_ID + " for EDIT_CATEGORY_BY_ID");
        System.out.println("Please input " + DELETE_CATEGORY_BY_ID + " for DELETE_CATEGORY_BY_ID");
        System.out.println("Please input " + ADD_PRODUCT + " for ADD_PRODUCT");
        System.out.println("Please input " + EDIT_PRODUCT_BY_ID + " for EDIT_PRODUCT_BY_ID");
        System.out.println("Please input " + DELETE_PRODUCT_BY_ID + " for DELETE_PRODUCT_BY_ID");
        System.out.println("Please input " + PRINT_SUM_OF_PRODUCTS + " for PRINT_SUM_OF_PRODUCTS");
        System.out.println("Please input " + PRINT_MAX_OF_PRICE_PRODUCT + " for PRINT_MAX_OF_PRICE_PRODUCT");
        System.out.println("Please input " + PRINT_MIN_OF_PRICE_PRODUCT + " for PRINT_MIN_OF_PRICE_PRODUCT");
        System.out.println("Please input " + PRINT_AVG_OF_PRICE_PRODUCT + " for PRINT_AVG_OF_PRICE_PRODUCT");
    }
}
