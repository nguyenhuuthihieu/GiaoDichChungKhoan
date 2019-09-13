/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.Service;

import Day9.ConnectDatabase.StockExchange.DAO.TraderDAO;
import Day9.ConnectDatabase.StockExchange.Model.Trader;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author NguyenHieu
 */
public class LoginService {
    TraderDAO traderDao = new TraderDAO();
    Map<String, Trader> traders = traderDao.selectTraders();
    Set<Trader> loginTrader = new TreeSet<Trader>();
    private String gmail;
    public int Login(String gmail, String password){
        boolean check = traders.containsKey(gmail);
        if(check){
            this.gmail = gmail;
            Trader trader = traders.get(gmail);
            loginTrader.add(traders.get(gmail));
            return traderDao.getRole(trader);
        }
        return 0;
    }
    public Trader getTrader(){
        return traders.get(gmail);
    }
    public TreeSet<Trader> getLoginTrader() {
        return (TreeSet<Trader>) loginTrader;
    }
    public void logout(Trader trader){
        loginTrader.remove(trader);
    }
    public static void main(String[] args) {
        LoginService loginService = new LoginService();
        System.out.println(loginService.Login("nguyenhuuthihieu@gmail.com", "123456"));
    }
}
