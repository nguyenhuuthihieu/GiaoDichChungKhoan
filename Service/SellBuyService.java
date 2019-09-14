/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.Service;

import Day9.ConnectDatabase.StockExchange.DAO.BuySellDAO;
import Day9.ConnectDatabase.StockExchange.DAO.TraderDAO;
import Day9.ConnectDatabase.StockExchange.DAO.TraderOrderDAO;
import Day9.ConnectDatabase.StockExchange.Model.Stock;
import Day9.ConnectDatabase.StockExchange.Model.TradeOrder;
import Day9.ConnectDatabase.StockExchange.Model.Trader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NguyenHieu
 */
public class SellBuyService {
    PriorityQueue<TradeOrder> buy = new PriorityQueue<TradeOrder>(new Comparator<TradeOrder>(){
        @Override
        public int compare(TradeOrder o1, TradeOrder o2) {
            return o1.compareTo(o2);
        }
    });
    PriorityQueue<TradeOrder> sell = new PriorityQueue<TradeOrder>(new Comparator<TradeOrder>(){
        @Override
        public int compare(TradeOrder o1, TradeOrder o2) {
            return o2.compareTo(o1);
        }
    });

    public SellBuyService() {
        BuySellDAO buySellDAO = new BuySellDAO();
        buy = buySellDAO.getBuy();
        sell = buySellDAO.getSell();
    }
    
