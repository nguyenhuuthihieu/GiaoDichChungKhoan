/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.DAO;

import Day9.ConnectDatabase.StockExchange.ConnectDB.Connections;
import Day9.ConnectDatabase.StockExchange.Model.TradeOrder;
import Day9.ConnectDatabase.StockExchange.Model.Trader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NguyenHieu
 */
public class TraderOrderDAO {
    public TradeOrder getTraderOrder(int idTrader, int idStock){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM Trader_Stocks WHERE idTrader = " + idTrader + " AND idStock = " + idStock;
        TradeOrder tradeOrder = null;
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    tradeOrder = new TradeOrder(rs.getInt("idTrader"),
                            rs.getInt("idStock"),0,
                            rs.getInt("AmountStocks"));
                            rs.getInt("price");
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return tradeOrder;
    }
    public void deleteTraderStock(TradeOrder t){
        Connection connection = Connections.getMysqlConnection();
        String sql = "DELETE FROM Trader_Stocks WHERE idTrader = " + t.getIdTrader() + " AND idStock = " + t.getIdStock();
        TradeOrder tradeOrder = null;
        if(connection != null){
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.executeUpdate();
                pre.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }
    public ArrayList<Integer> getIdStockOfTrader(Trader trader){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT idStock FROM Trader_Stocks WHERE idTrader = " + trader.getIdTrader();
        List<Integer> list = new ArrayList();
        TradeOrder tradeOrder = null;
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    list.add(new Integer(rs.getInt("idStock")));
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return (ArrayList<Integer>) list;
    }
    public ArrayList<Integer> getIdStockOfTrader(){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT idStock FROM Stock";
        List<Integer> list = new ArrayList();
        TradeOrder tradeOrder = null;
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    list.add(new Integer(rs.getInt("idStock")));
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return (ArrayList<Integer>) list;
    }
    public ArrayList<TradeOrder> getTradeOrders(){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM trader_Stocks";
        List<TradeOrder> list = new ArrayList();
        TradeOrder tradeOrder = null;
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    list.add(new TradeOrder(rs.getInt("idTrader"), rs.getInt("idStock"), (int) rs.getLong("price"), rs.getInt("AmountStocks")));
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return (ArrayList<TradeOrder>) list;
    }
}
