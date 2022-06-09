/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DTOService.DBConnectionDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.File.Account;

/**
 *
 * @author choco
 */
public class LoginService {

    public List<Account> checkLogin(String userName, String passWord) {
        List<Account> listAccount = new ArrayList<>();

        String sql = "SELECT * FROM account WHERE user_name = '" + userName + "' AND pass_word = '" + passWord + "' ";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String code = rs.getString("code_user");
                String name = rs.getString("user_name");
                String phone = rs.getString("phone");
                String permission = rs.getString("permisson");
                Account record = new Account(code, userName, phone, permission);
                listAccount.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();//
            return null;
        }
        return listAccount;
    }

}
