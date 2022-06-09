/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.BaoCaoService;

import DTOService.DBConnectionDTO;
import DTOService.GeneralMethod;
import controller.NhapController.SanPhamController;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.BaoCao.Kho;

/**
 *
 * @author choco
 */
public class KhoService {

    GeneralMethod GM = new GeneralMethod();
    SanPhamController SPController = new SanPhamController();

    public List<Kho> getTonKho(String nameSP, String loaiSP, String thuongHieu, String fromDate, String toDate) {
        List<Kho> listTonKho = new ArrayList<>();
        String loaiSPCode = SPController.getLoaiSPCode(loaiSP);
        String THCode = SPController.getThuongHieuCode(thuongHieu);
        String query = "";

        String sql = "SELECT b.code_sp_detail, a.product_code,a.capacity, a.name_sp, b.so_luong,b.don_gia_nhap, b.don_gia_xuat_ban_le,b.don_gia_xuat_ban_buon,c.name_loai_sp,d.name_thuong_hieu,b.created_at,b.updated_at "
                + " FROM san_pham a , san_pham_detail b , loai_sp c, thuong_hieu d "
                + " WHERE a.code_sp = b.code_sp and a.code_loai_sp = c.code_loai_sp and a.code_thuong_hieu = d.code_thuong_hieu "
                + " and b.created_at between '" + GM.strToDate(fromDate, "dd/MM/yyyy") + "' and '" + GM.strToDate(toDate, "dd/MM/yyyy") + "' ";
        if (nameSP.trim().length() <= 0 && !loaiSP.equalsIgnoreCase("select") && thuongHieu.equalsIgnoreCase("select")) {
            // trống tên + thương hiệu
            query = sql + " and a.code_loai_sp = '" + loaiSPCode + "' ";
        } else if (nameSP.trim().length() <= 0 && loaiSP.equalsIgnoreCase("select") && !thuongHieu.equalsIgnoreCase("select")) {
            // trống tên + loại sp
            query = sql + " and a.code_thuong_hieu = '" + THCode + "' ";
        } else if (nameSP.trim().length() <= 0 && !loaiSP.equalsIgnoreCase("select") && !thuongHieu.equalsIgnoreCase("select")) {
            // trống tên
            query = sql + " and a.code_loai_sp = '" + loaiSPCode + "' and a.code_thuong_hieu = '" + THCode + "' ";

        } else if (nameSP.trim().length() > 0 && loaiSP.equalsIgnoreCase("select") && thuongHieu.equalsIgnoreCase("select")) {
            // trống loại sp + thương hiệu
            query = sql + " and a.name_sp LIKE '%" + nameSP + "%' ";

        } else if (nameSP.trim().length() > 0 && !loaiSP.equalsIgnoreCase("select") && !thuongHieu.equalsIgnoreCase("select")) {
            // không trống j cả
            query = sql + " and a.name_sp LIKE '%" + nameSP + "%' and a.code_loai_sp = '" + loaiSPCode + "' and a.code_thuong_hieu = '" + THCode + "' ";
        } else if (nameSP.trim().length() > 0 && !loaiSP.equalsIgnoreCase("select") && thuongHieu.equalsIgnoreCase("select")) {
            // Trống thuong hiệu
            query = sql + " and a.name_sp LIKE '%" + nameSP + "%' and a.code_loai_sp  = '" + loaiSPCode + "' ";
        } else if (nameSP.trim().length() > 0 && loaiSP.equalsIgnoreCase("select") && !thuongHieu.equalsIgnoreCase("select")) {
            // Trống loai
            query = sql + " and a.name_sp LIKE '%" + nameSP + "%' and a.code_thuong_hieu = '" + THCode + "' ";
        } else {
            query = sql;
        }
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String code = rs.getString("code_sp_detail");
                String name = rs.getString("name_sp");
                Integer quantities = rs.getInt("so_luong");
                Double priceIn = rs.getDouble("don_gia_nhap");
                Double priceOutLe = rs.getDouble("don_gia_xuat_ban_le");
                Double priceOutBuon = rs.getDouble("don_gia_xuat_ban_buon");
                String codeProduct = rs.getString("product_code");
                String type = rs.getString("name_loai_sp");
                String branch = rs.getString("name_thuong_hieu");
                String create = rs.getString("created_at");
                String update = rs.getString("updated_at");
                String cap = rs.getString("capacity");
                LocalDate ngayNhap = GM.strToDate(create.substring(0, 10), "yyyy-MM-dd");
                LocalDate ngaySua = GM.strToDate(update.substring(0, 10), "yyyy-MM-dd");

                Kho newRecord = new Kho(code, codeProduct, cap, name, quantities, priceIn, priceOutLe, priceOutBuon,
                        type, branch, ngayNhap, ngaySua);

                listTonKho.add(newRecord);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listTonKho;
    }

    public Boolean updateSPdetail(String codeSPDetail, int soLuong, double donGiaNhap, double donGiaXuatle, double donGiaXuatBuon) {
        Boolean res = false;
        LocalDate date = LocalDate.now();
        String sql = "UPDATE san_pham_detail SET so_luong =  '" + soLuong + "', don_gia_nhap = '" + donGiaNhap + "', don_gia_xuat_ban_le = '" + donGiaXuatle + "', don_gia_xuat_ban_buon = '" + donGiaXuatBuon + "', updated_at =  '" + date + "'  WHERE code_sp_detail = '" + codeSPDetail + "' ";
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
}
