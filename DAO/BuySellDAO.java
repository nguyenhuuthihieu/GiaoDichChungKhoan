/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.DAO;

import Day9.ConnectDatabase.StockExchange.ConnectDB.Connections;
import Day9.ConnectDatabase.StockExchange.Model.Stock;
import Day9.ConnectDatabase.StockExchange.Model.TradeOrder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/**
 *
 * @author NguyenHieu
 */
public class BuySellDAO {
    public void addBuy(TradeOrder tradeOrder){
        Connection connection = Connections.getMysqlConnection();
        String sql = "INSERT INTO Buy(idTrader, idStock, AmountStocks, price) VALUES (?,?,?,?);";
        if(connection != null){
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1,tradeOrder.getIdTrader());
                pre.setInt(2, tradeOrder.getIdStock());
                pre.setInt(3, tradeOrder.getNumberStock());
                pre.setLong(4, tradeOrder.getCost());
                pre.executeUpdate();
                pre.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }
    public void addSell(TradeOrder tradeOrder){
        Connection connection = Connections.getMysqlConnection();
        String sql = "INSERT INTO Sell(idTrader, idStock, AmountStocks, price) VALUES (?,?,?,?);";
        if(connection != null){
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1,tradeOrder.getIdTrader());
                pre.setInt(2, tradeOrder.getIdStock());
                pre.setInt(3, tradeOrder.getNumberStock());
                pre.setLong(4, tradeOrder.getCost());
                pre.executeUpdate();
                pre.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }
    public PriorityQueue<TradeOrder> getBuy(){
        PriorityQueue<TradeOrder> buy = new PriorityQueue<TradeOrder>(new Comparator<TradeOrder>(){
            @Override
            public int compare(TradeOrder o1, TradeOrder o2) {
                return o1.compareTo(o2);
            }
        });
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM Buy;";
        TradeOrder t = null;
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    t = new TradeOrder(rs.getInt("idTrader"),rs.getInt("idStock"), (int) rs.getLong("price"), rs.getInt("AmountStocks"));
                    buy.add(t);
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return buy;
    }
    public PriorityQueue<TradeOrder> getSell(){
        PriorityQueue<TradeOrder> sell = new PriorityQueue<TradeOrder>(new Comparator<TradeOrder>(){
            @Override
            public int compare(TradeOrder o1, TradeOrder o2) {
                return o2.compareTo(o1);
            }
        });
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM Sell;";
        TradeOrder t = null;
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    t = new TradeOrder(rs.getInt("idTrader"),rs.getInt("idStock"), (int) rs.getLong("price"), rs.getInt("AmountStocks"));
                    sell.add(t);
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return sell;
    }
    public void updateTableBuy(PriorityQueue<TradeOrder> tradeOrders){
        Connection connection = Connections.getMysqlConnection();
        String sqlDel = "DELETE FROM Buy;";
        String sqlIns = "INSERT INTO Buy(idTrader, idStock, AmountStocks, price) VALUES (?,?,?,?);";
        if(connection != null){
            try {
                PreparedStatement preDel = connection.prepareStatement(sqlDel);
                preDel.executeUpdate();
                preDel.close();
                Iterator<TradeOrder> t = tradeOrders.iterator();
                while(t.hasNext()){
                    TradeOrder tradeOrder = t.next();
                    PreparedStatement preIns = connection.prepareStatement(sqlIns);
                    preIns.setInt(1,tradeOrder.getIdTrader());
                    preIns.setInt(2, tradeOrder.getIdStock());
                    preIns.setInt(3, tradeOrder.getNumberStock());
                    preIns.setLong(4, tradeOrder.getCost());
                    preIns.executeUpdate();
                    preIns.close();
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }
    public void updateTableSell(PriorityQueue<TradeOrder> tradeOrders){
        Connection connection = Connections.getMysqlConnection();
        String sqlDel = "DELETE FROM Sell;";
        String sqlIns = "INSERT INTO Sell(idTrader, idStock, AmountStocks, price) VALUES (?,?,?,?);";
        if(connection != null){
            try {
                PreparedStatement preDel = connection.prepareStatement(sqlDel);
                preDel.executeUpdate();
                preDel.close();
                Iterator<TradeOrder> t = tradeOrders.iterator();
                while(t.hasNext()){
                    TradeOrder tradeOrder = t.next();
                    PreparedStatement preIns = connection.prepareStatement(sqlIns);
                    preIns.setInt(1,tradeOrder.getIdTrader());
                    preIns.setInt(2, tradeOrder.getIdStock());
                    preIns.setInt(3, tradeOrder.getNumberStock());
                    preIns.setLong(4, tradeOrder.getCost());
                    preIns.executeUpdate();
                    preIns.close();
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }
}
