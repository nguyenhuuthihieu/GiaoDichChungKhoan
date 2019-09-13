/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.Model;

/**
 *
 * @author NguyenHieu
 */
public class Stock {
    private int idStock;
    private String name;
    private int NumStock;

    public Stock(String name) {
        this.name = name;
    }

    public Stock(String name, int NumStock) {
        this.name = name;
        this.NumStock = NumStock;
    }

    public Stock(int idStock, String name) {
        this.idStock = idStock;
        this.name = name;
    }

    public Stock(int idStock, String name, int NumStock) {
        this.idStock = idStock;
        this.name = name;
        this.NumStock = NumStock;
    }
    
    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumStock() {
        return NumStock;
    }

    public void setNumStock(int NumStock) {
        this.NumStock = NumStock;
    }
    

    public int getIdStock() {
        return idStock;
    }

    public String getName() {
        return name;
    }

 
}
