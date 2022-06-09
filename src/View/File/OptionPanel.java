/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.File;

import DTOService.GeneralMethod;
import View.MainJFrame;
import static View.MainJFrame.dis;
import static View.MainJFrame.discountFromDate;
import static View.MainJFrame.discountToDate;
import controller.FileController.OptionController;
import controller.FileController.ThemeController;
import java.awt.Color;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import model.File.Discount;
import model.File.Theme;

/**
 *
 * @author choco
 */
public class OptionPanel extends javax.swing.JPanel {

    //từ thằng con gọi thao tác lên thằng cha
    private MainJFrame mainJFrame;
    GeneralMethod GM = new GeneralMethod();
    ThemeController TC = new ThemeController();
    OptionController OCC = new OptionController();

    /**
     * Creates new form Option
     */
    public OptionPanel(MainJFrame mainJFrame) {
        initComponents();
        getCTGiamGia();

        //khai set mau
        setColor();
        this.mainJFrame = mainJFrame;
        formatClassCombobx(TC.getNameTheme());
        boxSellingMethod.addItem("Nhập trước xuất trước");
        boxSellingMethod.addItem("Nhập sau xuất trước");
        if (MainJFrame.sellingMethod == 1) {
            boxSellingMethod.setSelectedIndex(1);
        } else {
            boxSellingMethod.setSelectedIndex(0);
        }
        GM.setLabelIcon(iconAbout, MainJFrame.url + "about.png");
        GM.setLabelIcon(iconManual, MainJFrame.url + "manual.png");

    }

