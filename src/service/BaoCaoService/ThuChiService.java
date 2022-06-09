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
import model.BaoCao.ThuChi;

/**
 *
 * @author choco
 */
public class ThuChiService {

    GeneralMethod GM = new GeneralMethod();

    //get thu chi by date
    public List<ThuChi> getThuChiByDate(LocalDate date) {
        List<ThuChi> listThuChi = new ArrayList<>();
        String sql = "SELECT * FROM thu_chi a WHERE a.created_at = '" + date + "'  order by a.code_thu_chi";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String cr = rs.getString("created_at").substring(0, 10);
                String created = GM.dateToStr(GM.strToDate(cr, "yyyy-MM-dd"));
                Double thu = rs.getDouble("thu");
                Double chi = rs.getDouble("chi");
                Double tonQuy = rs.getDouble("ton_cuoi");
                String des = rs.getString("description");
                ThuChi record = new ThuChi(created, thu, chi, tonQuy, des);
                listThuChi.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listThuChi;
    }

    public List<ThuChi> getThuChiByMonth(Integer month, Integer year) {

        List<ThuChi> listThuChi = new ArrayList<>();
        String sql = "select sum(a.thu) thu, sum(a.chi) chi, sum(a.ton_cuoi) ton_cuoi, dayofmonth(a.created_at) created_at "
                + "FROM thu_chi a  WHERE MONTH(a.created_at)= '" + month + "' AND YEAR(a.created_at)= '" + year + "' group by dayofmonth(a.created_at) order by dayofmonth(a.created_at) ;";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                String created = rs.getString("created_at");
                Double thu = rs.getDouble("thu");
                Double chi = rs.getDouble("chi");
                Double tonQuy = rs.getDouble("ton_cuoi");
                String des = "Thu chi ngày " + created + "/" + month + "/" + year;
                ThuChi record = new ThuChi(created, thu, chi, tonQuy, des);
                listThuChi.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listThuChi;
    }

    public List<ThuChi> getThuChiByYear(Integer year) {

        List<ThuChi> listThuChi = new ArrayList<>();
        String sql = "SELECT sum(a.thu) thu, sum(a.chi) chi, sum(a.ton_cuoi) ton_cuoi, month(a.created_at) created_at "
                + "FROM thu_chi a  WHERE YEAR(a.created_at)= '" + year + "' group by MONTH(a.created_at) order by MONTH(a.created_at) ;";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String created = rs.getString("created_at");
                Double thu = rs.getDouble("thu");
                Double chi = rs.getDouble("chi");
                Double tonQuy = rs.getDouble("ton_cuoi");
                String des = "Thu chi tháng " + created + "/" + year;
                ThuChi record = new ThuChi(created, thu, chi, tonQuy, des);
                listThuChi.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listThuChi;
    }

    public Double getTonCuoi() {

        Double tonCuoi = 0.0;
        String sql = "select ton_cuoi from thu_chi where code_thu_chi = (select max(code_thu_chi) from thu_chi);";
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
}
