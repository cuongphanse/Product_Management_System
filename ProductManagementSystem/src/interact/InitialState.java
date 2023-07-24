/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interact;

import Valid.CheckTool;
import client.Menu;
import data_object.ProductListProxy;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class InitialState implements IState {

    Scanner sc = new Scanner(System.in);
    String[] options = {"Print", "Create Product", "Check exits Product", "Search Product's infomation by Name", "Update Product", "Save Product to file", "Print list Product form the file"};
    Menu menu = new Menu(options);
    int choice = 0;
    int flag = 0;
    boolean keepLoop = false;

    ProductListProxy prod = new ProductListProxy();

    public InitialState() {
        run();
    }

    @Override
    public int keepAsking() {
        return CheckTool.isContinue();
    }

    @Override
    public void run() {
        do {
            choice = menu.getChoice();
            keepLoop = choice > 0 && choice < menu.size();
            switch (choice) {
                case 1:
                    prod.printList();
                    flag = keepAsking();
                    break;
                case 2:
                    prod.createProduct();
                    flag = keepAsking();
                    break;
                case 3:
                    prod.checkProduct();
                    break;
                case 4:
                    prod.searchProductByName();
                    flag = keepAsking();
                    break;
                case 5:
                    prod.updateProductChoice();
                    flag = keepAsking();
                    break;
                case 6:
                    prod.saveProductToFile();
                    flag = keepAsking();
                    break;
                case 7:
                    break;
                default:
                    System.out.println("======FINISHED======");
            }
        } while (keepLoop && flag == 1);
        if (flag== -1) System.out.println("======FINISHED======");
    }
}
