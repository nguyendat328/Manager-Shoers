/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.BaoCao;

import DTOService.GeneralMethod;
import View.MainJFrame;
import View.Nhap.SanPhamPanel;
import controller.BaoCaoController.KhoController;
import controller.NhapController.SanPhamController;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.BaoCao.Kho;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;
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
 * @author choco
 */
public class KhoPanel extends javax.swing.JPanel {

    private MainJFrame mainJFrame;
    DefaultTableModel khoModel;
    GeneralMethod GM = new GeneralMethod();
    KhoController KC = new KhoController();
    SanPhamController SPController = new SanPhamController();
    JPopupMenu popupMenu = new JPopupMenu();

    /**
     *
     */
    List<Kho> recordView;
    List<Kho> recordAction = new ArrayList<>();
    String codeSPDetailSelected = "";

    public KhoPanel(MainJFrame mainJFrame) {
        initComponents();

        ////khai set mau----------------------
        setColor();
        // set iicon
        GM.setLabelIcon(iconDeleteMutil, MainJFrame.url + "delete.png");
        GM.setLabelIcon(iconExcel, MainJFrame.url + "excel.png");
        GM.setButtonIcon(resert, "../View/images/icon/clean.png", 20);
        GM.setButtonIcon(btnSearch, "../View/images/icon/search.png", 20);

        this.mainJFrame = mainJFrame;
        jFrame1.setDefaultCloseOperation(jFrame1.DISPOSE_ON_CLOSE);
        khoModel = (DefaultTableModel) khoTable.getModel();
        // ẩn cột code(id)
        khoTable.getColumnModel().getColumn(0).setMinWidth(0);
        khoTable.getColumnModel().getColumn(0).setMaxWidth(0);

        // set max Size của từng cột
        khoTable.getColumnModel().getColumn(1).setMaxWidth(70);
        khoTable.getColumnModel().getColumn(2).setMaxWidth(50);

        khoTable.getColumnModel().getColumn(6).setMaxWidth(150);
        // format combobox
        GM.formatClassCombobx(boxLoaiSP, SPController.getLoaiSP());
        GM.formatClassCombobx(boxTH, SPController.getThuongHieu());
        // khi chuột phải click vào thì chọn dòng đó
        khoTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                int r = khoTable.rowAtPoint(evt.getPoint());
                if (r >= 0 && r < khoTable.getRowCount()) {
                    khoTable.setRowSelectionInterval(r, r);
                } else {
                    khoTable.clearSelection();
                }
            }

        });
        // add popup menu
        JMenuItem editItem = new JMenuItem("Edit ");
        JMenuItem deleteItem = new JMenuItem("Delele ");
        popupMenu.add(editItem);
        popupMenu.add(deleteItem);
        editItem.addActionListener((e) -> {
            // chuột phải edit
            showUpdateDialog(recordAction);

        });
        deleteItem.addActionListener((e) -> {
            deleteSP(codeSPDetailSelected);

        });

        jFrame1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                KhoPanel.this.mainJFrame.setEnabled(true);
                KhoPanel.this.mainJFrame.setVisible(true);
            }
        });
        LocalDate date = LocalDate.now();

        fromDateText.setText("01/01/" + date.getYear());
        toDateText.setText("31/12/" + date.getYear());
