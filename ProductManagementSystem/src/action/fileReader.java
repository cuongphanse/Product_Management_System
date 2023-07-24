/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package action;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Product;

/**
 *
 * @author ASUS
 */
public class fileReader {

    private final String SEPARATE = "\\|";

    public ArrayList<Product> readProductFile(String csvFile) {
        ArrayList<Product> productList = new ArrayList();
        try {
            FileReader fr = new FileReader(csvFile);
            BufferedReader bf = new BufferedReader(fr);
            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] temp = line.split(SEPARATE);
                String id = temp[0].trim();
                String name = temp[1].trim();
                int price = Integer.parseInt(temp[2].trim()); 
                int quantity  = Integer.parseInt(temp[3].trim()); 
                String status = temp[4].trim();
                productList.add(new Product(id, name, price, quantity, status));
            }
            fr.close();
            bf.close();
        } catch (IOException e) {
            System.out.println("Cannot read csv.file");
        }
        return productList;
    }


    public void writeToProductFile(String dealerFile, ArrayList<Product> dealerList) {
        try {
            FileWriter fw = new FileWriter(dealerFile);
            PrintWriter pw = new PrintWriter(fw);
            for (Product x : dealerList) {
                pw.println(x.getProdID()+ " | " + x.getProdName()+ " | " + x.getProdPrice() + " | " + x.getProdQuantity()+ " | " + x.getProdStatus());
            }
            pw.close();
            System.out.println("WRITE TO FILE DONE!");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
