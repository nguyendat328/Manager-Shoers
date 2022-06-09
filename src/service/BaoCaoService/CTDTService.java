/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.BaoCaoService;

import DTOService.DBConnectionDTO;
import DTOService.GeneralMethod;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.BaoCao.CTDT;
import model.BaoCao.ThuChi;

/**
 *
 * @author choco
 */
public class CTDTService {

    GeneralMethod GM = new GeneralMethod();

    public List<CTDT> getCTDT(LocalDate dateIn, LocalDate dateOut, String year, String month, String monthYear) {
        List<CTDT> listCTDT = new ArrayList<>();
        String sql = "SELECT * FROM chi_tiet_doanh_thu WHERE status != 'DRAFT' ";
        if (dateIn != null & dateOut != null) {
            sql += " AND ngay_xuat BETWEEN '" + dateIn + "' AND '" + dateOut + "' ";
        } else if (year != null) {
            sql += " AND YEAR(ngay_xuat) = '" + year + "' ";
        } else if (month != null && monthYear != null) {
            sql += " AND YEAR(ngay_xuat) = '" + monthYear + "' AND MONTH(ngay_xuat) = '" + month + "' ";
        }
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String cr = rs.getString("ngay_nhap").substring(0, 10);
                String created = GM.dateToStr(GM.strToDate(cr, "yyyy-MM-dd"));
                String expo = rs.getString("ngay_xuat").substring(0, 10);
                String exportDate = GM.dateToStr(GM.strToDate(expo, "yyyy-MM-dd"));
                String nameKH = rs.getString("name_kh");
                String loaiKhach = rs.getString("loai_khach");
                String tenSP = rs.getString("ten_sp");
                Integer soLuong = rs.getInt("sl");
                Double giaNhap = rs.getDouble("gia_nhap");
                Double giaXuat = rs.getDouble("gia_xuat");
                String status = rs.getString("status");
                String thanhToan = "Thanh toán ngay";
                String productCode = rs.getString("code_product");
                String capacity = rs.getString("capacity");
                if (status.equalsIgnoreCase("DEPT")) {
                    thanhToan = "Bán nợ";
                }
                String nhanVien = rs.getString("NV");
                CTDT record = new CTDT(created, exportDate, nameKH, loaiKhach, tenSP,
                        soLuong, giaNhap, giaXuat, thanhToan, nhanVien, productCode, capacity);
                listCTDT.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCTDT;
    }
}