//        jFrame1.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                SanPhamPanel.this.mainJFrame.setEnabled(true);
//                SanPhamPanel.this.mainJFrame.setVisible(true);
//            }
//        });
    }

    private void setColor() {
        Theme theme = MainJFrame.listTheme.get(0);
        boxLoaiSP.setBackground(Color.decode(theme.getBackgroundTextField()));
        boxTH.setBackground(Color.decode(theme.getBackgroundTextField()));
        btnSearch.setBackground(Color.decode(theme.getBackgroundButton()));
        btnSearch.setBorder(new LineBorder(Color.decode(theme.getTxtColorTitle())));
        resert.setBackground(Color.decode(theme.getBackgroundButton()));
        resert.setBorder(new LineBorder(Color.decode(theme.getTxtColorTitle())));;
        khoTable.getTableHeader().setOpaque(false);
        khoTable.getTableHeader().setBackground(Color.decode(theme.getBackgroundTittleTable()));
        khoTable.getTableHeader().setForeground(Color.decode(theme.getTxtColorTitleClick()));
        jLabel2.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel3.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel4.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel5.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel6.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel8.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel7.setForeground(Color.decode(theme.getTxtColorTitle()));

        jPanel1.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        jPanel2.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        this.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        btnExportExcell.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        btnRemoveSelected.setBackground(Color.decode(theme.getBackgroundMainPanel()));
    }

    public void deleteSP(String codeSPDetailS) {
        int opt = JOptionPane.showConfirmDialog(null, "Bạn có chắc xoá sản phẩm ' " + recordAction.get(0).getTenSP() + " ' không?", "Xoá sản phẩm", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            // Integer total = SPController.getTotalSPDetail(codeSPDetailS.substring(0, 32));
            if (SPController.deleteSPDetail(codeSPDetailS)) {
                JOptionPane.showMessageDialog(null, "Xoá sản phẩm thành công");
                Integer total = SPController.getTotalSPDetail(codeSPDetailS.substring(0, 32));
                if (total == 0) {
                    SPController.deleteSP(codeSPDetailS.substring(0, 32));
                }
                showKho();

            } else {
                JOptionPane.showMessageDialog(null, "Xoá sản phẩm thất bại (Sản phẩm đã bán sẽ không thể xóa)");
            }

        } else {

        }

    }

    public void exportExcel(List<Kho> list) {
        JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\"); //  duong dan
        excelFileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("TON KHO");

                XSSFRow row = null;
                Cell cell = null;

                row = spreadsheet.createRow((short) 2);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("DANH SÁCH TỒN KHO");

                row = spreadsheet.createRow((short) 3);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã sản phẩm");
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Tên sản phẩm");
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Số lượng");
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Đơn giá nhập");
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Dung tích");
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue("Loại sản phẩm");
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue("Thương hiệu");
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue("Ngày nhập");
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue("Ngày sửa");

                for (int i = 0; i < list.size(); i++) {
                    Kho Kh = list.get(i);
                    row = spreadsheet.createRow((short) 4 + i);
                    row.setHeight((short) 400);
                    row.createCell(0).setCellValue(i + 1);
                    row.createCell(1).setCellValue(Kh.getCodeProduct());
                    row.createCell(2).setCellValue(Kh.getTenSP());
                    row.createCell(3).setCellValue(Kh.getSoLuong());
                    row.createCell(4).setCellValue(Kh.getGiaNhap());
                    row.createCell(5).setCellValue(Kh.getCapacity());
                    row.createCell(6).setCellValue(Kh.getLoaiSP());
                    row.createCell(7).setCellValue(Kh.getThuongHieu());
                    row.createCell(8).setCellValue(Kh.getNgayNhap());
                    row.createCell(9).setCellValue(Kh.getNgaySua());
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

    public void showUpdateDialog(List<Kho> recordEdit) {

        THTextDialog.setText(recordEdit.get(0).getThuongHieu());
        loaiSPDialog.setText(recordEdit.get(0).getLoaiSP());
        nameSPFieldDialog.setText(recordEdit.get(0).getTenSP());
        soLuongDialog.setText(recordEdit.get(0).getSoLuong().toString());
        donGiaNhapDialog.setText(recordEdit.get(0).getGiaNhap().toString());
        jFrame1.setVisible(true);
        // jFrame1.setLocation(300, 200);
        jFrame1.setBounds(300, 200, 900, 600);
        this.mainJFrame.setEnabled(false);
    }

    public void showTable(List<Kho> list) {
        int no = 1;
        khoModel.setRowCount(0);
        for (Kho lts : list) {
            khoModel.addRow(new Object[]{
                lts.getCode(), new Boolean(false), no++, lts.getCodeProduct(), lts.getTenSP(), lts.getCapacity(), lts.getSoLuong(), GM.doubleToDecimal(lts.getGiaNhap()), GM.doubleToDecimal(lts.getGiaBanLe()), GM.doubleToDecimal(lts.getGiaBanBuon()), lts.getLoaiSP(), lts.getThuongHieu(), GM.dateToStr(lts.getNgayNhap()), GM.dateToStr(lts.getNgaySua())
            });
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

        jFrame1 = new javax.swing.JFrame();
        jPanel3 = new javax.swing.JPanel();
        soLuongDialog = new javax.swing.JTextField();
        giaXuatLeDialog = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        donGiaNhapDialog = new javax.swing.JTextField();
        giaXuatBuonDialog = new javax.swing.JTextField();
        btnCancelDialog = new javax.swing.JButton();
        nameSpDialog = new javax.swing.JPanel();
        nameSPFieldDialog = new javax.swing.JLabel();
        btnUpdateDialog = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        nameSpDialog1 = new javax.swing.JPanel();
        THTextDialog = new javax.swing.JLabel();
        nameSpDialog2 = new javax.swing.JPanel();
        loaiSPDialog = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        khoTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        fromDateText = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        toDateText = new javax.swing.JFormattedTextField();
        btnSearch = new javax.swing.JButton();
        resert = new javax.swing.JButton();
        boxLoaiSP = new javax.swing.JComboBox<>();
        boxTH = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnRemoveSelected = new javax.swing.JPanel();
        iconDeleteMutil = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnExportExcell = new javax.swing.JPanel();
        iconExcel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        soLuongDialog.setPreferredSize(new java.awt.Dimension(445, 40));
        soLuongDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soLuongDialogActionPerformed(evt);
            }
        });

        giaXuatLeDialog.setPreferredSize(new java.awt.Dimension(445, 40));
        giaXuatLeDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                giaXuatLeDialogMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                giaXuatLeDialogMousePressed(evt);
            }
        });

        jLabel10.setText("Đơn giá xuất buôn:");

        donGiaNhapDialog.setPreferredSize(new java.awt.Dimension(445, 40));
        donGiaNhapDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                donGiaNhapDialogMousePressed(evt);
            }
        });
        donGiaNhapDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donGiaNhapDialogActionPerformed(evt);
            }
        });

        giaXuatBuonDialog.setPreferredSize(new java.awt.Dimension(445, 40));
        giaXuatBuonDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                giaXuatBuonDialogMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                giaXuatBuonDialogMousePressed(evt);
            }
        });

        btnCancelDialog.setText("Cancle");
        btnCancelDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCancelDialogMouseClicked(evt);
            }
        });
        btnCancelDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelDialogActionPerformed(evt);
            }
        });

        nameSpDialog.setBackground(new java.awt.Color(255, 255, 255));
        nameSpDialog.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        nameSpDialog.setMinimumSize(new java.awt.Dimension(327, 40));
        nameSpDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameSpDialogMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout nameSpDialogLayout = new javax.swing.GroupLayout(nameSpDialog);
        nameSpDialog.setLayout(nameSpDialogLayout);
        nameSpDialogLayout.setHorizontalGroup(
            nameSpDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameSpDialogLayout.createSequentialGroup()
                .addComponent(nameSPFieldDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        nameSpDialogLayout.setVerticalGroup(
            nameSpDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nameSPFieldDialog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        btnUpdateDialog.setText("Cập Nhật");
        btnUpdateDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateDialogMouseClicked(evt);
            }
        });

        jLabel16.setText("Đơn giá xuất lẻ:");

        jLabel11.setText("Đơn giá nhập:");

        jLabel12.setText("Số Lượng:");

        jLabel13.setText("Tên Hàng:");

        jLabel14.setText("Loại Hàng:");

        jLabel15.setText("Thương Hiệu:");

        nameSpDialog1.setBackground(new java.awt.Color(255, 255, 255));
        nameSpDialog1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        nameSpDialog1.setMinimumSize(new java.awt.Dimension(327, 40));
        nameSpDialog1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameSpDialog1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout nameSpDialog1Layout = new javax.swing.GroupLayout(nameSpDialog1);
        nameSpDialog1.setLayout(nameSpDialog1Layout);
        nameSpDialog1Layout.setHorizontalGroup(
            nameSpDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameSpDialog1Layout.createSequentialGroup()
                .addComponent(THTextDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        nameSpDialog1Layout.setVerticalGroup(
            nameSpDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(THTextDialog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        nameSpDialog2.setBackground(new java.awt.Color(255, 255, 255));
        nameSpDialog2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));
        nameSpDialog2.setMinimumSize(new java.awt.Dimension(327, 40));
        nameSpDialog2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameSpDialog2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout nameSpDialog2Layout = new javax.swing.GroupLayout(nameSpDialog2);
        nameSpDialog2.setLayout(nameSpDialog2Layout);
        nameSpDialog2Layout.setHorizontalGroup(
            nameSpDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(nameSpDialog2Layout.createSequentialGroup()
                .addComponent(loaiSPDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        nameSpDialog2Layout.setVerticalGroup(
            nameSpDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(loaiSPDialog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCancelDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdateDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel10))
                        .addGap(38, 38, 38)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(soLuongDialog, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                            .addComponent(donGiaNhapDialog, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                            .addComponent(giaXuatLeDialog, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE)
                            .addComponent(nameSpDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameSpDialog2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameSpDialog1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(giaXuatBuonDialog, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))))
                .addGap(17, 17, 17))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameSpDialog1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel15)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameSpDialog2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameSpDialog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(soLuongDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(donGiaNhapDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(giaXuatLeDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(giaXuatBuonDialog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdateDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 70, Short.MAX_VALUE))
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jFrame1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 97, Short.MAX_VALUE))
        );

        setBackground(new java.awt.Color(110, 110, 110));

        khoTable.setAutoCreateRowSorter(true);
        khoTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Code", "Check", "STT", "Mã SP", "Tên SP", "Dung tích", "Số Lượng", "Giá nhập", "Giá xuất lẻ", "Giá xuất buôn", "Loại SP", "Thương hiệu", "Ngày nhập", "Ngày sửa"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        khoTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khoTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                khoTableMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(khoTable);
        if (khoTable.getColumnModel().getColumnCount() > 0) {
            khoTable.getColumnModel().getColumn(0).setResizable(false);
            khoTable.getColumnModel().getColumn(0).setPreferredWidth(0);
            khoTable.getColumnModel().getColumn(4).setMinWidth(100);
            khoTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            khoTable.getColumnModel().getColumn(4).setMaxWidth(150);
        }

        jPanel1.setBackground(new java.awt.Color(110, 110, 110));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Tên SP:");

        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchTextKeyPressed(evt);
            }
        });

        try {
            fromDateText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fromDateText.setToolTipText("Định dạng ngày: 25/03/2020");

        jLabel5.setText("Từ ngày:");

        jLabel6.setText("Đến ngày:");

        try {
            toDateText.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        toDateText.setToolTipText("Định dạng ngày: 25/03/2020");
        toDateText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                toDateTextKeyPressed(evt);
            }
        });

        btnSearch.setText("Tìm kiếm");
        btnSearch.setPreferredSize(new java.awt.Dimension(82, 40));
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnSearchMousePressed(evt);
            }
        });
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        resert.setText("Clean");
        resert.setPreferredSize(new java.awt.Dimension(62, 40));
        resert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resertActionPerformed(evt);
            }
        });

        jLabel3.setText("Thương hiệu:");

        jLabel4.setText("Loại sản phẩm:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boxLoaiSP, 0, 273, Short.MAX_VALUE)
                    .addComponent(boxTH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchText, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(fromDateText))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(resert, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                            .addComponent(toDateText))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)
                            .addComponent(toDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(boxTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(fromDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel4)
                    .addComponent(boxLoaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(110, 110, 110));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        btnRemoveSelected.setBackground(new java.awt.Color(179, 177, 177));
        btnRemoveSelected.setPreferredSize(new java.awt.Dimension(153, 88));
        btnRemoveSelected.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnRemoveSelectedMousePressed(evt);
            }
        });

        iconDeleteMutil.setAlignmentX(0.5F);
        iconDeleteMutil.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Xoá dòng đã chọn");

        javax.swing.GroupLayout btnRemoveSelectedLayout = new javax.swing.GroupLayout(btnRemoveSelected);
        btnRemoveSelected.setLayout(btnRemoveSelectedLayout);
        btnRemoveSelectedLayout.setHorizontalGroup(
            btnRemoveSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(btnRemoveSelectedLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(iconDeleteMutil, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnRemoveSelectedLayout.setVerticalGroup(
            btnRemoveSelectedLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnRemoveSelectedLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconDeleteMutil, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap())
        );

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(btnRemoveSelected, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExportExcell, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnRemoveSelected, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(btnExportExcell, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void resertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resertActionPerformed
        LocalDate date = LocalDate.now();
        fromDateText.setText("01/01/" + date.getYear());
        toDateText.setText("31/12/" + date.getYear());
        searchText.setText("");
        GM.formatClassCombobx(boxLoaiSP, SPController.getLoaiSP());
        GM.formatClassCombobx(boxTH, SPController.getThuongHieu());
    }//GEN-LAST:event_resertActionPerformed

    private void btnRemoveSelectedMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnRemoveSelectedMousePressed
//        setSelectedColor(btn_sold);
//        resetColor(new JPanel[]{btnKho, btnKhachHang, btnCTDT});
        String listCodeSPDetail = "";
        int n = khoTable.getRowCount();
        int ck = 0;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                if (khoModel.getValueAt(i, 1).toString().equalsIgnoreCase("true")) {
                    listCodeSPDetail = listCodeSPDetail + "'" + khoModel.getValueAt(i, 0).toString() + "'" + ",";
                    ck++;
                }
            }
        }
        //listCodeSPDetail = listCodeSPDetail.substring(0, listCodeSPDetail.length() - 1);

        int otp = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn xoá " + ck + " sản phẩm trên?", " Delete Muti", JOptionPane.YES_NO_OPTION);
        if (otp == JOptionPane.YES_OPTION) {
            listCodeSPDetail = listCodeSPDetail.substring(0, listCodeSPDetail.length() - 1);
            if (SPController.deleteSPDetailMuti(listCodeSPDetail)) {
                JOptionPane.showMessageDialog(null, "Xoá thành công");
                showKho();
            } else {
                JOptionPane.showMessageDialog(null, "Xoá thất bại");
            }

        }

    }//GEN-LAST:event_btnRemoveSelectedMousePressed

    private void btnExportExcellMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportExcellMousePressed
        exportExcel(recordView);
    }//GEN-LAST:event_btnExportExcellMousePressed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // button tìm kiếm
        showKho();

    }//GEN-LAST:event_btnSearchMouseClicked

    public void showKho() {
        String name = searchText.getText();
        String loaiSP = boxLoaiSP.getSelectedItem().toString();
        String thuongHieu = boxTH.getSelectedItem().toString();
        String fromDate = fromDateText.getText();
        String toDate = toDateText.getText();
        int ck = 0;
        if (GM.checkDate(fromDate, "dd/MM/yyyy") && GM.checkDate(toDate, "dd/MM/yyyy")) {
            if (GM.strToDate(toDate, "dd/MM/yyyy").isAfter(GM.strToDate(fromDate, "dd/MM/yyyy")) || GM.strToDate(toDate, "dd/MM/yyyy").isEqual(GM.strToDate(fromDate, "dd/MM/yyyy"))) {
                ck = 1;
            } else {
                JOptionPane.showMessageDialog(null, "Ngày kết thúc phải lớn hơn hoặc bằng ngày bắt đầu");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại ngày tháng!");
        }
        if (ck == 1) {
            if (recordView != null) {

                recordView.removeAll(recordView);
            }
            recordView = KC.getTonKho(name, loaiSP, thuongHieu, fromDate, toDate);
            showTable(recordView);
        }

    }
    private void khoTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khoTableMouseClicked

        if (SwingUtilities.isRightMouseButton(evt)) {
            codeSPDetailSelected = "";
            if (recordAction.size() > 0) {
                recordAction.removeAll(recordAction);
            }
            int i = khoTable.getSelectedRow();
            codeSPDetailSelected = khoModel.getValueAt(i, 0).toString();
            String name = khoModel.getValueAt(i, 4).toString();
            Integer soLuong = Integer.parseInt(khoModel.getValueAt(i, 5).toString());
            Double donGiaNhap = Double.parseDouble(khoModel.getValueAt(i, 6).toString().replace(",", ""));

            String loaiSP = khoModel.getValueAt(i, 9).toString();
            String thuongHieu = khoModel.getValueAt(i, 10).toString();

            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
            Kho recordKho = new Kho(codeSPDetailSelected, name, soLuong, donGiaNhap, loaiSP, thuongHieu);
            recordAction.add(recordKho);
        }

    }//GEN-LAST:event_khoTableMouseClicked

    private void btnUpdateDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateDialogMouseClicked

        Double giaNhap = 0.0;
        Double giaXuatBuon = 0.0;
        Double giaXuatLe = 0.0;

        int ck = 0;

        Integer soLuong = 0;
        try {
            giaNhap = Double.parseDouble(donGiaNhapDialog.getText());
            if (!"".equalsIgnoreCase(giaXuatLeDialog.getText())) {
                giaXuatLe = Double.parseDouble(giaXuatLeDialog.getText());
            };
            if (!"".equalsIgnoreCase(giaXuatBuonDialog.getText())) {
                giaXuatBuon = Double.parseDouble(giaXuatBuonDialog.getText());
            };
            soLuong = Integer.parseInt(soLuongDialog.getText());
            ck = 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (ck == 1) {
            //neu don gia xuat lon hon don gia nhap
            if (KC.updateSPdetail(codeSPDetailSelected, soLuong, giaNhap, giaXuatLe, giaXuatBuon)) {
                JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thành công");

                codeSPDetailSelected = "";
                recordAction.removeAll(recordAction);
                this.mainJFrame.setEnabled(true);
                jFrame1.setVisible(false);
                showKho();

            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật sản phẩm thất bại");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại đơn giá và số lượng");
        }

    }//GEN-LAST:event_btnUpdateDialogMouseClicked

    private void nameSpDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameSpDialogMouseClicked
