/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.Service;

import Day9.ConnectDatabase.StockExchange.DAO.TraderDAO;
import Day9.ConnectDatabase.StockExchange.Model.Trader;

/**
 *
 * @author NguyenHieu
 */
public class RegisterService {
    public boolean register(String name, String password,String address,String gmail, long money){
        Trader trader;
        TraderDAO traderDAO = new TraderDAO();
        trader = traderDAO.getTraderByGmail(gmail);
        if(trader != null){
            return false;
        }else{
            trader = new Trader(name, password, address, gmail, money);
            traderDAO.insertTrader(trader);
            return true;
        }
    }
    public static void main(String[] args) {
        RegisterService registerService = new RegisterService();
        registerService.register("Nguyen Huu Thi Hieu", "123456", "Hà Nội", "nguyenhuuthihieu@gmail.com", 1000000);
        registerService.register("Nguyen Huu Toan", "123456", "Hà Nội", "nguyenhuutoan@gmail.com", 10000000);
        registerService.register("Nguyen Thi Loan", "123456", "Hà Nội", "nguyenthiloan@gmail.com", 900000);
        registerService.register("Nguyen Huu Thi Duyen", "123456", "Hà Nội", "nguyenhuuthiduyen@gmail.com", 900000);
        registerService.register("Nguyen Thi Hop", "123456", "Hà Nội", "nguyenthihop@gmail.com", 100000);
        registerService.register("Nguyen Thi Hanh", "123456", "Hà Nội", "nguyenthihanh@gmail.com", 100000);
    }
}
