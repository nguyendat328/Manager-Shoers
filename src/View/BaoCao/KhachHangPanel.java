/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.BaoCao;

import DTOService.GeneralMethod;
import View.MainJFrame;
import controller.BaoCaoController.KhoController;
import controller.NhapController.KhachHangController;
import controller.NhapController.SanPhamController;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.BaoCao.KhachHangBC;
import model.BaoCao.Kho;
import model.File.Theme;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author khai
 */
public class KhachHangPanel extends javax.swing.JPanel {

    private MainJFrame mainJFrame;
    DefaultTableModel KhModel;
    KhachHangController KHC = new KhachHangController();
    GeneralMethod GM = new GeneralMethod();
    String codeKhSelected = "";
//    JPopupMenu popupMenu = new JPopupMenu();

    List<KhachHangBC> recordAction = new ArrayList<>();
    JPopupMenu popupMenu = new JPopupMenu();

    /**
     * Creates new form KhachHangPanel
     */
    public KhachHangPanel(MainJFrame mainJFrame) {
        initComponents();
        ///khai set mau --------------

        Theme theme = MainJFrame.listTheme.get(0);
        KHTable.getTableHeader().setOpaque(false);
        KHTable.getTableHeader().setBackground(Color.decode(theme.getBackgroundTittleTable()));
        this.setBackground(Color.decode(theme.getBackgroundMainPanel()));

        // ẩn cột code(id)
        KhModel = (DefaultTableModel) KHTable.getModel();
        // ẩn cột code(id)
        KHTable.getColumnModel().getColumn(0).setMinWidth(0);
        KHTable.getColumnModel().getColumn(0).setMaxWidth(0);
        // set max Size của từng cột
        KHTable.getColumnModel().getColumn(1).setMaxWidth(70);
        KHTable.getColumnModel().getColumn(2).setMinWidth(100);
        KHTable.getColumnModel().getColumn(2).setMaxWidth(140);
        KHTable.getColumnModel().getColumn(3).setMinWidth(120);
        KHTable.getColumnModel().getColumn(3).setMaxWidth(120);
        // add popup menu
        JMenuItem editItem = new JMenuItem("Edit ");
        JMenuItem deleteItem = new JMenuItem("Delele ");
        popupMenu.add(editItem);
        popupMenu.add(deleteItem);
        editItem.addActionListener((e) -> {
            // chuột phải edit
            showUpdateDialog(codeKhSelected);

        });
        deleteItem.addActionListener((e) -> {
            deleteItem(codeKhSelected);

        });
        showTable(KHC.getKhachHang());
        KHTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                int r = KHTable.rowAtPoint(evt.getPoint());
                if (r >= 0 && r < KHTable.getRowCount()) {
                    KHTable.setRowSelectionInterval(r, r);
                } else {
                    KHTable.clearSelection();
                }
            }

        });
    }

    public void showUpdateDialog(String codeKhSelected) {

    }

    private void showTable(List<KhachHangBC> listKH) {

        int no = 1;
        KhModel.setRowCount(0);//xoa tat ca du lieu co trong bang mac dinh
        listKH.forEach((lts) -> {
            String nganHang = "";
            if (lts.getStk().length() > 0 && lts.getNgan_hang().length() > 0) {
                nganHang = lts.getStk() + " tại " + lts.getNgan_hang();
            } else {
                nganHang = lts.getStk();
            }
            KhModel.addRow(new Object[]{
                lts.getCode_kh(), KhModel.getRowCount() + 1,
                lts.getName_kh(), lts.getPhone(), nganHang, lts.getAddress(), lts.getMst(), lts.getLoaiKhach()});
        });
    }

    public void exportExcel(List<KhachHangBC> listKH) {
        JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\"); //  duong dan
        excelFileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("Khach Hang");

                XSSFRow row = null;
                Cell cell = null;

                row = spreadsheet.createRow((short) 2);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("DANH SÁCH KHÁCH HÀNG");

                row = spreadsheet.createRow((short) 3);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Tên khách hàng");
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Số điện thoại");
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Tài khoản");
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Địa chỉ");
                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue("Mã số thuế");

                for (int i = 0; i < listKH.size(); i++) {
                    KhachHangBC Kh = listKH.get(i);
                    row = spreadsheet.createRow((short) 4 + i);
                    row.setHeight((short) 400);
                    row.createCell(0).setCellValue(i + 1);
                    row.createCell(1).setCellValue(Kh.getName_kh());
                    row.createCell(2).setCellValue(Kh.getPhone());
                    row.createCell(3).setCellValue(Kh.getStk());
                    row.createCell(4).setCellValue(Kh.getAddress());
                    row.createCell(5).setCellValue(Kh.getMst());
                }

                FileOutputStream out = new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx");
                workbook.write(out);
                JOptionPane.showMessageDialog(null, "Xuất file thành công");
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

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

        jDialog1 = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nameKHText = new javax.swing.JTextField();
        mstText = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        stkText = new javax.swing.JTextField();
        nganHangText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        diaChiText = new javax.swing.JTextField();
        sdtText = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        boxLoaiKhach = new javax.swing.JComboBox<>();
        btnReset = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        KHTable = new javax.swing.JTable();
        btnExportExcell = new javax.swing.JPanel();
        iconExcel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        jLabel2.setText("Tên Khách hàng :");

        nameKHText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameKHTextActionPerformed(evt);
            }
        });

        jLabel4.setText("Mã số thuế :");

        jLabel6.setText("Số tài Khoản:");

        jLabel7.setText("Ngân Hàng");

        jLabel3.setText("Địa chỉ :");

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

        jLabel5.setText("Điện thoại:");

        jLabel9.setText("Loại Khách:");

        boxLoaiKhach.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Khách quen", "Đại lý" }));
        boxLoaiKhach.setMinimumSize(new java.awt.Dimension(65, 32));
        boxLoaiKhach.setName(""); // NOI18N

        btnReset.setText("Xoá thông tin");
        btnReset.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnResetMouseClicked(evt);
            }
        });

        btnAdd.setText("Thêm khách hàng");
        btnAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(499, Short.MAX_VALUE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sdtText)
                            .addComponent(diaChiText)
                            .addComponent(nganHangText)
                            .addComponent(stkText)
                            .addComponent(mstText)
                            .addComponent(nameKHText)
                            .addComponent(boxLoaiKhach, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameKHText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(mstText, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(stkText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(nganHangText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(diaChiText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(sdtText, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(boxLoaiKhach, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
        });

        KHTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "code", "STT", "Khách Hàng", "Điện Thoại", "TK Ngân Hàng", "Đia chỉ", "Mã số thuế", "Loại khách"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        KHTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KHTableMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                KHTableMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(KHTable);

        btnExportExcell.setBackground(new java.awt.Color(179, 177, 177));
        btnExportExcell.setPreferredSize(new java.awt.Dimension(153, 88));
        btnExportExcell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnExportExcellMousePressed(evt);
            }
        });

        iconExcel.setAlignmentX(0.5F);
        iconExcel.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Xuất file Excel");

        javax.swing.GroupLayout btnExportExcellLayout = new javax.swing.GroupLayout(btnExportExcell);
        btnExportExcell.setLayout(btnExportExcellLayout);
        btnExportExcellLayout.setHorizontalGroup(
            btnExportExcellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(btnExportExcellLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(iconExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnExportExcellLayout.setVerticalGroup(
            btnExportExcellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExportExcellLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconExcel, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 695, Short.MAX_VALUE)
                .addGap(33, 33, 33)
                .addComponent(btnExportExcell, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExportExcell, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 287, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void deleteItem(String codeKhSelected) {

        int option = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn xoá Khách hàng này?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (option == 0) {
            if (KHC.deleteKhachHang(codeKhSelected)) {
                JOptionPane.showMessageDialog(null, "Xoá thành công");

            } else {
                JOptionPane.showMessageDialog(null, "Xoá thất bại ( Khách đã mua hàng không thể xóa)");
            }

            showTable(KHC.getKhachHang());
        }
    }
    private void KHTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KHTableMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_KHTableMouseReleased

    private void btnExportExcellMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportExcellMousePressed
        exportExcel(KHC.getKhachHang());
    }//GEN-LAST:event_btnExportExcellMousePressed

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void KHTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KHTableMouseClicked
        if (SwingUtilities.isRightMouseButton(evt)) {
            codeKhSelected = "";
            if (recordAction.size() > 0) {
                recordAction.removeAll(recordAction);
            }
            int i = KHTable.getSelectedRow();
            codeKhSelected = KHTable.getValueAt(i, 0).toString();

            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());

        }
    }//GEN-LAST:event_KHTableMouseClicked

    private void nameKHTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameKHTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameKHTextActionPerformed

    private void sdtTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sdtTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sdtTextActionPerformed

    private void btnResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnResetMouseClicked

    }//GEN-LAST:event_btnResetMouseClicked

    private void btnAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddMouseClicked
    }//GEN-LAST:event_btnAddMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable KHTable;
    private javax.swing.JComboBox<String> boxLoaiKhach;
    private javax.swing.JButton btnAdd;
    private javax.swing.JPanel btnExportExcell;
    private javax.swing.JButton btnReset;
    private javax.swing.JTextField diaChiText;
    private javax.swing.JLabel iconExcel;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField mstText;
    private javax.swing.JTextField nameKHText;
    private javax.swing.JTextField nganHangText;
    private javax.swing.JFormattedTextField sdtText;
    private javax.swing.JTextField stkText;
    // End of variables declaration//GEN-END:variables
}
