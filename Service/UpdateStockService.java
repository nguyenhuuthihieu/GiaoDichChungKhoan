/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.Service;

import Day9.ConnectDatabase.StockExchange.DAO.StockDAO;
import Day9.ConnectDatabase.StockExchange.DAO.TraderDAO;
import Day9.ConnectDatabase.StockExchange.DAO.TraderOrderDAO;
import Day9.ConnectDatabase.StockExchange.Model.Stock;
import Day9.ConnectDatabase.StockExchange.Model.TradeOrder;
import Day9.ConnectDatabase.StockExchange.Model.Trader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NguyenHieu
 */
public class UpdateStockService {
    public int insertStock(String name, int NumStock, String gmail){
        StockDAO stockDAO = new StockDAO();
        TraderDAO traderDAO = new TraderDAO();
        TraderOrderDAO traderOrderDAO = new TraderOrderDAO();
        TradeOrder tradeOrder = null;
        Stock stock = stockDAO.getStockByName(name);
        Trader trader = traderDAO.getTraderByGmail(gmail);
        if(trader == null || traderDAO.getRole(trader) == 2){
            return -1;
        }else if(stock != null){
            return 0;
        }else{
            stock = new Stock(name, NumStock);
            stockDAO.insertStock(stock);
            stock = stockDAO.getStockByName(name);
            tradeOrder = new TradeOrder(trader.getIdTrader(), stock.getIdStock(), 0, NumStock);
            traderDAO.updateTrader_Stock(tradeOrder);
            return 1;
        }
        
    }
    public ArrayList<Stock> getStocks(Trader trader){
        List<Stock> list = new ArrayList<Stock>();
        TraderOrderDAO traderOrderDAO = new TraderOrderDAO();
        StockDAO stockDAO = new StockDAO();
        for(Integer i : traderOrderDAO.getIdStockOfTrader(trader)){
            list.add(stockDAO.getStock(i.intValue()));
        }
        return (ArrayList<Stock>) list;
    }
    public ArrayList<Stock> getStocks(){
        List<Stock> list = new ArrayList<Stock>();
        TraderOrderDAO traderOrderDAO = new TraderOrderDAO();
        StockDAO stockDAO = new StockDAO();
        for(Integer i : traderOrderDAO.getIdStockOfTrader()){
            list.add(stockDAO.getStock(i.intValue()));
        }
        return (ArrayList<Stock>) list;
    } 
    public static void main(String[] args) {
//        UpdateStockService uss = new UpdateStockService();
//        uss.insertStock("Luvina");
    }
}