//        nameSPInsert.setText("");
//        nameSpModel.setRowCount(0);
//        nameSPDialog.setVisible(true);
//        this.mainJFrame.setEnabled(false);
    }//GEN-LAST:event_nameSpDialogMouseClicked

    private void soLuongDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soLuongDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soLuongDialogActionPerformed

    private void donGiaNhapDialogMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donGiaNhapDialogMousePressed
        int a;
        try {
            a = Integer.parseInt(soLuongDialog.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại số lượng");
        }
    }//GEN-LAST:event_donGiaNhapDialogMousePressed

    private void donGiaNhapDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donGiaNhapDialogActionPerformed

    }//GEN-LAST:event_donGiaNhapDialogActionPerformed

    private void giaXuatLeDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giaXuatLeDialogMouseClicked

    }//GEN-LAST:event_giaXuatLeDialogMouseClicked

    private void giaXuatLeDialogMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giaXuatLeDialogMousePressed
//        Double price;
//        if (GM.isDouble(donGiaNhapDialog.getText())) {
//            price = Double.parseDouble(donGiaNhapDialog.getText()) * (1.4);
//            giaXuatLeDialog.setText(price.toString());
//        } else {
//            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại trường đơn giá nhập");
//
//        }
    }//GEN-LAST:event_giaXuatLeDialogMousePressed

    private void btnCancelDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCancelDialogMouseClicked
        jFrame1.setVisible(false);
        this.mainJFrame.setEnabled(true);
        this.mainJFrame.setVisible(true);
    }//GEN-LAST:event_btnCancelDialogMouseClicked

    private void btnCancelDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelDialogActionPerformed

    private void nameSpDialog1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameSpDialog1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_nameSpDialog1MouseClicked

    private void nameSpDialog2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameSpDialog2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_nameSpDialog2MouseClicked

    private void giaXuatBuonDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giaXuatBuonDialogMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_giaXuatBuonDialogMouseClicked

    private void giaXuatBuonDialogMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_giaXuatBuonDialogMousePressed
