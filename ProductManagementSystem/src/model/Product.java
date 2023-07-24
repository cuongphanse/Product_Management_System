/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Product {

    private String prodID;
    private String prodName;
    private int prodPrice;
    private int prodQuantity;
    private String prodStatus;

    public Product(String prodID, String prodName, int prodPrice, int prodQuantity, String prodStatus) {
        this.prodID = prodID;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodQuantity = prodQuantity;
        this.prodStatus = prodStatus;
    }

    public String getProdID() {
        return prodID;
    }

    public String getProdName() {
        return prodName;
    }

    public int getProdPrice() {
        return prodPrice;
    }

    public int getProdQuantity() {
        return prodQuantity;
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setprodID(String prodID) {
        this.prodID = prodID;
    }

    public void setProdPrice(int prodPrice) {
        this.prodPrice = prodPrice;
    }

    public void setProdQuantity(int prodQuantity) {
        this.prodQuantity = prodQuantity;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    @Override
    public String toString() {
        return "" + String.format("%-8s|%-20s|%-10d|%-13d|%-15s",prodID,prodName,prodPrice,prodQuantity, prodStatus);
    }

}
