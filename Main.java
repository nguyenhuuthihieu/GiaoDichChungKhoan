/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange;

import Day9.ConnectDatabase.StockExchange.Service.SellBuyService;
import Day9.ConnectDatabase.StockExchange.View.LoginView;

/**
 *
 * @author NguyenHieu
 */
public class Main extends Thread{
    public void run(){
        new LoginView().show();
    }
    public void run(int i){
        
    }
    public static void main(String args[]) {
        Main t1 = new Main();
        t1.start();
        SellBuyService t2 = new SellBuyService();
        t2.start();
    }
}
