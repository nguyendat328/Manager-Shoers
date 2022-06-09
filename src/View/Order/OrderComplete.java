/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Order;

import DTOService.GeneralMethod;
import View.MainJFrame;
import controller.Orders.OrderController;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import model.BaoCao.CTDT;
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
public class OrderComplete extends javax.swing.JPanel {

    OrderController OR = new OrderController();
    DefaultTableModel orderModel;
    DefaultTableModel displayOrderTableModel;
    GeneralMethod GM = new GeneralMethod();

    List<OrderDTO> listOrderDTO = new ArrayList<>();
    String codeOrder = "";
    JPopupMenu popupMenu = new JPopupMenu();

    /**
     * Creates new form OrderComplete
     */
    public OrderComplete() {
        initComponents();

        //khai set mau
        setColor();
        GM.setLabelIcon(iconExcel, MainJFrame.url + "excel.png");
        orderModel = (DefaultTableModel) completeTable.getModel();
        displayOrderTableModel = (DefaultTableModel) displayOrderTable.getModel();
        completeTable.getColumnModel().getColumn(0).setMaxWidth(70);

        JMenuItem detailItem = new JMenuItem("Chi tiết ");
        popupMenu.add(detailItem);
        detailItem.addActionListener((e) -> {
            // chuột phải chi tiết
            showdetailDialog(codeOrder);
        });
        completeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent evt) {
                int r = completeTable.rowAtPoint(evt.getPoint());
                if (r >= 0 && r < completeTable.getRowCount()) {
                    completeTable.setRowSelectionInterval(r, r);
                } else {
                    completeTable.clearSelection();
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

        completeTable.getTableHeader().setOpaque(false);
        completeTable.getTableHeader().setBackground(Color.decode(theme.getBackgroundTittleTable()));
        btnSearch.setBackground(Color.decode(theme.getBackgroundButton()));
        resert.setBackground(Color.decode(theme.getBackgroundButton()));
        this.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        jPanel2.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        jPanel3.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        btnExportExcell.setBackground(Color.decode(theme.getBackgroundMainPanel()));
        jPanel4.setBackground(Color.decode(theme.getBackgroundMainPanel()));
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

    public void exportExcel(List<OrderDTO> list) {
        JFileChooser excelFileChooser = new JFileChooser("C:\\Users\\"); //  duong dan
        excelFileChooser.setDialogTitle("Save As");
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("EXCEL FILES", "xls", "xlsx", "xlsm");
        excelFileChooser.setFileFilter(fnef);
        int excelChooser = excelFileChooser.showSaveDialog(null);

        if (excelChooser == JFileChooser.APPROVE_OPTION) {
            try {
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet spreadsheet = workbook.createSheet("ĐƠN ĐÃ BÁN");

                XSSFRow row = null;
                Cell cell = null;

                row = spreadsheet.createRow((short) 2);
                row.setHeight((short) 500);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("ĐƠN ĐÃ BÁN");

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

    public void showdetailDialog(String codeOrder) {
        detailOrderComplete.setVisible(true);
        System.out.println("list: " + OR.getListOrderDisplay(codeOrder).size());
        showDetailOrderTable(OR.getListOrderDisplay(codeOrder));
        showDisplayOrderInfo(OR.getDisplayOrderInfoDTO(codeOrder));
    }

    public void showDetailOrderTable(List<OrderDisplay> list) {
        displayOrderTableModel.setRowCount(0);
        int no = 1;
        for (OrderDisplay lts : list) {
            displayOrderTableModel.addRow(new Object[]{
                no++, lts.getNameSp(), GM.doubleToDecimal(lts.getDonGiaXuat()), lts.getSl(), GM.doubleToDecimal(lts.getSl() * lts.getDonGiaXuat())
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
      //  stk.setText(displayOrderInfoDTO.getStk());
        tongTien.setText(String.valueOf(displayOrderInfoDTO.getTongTien()));
        giamGia.setText(String.valueOf(displayOrderInfoDTO.getGiamGia()));

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

        detailOrderComplete = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameKh = new javax.swing.JLabel();
        mst = new javax.swing.JLabel();
        stk = new javax.swing.JLabel();
        nganHang = new javax.swing.JLabel();
        sdt = new javax.swing.JLabel();
        diaChi = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayOrderTable = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        giamGia = new javax.swing.JLabel();
        tongTien = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        orderCodeDisplay = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        createdAt = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        completeTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        btnExportExcell = new javax.swing.JPanel();
        iconExcel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        searchText = new javax.swing.JTextField();
        fromDateText = new javax.swing.JFormattedTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        toDateText = new javax.swing.JFormattedTextField();
        btnSearch = new javax.swing.JButton();
        resert = new javax.swing.JButton();

        detailOrderComplete.setMinimumSize(new java.awt.Dimension(900, 600));
        detailOrderComplete.setSize(new java.awt.Dimension(900, 600));

        jPanel2.setMinimumSize(new java.awt.Dimension(900, 600));
        jPanel2.setPreferredSize(new java.awt.Dimension(900, 600));
        jPanel2.setRequestFocusEnabled(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Tên Khách hàng :");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Mã số thuế :");

        jLabel6.setText("Số tài Khoản:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Ngân Hàng :");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Địa chỉ :");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Điện thoại :");

        nameKh.setPreferredSize(new java.awt.Dimension(200, 30));

        mst.setPreferredSize(new java.awt.Dimension(200, 25));

        nganHang.setPreferredSize(new java.awt.Dimension(200, 25));

        diaChi.setPreferredSize(new java.awt.Dimension(200, 30));

        displayOrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(displayOrderTable);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Tổng tiền:");

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Giảm giá:");

        giamGia.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        tongTien.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jButton2.setText("In hóa đơn");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CHI TIẾT ĐƠN HÀNG");

        jLabel8.setText("Mã hóa đơn :");

        jLabel9.setText("Ngày tạo :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(320, 320, 320))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(244, 244, 244)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(giamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(mst, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nganHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(nameKh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(stk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createdAt, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(150, 150, 150)
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(orderCodeDisplay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderCodeDisplay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createdAt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(nameKh, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(mst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(stk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nganHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(giamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout detailOrderCompleteLayout = new javax.swing.GroupLayout(detailOrderComplete.getContentPane());
        detailOrderComplete.getContentPane().setLayout(detailOrderCompleteLayout);
        detailOrderCompleteLayout.setHorizontalGroup(
            detailOrderCompleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailOrderCompleteLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        detailOrderCompleteLayout.setVerticalGroup(
            detailOrderCompleteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(detailOrderCompleteLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        completeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã hóa đơn", "Tên khách hàng", "Ngày nhận đơn", "Ngày trả khách", "Tổng Tiền", "Người bán"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        completeTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                completeTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(completeTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel4.setBackground(new java.awt.Color(110, 110, 110));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Action", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));

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
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(btnExportExcellLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(iconExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExportExcell, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnExportExcell, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(110, 110, 110));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lọc", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(102, 102, 102));
        jPanel3.setToolTipText("");

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
        resert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fromDateText, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(resert, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                            .addComponent(toDateText)))
                    .addComponent(searchText))
                .addGap(6, 6, 6))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(toDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromDateText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void completeTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_completeTableMouseClicked
        // TODO add your handling code here:
        if (SwingUtilities.isRightMouseButton(evt)) {
            int i = completeTable.getSelectedRow();
            codeOrder = "";
            codeOrder = orderModel.getValueAt(i, 1).toString();
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_completeTableMouseClicked

    private void searchTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchTextKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            showOrderComplete();

        }
    }//GEN-LAST:event_searchTextKeyPressed

    private void toDateTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_toDateTextKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            showOrderComplete();

        }
    }//GEN-LAST:event_toDateTextKeyPressed

    private void btnSearchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMouseClicked

    }//GEN-LAST:event_btnSearchMouseClicked

    public void showOrderComplete() {
        String txtSearch = searchText.getText().trim();
        String fromDate = fromDateText.getText().trim();
        String toDate = toDateText.getText().trim();

        if (txtSearch.length() > 0 || (GM.checkDate(fromDate, "dd/MM/yyyy") && GM.checkDate(toDate, "dd/MM/yyyy"))) {
            listOrderDTO.removeAll(listOrderDTO);
            listOrderDTO = OR.getListOrder("COMPLETE", txtSearch, fromDate, toDate);
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

    private void btnSearchMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSearchMousePressed
        // button tìm kiếm
        showOrderComplete();
    }//GEN-LAST:event_btnSearchMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnExportExcell;
    private javax.swing.JButton btnSearch;
    private javax.swing.JTable completeTable;
    private javax.swing.JLabel createdAt;
    private javax.swing.JDialog detailOrderComplete;
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
    private javax.swing.JPanel jPanel4;
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
