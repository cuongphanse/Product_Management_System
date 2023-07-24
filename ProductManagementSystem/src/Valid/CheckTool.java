/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Valid;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import model.Product;

/**
 *
 * @author ASUS
 */
public class CheckTool {

    public static final char SEPARATOR = ',';
    public static final String ID_FORMAT = "[A-Z]{2}\\d{3}";

    public static final String REGEX_TAKE_ALL = "^[a-zA-Z0-9 ]*$";
    public static final String REGEX_TAKE_ALL_NOT_BLANK = "^[a-zA-Z0-9 ][^\s]*$";

    public static final String IS_CONTINUE = "Do you want to back to main MENU [Y]/[N]?: ";

    public static final String INPUT_FORMAT_ID = "ID must be format two letter of Product name and 3 number [XX---]";
    public static final String INPUT_FORMAT_NAME = "Name must be contains at lest 5 letter";

    public static final String INPUT_FORMAT_PRICE = "Price must be in range from 0 to 10.000";
    public static final String INPUT_FORMAT_QUANTITY = "Quantity must be in range from 0 to 1.000";
    public static final String INPUT_FORMAT_STATUS = "Status: Type [Y]/[N] for Available/Disavailable ";
    public static final String INPUT_FORMAT_SAVE = "Your data in list is changed! Do you to save it ? Type [Y]/[N] for YES/NO ";

    public static final String INPUT_CHOICE = "Your choice: ";
    public static final String INPUT_NAME = "Product's name: ";
    public static final String INPUT_ID = "Product's ID: ";

    public static final String INPUT_PRICE = "Price: ";
    public static final String INPUT_QUANTITY = "Quantity: ";
    public static final String PRODUCT_FORMAT = "prodID | prodName | prodPrice | prodQuantity | prodStatus";

    public static Scanner sc = new Scanner(System.in);

    public static int checkUpdateQuantity() {
        int quantity = 0;
        String inputQuantity = "";
        boolean keepLoop = false;
        do {
            System.out.println(INPUT_FORMAT_QUANTITY);
            inputQuantity = checkFormat("Product quantity: ", REGEX_TAKE_ALL_NOT_BLANK);
            if (Objects.isNull(inputQuantity)) {
                quantity = -1;
                break;
            } else {
                quantity = Integer.parseInt(inputQuantity);
                if (quantity >= 0 && quantity <= 10000) {
                    keepLoop = false;
                    return quantity;
                } else {
                    keepLoop = true;
                }
            }
        } while (keepLoop);
        return quantity;
    }

    public static int checkUpdatePrice() {
        int price = 0;
        String inputPrice = "";
        boolean keepLoop = false;
        do {
            System.out.println(INPUT_FORMAT_PRICE);
            inputPrice = checkFormat("Product price: ", REGEX_TAKE_ALL_NOT_BLANK);
            if (Objects.isNull(inputPrice)) {
                price = -1;
                break;
            } else {
                price = Integer.parseInt(inputPrice);
                if (price >= 0 && price <= 10000) {
                    keepLoop = false;
                    return price;
                } else {
                    keepLoop = true;
                }
            }
        } while (keepLoop);
        return price;
    }

    public static String checkUpdateName() {
        String result = "";
        boolean keepLoop = false;
        do {
            System.out.println(INPUT_FORMAT_NAME);
            result = checkFormat(INPUT_NAME, REGEX_TAKE_ALL_NOT_BLANK);
            if (Objects.isNull(result)) {
                return null;
            } else if (result.length() >= 5) {
                keepLoop = false;
                return result;
            } else {
                keepLoop = true;
            }
        } while (keepLoop);

        return result;
    }

    public static int checkQuantity() {
        String inputQuantity = "";
        int quantity = 0;
        boolean keepLoop = false;
        do {
            System.out.println(INPUT_FORMAT_QUANTITY);
            inputQuantity = checkFormat("Product price: ", REGEX_TAKE_ALL_NOT_BLANK);
            if (inputQuantity == null) {
                keepLoop = true;
            } else {
                quantity = Integer.parseInt(inputQuantity);
                if (quantity >= 0 && quantity <= 10000) {
                    keepLoop = false;
                    return quantity;
                } else {
                    keepLoop = true;
                }
            }
        } while (keepLoop);
        return quantity;
    }

    public static int checkPrice() {
        int price = 0;
        String inputPrice = "";
        boolean keepLoop = false;
        do {
            System.out.println(INPUT_FORMAT_PRICE);
            inputPrice = checkFormat("Product price: ", REGEX_TAKE_ALL_NOT_BLANK);
            if (inputPrice == null) {
                keepLoop = true;
            } else {
                price = Integer.parseInt(inputPrice);
                if (price >= 0 && price <= 10000) {
                    keepLoop = false;
                    return price;
                } else {
                    keepLoop = true;
                }
            }
        } while (keepLoop);
        return price;
    }

    public static String checkId(String id) {
        String result = "";
        boolean keepLoop = false;
        do {
            if (id == null) {
                System.out.println(INPUT_FORMAT_ID);
                result = checkFormat("ID of new dealer: ", ID_FORMAT);
                if (result != null) {
                    keepLoop = false;
                    return result;
                } else {
                    keepLoop = true;
                }
            } else {
                return id;
            }
        } while (keepLoop);

        return result;
    }

    public static String checkName() {
        String result = "";
        boolean keepLoop = false;
        do {
            System.out.println(INPUT_FORMAT_NAME);
            result = checkFormat(INPUT_NAME, REGEX_TAKE_ALL_NOT_BLANK);
            if (Objects.isNull(result)) {
                keepLoop = true;
            } else if (result.length() >= 5) {
                keepLoop = false;
            } else {
                keepLoop = true;
            }
        } while (keepLoop);

        return result;
    }

    public static String checkStatus(String status) {
        boolean keepLoop = true;
        String result;
        if (Objects.isNull(status)) {
            return null;
        } else {
            int enable = status.compareToIgnoreCase("Y");
            int disable = status.compareToIgnoreCase("N");
            do {
                if (enable == 0) {
                    result = "Available";
                    keepLoop = false;
                    return result;
                } else if (disable == 0) {
                    result = "Disavailable";
                    keepLoop = false;
                    return result;
                } else {
                    System.out.println(INPUT_FORMAT_STATUS);
                    System.out.print(INPUT_CHOICE);
                    keepLoop = true;
                    return result = checkStatus(sc.nextLine().trim());
                }
            } while (keepLoop);

        }
    }

    public static String checkFormat(String data, String regex) {
        System.out.print(data);
        String result = sc.nextLine().trim();
        if (result.matches(regex)) {
            return result;
        } else {
            return null;
        }
    }

    public static boolean checkEmpyList(ArrayList<Product> list) {
        if (list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static int isContinue() {
        System.out.println(IS_CONTINUE);
        System.out.print(INPUT_CHOICE);
        String choice = sc.nextLine();
        boolean keepLoop = true;
        int y = choice.compareToIgnoreCase("Y");
        int n = choice.compareToIgnoreCase("N");
        do {
            if (y == 0) {
                keepLoop = false;
                return 1;
            } else if (n == 0) {
                keepLoop = false;
                return -1;
            } else {
                keepLoop = true;
                return isContinue();
            }
        } while (keepLoop);
    }
}
