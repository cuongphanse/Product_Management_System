/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_object;

/**
 *
 * @author ASUS
 */
public interface IProduct {

    public static final char SEPARATOR = ',';
    public static final String ID_FORMAT = "[A-Z]{2}\\d{3}";
    public static final String REGEX_TAKE_ALL = "^[a-zA-Z0-9 ]*$";

    public static final String INPUT_FORMAT_ID = "ID must be format two letter of Product name and 3 number [XX---]";
    public static final String INPUT_FORMAT_PRICE = "Price must be in range from 0 to 10.000";
    public static final String INPUT_FORMAT_NAME = "Name must be contains at lest 5 letter";


    public static final String INPUT_FORMAT_QUANTITY = "Quantity must be in range from 0 to 1.000";
    public static final String INPUT_FORMAT_STATUS = "Status: Type [Y]/[N] for Available/Disavailable ";
    public static final String INPUT_FORMAT_SAVE = "Your data in list is changed! Do you to save it ? Type [Y]/[N] for YES/NO ";

    public static final String INPUT_CHOICE = "Your choice: ";
    public static final String INPUT_NAME = "Product's name: ";
    public static final String INPUT_ID = "Product's ID: ";

    public static final String INPUT_PRICE = "Price: ";
    public static final String INPUT_QUANTITY = "Quantity: ";
    public static final String PRODUCT_FORMAT = "prodID  |prodName            |prodPrice |prodQuantity |prodStatus";

    void createProduct();

    int checkProduct();

    int searchProductByName();

    void updateProduct();

    void removeProduct();

    void printList();

}
