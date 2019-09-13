/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.ConnectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NguyenHieu
 */
public class Connections {
    public static Connection getMysqlConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String user="root";
            String password = "";
            String url = "jdbc:mysql://localhost/stockExchange";
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url, user, password);
            Statement stmt = null;
            ResultSet rs = null;    
            if(connection != null){
                String sqlStock = "CREATE TABLE IF NOT EXISTS Stock("
                        + "idStock INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                        + " name VARCHAR(20),"
                        + "NumStock INT(25))ENGINE=InnoDB DEFAULT CHARSET=utf8";
                String sqlRole = "CREATE TABLE IF NOT EXISTS Role("
                        + "idRole INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                        + " nameRole VARCHAR(20))ENGINE=InnoDB DEFAULT CHARSET=utf8";
                String sqlTrader = "CREATE TABLE IF NOT EXISTS Trader("
                        + "idTrader INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                        + " name VARCHAR(20),"
                        + "password VARCHAR(255), "
                        + "address VARCHAR(25),"
                        + "gmail VARCHAR(100),"
                        + "money LONG,"
                        + "idRole INT(11),"
                        + "FOREIGN KEY (idRole) REFERENCES Role(idRole))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
                String sqlTraderStocks = "CREATE TABLE IF NOT EXISTS Trader_Stocks("
                        + "id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                        + "idTrader INT(11),"
                        + "idStock INT(11),"
                        + "AmountStocks LONG,"
                        + "FOREIGN KEY (idTrader) REFERENCES Trader(idTrader),"
                        + "FOREIGN KEY (idStock) REFERENCES Stock(idStock))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
                String sqlBuy = "CREATE TABLE IF NOT EXISTS Buy(idBuy INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                        + "idTrader INT(11) NOT NULL ,"
                        + "idStock INT(11) NOT NULL ,"
                        + "AmountStocks INT(25) NOT NULL,"
                        + "price INT(25) NOT NULL,"
                        + "FOREIGN KEY (idTrader) REFERENCES Trader(idTrader),"
                        + "FOREIGN KEY (idStock) REFERENCES Stock(idStock))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
                String sqlSell = "CREATE TABLE IF NOT EXISTS Sell(idSell INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,"
                        + "idTrader INT(11) NOT NULL ,"
                        + "idStock INT(11) NOT NULL ,"
                        + "AmountStocks INT(25) NOT NULL,"
                        + "price INT(25) NOT NULL,"
                        + "FOREIGN KEY (idTrader) REFERENCES Trader(idTrader),"
                        + "FOREIGN KEY (idStock) REFERENCES Stock(idStock))ENGINE=InnoDB DEFAULT CHARSET=utf8;";
                stmt = connection.createStatement();
                stmt.execute(sqlRole);
                stmt.execute(sqlStock);
                stmt.execute(sqlTrader);
                stmt.execute(sqlTraderStocks);
                stmt.execute(sqlBuy);
                stmt.execute(sqlSell);
                stmt.close();
            }
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException : "+ ex.getMessage());
        }
        catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
        return connection;
    }
    public static void main(String[] args) {
        getMysqlConnection();
    }
}
