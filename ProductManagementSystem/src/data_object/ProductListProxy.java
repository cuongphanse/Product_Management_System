/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change temp license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit temp template
 */
package data_object;

import action.ConfigReader;
import action.fileReader;
import java.util.ArrayList;
import java.util.Scanner;
import model.Product;
import Notify.Messages;
import Valid.CheckTool;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class ProductListProxy implements IProduct {

    ArrayList<Product> temp = new ArrayList();
    public static boolean changed = false;
    Scanner sc = new Scanner(System.in);
    ConfigReader config = new ConfigReader();
    fileReader csv = new fileReader();

    String productFile = config.getProductFile();

    public ProductListProxy() {
        initial();
    }

    private void initial() {
        temp = csv.readProductFile(productFile);
    }

    Comparator AscQuantity_DecPrice = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Product p1 = (Product) o1;
            Product p2 = (Product) o2;
            int result = -1 * (p1.getProdQuantity() - (p2.getProdQuantity()));
            if (result == 0) {
                result = p1.getProdPrice() - p2.getProdPrice();
            }
            return result;
        }
    };
    Comparator AscName = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            Product p1 = (Product) o1;
            Product p2 = (Product) o2;
            int result = p1.getProdName().compareTo(p2.getProdName());
            return result;
        }
    };

    public boolean isChanged() {
        boolean keepLoop = false;
        do {
            if (changed) {
                System.out.print(INPUT_FORMAT_SAVE);
                String confirm = sc.nextLine().trim().toLowerCase();
                if (confirm.equals("y")) {
                    System.out.print(Messages.UPLOAD);
                    csv.writeToProductFile(productFile, temp);
                    keepLoop = false;
                    return true;
                } else if (confirm.equals("n")) {
                    keepLoop = false;
                    return false;
                } else {
                    keepLoop = true;
                }
            }
        } while (keepLoop);
        return false;
    }

    public int searchProduct(String ID) {
        for (int i = 0; i < temp.size(); i++) {
            if (ID.equalsIgnoreCase(temp.get(i).getProdID())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void printList() {
        Collections.sort(temp, AscQuantity_DecPrice);
        if (CheckTool.checkEmpyList(temp)) {
            System.out.println("Product list is empty!");
        } else {
            System.out.println(PRODUCT_FORMAT);
            for (int i = 0; i < temp.size(); i++) {
                System.out.println(temp.get(i));
            }
        }
    }

    @Override
    public void createProduct() {
        String id, name, status;
        int price, quantity;
        int pos;
        do {
            System.out.println(INPUT_FORMAT_ID);
            id = CheckTool.checkId(CheckTool.checkFormat("Product's ID: ", ID_FORMAT));
            id = id.toUpperCase();
            pos = searchProduct(id);
            if (pos != -1) {
                System.out.println(Messages.NOTIFY_DUPLICATE_ID);
            }
        } while (pos != -1);
        name = CheckTool.checkName();
        price = CheckTool.checkPrice();
        quantity = CheckTool.checkQuantity();
        System.out.println(INPUT_FORMAT_STATUS);
        System.out.print(INPUT_CHOICE);
        status = CheckTool.checkStatus(sc.nextLine().trim());
        temp.add(new Product(id, name, price, quantity, status));
        System.out.println("NEW DEALER HAS BEEN ADDED!");
        changed = true;
    }

    @Override
    public int checkProduct() {
        String pName;
        System.out.print(INPUT_NAME);
        pName = sc.nextLine();
        for (int i = 0; i < temp.size(); i++) {
            if (temp.get(i).getProdName().equalsIgnoreCase(pName)) {
                System.out.println(Messages.NOTIFY_CHECK_FOUND);
                return i;
            }
        }
        System.out.println(Messages.NOTIFY_CHECK_NOT_FOUND);
        return -1;
    }

    @Override
    public int searchProductByName() {
        int flag = 0;
        if (CheckTool.checkEmpyList(temp)) {
            System.out.println("Have no any Product!");
        } else {

            Collections.sort(temp, AscName);
            String pName;
            System.out.println(INPUT_NAME);
            pName = sc.nextLine().toUpperCase();
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getProdName().toUpperCase().contains(pName.toUpperCase())) {
                    System.out.println(temp.get(i));
                    flag = 1;
                }
            }
            if (flag != 1) {
                System.out.println(Messages.NOTIFY_CHECK_NOT_FOUND);
            }
        }
        return -1;
    }

    public void updateProductChoice() {
        System.out.println("1. Update Product");
        System.out.println("2. Remove Product");
        System.out.print(INPUT_CHOICE);
        int choice = Integer.parseInt(sc.nextLine());
        switch (choice) {
            case 1:
                updateProduct();
                break;
            case 2:
                removeProduct();
                break;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public void updateProduct() {
        String uID, uName, uStatus;
        int uPrice, uQuantity;
        System.out.println("Which ID's product you want to update: ");
        uID = sc.nextLine();
        int pos = searchProduct(uID);
        if (pos < 0) {
            System.out.println("Product " + uID + " not Found!");
        } else {
            Product getD = temp.get(pos);
            uName = CheckTool.checkUpdateName();
            uPrice = CheckTool.checkUpdatePrice();
            uQuantity = CheckTool.checkUpdateQuantity();

            uStatus = CheckTool.checkStatus(CheckTool.checkFormat(INPUT_FORMAT_STATUS, CheckTool.REGEX_TAKE_ALL_NOT_BLANK));
            if (!Objects.isNull(uName)) {
                getD.setProdName(uName);
            }
            if (uPrice != -1) {
                getD.setProdPrice(uPrice);
            }
            if (uQuantity != -1) {
                getD.setProdQuantity(uQuantity);
            }
            if (!Objects.isNull(uStatus)) {
                getD.setProdStatus(uStatus);
            }
            System.out.println(Messages.NOTIFY_UPDATE_SUCCESS);
            changed = true;
        }
    }

    @Override
    public void removeProduct() {
        int flag = 0;
        if (CheckTool.checkEmpyList(temp)) {
            System.out.println(Messages.NOTIFY_EMPTY_LIST);
        } else {
            String rID;
            System.out.print(INPUT_ID);
            rID = sc.nextLine();
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).getProdID().equalsIgnoreCase(rID)) {
                    temp.remove(i);
                    flag = 1;
                }
            }
             if(flag == 1) System.out.println(Messages.NOTIFY_REMOVE_SUCCESS);
        }
    }

    public void saveProductToFile() {
        csv.writeToProductFile(productFile, temp);
    }



}
