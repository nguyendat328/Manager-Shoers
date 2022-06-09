/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Order;

import DTOService.GeneralMethod;
import View.MainJFrame;
import com.itextpdf.text.DocumentException;
import controller.Orders.OrderController;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.DTO.DisplayOrderInfoDTO;
import model.DTO.OrderDTO;
import model.DTO.OrderDisplay;
import model.File.Theme;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Jarvis
 */
public class OrderDeptPanel extends javax.swing.JPanel {

    /**
     * Creates new form OrderDeptPanel
     */
    OrderController OR = new OrderController();
    DefaultTableModel orderModel;
    DefaultTableModel displayOrderTableModel;
    GeneralMethod GM = new GeneralMethod();
    List<OrderDTO> listOrderDTO = new ArrayList<>();
    String codeOrder = "";
    JPopupMenu popupMenu = new JPopupMenu();

    public OrderDeptPanel() {
        initComponents();

        //khai set mau
        setColor();
        GM.setLabelIcon(iconExcel, MainJFrame.url + "excel.png");
        orderModel = (DefaultTableModel) deptTable.getModel();
        displayOrderTableModel = (DefaultTableModel) displayOrderTable.getModel();
        deptTable.getColumnModel().getColumn(0).setMaxWidth(70);
//        showOrderTable(OR.getListOrder("DEPT"));

        JMenuItem detailItem = new JMenuItem("Chi tiết ");
        popupMenu.add(detailItem);
        detailItem.addActionListener((e) -> {
            // chuột phải chi tiết
            showdetailDialog(codeOrder);
        });
        deptTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                int r = deptTable.rowAtPoint(evt.getPoint());
                if (r >= 0 && r < deptTable.getRowCount()) {
                    deptTable.setRowSelectionInterval(r, r);
                } else {
                    deptTable.clearSelection();
                }
            }

        });
        LocalDate date = LocalDate.now();
        toDateText.setText(GM.dateToStr(date));
        fromDateText.setText(GM.dateToStr(date.minusMonths(1)));

    }

    private void setColor() {
        // main view
        Theme theme = MainJFrame.listTheme.get(0);

        deptTable.getTableHeader().setOpaque(false);
        deptTable.getTableHeader().setBackground(Color.decode(theme.getBackgroundTittleTable()));
        btnSearch.setBackground(Color.decode(theme.getBackgroundButton()));
        resert.setBackground(Color.decode(theme.getBackgroundButton()));
        this.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        jPanel2.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        jPanel3.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        btnExportExcell.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        jLabel17.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel16.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel15.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel21.setForeground(Color.decode(theme.getTxtColorTitle()));

        //Dialog
        jLabel1.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel2.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel3.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel4.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel5.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel6.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel7.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel8.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel9.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel11.setForeground(Color.decode(theme.getTxtColorTitle()));
        jLabel12.setForeground(Color.decode(theme.getTxtColorTitle()));
        giamGia.setForeground(Color.decode(theme.getTxtColorTitle()));
        tongTien.setForeground(Color.decode(theme.getTxtColorTitle()));
        stk.setForeground(Color.decode(theme.getTxtColorTitle()));
        nganHang.setForeground(Color.decode(theme.getTxtColorTitle()));
        mst.setForeground(Color.decode(theme.getTxtColorTitle()));
        diaChi.setForeground(Color.decode(theme.getTxtColorTitle()));
        sdt.setForeground(Color.decode(theme.getTxtColorTitle()));
        nameKh.setForeground(Color.decode(theme.getTxtColorTitle()));

        createdAt.setForeground(Color.decode(theme.getTxtColorTitle()));
        orderCodeDisplay.setForeground(Color.decode(theme.getTxtColorTitle()));
        jPanel1.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        displayOrderTable.getTableHeader().setOpaque(false);
        displayOrderTable.getTableHeader().setBackground(Color.decode(theme.getBackgroundTittleTable()));
    }

    public void showdetailDialog(String codeOrder) {
        detailOrderDept.setVisible(true);
        displayOrderTableModel = (DefaultTableModel) displayOrderTable.getModel();
        showDisplayOrderTable(OR.getListOrderDisplay(codeOrder));
        showDisplayOrderInfo(OR.getDisplayOrderInfoDTO(codeOrder));
    }

    public void showDisplayOrderTable(List<OrderDisplay> list) {
        displayOrderTableModel.setRowCount(0);
        for (OrderDisplay lts : list) {
            displayOrderTableModel.addRow(new Object[]{
                lts.getNameSp(), GM.doubleToDecimal(lts.getDonGiaXuat()), lts.getSl(), GM.doubleToDecimal(lts.getSl() * lts.getDonGiaXuat())
            });
        }
    }

    public void showDisplayOrderInfo(DisplayOrderInfoDTO displayOrderInfoDTO) {
        createdAt.setText(displayOrderInfoDTO.getCreatedAt());
        orderCodeDisplay.setText(displayOrderInfoDTO.getCodeOrder());
        diaChi.setText(displayOrderInfoDTO.getAddress());
       // mst.setText(displayOrderInfoDTO.getMst());
        sdt.setText(displayOrderInfoDTO.getPhone());
        nameKh.setText(displayOrderInfoDTO.getNameKH());
       // nganHang.setText(displayOrderInfoDTO.getNganHang());
        //stk.setText(displayOrderInfoDTO.getStk());
        tongTien.setText(GM.doubleToDecimal(displayOrderInfoDTO.getTongTien()));
        giamGia.setText(GM.doubleToDecimal(displayOrderInfoDTO.getGiamGia()));

    }

    public void exportExcel(List<OrderDTO> list) {
        JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\"); //  duong dan
        excelFileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("ĐƠN NỢ");

                XSSFRow row = null;
                Cell cell = null;

                row = spreadsheet.createRow((short) 2);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("ĐƠN NỢ");

                row = spreadsheet.createRow((short) 3);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("STT");
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Mã Hóa Đơn");
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Tên Đại lí");
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Ngày tạo");
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue("Tổng tiền");
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue("Người bán ");

                for (int i = 0; i < list.size(); i++) {
                    OrderDTO orderDTO = list.get(i);
                    row = spreadsheet.createRow((short) 4 + i);
                    row.setHeight((short) 400);
                    row.createCell(0).setCellValue(i + 1);
                    row.createCell(1).setCellValue(orderDTO.getCodeOrder());
                    row.createCell(2).setCellValue(orderDTO.getNameKh());
                    row.createCell(3).setCellValue(orderDTO.getCreatAt());
                    row.createCell(4).setCellValue(orderDTO.getTongTien());
                    row.createCell(5).setCellValue(orderDTO.getUserName());

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

    public void showOrderTable(List<OrderDTO> list) {
        orderModel.setRowCount(0);
        int no = 1;
        for (OrderDTO lts : list) {
            orderModel.addRow(new Object[]{
                no++, lts.getCodeOrder(), lts.getNameKh(), lts.getCreatAt(),
                GM.doubleToDecimal(lts.getTongTien()), lts.getUserName()
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

        detailOrderDept = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        mst = new javax.swing.JLabel();
        stk = new javax.swing.JLabel();
        nganHang = new javax.swing.JLabel();
        sdt = new javax.swing.JLabel();
        diaChi = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayOrderTable = new javax.swing.JTable();
        nameKh = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        giamGia = new javax.swing.JLabel();
        tongTien = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        orderCodeDisplay = new javax.swing.JLabel();
        createdAt = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        deptTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        fromDateText = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        toDateText = new javax.swing.JFormattedTextField();
        btnSearch = new javax.swing.JButton();
        resert = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnExportExcell = new javax.swing.JPanel();
        iconExcel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        detailOrderDept.setMinimumSize(new java.awt.Dimension(900, 600));
        detailOrderDept.setSize(new java.awt.Dimension(900, 600));

        jPanel1.setMinimumSize(new java.awt.Dimension(900, 600));
        jPanel1.setPreferredSize(new java.awt.Dimension(900, 600));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Tên Khách hàng :");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Mã số thuế :");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Số tài Khoản:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Ngân Hàng :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Địa chỉ :");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Điện thoại :");

        displayOrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(displayOrderTable);

        jButton2.setText("In hóa đơn");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton2MousePressed(evt);
            }
        });

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Giảm giá :");

        giamGia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        giamGia.setPreferredSize(new java.awt.Dimension(225, 23));

        tongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tongTien.setPreferredSize(new java.awt.Dimension(225, 23));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Tổng tiền :");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHI TIẾT ĐƠN HÀNG");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Mã hóa đơn :");

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Ngày tạo :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(giamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(290, 290, 290)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nameKh, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(stk, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(mst, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(diaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nganHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createdAt, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orderCodeDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(19, 19, 19))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(orderCodeDisplay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(createdAt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(nameKh, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel4)
                        .addComponent(mst, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(nganHang, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stk, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(giamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(68, 68, 68))
        );

        javax.swing.GroupLayout detailOrderDeptLayout = new javax.swing.GroupLayout(detailOrderDept.getContentPane());
        detailOrderDept.getContentPane().setLayout(detailOrderDeptLayout);
        detailOrderDeptLayout.setHorizontalGroup(
            detailOrderDeptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, detailOrderDeptLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        detailOrderDeptLayout.setVerticalGroup(
            detailOrderDeptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 623, Short.MAX_VALUE)
        );

        deptTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên khách hàng", "Ngày nhận đơn", "Tổng Tiền", "Người bán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        deptTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deptTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(deptTable);

        jPanel2.setBackground(new java.awt.Color(110, 110, 110));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setToolTipText("");

        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setText("Tên Khách");

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

        jLabel16.setText("Từ ngày:");

        jLabel17.setText("Đến ngày:");

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
        btnSearch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSearchMouseClicked(evt);
            }
        });
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        resert.setText("Clean");
        resert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(fromDateText, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(resert, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(toDateText)))
                    .addComponent(searchText))
                .addGap(6, 6, 6))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(toDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(110, 110, 110));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        btnExportExcell.setBackground(new java.awt.Color(179, 177, 177));
        btnExportExcell.setPreferredSize(new java.awt.Dimension(153, 88));
        btnExportExcell.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btnExportExcellMousePressed(evt);
            }
        });

        iconExcel.setAlignmentX(0.5F);
        iconExcel.setPreferredSize(new java.awt.Dimension(60, 60));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Xuất file Excel");

        javax.swing.GroupLayout btnExportExcellLayout = new javax.swing.GroupLayout(btnExportExcell);
        btnExportExcell.setLayout(btnExportExcellLayout);
        btnExportExcellLayout.setHorizontalGroup(
            btnExportExcellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
            .addGroup(btnExportExcellLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(iconExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        btnExportExcellLayout.setVerticalGroup(
            btnExportExcellLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnExportExcellLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iconExcel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExportExcell, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnExportExcell, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void deptTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deptTableMouseClicked
        // TODO add your handling code here:
        if (SwingUtilities.isRightMouseButton(evt)) {
            int i = deptTable.getSelectedRow();
            codeOrder = "";
            codeOrder = orderModel.getValueAt(i, 1).toString();
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_deptTableMouseClicked

    private void searchTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            showOrderDept();

        }
    }//GEN-LAST:event_searchTextKeyPressed

    private void toDateTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_toDateTextKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            showOrderDept();

        }
    }//GEN-LAST:event_toDateTextKeyPressed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked
        // button tìm kiếm
        showOrderDept();
    }//GEN-LAST:event_btnSearchMouseClicked

    public void showOrderDept() {
        String txtSearch = searchText.getText().trim();
        String fromDate = fromDateText.getText().trim();
        String toDate = toDateText.getText().trim();

        if (txtSearch.length() > 0 || (GM.checkDate(fromDate, "dd/MM/yyyy") && GM.checkDate(toDate, "dd/MM/yyyy"))) {
            listOrderDTO.removeAll(listOrderDTO);
            listOrderDTO = OR.getListOrder("DEPT", txtSearch, fromDate, toDate);
            if (listOrderDTO.size() > 0) {
                showOrderTable(listOrderDTO);
            } else {
                JOptionPane.showMessageDialog(this, "Không có dữ liệu");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Kiểm tra lại tên khách hoặc ngày tháng");
        }
    }
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void resertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_resertActionPerformed

    private void btnExportExcellMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnExportExcellMousePressed

        // exportExcel(recordView);
        exportExcel(listOrderDTO);
    }//GEN-LAST:event_btnExportExcellMousePressed

    private void jButton2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MousePressed
        try {
            // TODO add your handling code here:

            OR.PDFDept(OR.getListOrderDisplay(codeOrder), OR.getDisplayOrderInfoDTO(codeOrder));
            JOptionPane.showMessageDialog(null, "Đã in hóa đơn");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderDeptPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(OrderDeptPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2MousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnExportExcell;
    private javax.swing.JButton btnSearch;
    private javax.swing.JLabel createdAt;
    private javax.swing.JTable deptTable;
    private javax.swing.JDialog detailOrderDept;
    private javax.swing.JLabel diaChi;
    private javax.swing.JTable displayOrderTable;
    private javax.swing.JFormattedTextField fromDateText;
    private javax.swing.JLabel giamGia;
    private javax.swing.JLabel iconExcel;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel mst;
    private javax.swing.JLabel nameKh;
    private javax.swing.JLabel nganHang;
    private javax.swing.JLabel orderCodeDisplay;
    private javax.swing.JButton resert;
    private javax.swing.JLabel sdt;
    private javax.swing.JTextField searchText;
    private javax.swing.JLabel stk;
    private javax.swing.JFormattedTextField toDateText;
    private javax.swing.JLabel tongTien;
    // End of variables declaration//GEN-END:variables
}
