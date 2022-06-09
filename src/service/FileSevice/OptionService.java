/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.FileSevice;

import DTOService.DBConnectionDTO;
import DTOService.GeneralMethod;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.File.Discount;
import model.File.Option;
import model.File.Theme;

/**
 *
 * @author choco
 */
public class OptionService {

    GeneralMethod GM = new GeneralMethod();

    public List<Option> getOption() {

        List<Option> listOption = new ArrayList<>();
        String sql = "SELECT * FROM Setting where code = 1 ";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String nameTheme = rs.getString("theme_name");
                int sellingMethod = rs.getInt("selling_method");

                Option record = new Option(nameTheme, sellingMethod);

                listOption.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOption;
    }

    public Boolean updateOption(String nameTheme, int sellingMethod) {
        Boolean res = false;
        String sql = "UPDATE Setting  SET theme_name = '" + nameTheme + "' , selling_method = '" + sellingMethod + "'  WHERE code = 1 ";
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

    public List<Discount> getDiscout() {

        List<Discount> discount = new ArrayList<>();
        String sql = "SELECT * FROM discount where code = 1 ";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int dis = rs.getInt("discount");
                String fDate = rs.getString("fromDate").substring(0, 10);
                LocalDate fromDate = GM.strToDate(fDate, "yyyy-MM-dd");
                String tDate = rs.getString("toDate").substring(0, 10);
                LocalDate toDate = GM.strToDate(tDate, "yyyy-MM-dd");
                Discount record = new Discount(dis, fromDate, toDate);

                discount.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discount;
    }

    public Boolean updateDiscount(int dis, LocalDate fromDate, LocalDate toDate) {
        Boolean res = false;
        String sql = "UPDATE discount SET discount = '" + dis + "' , fromDate = '" + fromDate + "' "
                + ", toDate = '" + toDate + "' WHERE code = 1 ";
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
