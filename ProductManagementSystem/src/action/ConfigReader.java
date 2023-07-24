/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package action;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author ASUS
 */
public class ConfigReader {
    private static final String configFile = "config.properties";
    private String productFile; 
    public ConfigReader() {
        readData();
    }
    
    private void readData() {
        try {
            FileReader fr = new FileReader(configFile);
            BufferedReader bf = new BufferedReader(fr);
            String line = "";
            while ((line = bf.readLine()) != null) {
                String[] temp = line.split("=");
                line = line.toLowerCase();
                if(line.contains("product")) productFile = temp[1].trim();
            }
            fr.close();
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setProductFile(String productFile) {
        this.productFile = productFile;
    }

    public String getProductFile() {
        return productFile;
    }
    
}
