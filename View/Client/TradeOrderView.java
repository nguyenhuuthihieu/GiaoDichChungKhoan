/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.View.Client;

import Day9.ConnectDatabase.StockExchange.DAO.StockDAO;
import Day9.ConnectDatabase.StockExchange.DAO.TraderDAO;
import Day9.ConnectDatabase.StockExchange.Model.Stock;
import Day9.ConnectDatabase.StockExchange.Model.TradeOrder;
import Day9.ConnectDatabase.StockExchange.Model.Trader;
import Day9.ConnectDatabase.StockExchange.Service.LoginService;
import Day9.ConnectDatabase.StockExchange.Service.SellBuyService;
import Day9.ConnectDatabase.StockExchange.Service.UpdateStockService;
import Day9.ConnectDatabase.StockExchange.View.LoginView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NguyenHieu
 */
public class TradeOrderView extends javax.swing.JFrame {

    /**
     * Creates new form TradeOrderView
     */
    DefaultTableModel buyModel;
    DefaultTableModel sellModel;
    DefaultTableModel buySellModel;
    Vector buyTitle = new Vector();
    Vector sellTitle = new Vector();
    Vector buySellTitle = new Vector();
    Vector buyRecords = new Vector();
    Vector sellRecords = new Vector();
    Vector buySellRecords = new Vector();
    private Trader trader;
    PriorityQueue<TradeOrder> buy = new PriorityQueue<TradeOrder>(new Comparator<TradeOrder>(){
        @Override
        public int compare(TradeOrder o1, TradeOrder o2) {
            return o2.compareTo(o1);
        }
    });
    PriorityQueue<TradeOrder> sell = new PriorityQueue<TradeOrder>(new Comparator<TradeOrder>(){
        @Override
        public int compare(TradeOrder o1, TradeOrder o2) {
            return o1.compareTo(o2);
        }
    });
    ArrayList<TradeOrder> tradeOrders = new ArrayList<>();
    public TradeOrderView(Trader trader) {
        initComponents();
        this.trader = trader;
        lblName.setText(trader.getName());
        Long money = new Long(trader.getMoney());
        lblMoney.setText(money.toString());
        buyTitle.add("Người mua");
        buyTitle.add("Cổ phiếu");
        buyTitle.add("Số lượng");
        buyTitle.add("Giá mua");
        sellTitle.add("Người bán");
        sellTitle.add("Cổ phiếu");
        sellTitle.add("Số lượng");
        sellTitle.add("Giá bán");
        buySellTitle.add("Người chơi");
        buySellTitle.add("Cổ phiếu");
        buySellTitle.add("Số Lượng");
        buySellTitle.add("Giá");
        SellBuyService sellBuyService = new SellBuyService();
        TraderDAO traderDAO = new TraderDAO();
        StockDAO stockDAO = new StockDAO();
        buy = sellBuyService.getBuyStock();
        Iterator<TradeOrder> iteratorBuy = buy.iterator(); 
        while(iteratorBuy.hasNext()){
            TradeOrder tradeOrder = iteratorBuy.next();
            Trader t = traderDAO.getTrader(tradeOrder.getIdTrader());
            Stock s = stockDAO.getStock(tradeOrder.getIdStock());
            Vector b = new Vector();
            b.add(t.getName());
            b.add(s.getName());
            b.add(tradeOrder.getNumberStock());
            b.add(tradeOrder.getCost());
            buyRecords.add(b);
        }
        sell = sellBuyService.getSellStock();
        Iterator<TradeOrder> iteratorSell = sell.iterator(); 
        while(iteratorSell.hasNext()){
            TradeOrder tradeOrder = iteratorSell.next();
            Trader t = traderDAO.getTrader(tradeOrder.getIdTrader());
            Stock stock = stockDAO.getStock(tradeOrder.getIdStock());
            Vector s = new Vector();
            s.add(t.getName());
            s.add(stock.getName());
            s.add(tradeOrder.getNumberStock());
            s.add(tradeOrder.getCost());
            sellRecords.add(s);
        }
        tradeOrders = sellBuyService.getTradeOrders();
        for(TradeOrder t : tradeOrders){
            Trader trader1 = traderDAO.getTrader(t.getIdTrader());
            Stock stock = stockDAO.getStock(t.getIdStock());
            Vector s = new Vector();
            s.add(trader1.getName());
            s.add(stock.getName());
            s.add(t.getNumberStock());
            s.add(t.getCost());
            buySellRecords.add(s);
        }
        buyModel = new DefaultTableModel(buyRecords, buyTitle){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        sellModel = new DefaultTableModel(sellRecords, sellTitle){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        buySellModel = new DefaultTableModel(buySellRecords, buySellTitle){
            @Override
            public boolean isCellEditable(int row, int column) {
               //all cells false
               return false;
            }
        };
        tbl_buy.setModel(buyModel);
        tbl_sell.setModel(sellModel);
        tbl_buySell.setModel(buySellModel);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_buy = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_sell = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbl_buySell = new javax.swing.JTable();
        lbl_name = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbl_money = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_buy = new javax.swing.JButton();
        btn_Sell = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        lbl_errorSell = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        lblMoney = new javax.swing.JLabel();
        btn_logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sàn chứng khoán");

        tbl_buy.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Người mua", "Cổ phiếu", "Số lượng", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_buy);

        jLabel1.setText("BÊN MUA");

        tbl_sell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Người bán", "Cổ phiếu", "Số lượng", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbl_sell);

        jLabel2.setText("BÊN BÁN");

        tbl_buySell.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Người chơi", "Cổ phiếu", "Số lượng", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tbl_buySell);

        jLabel4.setText("Người chơi: ");

        jLabel5.setText("Tài khoản: ");

        jLabel7.setText("USD");

        btn_buy.setText("Mua cổ phiếu");
        btn_buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buyActionPerformed(evt);
            }
        });