//        Double price;
//        if (GM.isDouble(donGiaNhapDialog.getText())) {
//            price = Double.parseDouble(donGiaNhapDialog.getText()) * (1.2);
//            giaXuatBuonDialog.setText(price.toString());
//        } else {
//            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại trường đơn giá nhập");
//
//        }
    }//GEN-LAST:event_giaXuatBuonDialogMousePressed

    private void searchTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            showKho();

        }

    }//GEN-LAST:event_searchTextKeyPressed

    private void toDateTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_toDateTextKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            showKho();

        }
    }//GEN-LAST:event_toDateTextKeyPressed

    private void btnSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMousePressed
        showKho();
    }//GEN-LAST:event_btnSearchMousePressed

    private void khoTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khoTableMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_khoTableMouseEntered

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel THTextDialog;
    private javax.swing.JComboBox<String> boxLoaiSP;
    private javax.swing.JComboBox<String> boxTH;
    private javax.swing.JButton btnCancelDialog;
    private javax.swing.JPanel btnExportExcell;
    private javax.swing.JPanel btnRemoveSelected;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdateDialog;
    private javax.swing.JTextField donGiaNhapDialog;
    private javax.swing.JFormattedTextField fromDateText;
    private javax.swing.JTextField giaXuatBuonDialog;
    private javax.swing.JTextField giaXuatLeDialog;
    private javax.swing.JLabel iconDeleteMutil;
    private javax.swing.JLabel iconExcel;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable khoTable;
    private javax.swing.JLabel loaiSPDialog;
    private javax.swing.JLabel nameSPFieldDialog;
    private javax.swing.JPanel nameSpDialog;
    private javax.swing.JPanel nameSpDialog1;
    private javax.swing.JPanel nameSpDialog2;
    private javax.swing.JButton resert;
    private javax.swing.JTextField searchText;
    private javax.swing.JTextField soLuongDialog;
    private javax.swing.JFormattedTextField toDateText;
    // End of variables declaration//GEN-END:variables
}