    public void formatClassCombobx(List<String> boxContent) {
        boxTheme.removeAllItems();
        boxTheme.addItem(MainJFrame.nameTheme);
        for (String lts : boxContent) {

            boxTheme.addItem(lts);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        boxTheme = new javax.swing.JComboBox<>();
        boxSellingMethod = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnRemoveSelected = new javax.swing.JPanel();
        iconAbout = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnRemoveSelected1 = new javax.swing.JPanel();
        iconManual = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        giamGiaText = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        messGiamGia = new javax.swing.JLabel();
        toDateText = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        fromDateText = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        giamGiaButton = new javax.swing.JButton();

        jLabel1.setText("Giao diện:");

        boxSellingMethod.setToolTipText("");
        boxSellingMethod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxSellingMethodActionPerformed(evt);
            }
        });

        jLabel2.setText("Phương pháp bán:");

        btnRemoveSelected.setBackground(new java.awt.Color(179, 177, 177));
        btnRemoveSelected.setPreferredSize(new java.awt.Dimension(153, 88));
        btnRemoveSelected.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRemoveSelectedMousePressed(evt);
            }
        });

        iconAbout.setAlignmentX(0.5F);
        iconAbout.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Về chúng tôi");

        javax.swing.GroupLayout btnRemoveSelectedLayout = new javax.swing.GroupLayout(btnRemoveSelected);
        btnRemoveSelected.setLayout(btnRemoveSelectedLayout);
        btnRemoveSelectedLayout.setHorizontalGroup(
            btnRemoveSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(btnRemoveSelectedLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(iconAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnRemoveSelectedLayout.setVerticalGroup(
            btnRemoveSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRemoveSelectedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );

        btnRemoveSelected1.setBackground(new java.awt.Color(179, 177, 177));
        btnRemoveSelected1.setPreferredSize(new java.awt.Dimension(153, 88));
        btnRemoveSelected1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRemoveSelected1MousePressed(evt);
            }
        });

        iconManual.setAlignmentX(0.5F);
        iconManual.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Hướng dẫn sử dụng");

        javax.swing.GroupLayout btnRemoveSelected1Layout = new javax.swing.GroupLayout(btnRemoveSelected1);
        btnRemoveSelected1.setLayout(btnRemoveSelected1Layout);
        btnRemoveSelected1Layout.setHorizontalGroup(
            btnRemoveSelected1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(btnRemoveSelected1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(iconManual, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnRemoveSelected1Layout.setVerticalGroup(
            btnRemoveSelected1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRemoveSelected1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconManual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        okButton.setText("OK");
        okButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                okButtonMousePressed(evt);
            }
        });

        jLabel3.setText("Giảm giá:");

        jLabel4.setText("%");

        try {
            toDateText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        toDateText.setToolTipText("Định dạng ngày: 25/03/2020");

        jLabel5.setText("Đến :");

        try {
            fromDateText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fromDateText.setToolTipText("Định dạng ngày: 25/03/2020");

        jLabel6.setText("Từ :");

        giamGiaButton.setText("Xác nhận");
        giamGiaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                giamGiaButtonMousePressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(messGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(boxTheme, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(boxSellingMethod, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRemoveSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnRemoveSelected1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(giamGiaText, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fromDateText, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(toDateText, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addComponent(giamGiaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRemoveSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(boxTheme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(boxSellingMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnRemoveSelected1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(giamGiaText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(fromDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(toDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(giamGiaButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(messGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
                .addComponent(okButton)
                .addGap(19, 19, 19))
        );
    }// </editor-fold>//GEN-END:initComponents
private void setColor() {
        Theme theme = MainJFrame.listTheme.get(0);
        btnRemoveSelected.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        btnRemoveSelected1.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        this.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        boxTheme.setBackground(Color.decode(theme.getBackgroundTextField()));
        boxSellingMethod.setBackground(Color.decode(theme.getBackgroundTextField()));
        okButton.setBackground(Color.decode(theme.getBackgroundButton()));

        jLabel1.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel2.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel7.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel8.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel3.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel6.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel5.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel4.setForeground(Color.decode(theme.getTxtColorTitle()));
        messGiamGia.setForeground(Color.decode(theme.getTxtColorTitle()));
    }
    private void btnRemoveSelectedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveSelectedMousePressed
        //        setSelectedColor(btn_sold);
        //        resetColor(new JPanel[]{btnKho, btnKhachHang, btnCTDT});

    }//GEN-LAST:event_btnRemoveSelectedMousePressed

    private void btnRemoveSelected1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveSelected1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRemoveSelected1MousePressed

    private void boxSellingMethodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxSellingMethodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxSellingMethodActionPerformed

    private void okButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okButtonMousePressed

//        MainJFrame.listTheme = TC.getTheme(theme);
//        this.mainJFrame.dispose();
//        MainJFrame frame = new MainJFrame();
//        frame.setVisible(true);
        int opt = JOptionPane.showConfirmDialog(this, "Xác nhận lưu lại những cài đặt trên?", "ask", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            String theme = boxTheme.getSelectedItem().toString();
            int typeSale = boxSellingMethod.getSelectedIndex();

            if (OCC.updateOption(theme, typeSale)) {
                JOptionPane.showMessageDialog(this, "Cập nhật cài đặt thành công, khỏi động lại chương trình!");

            } else {
                JOptionPane.showMessageDialog(this, "Có lỗi hệ thống, khỏi động lại chương trình!");
            }
            // MainJFrame.listTheme = TC.getTheme(theme);
            this.mainJFrame.dispose();
            MainJFrame frame = new MainJFrame();
            frame.setVisible(true);

        }

    }//GEN-LAST:event_okButtonMousePressed

    private void getCTGiamGia() {

        List<Discount> discountOtp = new ArrayList<>();
        discountOtp = OCC.getDiscout();
        String mess = "";
        if (discountOtp != null || discountOtp.size() > 0) {
            LocalDate date = LocalDate.now();
            int diss = discountOtp.get(0).getDiscount();
            LocalDate disFromDate = discountOtp.get(0).getFromDate();
            LocalDate disToDate = discountOtp.get(0).getToDate();
            if (disFromDate.isAfter(date) || disToDate.isBefore(date)) {
                mess = "Hiện tại không có chương trình giảm giá nào";

            } else {
                mess = "Giảm giá " + diss + "% từ ngày " + GM.dateToStr(disFromDate) + " tới ngày " + GM.dateToStr(disToDate) + " !";
            }

        } else {
            mess = "Hiện tại không có chương trình giảm giá nào!";
        }
        messGiamGia.setText(mess);
    }
    private void giamGiaButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giamGiaButtonMousePressed
        if (MainJFrame.permisson.equals("1")) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền thực hiện chức năng này");
        } else {

            int giamGia = 0;
            int ck = 1;
            try {
                String gg = giamGiaText.getText().trim();
                if (gg.length() > 0) {
                    giamGia = Integer.parseInt(gg);
                } else {
                    giamGia = 0;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Trường giảm giá phải nhập số nguyên");
                ck = 0;
            }
            if (giamGia >= 100 || giamGia < 0) {
                ck = 0;
                JOptionPane.showMessageDialog(this, "Trường giảm giá phải lớn hơn 0 và nhỏ hơn 100!");
            }
            if (giamGia > 0 && ck == 1) {

                String fromDate = fromDateText.getText().trim();
                String toDate = toDateText.getText().trim();

                if (GM.checkDate(toDate, "dd/MM/yyyy") && GM.checkDate(fromDate.trim(), "dd/MM/yyyy")) {

                    if (GM.strToDate(toDate, "dd/MM/yyyy").isBefore(GM.strToDate(fromDate, "dd/MM/yyyy"))) {
                        JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu");
                    } else {
                        if (OCC.updateDiscount(giamGia, GM.strToDate(fromDate, "dd/MM/yyyy"), GM.strToDate(toDate, "dd/MM/yyyy"))) {
                            JOptionPane.showMessageDialog(this, "Cập nhật chương trình giảm giá thành công");
                            getCTGiamGia();
                            this.mainJFrame.dispose();
                            MainJFrame frame = new MainJFrame();
                            frame.setVisible(true);

                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Kiểm tra lại ngày tháng");
                }
            }
        }
    }//GEN-LAST:event_giamGiaButtonMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxSellingMethod;
    private javax.swing.JComboBox<String> boxTheme;
    private javax.swing.JPanel btnRemoveSelected;
    private javax.swing.JPanel btnRemoveSelected1;
    private javax.swing.JFormattedTextField fromDateText;
    private javax.swing.JButton giamGiaButton;
    private javax.swing.JTextField giamGiaText;
    private javax.swing.JLabel iconAbout;
    private javax.swing.JLabel iconManual;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel messGiamGia;
    private javax.swing.JButton okButton;
    private javax.swing.JFormattedTextField toDateText;
    // End of variables declaration//GEN-END:variables
}
