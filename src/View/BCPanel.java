/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.*;
import javax.swing.*;

import DTOService.GeneralMethod;
import View.BaoCao.CTDTPanel;
import View.BaoCao.KhachHangPanel;

import View.BaoCao.KhoPanel;
import View.BaoCao.ThuChiPanel;
import model.File.Theme;

/**
 *
 * @author choco
 */
public class BCPanel extends javax.swing.JPanel {

    private MainJFrame mainJFrame;
    GeneralMethod GM = new GeneralMethod();
    Theme theme = MainJFrame.listTheme.get(0);

    /**
     * Creates new form BHPanel
     */
    public BCPanel(MainJFrame mainJFrame) {
        initComponents();
        this.mainJFrame = mainJFrame;
        GM.setLabelIcon(iconCTDT, MainJFrame.url + "ctdt.png");
        GM.setLabelIcon(iconKho, MainJFrame.url + "tonkho.png");
        GM.setLabelIcon(iconKhach, MainJFrame.url + "listkhach.png");
        GM.setLabelIcon(iconThuChi, MainJFrame.url + "thuchi.png");

        mainPanel.setBackground(Color.decode(theme.getBackgroundMainPanel()));

        this.setBackground(Color.decode(theme.getBackgroundTaskPanel()));
        setOriginColor(new JPanel[]{btnCTDT, btnKho, btnKhachHang, btnThuChi});
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCTDT = new javax.swing.JPanel();
        iconCTDT = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnKho = new javax.swing.JPanel();
        iconKho = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnKhachHang = new javax.swing.JPanel();
        iconKhach = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        btnThuChi = new javax.swing.JPanel();
        iconThuChi = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(212, 210, 210));
        setPreferredSize(new java.awt.Dimension(890, 459));

        btnCTDT.setBackground(new java.awt.Color(179, 177, 177));
        btnCTDT.setPreferredSize(new java.awt.Dimension(153, 88));
        btnCTDT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnCTDTMousePressed(evt);
            }
        });

        iconCTDT.setAlignmentX(0.5F);
        iconCTDT.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Chi tiết doanh thu");

        javax.swing.GroupLayout btnCTDTLayout = new javax.swing.GroupLayout(btnCTDT);
        btnCTDT.setLayout(btnCTDTLayout);
        btnCTDTLayout.setHorizontalGroup(
            btnCTDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnCTDTLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconCTDT, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        btnCTDTLayout.setVerticalGroup(
            btnCTDTLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnCTDTLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(iconCTDT, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnKho.setBackground(new java.awt.Color(179, 177, 177));
        btnKho.setPreferredSize(new java.awt.Dimension(153, 88));
        btnKho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKhoMousePressed(evt);
            }
        });

        iconKho.setAlignmentX(0.5F);
        iconKho.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tồn Kho");

        javax.swing.GroupLayout btnKhoLayout = new javax.swing.GroupLayout(btnKho);
        btnKho.setLayout(btnKhoLayout);
        btnKhoLayout.setHorizontalGroup(
            btnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
            .addGroup(btnKhoLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(iconKho, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnKhoLayout.setVerticalGroup(
            btnKhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnKhoLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(iconKho, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnKhachHang.setBackground(new java.awt.Color(179, 177, 177));
        btnKhachHang.setPreferredSize(new java.awt.Dimension(153, 88));
        btnKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnKhachHangMousePressed(evt);
            }
        });

        iconKhach.setAlignmentX(0.5F);
        iconKhach.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Danh sách Khách Hàng");

        javax.swing.GroupLayout btnKhachHangLayout = new javax.swing.GroupLayout(btnKhachHang);
        btnKhachHang.setLayout(btnKhachHangLayout);
        btnKhachHangLayout.setHorizontalGroup(
            btnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        btnKhachHangLayout.setVerticalGroup(
            btnKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnKhachHangLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(iconKhach, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        mainPanel.setPreferredSize(new java.awt.Dimension(890, 371));
        mainPanel.setLayout(new java.awt.BorderLayout());

        btnThuChi.setBackground(new java.awt.Color(179, 177, 177));
        btnThuChi.setPreferredSize(new java.awt.Dimension(153, 88));
        btnThuChi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnThuChiMousePressed(evt);
            }
        });

        iconThuChi.setAlignmentX(0.5F);
        iconThuChi.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Thu Chi");

        javax.swing.GroupLayout btnThuChiLayout = new javax.swing.GroupLayout(btnThuChi);
        btnThuChi.setLayout(btnThuChiLayout);
        btnThuChiLayout.setHorizontalGroup(
            btnThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btnThuChiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        btnThuChiLayout.setVerticalGroup(
            btnThuChiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnThuChiLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(iconThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnCTDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCTDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThuChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnCTDTMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCTDTMousePressed
        setSelectedColor(btnCTDT);
        resetColor(new JPanel[]{btnKho, btnKhachHang, btnThuChi});
        GM.showPanel(mainPanel, new CTDTPanel());
    }//GEN-LAST:event_btnCTDTMousePressed

    private void btnKhoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhoMousePressed
        setSelectedColor(btnKho);
        resetColor(new JPanel[]{btnCTDT, btnKhachHang, btnThuChi});
        GM.showPanel(mainPanel, new KhoPanel(this.mainJFrame));
    }//GEN-LAST:event_btnKhoMousePressed

    private void btnKhachHangMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMousePressed
        setSelectedColor(btnKhachHang);
        resetColor(new JPanel[]{btnKho, btnCTDT, btnThuChi});
        GM.showPanel(mainPanel, new KhachHangPanel(this.mainJFrame));
    }//GEN-LAST:event_btnKhachHangMousePressed

    private void btnThuChiMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThuChiMousePressed
        setSelectedColor(btnThuChi);
        resetColor(new JPanel[]{btnKho, btnCTDT, btnKhachHang});
        GM.showPanel(mainPanel, new ThuChiPanel(this.mainJFrame));

    }//GEN-LAST:event_btnThuChiMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnCTDT;
    private javax.swing.JPanel btnKhachHang;
    private javax.swing.JPanel btnKho;
    private javax.swing.JPanel btnThuChi;
    private javax.swing.JLabel iconCTDT;
    private javax.swing.JLabel iconKhach;
    private javax.swing.JLabel iconKho;
    private javax.swing.JLabel iconThuChi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}