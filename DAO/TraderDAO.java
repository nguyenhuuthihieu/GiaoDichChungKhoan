/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.DAO;

import Day9.ConnectDatabase.StockExchange.ConnectDB.Connections;
import Day9.ConnectDatabase.StockExchange.DAO.TraderOrderDAO;
import Day9.ConnectDatabase.StockExchange.Model.Stock;
import Day9.ConnectDatabase.StockExchange.Model.TradeOrder;
import Day9.ConnectDatabase.StockExchange.Model.Trader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author NguyenHieu
 */
public class TraderDAO {
     public void insertTrader (Trader trader){
        Connection connection = Connections.getMysqlConnection();
        String sql = "INSERT INTO Trader(name, password, address,gmail, money, idRole) VALUES (?,?, ?, ?, ?, 1);";
        if(connection != null){
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1,trader.getName());
                pre.setString(2, trader.getPassword());
                pre.setString(3, trader.getAddress());
                pre.setString(4, trader.getGmail());
                pre.setLong(5, trader.getMoney());
                pre.executeUpdate();
                pre.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }
    public void deleteTrader(Trader trader){
        Connection connection = Connections.getMysqlConnection();
        String sql = "DELETE FROM Trader WHERE idTrader = ?;";
        if(connection != null){
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1,trader.getIdTrader());
                pre.executeUpdate();
                pre.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }
    public void updateTrader(Trader trader){
        Connection connection = Connections.getMysqlConnection();
        String sql = "UPDATE Trader SET name = ?,password = ?, address = ?, money = ?, gmail = ? WHERE idTrader = " + trader.getIdTrader();
        if(connection != null){
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1,trader.getName());
                pre.setString(2, trader.getPassword());
                pre.setString(3, trader.getAddress());
                pre.setLong(4, trader.getMoney());
                pre.setString(5, trader.getGmail());
                pre.executeUpdate();
                pre.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }
    public TreeMap<String, Trader> selectTraders(){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM Trader";
        TreeMap<String, Trader> traders = new TreeMap<>();
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    Trader trader = new Trader(rs.getString("name"), 
                                            rs.getInt("idTrader"),
                                            rs.getString("password"),
                                            rs.getString("address"),
                                            rs.getLong("money"),
                                            rs.getString("gmail")
                                            );
                    traders.put(rs.getString("gmail"), trader);
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return traders;
    }
    public HashMap<Integer, Stock> getListStocks(int idTrader){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM Trader JOIN Trader_Stocks ON USING(idTrader)";
        HashMap<Integer, Stock> stocks = new HashMap<>();
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    Stock stock = new Stock(rs.getInt("idStock"), 
                                            rs.getString("name")
                                            );
                    stocks.put(rs.getInt("idStock"), stock);
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return stocks;
    }
    public void updateTrader_Stock(TradeOrder t){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM Trader_Stocks WHERE idTrader = "+t.getIdTrader()+" AND idStock = " + t.getIdStock() + ";";
        String sqlInsert = "INSERT INTO Trader_Stocks(idTrader, idStock, AmountStocks, price) VALUES (?,?,?,?);";
        String sqlUpdate = "UPDATE Trader_Stocks SET AmountStocks = ?, Price = ? WHERE idStock = ? AND idTrader = ? ;";
        if(t.getNumberStock() != 0){
            if(connection != null){
                try {
                    Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    ResultSet rs = st.executeQuery(sql);
                    if(!rs.next()){
                        PreparedStatement pre = connection.prepareStatement(sqlInsert);
                        pre.setLong(1,t.getIdTrader());
                        pre.setInt(2, t.getIdStock());
                        pre.setInt(3, t.getNumberStock());
                        pre.setInt(4, t.getCost());
                        pre.executeUpdate();
                        pre.close();
                    }else{
                        PreparedStatement pre = connection.prepareStatement(sqlUpdate);
                        pre.setLong(1,t.getNumberStock());
                        pre.setInt(2, t.getCost());
                        pre.setInt(3, t.getIdStock());
                        pre.setInt(4, t.getIdTrader());
                        pre.executeUpdate();
                        pre.close();
                    }

                    connection.close();
                } catch (SQLException ex) {
                    System.out.println("SQLException: " + ex.getMessage());
                }
            }
        }else {
            TraderOrderDAO traderOrderDAO = new TraderOrderDAO();
            traderOrderDAO.deleteTraderStock(t);
        }
        
    }
    public Trader getTrader(int id){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM Trader WHERE idTrader = " + id;
        Trader trader= null;
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    trader = new Trader(rs.getString("name"),
                            rs.getInt("idTrader"),
                            rs.getString("password"), 
                            rs.getString("address"),
                            rs.getLong("money"), 
                            rs.getString("gmail"));
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return trader;
    }
    public Trader getTraderByGmail(String gmail){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM Trader WHERE gmail ='"+ gmail + "'";
        Trader trader= null;
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    trader = new Trader(rs.getString("name"),
                            rs.getInt("idTrader"),
                            rs.getString("password"), 
                            rs.getString("address"),
                            rs.getLong("money"), 
                            rs.getString("gmail"));
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return trader;
    }
    public int getRole(Trader trader){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT idRole FROM Role JOIN Trader USING(idRole) WHERE idTrader = " + trader.getIdTrader();
        int idRole = 0;
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    idRole = rs.getInt(1);
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return idRole;
    }
    public static void main(String[] args) {
        
    }
}
