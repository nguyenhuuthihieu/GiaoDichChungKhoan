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
public class TradeOrder implements Comparable<TradeOrder>{
    private int idTrader;
    private int idStock;
    private int cost;
    private int numberStock;

    public TradeOrder(int idTrader,int idStock, int cost, int numberStock) {
        this.idTrader = idTrader;
        this.idStock = idStock;
        this.cost = cost;
        this.numberStock = numberStock;
    }

    public void setIdStock(int idStock) {
        this.idStock = idStock;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setNumberStock(int numberStock) {
        this.numberStock = numberStock;
    }

    public int getIdTrader() {
        return idTrader;
    }

    public void setIdTrader(int idTrader) {
        this.idTrader = idTrader;
    }

    public int getIdStock() {
        return idStock;
    }

    public int getCost() {
        return cost;
    }

    public int getNumberStock() {
        return numberStock;
    }

    @Override
    public int compareTo(TradeOrder o) {
        return o.getCost() - this.getCost();
    }
    
}
