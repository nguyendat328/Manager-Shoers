/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Nhap;

import DTOService.GeneralMethod;
import View.MainJFrame;

import controller.NhapController.SanPhamController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import model.File.Theme;
import model.Nhap.SanPham;

/**
 *
 * @author choco
 */
public class SanPhamPanel extends javax.swing.JPanel {

    private MainJFrame mainJFrame;
    GeneralMethod GM = new GeneralMethod();
    SanPhamController SPController = new SanPhamController();
    DefaultTableModel nameSpModel;
    List<SanPham> listNameSP;
    private String nameSp;

    /**
     * Creates new form Product
     */
    public SanPhamPanel(MainJFrame mainJFrame) {
        nameSp = "";
        initComponents();

        //khai set mau-----------------------------
        setColor();

        this.mainJFrame = mainJFrame;
        nameSpModel = (DefaultTableModel) nameSPTable.getModel();
        GM.formatClassCombobx(boxLoaiSP, SPController.getLoaiSP());
        // GM.formatClassCombobx(boxThuongHieu, SPController.getThuongHieu());
//        nameSPDialog.addWindowListener(new WindowAdapter() {
//
//        });

        nameSPDialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                SanPhamPanel.this.mainJFrame.setEnabled(true);
                SanPhamPanel.this.mainJFrame.setVisible(true);
            }
        });
        addLoaiSPDialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                SanPhamPanel.this.mainJFrame.setEnabled(true);
                SanPhamPanel.this.mainJFrame.setVisible(true);
            }
        });
        addTHDialog.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                SanPhamPanel.this.mainJFrame.setEnabled(true);
                SanPhamPanel.this.mainJFrame.setVisible(true);
            }
        });

        int x = this.mainJFrame.getLocationOnScreen().x + 400;
        int y = this.mainJFrame.getLocationOnScreen().y + 100;
        nameSPDialog.setLocation(x, y);
        addLoaiSPDialog.setLocation(x, y);
        addTHDialog.setLocation(x, y);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameSPDialog = new javax.swing.JDialog();
        nameSPInsert = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        nameSPTable = new javax.swing.JTable();
        okNameSP = new javax.swing.JButton();
        addTHDialog = new javax.swing.JDialog();
        jLabel7 = new javax.swing.JLabel();
        addTHText = new javax.swing.JTextField();
        btnAddThOk = new javax.swing.JButton();
        addLoaiSPDialog = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        addLoaiSPText = new javax.swing.JTextField();
        btnaddLoaiSP = new javax.swing.JButton();
        boxLoaiSP = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nameSpPanel = new javax.swing.JPanel();
        codeProductField = new javax.swing.JLabel();
        donGiaNhap = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        btnOkNhapSP = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        soLuongText = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        nameSPText = new javax.swing.JTextField();

        nameSPDialog.setSize(new java.awt.Dimension(500, 371));

        nameSPInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameSPInsertActionPerformed(evt);
            }
        });
        nameSPInsert.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nameSPInsertKeyTyped(evt);
            }
        });

        jLabel4.setText("Mã Hàng: ");

        nameSPTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hàng", "Tên Mặt Hàng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        nameSPTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameSPTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(nameSPTable);
        if (nameSPTable.getColumnModel().getColumnCount() > 0) {
            nameSPTable.getColumnModel().getColumn(0).setResizable(false);
            nameSPTable.getColumnModel().getColumn(1).setResizable(false);
        }

        okNameSP.setText("OK");
        okNameSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                okNameSPMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                okNameSPMousePressed(evt);
            }
        });
        okNameSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okNameSPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout nameSPDialogLayout = new javax.swing.GroupLayout(nameSPDialog.getContentPane());
        nameSPDialog.getContentPane().setLayout(nameSPDialogLayout);
        nameSPDialogLayout.setHorizontalGroup(
            nameSPDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameSPDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(nameSPDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(nameSPDialogLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(nameSPInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, nameSPDialogLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(okNameSP, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        nameSPDialogLayout.setVerticalGroup(
            nameSPDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameSPDialogLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(nameSPDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(nameSPInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(okNameSP)
                .addContainerGap())
        );

        addTHDialog.setResizable(false);
        addTHDialog.setSize(new java.awt.Dimension(500, 230));

        jLabel7.setText("Nhập Thương Hiệu mới");

        addTHText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTHTextActionPerformed(evt);
            }
        });

        btnAddThOk.setText("Thêm");
        btnAddThOk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddThOkMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout addTHDialogLayout = new javax.swing.GroupLayout(addTHDialog.getContentPane());
        addTHDialog.getContentPane().setLayout(addTHDialogLayout);
        addTHDialogLayout.setHorizontalGroup(
            addTHDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTHDialogLayout.createSequentialGroup()
                .addGroup(addTHDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAddThOk, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addTHDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addTHDialogLayout.createSequentialGroup()
                            .addGap(187, 187, 187)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(addTHDialogLayout.createSequentialGroup()
                            .addGap(34, 34, 34)
                            .addComponent(addTHText, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        addTHDialogLayout.setVerticalGroup(
            addTHDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTHDialogLayout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addTHText, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAddThOk)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        addLoaiSPDialog.setSize(new java.awt.Dimension(515, 176));

        jLabel8.setText("Nhập loại sản phẩm mới");

        btnaddLoaiSP.setText("Thêm");
        btnaddLoaiSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnaddLoaiSPMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout addLoaiSPDialogLayout = new javax.swing.GroupLayout(addLoaiSPDialog.getContentPane());
        addLoaiSPDialog.getContentPane().setLayout(addLoaiSPDialogLayout);
        addLoaiSPDialogLayout.setHorizontalGroup(
            addLoaiSPDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLoaiSPDialogLayout.createSequentialGroup()
                .addGroup(addLoaiSPDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnaddLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addLoaiSPDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(addLoaiSPDialogLayout.createSequentialGroup()
                            .addGap(180, 180, 180)
                            .addComponent(jLabel8))
                        .addGroup(addLoaiSPDialogLayout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(addLoaiSPText, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        addLoaiSPDialogLayout.setVerticalGroup(
            addLoaiSPDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addLoaiSPDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addLoaiSPText, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnaddLoaiSP)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        boxLoaiSP.setPreferredSize(new java.awt.Dimension(33, 30));
        boxLoaiSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxLoaiSPActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã:");

        jLabel3.setText("Dịch vụ / Sản phẩm:");

        nameSpPanel.setBackground(new java.awt.Color(255, 255, 255));
        nameSpPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        nameSpPanel.setMinimumSize(new java.awt.Dimension(327, 30));
        nameSpPanel.setPreferredSize(new java.awt.Dimension(327, 30));
        nameSpPanel.setRequestFocusEnabled(false);
        nameSpPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameSpPanelMouseClicked(evt);
            }
        });

        codeProductField.setText("Click để nhập Mã hàng!");

        javax.swing.GroupLayout nameSpPanelLayout = new javax.swing.GroupLayout(nameSpPanel);
        nameSpPanel.setLayout(nameSpPanelLayout);
        nameSpPanelLayout.setHorizontalGroup(
            nameSpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameSpPanelLayout.createSequentialGroup()
                .addComponent(codeProductField, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        nameSpPanelLayout.setVerticalGroup(
            nameSpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(codeProductField, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        donGiaNhap.setPreferredSize(new java.awt.Dimension(445, 30));
        donGiaNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                donGiaNhapMousePressed(evt);
            }
        });
        donGiaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donGiaNhapActionPerformed(evt);
            }
        });

        jLabel5.setText("Giá nhập:");

        jButton3.setText("Xoá thông tin");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        btnOkNhapSP.setText("Nhập Hàng");
        btnOkNhapSP.setPreferredSize(new java.awt.Dimension(215, 110));
        btnOkNhapSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOkNhapSPMouseClicked(evt);
            }
        });

        jLabel9.setText("Số Lượng:");

        soLuongText.setPreferredSize(new java.awt.Dimension(445, 30));
        soLuongText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soLuongTextActionPerformed(evt);
            }
        });

        jLabel11.setText("Tên:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel9)
                    .addComponent(jLabel11))
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxLoaiSP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(soLuongText, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addComponent(donGiaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameSpPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
                    .addComponent(nameSPText))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOkNhapSP, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOkNhapSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(boxLoaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameSpPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameSPText, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(soLuongText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(donGiaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addContainerGap(241, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
private void setColor() {
        Theme theme = MainJFrame.listTheme.get(0);
        boxLoaiSP.setBackground(Color.decode(theme.getBackgroundTextField()));
        // boxThuongHieu.setBackground(Color.decode(theme.getBackgroundTextField()));
        //btnAddLoaiSP.setBackground(Color.decode(theme.getBackgroundButton()));
        // btnAddTH.setBackground(Color.decode(theme.getBackgroundButton()));
        btnAddThOk.setBackground(Color.decode(theme.getBackgroundButton()));
        btnOkNhapSP.setBackground(Color.decode(theme.getBackgroundButton()));
        jButton3.setBackground(Color.decode(theme.getBackgroundButton()));
        btnaddLoaiSP.setBackground(Color.decode(theme.getBackgroundButton()));
        this.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        //  jLabel1.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel2.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel3.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel5.setForeground(Color.decode(theme.getTxtColorTitle()));
        // jLabel6.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel9.setForeground(Color.decode(theme.getTxtColorTitle()));

        jLabel11.setForeground(Color.decode(theme.getTxtColorTitle()));
    }
    private void boxLoaiSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxLoaiSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxLoaiSPActionPerformed

    private void nameSPInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameSPInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameSPInsertActionPerformed

    private void nameSPInsertKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameSPInsertKeyTyped
        String codeSP = nameSPInsert.getText();
        String thuongHieu = "select";
        String loaiSP = boxLoaiSP.getSelectedItem().toString();
        if (codeSP.length() >= 1) {
            listNameSP = SPController.getNameSanPham(thuongHieu, loaiSP, codeSP);
            showNameSpTable(listNameSP);
        }

    }//GEN-LAST:event_nameSPInsertKeyTyped

    private void okNameSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okNameSPActionPerformed

    }//GEN-LAST:event_okNameSPActionPerformed

    private void donGiaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donGiaNhapActionPerformed

    }//GEN-LAST:event_donGiaNhapActionPerformed

    private void addTHTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTHTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addTHTextActionPerformed

    private void btnAddThOkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddThOkMouseClicked
//        String nameTH = addTHText.getText().trim();
//        if (nameTH.length() > 0) {
//
//            if (SPController.getThuongHieuCode(nameTH).length() <= 0) {
//                String code = GM.randIdCreate();
//                if (SPController.insertThuongHieu(code, nameTH)) {
//                    JOptionPane.showMessageDialog(null, "Thêm thành công thương hiệu " + nameTH, "Thêm Thương Hiệu", JOptionPane.INFORMATION_MESSAGE);
//
//                    //GM.formatClassCombobx(boxThuongHieu, SPController.getThuongHieu());
//                    addTHDialog.setVisible(false);
//                    this.mainJFrame.setEnabled(true);
//                    this.mainJFrame.setVisible(true);
//                } else {
//                    JOptionPane.showMessageDialog(null, "Thêm thất bại, vui lòng thử lại ", "Thêm Thương Hiệu", JOptionPane.ERROR_MESSAGE);
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Thương hiệu đã tồn tại, vui lòng kiểm tra lại", "Thương Hiệu lỗi", JOptionPane.ERROR_MESSAGE);
//
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Vui lòng nhập thương hiệu", "Thương Hiệu lỗi", JOptionPane.ERROR_MESSAGE);
//        }
        JOptionPane.showMessageDialog(null, "BTN nhapj thuong hieu", "Thương Hiệu lỗi", JOptionPane.ERROR_MESSAGE);

    }//GEN-LAST:event_btnAddThOkMouseClicked

    private void btnaddLoaiSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnaddLoaiSPMouseClicked
//        String nameloaiSP = addLoaiSPText.getText().trim();
//        if (nameloaiSP.length() > 0) {
//            if (SPController.getLoaiSPCode(nameloaiSP).length() <= 0) {
//                String code = GM.randIdCreate();
//                if (SPController.insertLoaiSP(code, nameloaiSP)) {
//                    JOptionPane.showMessageDialog(null, "Thêm thành công loại mới " + nameloaiSP, "Thêm Loại Sản Phẩm", JOptionPane.PLAIN_MESSAGE);
//                    GM.formatClassCombobx(boxLoaiSP, SPController.getLoaiSP());
//                    addLoaiSPDialog.setVisible(false);
//                    this.mainJFrame.setEnabled(true);
//                    this.mainJFrame.setVisible(true);
//
//                } else {
//                    JOptionPane.showMessageDialog(null, "Thêm thất bại, vui lòng thử lại ", "Thêm Loại Sản Phẩm Lỗi", JOptionPane.ERROR_MESSAGE);
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Loại Sản Phẩm đã tồn tại, vui lòng kiểm tra lại", "Thêm Loại Sản Phẩm Lỗi", JOptionPane.ERROR_MESSAGE);
//
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Vui lòng nhập loại sản phẩm", "Thêm Loại Sản Phẩm Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
        JOptionPane.showMessageDialog(null, "BTN nhap loai", "Loai lỗi", JOptionPane.ERROR_MESSAGE);
        //loai chỉ có sản phẩm hoặc dịch vụ đã hash trong db

    }//GEN-LAST:event_btnaddLoaiSPMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        cleanForm();
    }//GEN-LAST:event_jButton3MouseClicked

    private void btnOkNhapSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOkNhapSPMouseClicked
        // nhạp sản pham vao he thong
        importProduct();
    }//GEN-LAST:event_btnOkNhapSPMouseClicked

    public void importProduct() {
        int ck = 0;
        btnOkNhapSP.setEnabled(false);
        // String thuongHieuCode = SPController.getThuongHieuCode(boxThuongHieu.getSelectedItem().toString());
        String loaiSPCode = SPController.getLoaiSPCode(boxLoaiSP.getSelectedItem().toString());
        String nameSP = nameSPText.getText();
        String codeProduct = codeProductField.getText();
        String codeSP = GM.randIdCreate();
        List<SanPham> listCode = SPController.getSPCode(codeProduct);
        String checkCodeSP = "";
        if (listCode.size() > 0) {
            checkCodeSP = listCode.get(0).getCodeSP();
        }

        double dGNhap = 0.0;
        double dGXuat = 0.0;
        if (GM.isDouble(donGiaNhap.getText()) || donGiaNhap.getText().equals("0")) {
            dGNhap = Double.parseDouble(donGiaNhap.getText());
        } else {
            ck = 1;
        }

        // String cap = txtDungTich.getText();
        int soLuong = 0;
        try {
            soLuong = Integer.parseInt(soLuongText.getText());
        } catch (Exception e) {
            ck = 1;
        }

        if (dGNhap >= 0 && ck == 0) {
            //neu don gia xuat lon hon don gia nhap
            if (checkCodeSP.length() > 0) {
                //neu co code sp
                Integer total = SPController.getTotalSPDetail(checkCodeSP);
                if (total > 0) {
                    //dem so luong sân_pham_detail
                    int a = total + 1;

                    // nhap vao bang sp_detrail khi da ton tai sp
                    String codeSPDetail = checkCodeSP + a;
                    if (SPController.insertSPDetail(codeSPDetail, checkCodeSP, soLuong, dGNhap, dGXuat)) {
                        if (SPController.updateSPdetailByNewPrice(checkCodeSP, dGXuat)) {
                            JOptionPane.showMessageDialog(null, "Nhập Hàng Thành Công!");
                        }

                        cleanForm();
                    } else {
                        JOptionPane.showMessageDialog(null, "Nhập Hàng Thất Bại!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nhập Hàng Thất Bại!");
                }

            } else {
                //nhap  sp moi
                if (!boxLoaiSP.getSelectedItem().toString().equalsIgnoreCase("select")) {

                    if (SPController.insertSP(codeSP, loaiSPCode, codeProduct, nameSP)) {
                        String codeSPDetail = codeSP + "1";
                        if (SPController.insertSPDetail(codeSPDetail, codeSP, soLuong, dGNhap, dGXuat)) {
                            JOptionPane.showMessageDialog(null, "Nhập Hàng Thành Công!");
                            cleanForm();
                        } else {
                            JOptionPane.showMessageDialog(null, "Nhập Hàng Thất Bại!");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Nhập Hàng Thất Bại!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn Thương Hiệu và Loại Sản Phẩm");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại đơn giá và số lượng");
        }
        btnOkNhapSP.setEnabled(true);
    }

    public void cleanForm() {
        // xoa thong tin da nhap/chon
        GM.formatClassCombobx(boxLoaiSP, SPController.getLoaiSP());
        //GM.formatClassCombobx(boxThuongHieu, SPController.getThuongHieu());
        soLuongText.setText("");
        codeProductField.setText("Click để nhập Mã hàng!");
        donGiaNhap.setText("");
        nameSPText.setText("");

    }

    private void okNameSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okNameSPMouseClicked

    }//GEN-LAST:event_okNameSPMouseClicked

    private void nameSPTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameSPTableMouseClicked
        int row = nameSPTable.getSelectedRow();
        String name = nameSPInsert.getText();

        String codeSP = nameSpModel.getValueAt(row, 0).toString();
        nameSp = nameSpModel.getValueAt(row, 1).toString();

        nameSPInsert.setText(codeSP);
        nameSPText.setText(nameSp);
    }//GEN-LAST:event_nameSPTableMouseClicked

    private void okNameSPMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_okNameSPMousePressed
        String code = nameSPInsert.getText();
        if (code.trim().length() > 0) {
            codeProductField.setText(code);
            nameSPDialog.setVisible(false);
            this.mainJFrame.setEnabled(true);
            this.mainJFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại tên sản phẩm");

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_okNameSPMousePressed

    private void soLuongTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soLuongTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soLuongTextActionPerformed

    private void donGiaNhapMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donGiaNhapMousePressed
        int a;
        try {
            a = Integer.parseInt(soLuongText.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại số lượng");
        }
    }//GEN-LAST:event_donGiaNhapMousePressed

    private void nameSpPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameSpPanelMouseClicked
        nameSp = "";
        nameSPInsert.setText("");
        nameSpModel.setRowCount(0);
        nameSPDialog.setVisible(true);
        this.mainJFrame.setEnabled(false);
    }//GEN-LAST:event_nameSpPanelMouseClicked

    public void showNameSpTable(List<SanPham> list) {
        nameSpModel.setRowCount(0);
        for (SanPham lts : list) {
            nameSpModel.addRow(new Object[]{
                lts.getProductCode(), lts.getName()
            });
        }
//        nameSPInsert.setText("");
//        nameSpModel.setRowCount(0);
//        nameSPDialog.setVisible(true);
//        this.mainJFrame.setEnabled(false);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog addLoaiSPDialog;
    private javax.swing.JTextField addLoaiSPText;
    private javax.swing.JDialog addTHDialog;
    private javax.swing.JTextField addTHText;
    private javax.swing.JComboBox<String> boxLoaiSP;
    private javax.swing.JButton btnAddThOk;
    private javax.swing.JButton btnOkNhapSP;
    private javax.swing.JButton btnaddLoaiSP;
    private javax.swing.JLabel codeProductField;
    private javax.swing.JTextField donGiaNhap;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JDialog nameSPDialog;
    private javax.swing.JTextField nameSPInsert;
    private javax.swing.JTable nameSPTable;
    private javax.swing.JTextField nameSPText;
    private javax.swing.JPanel nameSpPanel;
    private javax.swing.JButton okNameSP;
    private javax.swing.JTextField soLuongText;
    // End of variables declaration//GEN-END:variables
}
