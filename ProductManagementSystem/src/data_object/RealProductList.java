/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_object;

import Notify.Messages;
import Valid.CheckTool;
import action.ConfigReader;
import action.fileReader;
import java.util.ArrayList;
import java.util.Scanner;
import model.Product;

/**
 *
 * @author ASUS
 */
public class RealProductList extends ArrayList<Product> implements IProduct {

    public static boolean changed = false;

    ConfigReader config = new ConfigReader();
    fileReader csv = new fileReader();

    String productFile = config.getProductFile();
    Scanner sc = new Scanner(System.in);

    public RealProductList() {
        initial();
    }

    private void initial() {
        initialDealerList();
    }

    private void initialDealerList() {
        ArrayList<Product> temp = csv.readProductFile(productFile);
        for (int i = 0; i < temp.size(); i++) {
            this.add(temp.get(i));
        }
    }

    public int searchProduct(String ID) {
        for (int i = 1; i < this.size(); i++) {
            if (ID.equalsIgnoreCase(this.get(i).getProdID())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void printList() {
        for (int i = 0; i < this.size(); i++) {
            System.out.println(this.get(i));
        }
    }

    @Override
    public void createProduct() {
        String id, name, status;
        int price, quantity;
        System.out.println(CheckTool.INPUT_ID);
        id = sc.nextLine();
        System.out.println(CheckTool.INPUT_NAME);
        name = sc.nextLine();
        System.out.println(CheckTool.INPUT_PRICE);
        price = Integer.parseInt(sc.nextLine());
        System.out.println(CheckTool.INPUT_QUANTITY);
        quantity = Integer.parseInt(sc.nextLine());
        System.out.println(CheckTool.INPUT_FORMAT_STATUS);
        status = sc.nextLine();
        this.add(new Product(id, name, price, quantity, status));
        System.out.println("NEW DEALER HAS BEEN ADDED!");
        changed = true;
    }

    @Override
    public int checkProduct() {
        String pName;
        System.out.println(CheckTool.INPUT_NAME);
        pName = sc.nextLine();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProdName().equalsIgnoreCase(pName)) {
                System.out.println(Messages.NOTIFY_CHECK_FOUND);
                return i;
            }
        }
        System.out.println(Messages.NOTIFY_CHECK_NOT_FOUND);
        return -1;
    }

    @Override
    public int searchProductByName() {
        String pName;
        System.out.println(CheckTool.INPUT_NAME);
        pName = sc.nextLine().toUpperCase();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProdName().toUpperCase().contains(pName)) {
                System.out.println(this.get(i));
                return i;
            }
        }
        System.out.println(Messages.NOTIFY_CHECK_NOT_FOUND);
        return -1;
    }

    @Override
    public void updateProduct() {
        String uID, uName, uStatus;
        int uPrice, uQuantity;
        System.out.println("Which ID's Dealer you want to update: ");
        uID = sc.nextLine();
        int pos = searchProduct(uID);
        if (pos < 0) {
            System.out.println("Dealer " + uID + " not Found!");
        } else {
            Product getD = this.get(pos);
            System.out.println(CheckTool.INPUT_FORMAT_ID);
            uID = sc.nextLine();
            System.out.println(CheckTool.INPUT_NAME);
            uName = sc.nextLine();
            System.out.println(CheckTool.INPUT_PRICE);
            uPrice = Integer.parseInt(sc.nextLine());
            System.out.println(CheckTool.INPUT_QUANTITY);
            uQuantity = Integer.parseInt(sc.nextLine());
            System.out.println(CheckTool.INPUT_FORMAT_STATUS);
            uStatus = sc.nextLine();
            getD.setProdName(uName);
            getD.setProdPrice(uPrice);
            getD.setProdPrice(uQuantity);
            getD.setProdStatus(uStatus);
            System.out.println("NEW DEALER HAS BEEN UPDATE!");
            changed = true;
        }
    }

    @Override
    public void removeProduct() {
        String rID;
        System.out.println(CheckTool.INPUT_ID);
        rID = sc.nextLine();
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getProdID().equalsIgnoreCase(rID)) {
                this.remove(i);
            }
        }
    }



}
