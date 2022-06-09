package service.NhapService;

import DTOService.DBConnectionDTO;
import DTOService.GeneralMethod;
import model.Nhap.SanPham;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Nhap.KhachHang;
import model.BaoCao.KhachHangBC;

public class KhachHangService {
//khai code//

    GeneralMethod GM = new GeneralMethod();

    public List<KhachHangBC> getKhachHang() {

        List<KhachHangBC> listKhachHang = new ArrayList<>();
        String sql = "SELECT * FROM khach_hang";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                KhachHangBC khachhang = new KhachHangBC(rs.getString("code_kh"), rs.getString("name_kh"),
                        rs.getString("mst"), rs.getString("stk"), rs.getString("ngan_hang"),
                        rs.getString("dia_chi"), rs.getString("phone"), rs.getString("loai_khach"));
                listKhachHang.add(khachhang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKhachHang;
    }

    public boolean deleteKhachHang(String code_kh) {
        Boolean res = false;
        String sql = "DELETE FROM khach_hang WHERE code_kh = '" + code_kh + "' ";
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

    //--------------------Dat code------------------------------------//
    public String getKhCode(String nameKH) {
        String khCode = "";
        String sql = "SELECT a.code_kh FROM khach_hang a WHERE a.name_kh = '" + nameKH + "'";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                khCode = rs.getString("code_kh");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khCode;
    }

    public Boolean insertKH(String codeKH, String nameKH, String face, String diaChi, String phone, String note) {
        Boolean res = false;
        String sql = "INSERT INTO khach_hang VALUES('" + codeKH + "' , '" + nameKH + "', '" + diaChi + "', '" + phone + "', '" + face + "', '" + note + "' )";
        Connection conn = DBConnectionDTO.getConnection();
        System.out.println("insert kh:" + sql);
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            res = true;
        } catch (Exception e) {
            e.printStackTrace();

        }
        return res;
    }

    // Viet
    public List<String> getNameKh(String nameKH) {
        List<String> listNameKh = new ArrayList<>();
        String sql = "SELECT a.name_kh FROM khach_hang a WHERE a.name_kh like '%" + nameKH + "%'";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String khName = rs.getString("name_kh");
                listNameKh.add(khName);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNameKh;
    }
//dat

    public List<KhachHang> getKhInfor(String nameKH) {
        List<KhachHang> listKH = new ArrayList<KhachHang>();
        String sql = "SELECT * FROM khach_hang  ";
        if (nameKH.trim().length() > 0) {
            sql += " WHERE name_kh LIKE '%" + nameKH + "%'";
        }
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String code = rs.getString("code_kh");
                String name = rs.getString("name_kh");
                String diachi = rs.getString("dia_chi");
                String sdt = rs.getString("phone");
                String fb = rs.getString("fb");
                String note = rs.getString("note");

                KhachHang record = new KhachHang(code, name, diachi, sdt, fb, note);

                listKH.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listKH;
    }

//    public Double getPhaiThuKH(String nameKH) {
//        Double phaiThu = 0.0;
//        String sql = "SELECT phai_thu FROM khach_hang a WHERE a.name_kh = '" + nameKH + "' ";
//        Connection conn = DBConnectionDTO.getConnection();
//        try {
//            Statement st = conn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            while (rs.next()) {
//
//                phaiThu = rs.getDouble("phai_thu");
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        return phaiThu;
//    }

    public Boolean updatePhaiThu(String nameKH, Double phaiThuMoi) {
        Boolean res = false;
        String sql = "UPDATE khach_hang  SET phai_thu = '" + phaiThuMoi + "' WHERE name_kh = '" + nameKH + "' ";
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

    public Boolean insertCongNoKHTang(String nameKH, Double noTang) {
        Boolean res = false;
        String codeCN = GM.randIdCreate();
        String codeKH = getKhCode(nameKH);
        LocalDate date = LocalDate.now();
        String sql = "INSERT INTO cong_no_kh VALUES('" + codeCN + "' , '" + codeKH + "', '" + noTang + "', '" + 0 + "', '" + date + "' )";
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

    public Boolean insertCongNoKHGiam(String nameKH, Double noGiam) {
        Boolean res = false;
        String codeCN = GM.randIdCreate();
        String codeKH = getKhCode(nameKH);
        LocalDate date = LocalDate.now();
        String sql = "INSERT INTO cong_no_kh VALUES('" + codeCN + "' , '" + codeKH + "', '" + 0 + "', '" + noGiam + "', '" + date + "' )";
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
