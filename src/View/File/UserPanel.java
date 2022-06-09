/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.File;

import DTOService.GeneralMethod;
import View.BaoCao.KhoPanel;
import View.MainJFrame;
import controller.BaoCaoController.KhoController;
import javax.swing.JOptionPane;
import controller.FileController.UserController;
import controller.NhapController.SanPhamController;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.BaoCao.Kho;
import model.File.Account;
import model.File.Theme;

public class UserPanel extends javax.swing.JPanel {

    private MainJFrame mainJFrame;
    UserController UC = new UserController();
    GeneralMethod GM = new GeneralMethod();

    DefaultTableModel userModel;
    JPopupMenu popupMenu = new JPopupMenu();

    /**
     * Creates new form UserPanel
     */
    List<Account> recordAction = new ArrayList<>();
    String codeUserSelected = "";

    public UserPanel(MainJFrame mainJFrame) {
        initComponents();
        this.mainJFrame = mainJFrame;
        //khai set mau-------------------------
        setColor();
        userModel = (DefaultTableModel) userTable.getModel();
        // ẩn cột code(id)
        userTable.getColumnModel().getColumn(0).setMinWidth(0);
        userTable.getColumnModel().getColumn(0).setMaxWidth(0);
        userTable.getColumnModel().getColumn(1).setMaxWidth(100);
        showTable(UC.getAll());
        // khi chuột phải click vào thì chọn dòng đó
        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                int r = userTable.rowAtPoint(evt.getPoint());
                if (r >= 0 && r < userTable.getRowCount()) {
                    userTable.setRowSelectionInterval(r, r);
                } else {
                    userTable.clearSelection();
                }
            }

        });
        // add popup menu
        JMenuItem editItem = new JMenuItem("Đổi mật khẩu ");
        JMenuItem deleteItem = new JMenuItem("Delele ");
        popupMenu.add(editItem);
        popupMenu.add(deleteItem);
        editItem.addActionListener((e) -> {
            // chuột phải edit
            showUpdateDialog();

        });
        deleteItem.addActionListener((e) -> {
            deleteUser();

        });

        updatePassDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                UserPanel.this.mainJFrame.setEnabled(true);
                UserPanel.this.mainJFrame.setVisible(true);
            }
        });
        int x = this.mainJFrame.getLocationOnScreen().x + 400;
        int y = this.mainJFrame.getLocationOnScreen().y + 100;
        updatePassDialog.setLocation(x, y);

    }

    private void setColor() {

        Theme theme = MainJFrame.listTheme.get(0);

        action.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        this.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        jLabel1.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel2.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel3.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel7.setForeground(Color.decode(theme.getTxtColorTitle()));

        add.setBackground(Color.decode(theme.getBackgroundButton()));
        userTable.getTableHeader().setOpaque(false);
        userTable.getTableHeader().setBackground(Color.decode(theme.getBackgroundTittleTable()));
    }

    public void showTable(List<Account> list) {
        int no = 1;
        userModel.setRowCount(0);
        for (Account lts : list) {
            userModel.addRow(new Object[]{
                lts.getCode(), no++, lts.getUserName(), lts.getPhone(), lts.getPermission().equals("1") ? "Nhân Viên" : "Admin"
            });
        }

    }

    public void showUpdateDialog() {
        updatePassDialog.setVisible(true);
        this.mainJFrame.setEnabled(false);
    }

    public void deleteUser() {
        if (!recordAction.get(0).getPermission().equalsIgnoreCase("Admin")) {
            int opt = JOptionPane.showConfirmDialog(null, "Xác nhận xoá tài khoản ' " + recordAction.get(0).getUserName() + " ' ", "Xoá tài khoản ", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                if (UC.deleteUser(recordAction.get(0).getCode())) {
                    JOptionPane.showMessageDialog(null, "Xoá Tài khoản Thành công");
                    showTable(UC.getAll());
                } else {
                    JOptionPane.showMessageDialog(null, "Xoá Tài khoản Thất bại");
                }

            }

        } else {
            JOptionPane.showMessageDialog(null, "Không được xoá tài khoản Admin");
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

        updatePassDialog = new javax.swing.JDialog();
        reNewPass = new javax.swing.JPasswordField();
        newPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnOKJdialog = new javax.swing.JButton();
        oldPass = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        btnCancelJdailog = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTable = new javax.swing.JTable();
        action = new javax.swing.JPanel();
        userName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        rePass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        phoneText = new javax.swing.JTextField();

        updatePassDialog.setSize(new java.awt.Dimension(370, 210));

        jLabel4.setText("Mật Khẩu Mới:");

        jLabel5.setText("Nhập lại MK :");

        btnOKJdialog.setText("OK");
        btnOKJdialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOKJdialogMouseClicked(evt);
            }
        });
        btnOKJdialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKJdialogActionPerformed(evt);
            }
        });

        jLabel6.setText("Mật Khẩu Cũ:");

        btnCancelJdailog.setText("Cancel");
        btnCancelJdailog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelJdailogMouseClicked(evt);
            }
        });
        btnCancelJdailog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelJdailogActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updatePassDialogLayout = new javax.swing.GroupLayout(updatePassDialog.getContentPane());
        updatePassDialog.getContentPane().setLayout(updatePassDialogLayout);
        updatePassDialogLayout.setHorizontalGroup(
            updatePassDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatePassDialogLayout.createSequentialGroup()
                .addGroup(updatePassDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatePassDialogLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(updatePassDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(updatePassDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(oldPass)
                            .addComponent(reNewPass, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(newPass)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updatePassDialogLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelJdailog, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnOKJdialog, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(20, 20, 20))
        );
        updatePassDialogLayout.setVerticalGroup(
            updatePassDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updatePassDialogLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(updatePassDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(oldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(updatePassDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(newPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(updatePassDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(reNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(updatePassDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnOKJdialog)
                    .addComponent(btnCancelJdailog))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        setPreferredSize(new java.awt.Dimension(890, 371));
        setRequestFocusEnabled(false);

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "STT", "User Name", "Phone", "Permission"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(userTable);

        action.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tạo tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setText("User Name :");

        jLabel2.setText("Mật Khẩu:");

        jLabel3.setText("Nhập lại MK :");

        add.setText("Thêm");
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jLabel7.setText("Điện thoại:");

        javax.swing.GroupLayout actionLayout = new javax.swing.GroupLayout(action);
        action.setLayout(actionLayout);
        actionLayout.setHorizontalGroup(
            actionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(actionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(actionLayout.createSequentialGroup()
                        .addGroup(actionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(actionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, actionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(actionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rePass, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                            .addComponent(pass)
                            .addComponent(userName)
                            .addComponent(phoneText, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        actionLayout.setVerticalGroup(
            actionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(actionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(actionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(phoneText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(actionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(actionLayout.createSequentialGroup()
                        .addGroup(actionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addComponent(rePass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel3))
                .addGap(8, 8, 8)
                .addComponent(add)
                .addContainerGap(102, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(action, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(action, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addActionPerformed

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        String user = userName.getText();
        String phone = phoneText.getText();

        String passWord = GM.getPassWordMD5(new String(pass.getPassword()));
        String rePassWord = GM.getPassWordMD5(new String(rePass.getPassword()));
        String code = GM.randIdCreate();
        if (!passWord.equals(rePassWord)) {
            JOptionPane.showMessageDialog(null, "Hai trường mật khẩu không khớp!");
        } else {
            if (user.equalsIgnoreCase(UC.getUserName(user))) {
                JOptionPane.showMessageDialog(null, "Tên đăng nhập đã tồn tại");
            } else {
                if (UC.insertUser(code, user, passWord, phone)) {
                    JOptionPane.showMessageDialog(null, "Thêm tài khoản thành công!");
                    showTable(UC.getAll());
                    userName.setText("");
                    phoneText.setText("");
                    pass.setText("");
                    rePass.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Thêm tài khoản thất bại!");
                }

            }
        }

    }//GEN-LAST:event_addMouseClicked

    private void userTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTableMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            codeUserSelected = "";
            if (recordAction.size() > 0) {
                recordAction.removeAll(recordAction);
            }
            int i = userTable.getSelectedRow();
            codeUserSelected = userModel.getValueAt(i, 0).toString();
            String Uname = userModel.getValueAt(i, 2).toString();
            String phone = userModel.getValueAt(i, 3).toString();

            String per = userModel.getValueAt(i, 4).toString();

            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            Account record = new Account(codeUserSelected, Uname, phone, per);
            recordAction.add(record);
        }

    }//GEN-LAST:event_userTableMouseClicked

    private void btnOKJdialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOKJdialogMouseClicked
        String oldPassword = GM.getPassWordMD5(new String(oldPass.getPassword()));
        String newPassword = GM.getPassWordMD5(new String(newPass.getPassword()));
        String renewPassword = GM.getPassWordMD5(new String(reNewPass.getPassword()));
        if (UC.getPassword(codeUserSelected).equals(oldPassword)) {
            if (newPassword.equals(renewPassword)) {
                if (UC.updatePassWord(codeUserSelected, newPassword)) {
                    JOptionPane.showMessageDialog(null, "Cập nhật mật khẩu thành công !");
                    updatePassDialog.setVisible(false);
                    mainJFrame.setEnabled(true);
                    mainJFrame.setVisible(true);

                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật mật khẩu thất bại!");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Hai trường mật khẩu không khớp! ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Sai mật khẩu cũ");
        }

    }//GEN-LAST:event_btnOKJdialogMouseClicked

    private void btnOKJdialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKJdialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnOKJdialogActionPerformed

    private void btnCancelJdailogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelJdailogMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelJdailogMouseClicked

    private void btnCancelJdailogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelJdailogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelJdailogActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel action;
    private javax.swing.JButton add;
    private javax.swing.JButton btnCancelJdailog;
    private javax.swing.JButton btnOKJdialog;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPasswordField newPass;
    private javax.swing.JPasswordField oldPass;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField phoneText;
    private javax.swing.JPasswordField reNewPass;
    private javax.swing.JPasswordField rePass;
    private javax.swing.JDialog updatePassDialog;
    private javax.swing.JTextField userName;
    private javax.swing.JTable userTable;
    // End of variables declaration//GEN-END:variables
}
