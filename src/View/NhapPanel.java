/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.*;
import javax.swing.*;

import DTOService.GeneralMethod;
import View.Nhap.KhachHangPanel;

import View.Nhap.SanPhamPanel;
import model.File.Theme;

/**
 *
 * @author choco
 */
public class NhapPanel extends javax.swing.JPanel {

    GeneralMethod GM = new GeneralMethod();
    private MainJFrame mainJFrame;
    Theme theme = MainJFrame.listTheme.get(0);

    /**
     * Creates new form BHPanel
     */
    public NhapPanel(MainJFrame mainJFrame) {
        initComponents();
        this.mainJFrame = mainJFrame;
        GM.setLabelIcon(iconImport, MainJFrame.url + "import.png");
        GM.setLabelIcon(iconAddCus, MainJFrame.url + "addkhach.png");
        mainPanel.setBackground(Color.decode("#adadad"));
        this.setBackground(Color.decode(theme.getBackgroundTaskPanel()));
        setOriginColor(new JPanel[]{btnImportProduct, btnImportCustomer});
        Theme theme = MainJFrame.listTheme.get(0);
        mainPanel.setBackground(Color.decode(theme.getBackgroundMainPanel()));
    }

    public void setSelectedColor(JPanel jp) {
        jp.setBackground(Color.decode(theme.getBackgroundTaskPanelClick()));
    }

    public void resetColor(JPanel[] pane) {
        for (int i = 0; i < pane.length; i++) {
            pane[i].setBackground(Color.decode(theme.getBackgroundTaskPanel()));
        }
    }

    public void setOriginColor(JPanel[] pane) {
        for (int i = 0; i < pane.length; i++) {
            pane[i].setBackground(Color.decode(theme.getBackgroundTaskPanel()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImportProduct = new javax.swing.JPanel();
        iconImport = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnImportCustomer = new javax.swing.JPanel();
        iconAddCus = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(212, 210, 210));
        setPreferredSize(new java.awt.Dimension(890, 459));

        btnImportProduct.setBackground(new java.awt.Color(179, 177, 177));
        btnImportProduct.setPreferredSize(new java.awt.Dimension(140, 88));
        btnImportProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnImportProductMousePressed(evt);
            }
        });

        iconImport.setAlignmentX(0.5F);
        iconImport.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Nhập hàng");

        javax.swing.GroupLayout btnImportProductLayout = new javax.swing.GroupLayout(btnImportProduct);
        btnImportProduct.setLayout(btnImportProductLayout);
        btnImportProductLayout.setHorizontalGroup(
            btnImportProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
            .addGroup(btnImportProductLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(iconImport, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnImportProductLayout.setVerticalGroup(
            btnImportProductLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnImportProductLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(iconImport, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnImportCustomer.setBackground(new java.awt.Color(179, 177, 177));
        btnImportCustomer.setPreferredSize(new java.awt.Dimension(100, 88));
        btnImportCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnImportCustomerMousePressed(evt);
            }
        });

        iconAddCus.setAlignmentX(0.5F);
        iconAddCus.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Thêm khách hàng");

        javax.swing.GroupLayout btnImportCustomerLayout = new javax.swing.GroupLayout(btnImportCustomer);
        btnImportCustomer.setLayout(btnImportCustomerLayout);
        btnImportCustomerLayout.setHorizontalGroup(
            btnImportCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnImportCustomerLayout.createSequentialGroup()
                .addContainerGap(42, Short.MAX_VALUE)
                .addComponent(iconAddCus, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        btnImportCustomerLayout.setVerticalGroup(
            btnImportCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnImportCustomerLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(iconAddCus, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainPanel.setPreferredSize(new java.awt.Dimension(890, 371));
        mainPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnImportProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnImportCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImportProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImportCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportProductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportProductMousePressed
        setSelectedColor(btnImportProduct);
        resetColor(new JPanel[]{btnImportCustomer});
        GM.showPanel(mainPanel, new SanPhamPanel(this.mainJFrame));

    }//GEN-LAST:event_btnImportProductMousePressed

    private void btnImportCustomerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnImportCustomerMousePressed
        setSelectedColor(btnImportCustomer);
        resetColor(new JPanel[]{btnImportProduct});
        GM.showPanel(mainPanel, new KhachHangPanel(this.mainJFrame));
    }//GEN-LAST:event_btnImportCustomerMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnImportCustomer;
    private javax.swing.JPanel btnImportProduct;
    private javax.swing.JLabel iconAddCus;
    private javax.swing.JLabel iconImport;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
