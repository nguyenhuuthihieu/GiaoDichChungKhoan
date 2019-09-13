/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Day9.ConnectDatabase.StockExchange.View.Client;

import Day9.ConnectDatabase.StockExchange.DAO.StockDAO;
import Day9.ConnectDatabase.StockExchange.Model.Stock;
import Day9.ConnectDatabase.StockExchange.Model.Trader;
import Day9.ConnectDatabase.StockExchange.Service.SellBuyService;
import Day9.ConnectDatabase.StockExchange.Service.UpdateStockService;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author NguyenHieu
 */
public class BuyStockView extends javax.swing.JFrame {

    /**
     * Creates new form BuyStockView
     */
    Trader trader;
    public BuyStockView(ArrayList<Stock> listStock, Trader trader) {
        initComponents();
        this.trader = trader;
        for(Stock stock : listStock)
            cbb_listStock.addItem(stock.getName());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbb_listStock = new javax.swing.JComboBox<>();
        spn_amountStock = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_buy = new javax.swing.JButton();
        lbl_errorAmountStock = new javax.swing.JLabel();
        spn_price = new javax.swing.JSpinner();
        lbl_errorPrice = new javax.swing.JLabel();
        lbl_error = new javax.swing.JLabel();
        btn_Back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mua cổ phiếu");

        jLabel1.setText("MUA CỔ PHIẾU");

        cbb_listStock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_listStockActionPerformed(evt);
            }
        });

        jLabel2.setText("Cổ phiếu");

        jLabel3.setText("Số lượng");

        jLabel4.setText("Giá mua");

        jLabel5.setText("USD");

        btn_buy.setText("Mua");
        btn_buy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buyActionPerformed(evt);
            }
        });

        lbl_errorAmountStock.setForeground(new java.awt.Color(255, 0, 0));

        lbl_errorPrice.setForeground(new java.awt.Color(255, 0, 0));

        lbl_error.setForeground(new java.awt.Color(255, 0, 0));

        btn_Back.setText("Trở lại");
        btn_Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_BackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(lbl_error)
                    .addComponent(lbl_errorPrice)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbb_listStock, 0, 233, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(spn_price, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabel5))
                        .addComponent(lbl_errorAmountStock)
                        .addComponent(spn_amountStock)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn_buy, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addComponent(lbl_error)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbb_listStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spn_amountStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(4, 4, 4)
                .addComponent(lbl_errorAmountStock)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(spn_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_errorPrice)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_buy)
                    .addComponent(btn_Back))
                .addContainerGap(84, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_buyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buyActionPerformed
       if((Integer)spn_amountStock.getValue() <= 0) lbl_errorAmountStock.setText("Không hợp lệ");
       else lbl_errorAmountStock.setText("");
       if((Integer)spn_price.getValue() < 0) lbl_errorPrice.setText("Không hợp lệ");
       else lbl_errorPrice.setText("");
       if((Integer)spn_amountStock.getValue() > 0 && (Integer)spn_price.getValue() >= 0){
           SellBuyService sellBuyService = new SellBuyService();
           String stockName = (String) cbb_listStock.getSelectedItem();
           StockDAO stockDAO = new StockDAO();
           Stock stock = stockDAO.getStockByName(stockName);
           int op = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn mua?");
           if(op == JOptionPane.OK_OPTION){
               int buy = sellBuyService.buyStock(trader, 
                        stock, 
                        (Integer)spn_price.getValue(), 
                        (Integer)spn_amountStock.getValue());
               if(buy == 1){
                   new TradeOrderView(trader).show();
                   this.setVisible(false);
               }else if(buy == -1)lbl_error.setText("Số tiền của bạn không đủ để mua");
               else lbl_error.setText("Số cổ phiếu quá lớn");
           }
       }
    }//GEN-LAST:event_btn_buyActionPerformed

    private void cbb_listStockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_listStockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_listStockActionPerformed

    private void btn_BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_BackActionPerformed
        new TradeOrderView(trader).show();
        this.setVisible(false);
    }//GEN-LAST:event_btn_BackActionPerformed

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
            java.util.logging.Logger.getLogger(BuyStockView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuyStockView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuyStockView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuyStockView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BuyStockView().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Back;
    private javax.swing.JButton btn_buy;
    private javax.swing.JComboBox<String> cbb_listStock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lbl_error;
    private javax.swing.JLabel lbl_errorAmountStock;
    private javax.swing.JLabel lbl_errorPrice;
    private javax.swing.JSpinner spn_amountStock;
    private javax.swing.JSpinner spn_price;
    // End of variables declaration//GEN-END:variables
}