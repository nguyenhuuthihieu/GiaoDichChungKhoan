/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.DAO;

import Day9.ConnectDatabase.StockExchange.ConnectDB.Connections;
import Day9.ConnectDatabase.StockExchange.Model.Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 *
 * @author NguyenHieu
 */
public class StockDAO {
    public void insertStock (Stock stock){
        Connection connection = Connections.getMysqlConnection();
        String sql = "INSERT INTO stock(name, NumStock) VALUES (?,?);";
        if(connection != null){
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1,stock.getName());
                pre.setInt(2, stock.getNumStock());
                pre.executeUpdate();
                pre.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }
    public Stock getStock (int idStock){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM Stock WHERE idStock = " + idStock;
        Stock stock = null;
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    stock = new Stock(rs.getInt("idStock"),rs.getString("name"), rs.getInt("NumStock"));
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return stock;
    }
    public Stock getStockByName (String name){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM Stock WHERE name = '" +name + "'";
        Stock stock = null;
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    stock = new Stock(rs.getInt("idStock"),rs.getString("name"), rs.getInt("NumStock"));
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return stock;
    } 
    public void deleteStock(Stock stock){
        Connection connection = Connections.getMysqlConnection();
        String sql = "DELETE FROM stock WHERE idStock = ?;";
        if(connection != null){
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setInt(1,stock.getIdStock());
                pre.executeUpdate();
                pre.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }
    public void updateStock(Stock stock){
        Connection connection = Connections.getMysqlConnection();
        String sql = "UPDATE stock SET name = ?, NumStock = ? WHERE idStock = ?;";
        if(connection != null){
            try {
                PreparedStatement pre = connection.prepareStatement(sql);
                pre.setString(1,stock.getName());
                pre.setInt(2,stock.getNumStock());
                pre.setInt(3,stock.getIdStock());
                pre.executeUpdate();
                pre.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
    }
    public static HashMap<Integer, Stock> selectStocks(){
        Connection connection = Connections.getMysqlConnection();
        String sql = "SELECT * FROM Stock";
        HashMap<Integer, Stock> stocks = new HashMap<>();
        if(connection != null){
            try {
                Statement st = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(sql);
                while(rs.next()){
                    Stock stock = new Stock(rs.getInt("idStock"),rs.getString("name"), rs.getInt("NumStock"));
                    stocks.put(rs.getInt("idStock"), stock);
                }
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException: " + ex.getMessage());
            }
        }
        return stocks;
    }
    public static void main(String[] args) {
        HashMap<Integer, Stock> s = selectStocks();
        System.out.println("");
    }
}
