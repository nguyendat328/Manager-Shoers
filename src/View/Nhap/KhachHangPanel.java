/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Nhap;

import DTOService.GeneralMethod;
import View.MainJFrame;
import controller.NhapController.KhachHangController;
import java.awt.Color;
import javax.swing.JOptionPane;
import model.File.Theme;

/**
 *
 * @author khai
 */
public class KhachHangPanel extends javax.swing.JPanel {

    private MainJFrame mainJFrame;
    GeneralMethod GM = new GeneralMethod();
    KhachHangController KHController = new KhachHangController();

    /**
     * Creates new form KhachHangPanel
     */
    public KhachHangPanel(MainJFrame mainJFrame) {
        initComponents();
        this.mainJFrame = mainJFrame;
        ///khai set mau -----------------------
        setColor();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameKHText = new javax.swing.JTextField();
        faceText = new javax.swing.JTextField();
        diaChiText = new javax.swing.JTextField();
        sdtText = new javax.swing.JFormattedTextField();
        btnAdd = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        noteText = new javax.swing.JTextField();

        jLabel2.setText("Tên Khách hàng :");

        jLabel3.setText("Địa chỉ :");

        jLabel4.setText("Face / Ins ...");

        jLabel5.setText("Ghi chú");

        nameKHText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameKHTextActionPerformed(evt);
            }
        });

        try {
            sdtText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####.###.###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        sdtText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sdtTextActionPerformed(evt);
            }
        });

        btnAdd.setText("Thêm khách hàng");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        btnReset.setText("Xoá thông tin");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        jLabel7.setText("Điện thoại:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(399, Short.MAX_VALUE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sdtText)
                            .addComponent(diaChiText)
                            .addComponent(faceText)
                            .addComponent(nameKHText)
                            .addComponent(noteText, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameKHText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(faceText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(sdtText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(diaChiText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noteText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
private void setColor() {

        Theme theme = MainJFrame.listTheme.get(0);
        btnAdd.setBackground(Color.decode(theme.getBackgroundButton()));
        btnReset.setBackground(Color.decode(theme.getBackgroundButton()));
        this.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        jLabel4.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel2.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel3.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel5.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel7.setForeground(Color.decode(theme.getTxtColorTitle()));
    }
    private void nameKHTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameKHTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameKHTextActionPerformed

    private void sdtTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sdtTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sdtTextActionPerformed

    public void resetInfor() {
        nameKHText.setText("");
        faceText.setText("");
        noteText.setText("");
        diaChiText.setText("");
        sdtText.setText("");
    }

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked

        String codeKH = GM.randIdCreate();
        String nameKH = nameKHText.getText().trim();
        String face = faceText.getText().trim();
        String note = noteText.getText().trim();
        String diaChi = diaChiText.getText().trim();
        String phone = sdtText.getText();
        phone = phone.replace(".", "");
        if (nameKH.length() > 0 && diaChi.length() > 0 && phone.length() > 0) {
            if (KHController.getKhCode(nameKH).length() > 0) {
                JOptionPane.showMessageDialog(null, "Khách hàng đã tồn tại!");
            } else {
                if (KHController.insertKH(codeKH, nameKH, face, diaChi, phone, note)) {
                    JOptionPane.showMessageDialog(null, "Thêm Khách Hàng Thành Công !");
                    resetInfor();
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm Khách Hàng Thất Bại !");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Tên, địa chỉ và số điện thoại không được để trống !");
        }
    }//GEN-LAST:event_btnAddMouseClicked

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked
        resetInfor();
    }//GEN-LAST:event_btnResetMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnReset;
    private javax.swing.JTextField diaChiText;
    private javax.swing.JTextField faceText;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField nameKHText;
    private javax.swing.JTextField noteText;
    private javax.swing.JFormattedTextField sdtText;
    // End of variables declaration//GEN-END:variables
}