        btn_Sell.setText("Bán cổ phiếu");
        btn_Sell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SellActionPerformed(evt);
            }
        });

        jLabel6.setText("CHÚ Ý: Phiên giao dịch 5 phút thực hiện 1 lần");

        lbl_errorSell.setForeground(new java.awt.Color(255, 0, 0));

        jLabel8.setText("GIAO DỊCH CHỨNG KHOÁN");

        lblName.setText("Nguyen Huu Thi Hieu");

        lblMoney.setText("100000");

        btn_logout.setText("Đăng xuất");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbl_name))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbl_errorSell)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(lblName))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(lblMoney)
                                .addGap(30, 30, 30)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btn_logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_Sell, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                                    .addComponent(btn_buy, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_money)
                                .addGap(78, 78, 78))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(199, 199, 199)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(247, 247, 247)
                        .addComponent(jLabel1)))
                .addContainerGap(68, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(287, 287, 287))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lblName))
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(lblMoney))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(80, 80, 80)
                                    .addComponent(lbl_name)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btn_buy, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(btn_Sell, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(15, 15, 15)))
                            .addComponent(lbl_money)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btn_logout)
                            .addGap(51, 51, 51)
                            .addComponent(jLabel8))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addContainerGap(26, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbl_errorSell)
                                .addGap(164, 164, 164))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buyActionPerformed
        UpdateStockService updateStockService = new UpdateStockService();
        ArrayList<Stock> listStock = updateStockService.getStocks();
        if(listStock.size() == 0){
            lbl_errorSell.setText("Không có cổ phiếu để bán");
        } else{
            new BuyStockView(listStock, trader).show();
            this.setVisible(false);
        }
    }//GEN-LAST:event_btn_buyActionPerformed

    private void btn_SellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SellActionPerformed
        UpdateStockService updateStockService = new UpdateStockService();
        ArrayList<Stock> listStock = updateStockService.getStocks(trader);
        if(listStock.size() == 0){
            lbl_errorSell.setText("Không có cổ phiếu để bán");
        } else{
            new SellStockView(listStock, trader).show();
            this.setVisible(false);
        }
    }//GEN-LAST:event_btn_SellActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        LoginService loginService = new LoginService();
        loginService.logout(trader);
        new LoginView().show();
        this.setVisible(false);
    }//GEN-LAST:event_btn_logoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TradeOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TradeOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TradeOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TradeOrderView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TradeOrderView().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Sell;
    private javax.swing.JButton btn_buy;
    private javax.swing.JButton btn_logout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lblMoney;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lbl_errorSell;
    private javax.swing.JLabel lbl_money;
    private javax.swing.JLabel lbl_name;
    private javax.swing.JTable tbl_buy;
    private javax.swing.JTable tbl_buySell;
    private javax.swing.JTable tbl_sell;
    // End of variables declaration//GEN-END:variables
}
