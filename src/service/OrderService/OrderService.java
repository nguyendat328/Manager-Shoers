/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.OrderService;

import DTOService.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.DTO.*;

import javax.swing.*;

import DTOService.DBConnectionDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.BanHang.OrderDetail;
import model.BanHang.SPDetail;
import service.NhapService.SanPhamService;

import DTOService.DBConnectionDTO;
import DTOService.GeneralMethod;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.TextAlignment;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import model.BanHang.OrderDetail;
import model.BanHang.SPDetail;
import service.NhapService.SanPhamService;

/**
 *
 * @author Jarvis
 */
public class OrderService {

    // Việt
    GeneralMethod GM = new GeneralMethod();

    public List<OrderDTO> getListOrder(String status, String txtSearch, String fromDate, String toDate) {
        List<OrderDTO> listOrder = new ArrayList<>();
        String sql = "";
         if (status.equalsIgnoreCase(Constanst.ORDER_DRAFT)) {
            sql = " SELECT * FROM DANH_SACH_NHAP ";
        } else if (status.equalsIgnoreCase(Constanst.ORDER_DONE)) {
            sql = " SELECT * FROM DANH_SACH_TT_NGAY ";
            if (txtSearch.length() > 0 && fromDate.length() == 10 && toDate.length() == 10) {
                sql += " WHERE name_kh = '" + txtSearch + "' AND created_at between '" + GM.strToDate(fromDate, "dd/MM/yyyy") + "' AND '" + GM.strToDate(toDate, "dd/MM/yyyy") + "' ";
            } else if (txtSearch.length() > 0 && fromDate.length() < 10) {
                sql += " WHERE name_kh = '" + txtSearch + "' ";
            } else if (fromDate.length() == 10 && toDate.length() == 10) {
                sql += " WHERE created_at between '" + GM.strToDate(fromDate, "dd/MM/yyyy") + "' AND '" + GM.strToDate(toDate, "dd/MM/yyyy") + "' ";
            }
        }
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                String khName = rs.getString("name_kh");
                String codeOrder = rs.getString("code_order");
                double tongTien = rs.getDouble("tong_tien");
                String cr = rs.getString("created_at").substring(0, 10);
                String createdAt = GM.dateToStr(GM.strToDate(cr, "yyyy-MM-dd"));
                String userName = rs.getString("user_name");
                OrderDTO orderDto = new OrderDTO(codeOrder, khName, tongTien, createdAt, userName);
                listOrder.add(orderDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOrder;
    }

    public List<OrderDisplay> getListOrderDisplay(String codeOrder) {
        List<OrderDisplay> listOrderDisplay = new ArrayList<>();
        String sql = "SELECT a.name_sp , c.sl, c.don_gia_xuat FROM ((san_pham_detail b JOIN san_pham a ON a.code_sp = b.code_sp) JOIN order_detail c ON c.code_sp_detail = b.code_sp_detail) WHERE c.code_order =  '" + codeOrder + "'";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                OrderDisplay orderDisplay = new OrderDisplay();
                String nameSp = rs.getString("name_sp");
                Integer sl = rs.getInt("sl");
                double donGiaXuat = rs.getDouble("don_gia_xuat");
                orderDisplay.setNameSp(nameSp);
                orderDisplay.setSl(sl);
                orderDisplay.setDonGiaXuat(donGiaXuat);
                listOrderDisplay.add(orderDisplay);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOrderDisplay;
    }

    public List<OrderDetail> getListOrderDetail(String codeOrder) {
        List<OrderDetail> listOrderDetail = new ArrayList<>();
        String sql = "SELECT a.name_sp,a.product_code, b.code_sp_detail , c.sl, c.don_gia_xuat FROM san_pham_detail b JOIN san_pham a ON a.code_sp = b.code_sp JOIN order_detail c ON c.code_sp_detail = b.code_sp_detail WHERE c.code_order =  '" + codeOrder + "'";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                OrderDetailDisplay OrderDetailDisplay = new OrderDetailDisplay();
                String nameSp = rs.getString("name_sp");
                String codeSPDetail = rs.getString("code_sp_detail");
                Integer sl = rs.getInt("sl");
                double donGiaXuat = rs.getDouble("don_gia_xuat");
                String codeProduct = rs.getString("product_code");
                OrderDetail record = new OrderDetail(codeSPDetail, codeProduct, nameSp, sl, donGiaXuat, (Double) donGiaXuat * sl);
                listOrderDetail.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOrderDetail;
    }

    public DisplayOrderInfoDTO getDisplayOrderInfoDTO(String codeOrderSer) {
        DisplayOrderInfoDTO displayOrderInfoDTO = new DisplayOrderInfoDTO();
        String sql = "SELECT a.*, b.*, c.user_name FROM  orders b   inner join khach_hang a on b.code_kh = a.code_kh "
                + " inner join account c on c.code_user = b.code_user   WHERE b.code_order = '" + codeOrderSer + "'";
        System.out.println(sql);
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String khName = rs.getString("name_kh");
                String fb = rs.getString("fb");
                String address = rs.getString("dia_chi");
                String phone = rs.getString("phone");
                String codeOrder = rs.getString("code_order");
                Double giamGia = rs.getDouble("giam_gia");
                Double coc = rs.getDouble("coc");

                Double tongTien = rs.getDouble("tong_tien");
                String cr = rs.getString("created_at").substring(0, 10);
                String createdAt = GM.dateToStr(GM.strToDate(cr, "yyyy-MM-dd"));
                String cd = rs.getString("complete_at").substring(0, 10);
                String completeAt = GM.dateToStr(GM.strToDate(cd, "yyyy-MM-dd"));

                displayOrderInfoDTO.setNameKH(khName);
                displayOrderInfoDTO.setFb(fb);
                displayOrderInfoDTO.setAddress(address);
                displayOrderInfoDTO.setPhone(phone);
                displayOrderInfoDTO.setCodeOrder(codeOrder);
                displayOrderInfoDTO.setGiamGia(giamGia);
                displayOrderInfoDTO.setTongTien(tongTien);
                displayOrderInfoDTO.setCreatedAt(createdAt);
                displayOrderInfoDTO.setCoc(coc);
                displayOrderInfoDTO.setCompleteAt(completeAt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return displayOrderInfoDTO;
    }

    public DetailCustomer getDetailCustomer(String codeOrderSer) {
        DetailCustomer detailCustomer = new DetailCustomer();
        String sql = "SELECT a.* , b.* FROM khach_hang a join orders b ON a.code_kh = b.code_kh WHERE b.code_order = '" + codeOrderSer + "'";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String khName = rs.getString("name_kh");
                String phone = rs.getString("phone");
                Double coc = rs.getDouble("coc");
                Double giamGia = rs.getDouble("giam_gia");
                Double tongTien = rs.getDouble("tong_tien");
                detailCustomer.setNameKH(khName);
                detailCustomer.setPhone(phone);
                detailCustomer.setGiamGia(giamGia);
                detailCustomer.setTongTien(tongTien);
                detailCustomer.setCoc(coc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detailCustomer;
    }

    public boolean deleteOdrer(String codeOrder) {
        Boolean res = false;
        String sqlDelOrder = "DELETE FROM orders WHERE code_order = '" + codeOrder + "' ";
        String sqlDeleteDetail = "DELETE FROM order_detail WHERE code_order = '" + codeOrder + "' ";
        System.out.println("code order la : " + codeOrder);
        Connection conn = DBConnectionDTO.getConnection();
        try {
            conn.setAutoCommit(false);
            Statement st = conn.createStatement();

            st.executeUpdate(sqlDeleteDetail);
            st.executeUpdate(sqlDelOrder);
            conn.commit();
            res = true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                conn.rollback();
            } catch (Exception ev) {
                System.out.println("Xóa đơn nháp thất bại");
            }
        }
        return res;
    }

    public Integer getTotalSP(String codeSP) {

        Integer total = 0;
        String sql = "select sum(so_luong) as total from san_pham_detail where code_sp = '" + codeSP + "' ";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                total = rs.getInt("total");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public List<SPDetail> getListSPDetail(int typeSale, String codeSP) {
        List<SPDetail> listResult = new ArrayList<>();
        String sql = "";
        if (typeSale == 0) {
            sql = "select * from san_pham_detail where code_sp = '" + codeSP + "' AND so_luong > 0 order by updated_at asc;";
        } else if (typeSale == 1) {
            sql = "select * from san_pham_detail where code_sp = '" + codeSP + "' AND so_luong > 0 order by updated_at desc;";
        }
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String codeSPDetail = rs.getString("code_sp_detail");
                Integer SL = rs.getInt("so_luong");
                Double priceIn = rs.getDouble("don_gia_nhap");
                Double priceOut = rs.getDouble("don_gia_xuat");
                SPDetail record = new SPDetail(codeSPDetail, SL, priceIn, priceOut);
                listResult.add(record);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listResult;

    }

    public List<SPDetail> getListSPDetailOut(int typeSale, String codeSP, int soLuong) {
        List<SPDetail> listSPDetail = new ArrayList<>();
        List<SPDetail> listSPDetailOut = new ArrayList<>();
        listSPDetail = getListSPDetail(typeSale, codeSP);

        for (int i = 0; i < listSPDetail.size(); i++) {
            if (soLuong > listSPDetail.get(i).getQuantity()) {
                SPDetail record = new SPDetail(listSPDetail.get(i).getCodeSPDetail(), listSPDetail.get(i).getQuantity(),
                        listSPDetail.get(i).getPriceIn(), listSPDetail.get(i).getPriceOut());
                listSPDetailOut.add(record);
                soLuong -= listSPDetail.get(i).getQuantity();
                listSPDetail.get(i).setQuantity(0);
            } else {
                listSPDetail.get(i).setQuantity(listSPDetail.get(i).getQuantity() - soLuong);
                SPDetail record = new SPDetail(listSPDetail.get(i).getCodeSPDetail(), listSPDetail.get(i).getQuantity(),
                        listSPDetail.get(i).getPriceIn(), listSPDetail.get(i).getPriceOut());
                listSPDetailOut.add(record);
                listSPDetailOut.get(i).setQuantity(soLuong);
                break;
            }
        }
        return listSPDetailOut;
    }

    public Boolean insertOrder(String codeOrder, String codeUser, String codeKH,
            Double coc, Double giamGia, Double tongTien, String status) {
        Boolean res = false;
        LocalDate date = LocalDate.now();
        String sql = "INSERT INTO orders VALUES('" + codeOrder + "' , '" + codeUser + "', '" + codeKH + "'," +
                " '" + coc + "','" + giamGia + "', '" + tongTien + "', '" + status + "', '" + date + "' , null,'" + date + "' , null )";
        System.out.println(sql);
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();//

        }
        return res;
    }

    public Boolean insertOrderDetail(String codeOrderDetail, String codeOrder,
            String codeSPDetail, Integer soLuong, Double donGiaXuat) {
        Boolean res = false;

        String sql = "INSERT INTO order_detail VALUES('" + codeOrderDetail + "' , '" + codeOrder + "', '" + codeSPDetail + "', '" + soLuong + "', '" + donGiaXuat + "' )";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Integer getSlSpDetail(String codeSPDetail) {

        Integer soLuong = 0;
        String sql = "select so_luong from san_pham_detail where code_sp_detail = '" + codeSPDetail + "' ";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                soLuong = rs.getInt("so_luong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }

    public Double getPhaiThuKH(String codeKH) {
        Double phaiThu = 0.0;
        String sql = "select phai_thu from khach_hang WHERE  code_kh = '" + codeKH + "' ";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                phaiThu = rs.getDouble("phai_thu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return phaiThu;
    }

    public Boolean updateSpDetail(String codeSPDetail, int slDaBan) {
        Boolean res = false;
        Integer soTonKho = getSlSpDetail(codeSPDetail);
        Integer soTonKhoNew = soTonKho - slDaBan;

        String sql = "UPDATE san_pham_detail SET so_luong = '" + soTonKhoNew + "' WHERE code_sp_detail = '" + codeSPDetail + "' ";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Boolean updateNoKH(String codeKH, Double noTang) {
        Boolean res = false;
        Double phaiThuCu = getPhaiThuKH(codeKH);
        Double phaiThuMoi = phaiThuCu + noTang;
        String sql = "UPDATE khach_hang SET phai_thu = '" + phaiThuMoi + "' WHERE code_kh = '" + codeKH + "' ";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            res = true;
            System.out.println("tăng nợ thành công cho :" + codeKH + " vói so tien: " + phaiThuMoi);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Boolean insertThu(String codeUser, Double soTien, String description) {
        Boolean res = false;
        int codeThuChi = getMaxCodeThuChi() + 1;
        Double tonCuoi = getLastTonCuoi() + soTien;
        LocalDate date = LocalDate.now();
        String sql = "INSERT INTO thu_chi values('" + codeThuChi + "' , '" + codeUser + "', '" + soTien + "', '" + 0 + "', '" + tonCuoi + "', '" + description + "', '" + date + "' )";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public Boolean insertChi(String codeUser, Double soTien, String description) {
        Boolean res = false;
        int codeThuChi = getMaxCodeThuChi() + 1;
        Double tonCuoi = getLastTonCuoi() - soTien;
        LocalDate date = LocalDate.now();
        String sql = "INSERT INTO thu_chi values('" + codeThuChi + "' , '" + codeUser + "', '" + 0 + "', '" + soTien + "', '" + tonCuoi + "', '" + description + "', '" + date + "' )";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public int getMaxCodeThuChi() {
        int code = 0;
        String sql = "SELECT MAX(code_thu_chi) AS code FROM thu_chi";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                code = rs.getInt("code");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return code;
    }

    public Double getLastTonCuoi() {
        Double tonCuoi = 0.0;

        String sql = "select ton_cuoi from thu_chi where code_thu_chi = (SELECT MAX(code_thu_chi) FROM thu_chi);";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                tonCuoi = rs.getDouble("ton_cuoi");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tonCuoi;
    }

    public boolean transactionBanHang(
            //order
            String codeOrderDraft, String codeOrder, String codeUser, String codeKH,
            Double coc, Double giamGia, Double tongTien, String status,
            //orderDetail
            List<OrderDetail> listOrderDetail,
            //tang thu chi
            String description, String loaiSP, String userName
    ) {
        Connection conn = DBConnectionDTO.getConnection();

        LocalDate date = LocalDate.now();

        try {
            Statement st = conn.createStatement();
            conn.setAutoCommit(false);
            //order
            if (codeOrderDraft.length() > 0) {
                String sqlDeleteOrderDraftDetail = "delete from order_detail where code_order = '" + codeOrderDraft + "'";
                String sqlDeleteOrderDraft = "delete from orders where code_order = '" + codeOrderDraft + "'";
                st.executeUpdate(sqlDeleteOrderDraftDetail);
                st.executeUpdate(sqlDeleteOrderDraft);
            }
            String sqlOrder = "INSERT INTO orders VALUES('" + codeOrder + "' , '" + codeUser + "', '" + codeKH + "', '"
                    + coc + "','" + giamGia + "', '" + tongTien + "', '" + status + "', '" + date + "' ,null,'"  + date + "', '" + userName +"' )";
            System.out.println("vao sqp orders: " + codeOrder);
            st.executeUpdate(sqlOrder);
            //orderdetail
            int a = 1;
            for (OrderDetail lts : listOrderDetail) {
                String codeOrderDetail = GM.randIdCreate();
                String sqlOrderDetail = "INSERT INTO order_detail VALUES('" + codeOrderDetail + "' , '" + codeOrder + "', '"
                        + lts.getCodeSPDetail() + "', '" + lts.getQuantity() + "', '" + lts.getPrice() + "' )";
                st.executeUpdate(sqlOrderDetail);
                System.out.println("vao sqp orders: " + codeOrderDetail);
                //cap nhat sp detal
                Integer soTonKho = getSlSpDetail(lts.getCodeSPDetail());
                Integer soTonKhoNew = soTonKho - lts.getQuantity();
// neu loại sp là sp thì cập nhật lại so luong hang trong kho
                if (loaiSP.equalsIgnoreCase("Sản phẩm")) {
                    String sqlUpSpDetail = "UPDATE san_pham_detail SET so_luong = '" + soTonKhoNew + "' WHERE code_sp_detail = '" + lts.getCodeSPDetail() + "' ";
                    st.executeUpdate(sqlUpSpDetail);
                    System.out.println("vao sql update sl sp detail : " + soTonKhoNew);
                }

            }

            //insert thu chi
            int codeThuChi = getMaxCodeThuChi() + 1;
            Double tonCuoi = getLastTonCuoi() + tongTien;
            String sqlupdateThuChi = "INSERT INTO thu_chi values('" + codeThuChi + "' , '" + codeUser + "', '" + tongTien + "', '" + 0 + "', '"
                    + tonCuoi + "', '" + description + "', '" + date + "' )";
            st.executeUpdate(sqlupdateThuChi);
            System.out.println("vao sql update thu chi: " + codeThuChi);

            conn.commit();

            return true;

        } catch (Exception e) {
            try {
                conn.rollback();

            } catch (Exception ev) {
                ev.printStackTrace();
            }
            e.printStackTrace();

        }

        return false;
    }

//    public boolean transactionBanNo(
//            //order
//            String codeOrderDraft, String codeOrder, String codeUser, String codeKH,
//            Double noCu, Double giamGia, Double tongTien, String status,
//            //orderDetail
//            List<OrderDetail> listOrderDetail
//    //tang cong no
//    ) {
//        Connection conn = DBConnectionDTO.getConnection();
//        LocalDate date = LocalDate.now();
//        try {
//            Statement st = conn.createStatement();
//            conn.setAutoCommit(false);
//            //order
//            if (codeOrderDraft.length() > 0) {
//                String sqlDeleteOrderDraftDetail = "delete from order_detail where code_order = '" + codeOrderDraft + "'";
//                String sqlDeleteOrderDraft = "delete from orders where code_order = '" + codeOrderDraft + "'";
//                st.executeUpdate(sqlDeleteOrderDraftDetail);
//                st.executeUpdate(sqlDeleteOrderDraft);
//            }
//            String sqlOrder = "INSERT INTO orders VALUES('" + codeOrder + "' , '" + codeUser + "', '" + codeKH + "', '"
//                    + noCu + "','" + giamGia + "', '" + tongTien + "', '" + status + "', '" + date + "' ,'" + date + "' )";
//            System.out.println("vao sqp orders: " + codeOrder);
//            st.executeUpdate(sqlOrder);
//            //orderdetail
//            int a = 1;
//            for (OrderDetail lts : listOrderDetail) {
//                String codeOrderDetail = GM.randIdCreate();
//                String sqlOrderDetail = "INSERT INTO order_detail VALUES('" + codeOrderDetail + "' , '" + codeOrder + "', '"
//                        + lts.getCodeSPDetail() + "', '" + lts.getQuantity() + "', '" + lts.getPrice() + "' )";
//                st.executeUpdate(sqlOrderDetail);
//                System.out.println("vao sqp orders: " + codeOrderDetail);
//                //cap nhat sp detal
//                Integer soTonKho = getSlSpDetail(lts.getCodeSPDetail());
//                Integer soTonKhoNew = soTonKho - lts.getQuantity();
//                String sqlUpSpDetail = "UPDATE san_pham_detail SET so_luong = '" + soTonKhoNew + "' WHERE code_sp_detail = '" + lts.getCodeSPDetail() + "' ";
//                st.executeUpdate(sqlUpSpDetail);
//                System.out.println("vao sql update sl sp detail : " + soTonKhoNew);
//
//            }
//
////insert tang cong no
//            Double phaiThuCu = getPhaiThuKH(codeKH);
//            Double phaiThuMoi = phaiThuCu + tongTien;
//            String sqlUpCongNo = "UPDATE khach_hang SET phai_thu = '" + phaiThuMoi + "' WHERE code_kh = '" + codeKH + "' ";
////
//            st.executeUpdate(sqlUpCongNo);
//            // insert bang cong no khach hang
//            String codeCN = GM.randIdCreate();
//
//            String sqlInsertCongNo = "INSERT INTO cong_no_kh VALUES('" + codeCN + "' , '" + codeKH + "', '" + tongTien + "', '" + 0 + "', '" + date + "' )";
//            st.executeUpdate(sqlInsertCongNo);
//            conn.commit();
//
//            return true;
//
//        } catch (Exception e) {
//            try {
//                conn.rollback();
//
//            } catch (Exception ev) {
//                ev.printStackTrace();
//            }
//            e.printStackTrace();
//
//        }
//
//        return false;
//    }

    // tuan xuât pdf
//
//    public boolean XuatPDF(List<OrderDetail> ls, String nameKH, String codeOrder) throws FileNotFoundException, DocumentException {
//        Boolean flag = false;
//        try {
//            LocalDate currentDate = LocalDate.now();
//            String fileName = "E:\\project2\\hoadon_" + codeOrder + ".pdf";
//            Document document = new Document();
//            PdfWriter.getInstance(document, new FileOutputStream(fileName));
//            document.open();
//
//            Paragraph para = new Paragraph();
//
//            para.add("#Order: " + codeOrder);
//            para.add("\n");
//            para.add("Date: " + currentDate);
//            para.add("\n");
//            para.add("Tên khách hàng: " + nameKH);
//            para.add("\n");
//            addEmptyLine(para, 1);
//            document.add(para);
//
//            System.out.println("Trần Anh Tuấn");
//
//            createTable(document, ls);
//            Paragraph para2 = new Paragraph();
//            Double total = 0.0;
//            for (OrderDetail lts : ls) {
//
//                total = total + lts.getAmount();
//            }
//            para2.add("Tam tinh: " + total + " đ");
//            para2.add("\n");
//            para2.add("--------------------------");
//            para2.add("\n");
//            para2.add("Giam gia: " + "0.0" + " đ");
//            para2.add("\n");
//            para2.add("--------------------------");
//            para2.add("\n");
//            para2.add("Tong tien: " + total + " đ");
//            para2.setAlignment(Paragraph.ALIGN_RIGHT);
//            document.add(para2);
//
//            flag = true;
//            document.close();
//
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//        return flag;
//
//    }
//
//    private static void addEmptyLine(Paragraph paragraph, int number) {
//        for (int i = 0; i < number; i++) {
//            paragraph.add(new Paragraph(" "));
//        }
//    }
//
//    private static void createTable(Document document, List<OrderDetail> ls)
//            throws BadElementException, DocumentException {
//        PdfPTable table = new PdfPTable(5);
//
//        // t.setBorderColor(BaseColor.GRAY);
//        // t.setPadding(4);
//        // t.setSpacing(4);
//        // t.setBorderWidth(1);
//        table.setWidths(new int[]{1, 5, 2, 1, 4});
//        table.setWidthPercentage(100);
//        PdfPCell c1 = new PdfPCell(new Phrase("STT"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("Ten san pham"));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("Don gia "));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("So luong "));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        c1 = new PdfPCell(new Phrase("Thanh tien "));
//        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
//        table.addCell(c1);
//
//        table.setHeaderRows(1);
//        int i = 1;
//        for (OrderDetail lts : ls) {
//            System.out.println(lts.getNameSP() + "xxx");
//            table.addCell("" + i);
//            table.addCell(lts.getNameSP());
//            table.addCell("" + lts.getPrice());
//            table.addCell("" + lts.getQuantity());
//            table.addCell("" + lts.getAmount());
//            i++;
//        }
//
//        document.add(table);
//
//  }
    //      }
    public boolean XuatPDF(List<OrderDetail> ls, String nameKH, String codeOrder) throws FileNotFoundException, DocumentException {
        Boolean flag = false;
        try {
            LocalDate currentDate = LocalDate.now();
            String fileName = "E:\\project2\\hoadon_" + codeOrder + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Paragraph para = new Paragraph();
            addEmptyLine(para, 1);
            para.add("Cua hang van phong pham DD");
            para.add("\n");
            para.add("Dia chi: so 96 Nguyen Trai Thanh Xuan Ha Noi");
            para.add("\n");
            para.add("So dien thoai: 0986868686");
            addEmptyLine(para, 1);

            Paragraph para3 = new Paragraph();
            para3.add(new Paragraph("Hoa don", catFont));
            para3.setAlignment(Element.ALIGN_CENTER);
            document.add(para3);

            para.add("#Order: " + codeOrder);
            para.add("\n");
            para.add("Date: " + currentDate);
            para.add("\n");
            para.add("Ten khach hang: " + nameKH);
            para.add("\n");
            addEmptyLine(para, 1);
            document.add(para);

            System.out.println("Trần Anh Tuấn");

            createTable(document, ls);
            Paragraph para2 = new Paragraph();
            Double total = 0.0;
            for (OrderDetail lts : ls) {

                total = total + lts.getAmount();
            }
            para2.add("Tam tinh: " + total + " đ");
            para2.add("\n");
            para2.add("--------------------------");
            para2.add("\n");
            para2.add("Giam gia: " + "0.0" + " đ");
            para2.add("\n");
            para2.add("--------------------------");
            para2.add("\n");
            para2.add("Tong tien: " + total + " đ");
            para2.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(para2);

            flag = true;
            document.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return flag;

    }
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);

    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

    private static void createTable(Document document, List<OrderDetail> ls)
            throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(5);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        table.setWidths(new int[]{1, 5, 2, 1, 4});
        table.setWidthPercentage(100);
        PdfPCell c1 = new PdfPCell(new Phrase("STT"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ten san pham"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Don gia "));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("So luong "));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Thanh tien "));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);
        int i = 1;
        for (OrderDetail lts : ls) {
            System.out.println(lts.getNameSP() + "xxx");
            table.addCell("" + i);
            table.addCell(lts.getNameSP());
            table.addCell("" + lts.getPrice());
            table.addCell("" + lts.getQuantity());
            table.addCell("" + lts.getAmount());
            i++;
        }

        document.add(table);

    }

    public boolean XuatPDFDept(List<OrderDisplay> ls, DisplayOrderInfoDTO displayOrderInfoDTO)
            throws FileNotFoundException, DocumentException {
        Boolean flag = false;
        try {
            String fileName = "E:\\project2\\hoadon_ban_no" + displayOrderInfoDTO.getCodeOrder() + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            Paragraph para = new Paragraph();
            addEmptyLine(para, 1);
            para.add("Cua hang van phong pham DD");
            para.add("\n");
            para.add("Dia chi: so 96 Nguyen Trai Thanh Xuan Ha Noi");
            para.add("\n");
            para.add("So dien thoai: 0986868686");
            addEmptyLine(para, 1);

            Paragraph para3 = new Paragraph();
            para3.add(new Paragraph("Hoa don", catFont));
            para3.setAlignment(Element.ALIGN_CENTER);
            document.add(para3);

            para.add("#Order: " + displayOrderInfoDTO.getCodeOrder());
            para.add("\n");
            para.add("Date: " + displayOrderInfoDTO.getCreatedAt());
            para.add("\n");
            para.add("Ten khach hang: " + displayOrderInfoDTO.getNameKH());
            para.add("\n");
            para.add("So dien thoai: " + displayOrderInfoDTO.getPhone());
            para.add("\n");
            para.add("Dia chi: " + displayOrderInfoDTO.getAddress());
            para.add("\n");
            addEmptyLine(para, 1);
            document.add(para);

            System.out.println("Trần Anh Tuấn");

            createTableDept(document, ls);
            Paragraph para2 = new Paragraph();
            Double total = 0.0;
            for (OrderDisplay lts : ls) {

                total = total + lts.getSl() * lts.getDonGiaXuat();
            }
            para2.add("Tam tinh: " + total + " đ");
            para2.add("\n");
            para2.add("--------------------------");
            para2.add("\n");
            para2.add("Giam gia: " + "0.0" + " đ");
            para2.add("\n");
            para2.add("--------------------------");
            para2.add("\n");
            para2.add("Tong tien: " + total + " đ");
            para2.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(para2);

            flag = true;
            document.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return flag;

    }

    private static void createTableDept(Document document, List<OrderDisplay> ls)
            throws BadElementException, DocumentException {
        PdfPTable table = new PdfPTable(5);

        // t.setBorderColor(BaseColor.GRAY);
        // t.setPadding(4);
        // t.setSpacing(4);
        // t.setBorderWidth(1);
        table.setWidths(new int[]{1, 5, 2, 1, 4});
        table.setWidthPercentage(100);
        PdfPCell c1 = new PdfPCell(new Phrase("STT"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ten san pham"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Don gia "));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("So luong "));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Thanh tien "));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        table.setHeaderRows(1);
        int i = 1;
        for (OrderDisplay lts : ls) {
            System.out.println(lts.getNameSp() + "xxx");
            table.addCell("" + i);
            table.addCell(lts.getNameSp());
            table.addCell("" + lts.getDonGiaXuat());
            table.addCell("" + lts.getSl());
            table.addCell("" + lts.getSl() * lts.getDonGiaXuat());
            i++;
        }

        document.add(table);

    }

}
