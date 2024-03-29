/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.View.Admin;

import Day9.ConnectDatabase.StockExchange.DAO.StockDAO;
import Day9.ConnectDatabase.StockExchange.DAO.TraderDAO;
import Day9.ConnectDatabase.StockExchange.Model.Stock;
import Day9.ConnectDatabase.StockExchange.Model.TradeOrder;
import Day9.ConnectDatabase.StockExchange.Model.Trader;
import Day9.ConnectDatabase.StockExchange.Service.LoginService;
import Day9.ConnectDatabase.StockExchange.Service.RegisterService;
import Day9.ConnectDatabase.StockExchange.Service.SellBuyService;
import Day9.ConnectDatabase.StockExchange.View.LoginView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author NguyenHieu
 */
public class HomeAdminView extends javax.swing.JFrame {

    /**
     * Creates new form HomeAdminView
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
    public HomeAdminView(Trader trader) {
        initComponents();
        this.trader = trader;
        lbl_nameAdmin.setText(trader.getName());
        buyTitle.add("Người mua");
        buyTitle.add("Cổ phiếu");
        buyTitle.add("Số lượng");
        buyTitle.add("Giá mua");
        sellTitle.add("Người bán");
        sellTitle.add("Cổ phiếu");
        sellTitle.add("Số lượng");
        sellTitle.add("Giá bán");
        buySellTitle.add("Mã");
        buySellTitle.add("Người chơi");
        buySellTitle.add("Cổ phiếu");
        buySellTitle.add("Số Lượng");
        buySellTitle.add("Giá");
        SellBuyService sellBuyService = new SellBuyService();
        buy = sellBuyService.getBuyStock();
        TraderDAO traderDAO = new TraderDAO();
        StockDAO stockDAO = new StockDAO();
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
            s.add(trader1.getIdTrader());
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
        tbl_Buy.setModel(buyModel);
        tbl_Sell.setModel(sellModel);
        tbl_trader.setModel(buySellModel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_Sell = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_Buy = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_trader = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        lbl_nameAdmin = new javax.swing.JLabel();
        btn_addTrader = new javax.swing.JButton();
        btn_addStock = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trang chủ");

        tbl_Sell.setModel(new javax.swing.table.DefaultTableModel(
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
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbl_Sell);

        jLabel1.setText("BÊN BÁN");

        tbl_Buy.setModel(new javax.swing.table.DefaultTableModel(
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
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_Buy);

        jLabel2.setText("BÊN MUA");

        tbl_trader.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Người chơi", "Cổ phiếu", "Số Lượng", "Giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Long.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_trader.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_traderMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbl_trader);

        jLabel3.setText("GIAO DỊCH CHỨNG KHOÁN");

        lbl_nameAdmin.setText("Nguyen Huu Thi Hieu");

        btn_addTrader.setText("Thêm người dùng");
        btn_addTrader.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addTraderActionPerformed(evt);
            }
        });

        btn_addStock.setText("Thêm cổ phiếu");
        btn_addStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addStockActionPerformed(evt);
            }
        });

        jLabel4.setText("Admin:");

        jLabel5.setText("CHÚ Ý: Phiên giao dịch 5 phút thực hiện 1 lần");

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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(226, 226, 226))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(254, 254, 254))))
            .addGroup(layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(lbl_nameAdmin)
                .addGap(33, 33, 33)
                .addComponent(btn_addTrader, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_addStock, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(255, 255, 255))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_nameAdmin)
                    .addComponent(jLabel4)
                    .addComponent(btn_addTrader, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_addStock, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_addTraderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addTraderActionPerformed
        new AddTraderView(trader).show();
        this.setVisible(false);
    }//GEN-LAST:event_btn_addTraderActionPerformed

    private void btn_addStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addStockActionPerformed
        new AddStockView(trader).show();
        this.setVisible(false);
    }//GEN-LAST:event_btn_addStockActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        LoginService registerService = new LoginService();
        registerService.logout(trader);
        new LoginView().show();
        this.setVisible(false);
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void tbl_traderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_traderMouseClicked
        int item = tbl_trader.getSelectedRow();
        TraderDAO traderDAO = new TraderDAO();
        Trader trader = traderDAO.getTrader(tradeOrders.get(item).getIdTrader());
        new TraderHomeView(trader, this.trader).show();
        this.setVisible(false);
    }//GEN-LAST:event_tbl_traderMouseClicked

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
            java.util.logging.Logger.getLogger(HomeAdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeAdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeAdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeAdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new HomeAdminView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_addStock;
    private javax.swing.JButton btn_addTrader;
    private javax.swing.JButton btn_logout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_nameAdmin;
    private javax.swing.JTable tbl_Buy;
    private javax.swing.JTable tbl_Sell;
    private javax.swing.JTable tbl_trader;
    // End of variables declaration//GEN-END:variables
}