    public int buyStock(Trader trader, Stock stock, int cost, int numberStock){
        TradeOrder order = new TradeOrder(trader.getIdTrader(), stock.getIdStock(), cost, numberStock);
        TraderDAO traderDAO = new TraderDAO();
        TraderOrderDAO traderOrderDAO = new TraderOrderDAO();
        TradeOrder tradeOrder = traderOrderDAO.getTraderOrder(trader.getIdTrader(), stock.getIdStock());
        int stocks = 0;
        if(tradeOrder  != null) stocks = tradeOrder.getNumberStock();
        int Numstock = stock.getNumStock() - stocks;
        if(order.getNumberStock() * order.getCost() <= trader.getMoney() && numberStock <= Numstock ){
            buy.add(order);
            BuySellDAO buySellDAO = new BuySellDAO();
            buySellDAO.addBuy(order);
            return 1;
        }else if(numberStock > Numstock) return 0;// không đủ số stock còn lại để mua
        return -1;// không đủ tiền để mua
    }
    public boolean sellStock(Trader trader, Stock stock, int cost, int numberStock){
        TradeOrder order = new TradeOrder(trader.getIdTrader(), stock.getIdStock(), cost, numberStock);
        TraderOrderDAO tradeOrderDAO = new TraderOrderDAO();
        TradeOrder t = tradeOrderDAO.getTraderOrder(trader.getIdTrader(), stock.getIdStock());
        if(t.getNumberStock() >= numberStock){
            sell.add(order);
            BuySellDAO buySellDAO = new BuySellDAO();
            buySellDAO.addSell(order);
            return true;
        }
        return false;
    }
    public ArrayList<TradeOrder> getTradeOrders(){
        TraderOrderDAO traderOrderDAO = new TraderOrderDAO();
        ArrayList<TradeOrder> list = traderOrderDAO.getTradeOrders();
        Collections.sort(list, new Comparator<TradeOrder>(){
            @Override
            public int compare(TradeOrder o1, TradeOrder o2) {
                return o2.getNumberStock()-o1.getNumberStock();
            }
        });
        return list;
    }
    public PriorityQueue<TradeOrder> getSellStock (){
        return sell;
    }
    public PriorityQueue<TradeOrder> getBuyStock (){
        return buy;
    }
    public void sellBuy(){
        TraderDAO traderDAO = new TraderDAO();
        TraderOrderDAO traderOrderDAO = new TraderOrderDAO();
        List<TradeOrder> buyStock = new ArrayList<TradeOrder>();
        buyStock.addAll(buy);
        Collections.sort(buyStock,new Comparator<TradeOrder>(){
            @Override
            public int compare(TradeOrder o1, TradeOrder o2) {
                return o1.getIdStock() - o2.getIdStock();
            }
        });
        List<TradeOrder> sellStock = new ArrayList<TradeOrder>();
        sellStock.addAll(sell);
        Collections.sort(sellStock,new Comparator<TradeOrder>(){
            @Override
            public int compare(TradeOrder o1, TradeOrder o2) {
                return o1.getIdStock() - o2.getIdStock();
            }
        });
        PriorityQueue<TradeOrder> buyFake = new PriorityQueue<TradeOrder>(new Comparator<TradeOrder>(){
            @Override
            public int compare(TradeOrder o1, TradeOrder o2) {
                return o1.compareTo(o2);
            }
        });
        PriorityQueue<TradeOrder> sellFake = new PriorityQueue<TradeOrder>(new Comparator<TradeOrder>(){
            @Override
            public int compare(TradeOrder o1, TradeOrder o2) {
                return o2.compareTo(o1);
            }
        });
        TradeOrder tmpB = null;
        TradeOrder tmpS = null;
        while(!sellStock.isEmpty() && !buyStock.isEmpty() ){
            tmpB = buyStock.get(0);
            tmpS = sellStock.get(0);
            TradeOrder tradeOrderBuy = traderOrderDAO.getTraderOrder(tmpB.getIdTrader(), tmpB.getIdStock());
            TradeOrder tradeOrderSell = traderOrderDAO.getTraderOrder(tmpS.getIdTrader(), tmpS.getIdStock());
            Trader traderBuy = traderDAO.getTrader(tmpB.getIdTrader());
            Trader traderSell = traderDAO.getTrader(tmpS.getIdTrader());  
            if(tmpB.getIdStock() == tmpS.getIdStock()){
                if(tmpB.getCost() >= tmpS.getCost()){
                    if(tmpB.getNumberStock() > tmpS.getNumberStock()){
                        long moneyB = traderBuy.getMoney();
                        long moneyS = traderSell.getMoney();
                        int tmp = buyStock.get(0).getNumberStock();
                        traderBuy.setMoney(moneyB - tmpS.getCost()*tmpS.getNumberStock());
                        traderSell.setMoney(moneyS + tmpS.getCost()*tmpS.getNumberStock());
                        if(tradeOrderBuy!= null){
                            int stocks = tradeOrderBuy.getNumberStock();
                            tradeOrderBuy.setNumberStock(stocks + tmpS.getNumberStock());
                            tradeOrderBuy.setCost(tmpS.getCost());
                        }else tradeOrderBuy= new TradeOrder(tmpB.getIdTrader(), tmpB.getIdStock(), tmpS.getCost(), tmpS.getNumberStock());
                        int stocksell = tradeOrderSell.getNumberStock();
                        tradeOrderSell.setNumberStock(stocksell- tmpS.getNumberStock());
                        tradeOrderSell.setCost(tmpS.getCost());
                        buyStock.get(0).setNumberStock(tmp - tmpS.getNumberStock());
                        sellStock.remove(0);
                    } else if( tmpB.getNumberStock() < tmpS.getNumberStock()){
                        int tmp = sellStock.get(0).getNumberStock();
                        int numberS = tmpS.getNumberStock();
                        int numberB = tmpB.getNumberStock();
                        long moneyB = traderBuy.getMoney();
                        long moneyS = traderSell.getMoney();
                        traderBuy.setMoney(moneyB - tmpS.getCost()*numberB);
                        traderSell.setMoney(moneyS + tmpS.getCost()*numberB);
                        if(tradeOrderBuy!= null){
                            int stockbuy = tradeOrderBuy.getNumberStock();
                            tradeOrderBuy.setNumberStock(stockbuy + tmpB.getNumberStock());
                            tradeOrderBuy.setCost(tmpS.getCost());
                        }else tradeOrderBuy= new TradeOrder(tmpB.getIdTrader(), tmpB.getIdStock(), tmpS.getCost(), tmpB.getNumberStock());
                        int stocksell = tradeOrderSell.getNumberStock();
                        tradeOrderSell.setNumberStock(stocksell- tmpB.getNumberStock());
                        tradeOrderSell.setCost(tmpS.getCost());
                        sellStock.get(0).setNumberStock(tmp - tmpB.getNumberStock());
                        buyStock.remove(0);
                    } else {
                        int numberS = tmpS.getNumberStock();
                        long moneyB = traderBuy.getMoney();
                        long moneyS = traderSell.getMoney();
                        traderBuy.setMoney(moneyB - tmpS.getCost()*tmpB.getNumberStock());
                        traderSell.setMoney(moneyS + tmpS.getCost()*tmpB.getNumberStock());
                        if(tradeOrderBuy!= null){
                            int stockbuy = tradeOrderBuy.getNumberStock();
                            tradeOrderBuy.setNumberStock(stockbuy + tmpS.getNumberStock());
                            tradeOrderBuy.setCost(tmpS.getCost());
                        }else tradeOrderBuy= new TradeOrder(tmpB.getIdTrader(), tmpB.getIdStock(), tmpS.getCost(), tmpS.getNumberStock());
                        tradeOrderSell.setNumberStock(0);
                        buyStock.remove(0);
                        sellStock.remove(0);
                    }
                    traderDAO.updateTrader(traderSell);
                    traderDAO.updateTrader(traderBuy);
                    traderDAO.updateTrader_Stock(tradeOrderSell);
                    traderDAO.updateTrader_Stock(tradeOrderBuy);
                } else if(tmpB.getCost() < tmpS.getCost()){
                    sellFake.add(tmpS);
                    sellStock.remove(0);
                    buyFake.add(tmpB);
                    buyStock.remove(0);
                }
            } else if(tmpB.getIdStock() > tmpS.getIdStock()){
                sellFake.add(tmpS);
                sellStock.remove(0);
            } else {
                buyFake.add(tmpB);
                buyStock.remove(0);
            }
        }
        buyFake.addAll(buyStock);
        sellFake.addAll(sellStock);
        BuySellDAO buySellDAO = new BuySellDAO();
        buySellDAO.updateTableBuy(buyFake);
        buySellDAO.updateTableSell(sellFake);
        sell = sellFake;
        buy = buyFake;
        
    }
    public void sell_buy(){
        TraderDAO traderDAO = new TraderDAO();
        TraderOrderDAO traderOrderDAO = new TraderOrderDAO();
        TradeOrder tmpB = null;
        TradeOrder tmpS = null;
        
        while(!buy.isEmpty() && !sell.isEmpty()|| (tmpB!=null &&  !sell.isEmpty()) || (tmpS!=null &&  !buy.isEmpty()) ){
            if(tmpB == null) {
                tmpB = buy.remove();
            }
            if(tmpS == null){
                tmpS = sell.remove();
            }
            if(tmpB.getCost() >= tmpS.getCost() ){
                TradeOrder B = traderOrderDAO.getTraderOrder(tmpB.getIdTrader(), tmpB.getIdStock());
                TradeOrder S = traderOrderDAO.getTraderOrder(tmpS.getIdTrader(), tmpS.getIdStock());
                Trader traderB = traderDAO.getTrader(tmpB.getIdTrader());
                Trader traderS = traderDAO.getTrader(tmpS.getIdTrader());
                if(tmpB.getNumberStock() > tmpS.getNumberStock()){
                    int numberB = tmpB.getNumberStock();
                    long moneyB = traderB.getMoney();
                    long moneyS = traderS.getMoney();
                    traderB.setMoney(moneyB - tmpS.getCost()*tmpS.getNumberStock());
                    traderS.setMoney(moneyS + tmpS.getCost()*tmpS.getNumberStock());
                    if(B!= null)
                        B.setNumberStock(B.getNumberStock() + tmpS.getNumberStock());
                    else B= new TradeOrder(tmpB.getIdTrader(), tmpB.getIdStock(), tmpS.getCost(), tmpS.getNumberStock());
                    S.setNumberStock(S.getNumberStock() - tmpS.getNumberStock());
                    tmpB.setNumberStock(numberB - tmpS.getNumberStock());
                    tmpS = null;
                } else if(tmpB.getNumberStock() < tmpS.getNumberStock()){
                    int numberS = tmpS.getNumberStock();
                    int numberB = tmpB.getNumberStock();
                    long moneyB = traderB.getMoney();
                    long moneyS = traderS.getMoney();
                    traderB.setMoney(moneyB - tmpS.getCost()*numberB);
                    traderS.setMoney(moneyS + tmpS.getCost()*numberB);
                    if(B!= null)
                        B.setNumberStock(B.getNumberStock() + tmpB.getNumberStock());
                    else B= new TradeOrder(tmpB.getIdTrader(), tmpB.getIdStock(), tmpS.getCost(), tmpB.getNumberStock());
                    S.setNumberStock(S.getNumberStock() - tmpB.getNumberStock());
                    tmpS.setNumberStock(numberS - tmpB.getNumberStock());
                    tmpB = null;
                } else {
                    int numberS = tmpS.getNumberStock();
                    long moneyB = traderB.getMoney();
                    long moneyS = traderS.getMoney();
                    traderB.setMoney(moneyB - tmpS.getCost()*tmpB.getNumberStock());
                    traderS.setMoney(moneyS + tmpS.getCost()*tmpB.getNumberStock());
                    if(B!= null)
                        B.setNumberStock(B.getNumberStock() + tmpS.getNumberStock());
                    else B= new TradeOrder(tmpB.getIdTrader(), tmpB.getIdStock(), tmpS.getCost(), tmpS.getNumberStock());
                    S.setNumberStock(0);
                    tmpS = null;
                    tmpB = null;
                }
                traderDAO.updateTrader(traderS);
                traderDAO.updateTrader(traderB);
                traderDAO.updateTrader_Stock(S);
                traderDAO.updateTrader_Stock(B);
            }
            else return;
        }
        if(tmpS != null) sell.add(tmpS);
        if(tmpB != null) buy.add(tmpB);
    }
    public void integratedSB(){
        while(true){
            try {
                Thread.sleep(300000);
                sellBuy();
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException: " + ex.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        SellBuyService sb = new SellBuyService();
        TraderDAO t = new TraderDAO();
//        TradeOrder t1 = new TradeOrder(1, 1, 0, 40);
//        TradeOrder t2 = new TradeOrder(2, 1, 0, 30);
//        TradeOrder t3 = new TradeOrder(3, 1, 0, 30);
//        t.updateTrader_Stock(t1);
//        t.updateTrader_Stock(t2);
//        t.updateTrader_Stock(t3);
//        sb.buy.add(new TradeOrder(4, 1, 10000, 3));
//        sb.sell.add(new TradeOrder(2, 1, 8000, 8));
//        sb.buy.add(new TradeOrder(5, 1, 11000, 4));
//        sb.buy.add(new TradeOrder(3, 2, 12000, 4));
//        sb.buy.add(new TradeOrder(5, 2, 1000, 4));
//        sb.sell.add(new TradeOrder(4, 2, 5000, 1));
//        List<TradeOrder> b = new ArrayList<TradeOrder>();
//        b.addAll(sb.buy);
//        Collections.sort(b,new Comparator<TradeOrder>(){
//            @Override
//            public int compare(TradeOrder o1, TradeOrder o2) {
//                return o1.getIdStock() - o2.getIdStock();
//            }
//            
//        });
//        TradeOrder[] se = sb.sell.toArray(new TradeOrder[sb.sell.size()]);
//        
        sb.sellBuy();
        while (!sb.sell.isEmpty()) {
            System.out.println(sb.sell.remove().getCost());
        }
        System.out.println("");
        int s = 0;
        while (!sb.buy.isEmpty()) {
            s++;
            System.out.println(s+ " " +sb.buy.remove().getCost());
        }
        
    }
}
