/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.Model;

import Day9.ConnectDatabase.StockExchange.DAO.TraderDAO;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NguyenHieu
 */
public class Trader implements Comparable<Trader>{
    private String name;
    private int idTrader;
    private String password;
    private String address;
    private long money;
    private String gmail;
//    private int idRole;
    private Map<Integer, Stock> listStock = new HashMap<>();

    public Trader(String name, String password, String address, String gmail, long money) {
        this.name = name;
        this.password = password;
        this.address = address;
        this.gmail = gmail;
        this.money = money;
    }
    
    public Trader(String name, int id, String password, String address, String gmail) {
        this.name = name;
        this.idTrader = id;
        this.password = password;
        this.address = address;
        this.money = 0;
        this.gmail = gmail;
    }

    public Trader(String name, int id, String password, String address, long money, String gmail) {
        this.name = name;
        this.idTrader = id;
        this.password = password;
        this.address = address;
        this.money = money;
        this.gmail = gmail;
    }

    public Map<Integer, Stock> getListStock(int idTrader) {
        TraderDAO traderDao = new TraderDAO();
        return traderDao.getListStocks(idTrader);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public int getIdTrader() {
        return idTrader;
    }

    public void setIdTrader(int idTrader) {
        this.idTrader = idTrader;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public long getMoney() {
        return money;
    }

    @Override
    public int compareTo(Trader o) {
        return this.idTrader-o.getIdTrader();
    }
    
}
