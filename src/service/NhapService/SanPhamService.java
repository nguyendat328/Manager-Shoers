package service.NhapService;

import DTOService.DBConnectionDTO;
import model.Nhap.SanPham;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SanPhamService {

    //--------------------Dat code------------------------------------//
    public List<String> getThuongHieu() {
        List<String> listThuongHieu = new ArrayList<>();
        String sql = "SELECT a.name_thuong_hieu FROM thuong_hieu a";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String res = rs.getString("name_thuong_hieu");
                listThuongHieu.add(res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listThuongHieu;
    }

    public String getThuongHieuCode(String ThuongHieu) {
        String ThuongHieuCode = "";
        String sql = "SELECT a.code_thuong_hieu FROM thuong_hieu a WHERE a.name_thuong_hieu='" + ThuongHieu + "';";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ThuongHieuCode = rs.getString("code_thuong_hieu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ThuongHieuCode;
    }

    public List<String> getLoaiSP() {
        List<String> listLoaiSP = new ArrayList<>();
        String sql = "SELECT a.name_loai_sp FROM loai_sp a order by a.code_loai_sp";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String res = rs.getString("name_loai_sp");
                listLoaiSP.add(res);

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listLoaiSP;
    }

    public String getLoaiSPCode(String loaiSP) {
        String LoaiSPCode = "";
        String sql = "SELECT a.code_loai_sp FROM loai_sp a WHERE a.name_loai_sp = '" + loaiSP + "';";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                LoaiSPCode = rs.getInt("code_loai_sp") + "";
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return LoaiSPCode;
    }

    public List<SanPham> getSPCode(String codeProduct) {

        List<SanPham> listSP = new ArrayList<>();
        String sql = "SELECT * FROM san_pham a WHERE a.product_code = '" + codeProduct + "'";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();

                sp.setCodeSP(rs.getString("code_sp"));
                sp.setName(rs.getString("name_sp"));
                listSP.add(sp);
            }
        } catch (Exception e) {
            System.out.println("Không có code sp");
            e.printStackTrace();

        }
        return listSP;
    }

    public Integer getTotalSPDetail(String codeSP) {
        Integer total = 0;
        String sql = "SELECT count(1) AS total FROM san_pham_detail a WHERE a.code_sp = '" + codeSP + "'";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                total = Integer.parseInt(rs.getString("total"));
            }
        } catch (Exception e) {

            e.printStackTrace();

        }
        return total;
    }

    public List<SanPham> getNameSanPham(String ThuongHieu, String loaiSP, String nameSP) {
        List<SanPham> listSp = new ArrayList<>();
        String sql = "SELECT * FROM san_pham a WHERE 1 and a.product_code LIKE '%" + nameSP + "%' ";
        if (!ThuongHieu.equalsIgnoreCase("select") && !loaiSP.equalsIgnoreCase("select")) {
            sql += " and a.code_thuong_hieu = '" + getThuongHieuCode(ThuongHieu) + "' and a.code_loai_sp = '" + getLoaiSPCode(loaiSP) + "'";
        } else if (!ThuongHieu.equalsIgnoreCase("select") && loaiSP.equalsIgnoreCase("select")) {
            sql += " and a.code_thuong_hieu = '" + getThuongHieuCode(ThuongHieu) + "' ";
        } else if (ThuongHieu.equalsIgnoreCase("select") && !loaiSP.equalsIgnoreCase("select")) {
            sql += " and a.code_loai_sp = '" + getLoaiSPCode(loaiSP) + "'";
        }
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setName(rs.getString("name_sp"));
                sp.setProductCode(rs.getString("product_code"));
                listSp.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listSp;
    }

    public Boolean insertLoaiSP(String code, String loaiSP) {
        Boolean res = false;
        String sql = "INSERT INTO loai_sp VALUES('" + code + "' , '" + loaiSP + "' )";
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

    public Boolean insertSP(String code, String codeLoaiSP, String codeProduct, String nameSP) {
        Boolean res = false;
        LocalDateTime date = LocalDateTime.now();
        String sql = "INSERT INTO san_pham VALUES('" + code + "' , '" + codeLoaiSP + "','" + codeProduct + "', '" + nameSP + "', '" + date + "', '" + date + "' )";
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

    public Boolean insertSPDetail(String code, String codeSP, int soLuong, double donGiaNhap, double donGiaXuat) {
        Boolean res = false;
        LocalDateTime date = LocalDateTime.now();
        String sql = "INSERT INTO san_pham_detail VALUES('" + code + "' , '" + codeSP + "', '" + soLuong + "', '"
                + donGiaNhap + "', '" + donGiaXuat + "', '" + date + "', '" + date + "' )";
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

    public Boolean deleteSPDetail(String codeSPDetail) {
        Boolean res = false;
        String sql = "DELETE FROM san_pham_detail WHERE code_sp_detail = '" + codeSPDetail + "' ";
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

    public Boolean deleteSP(String codeSP) {
        Boolean res = false;
        String sql = "DELETE FROM san_pham WHERE code_sp = '" + codeSP + "' ";
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

    public Boolean deleteSPDetailMuti(String listCodeSPDetail) {
        Boolean res = false;
        String sql = "DELETE FROM san_pham_detail WHERE code_sp_detail IN  (" + listCodeSPDetail + ") ";
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

    // update dp detail theo đơn giá mới nhất
    public Boolean updateSPdetailByNewPrice(String codeSP, double donGiaXuat) {
        Boolean res = false;
        LocalDateTime date = LocalDateTime.now();
        String sql = "UPDATE san_pham_detail SET  don_gia_xuat = '" + donGiaXuat + "', updated_at =  '" + date + "'  WHERE code_sp = '" + codeSP + "' ";
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
