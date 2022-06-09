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
import java.util.ArrayList;
import java.util.List;
import model.File.Account;

/**
 *
 * @author choco
 */
public class UserService {

    public String getUserName(String name) {
        String userName = "";
        String sql = "SELECT a.user_name FROM account a WHERE a.user_name = '" + name + "' ";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                userName = rs.getString("user_name");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userName;
    }

    public String getPassword(String code) {
        String pass = "";
        String sql = "SELECT a.pass_word FROM account a WHERE a.code_user = '" + code + "' ";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pass = rs.getString("pass_word");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pass;
    }

    public Boolean insertUser(String code, String userName, String password, String phone) {
        Boolean res = false;
        String sql = "INSERT INTO account VALUES('" + code + "' , '" + userName + "', '" + password + "', '" + phone + "', '" + 1 + "' )";
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

    public Boolean updatePassWord(String code, String password) {
        Boolean res = false;
        String sql = "UPDATE account SET pass_word = '" + password + "' WHERE code_user = ;'" + code + "' ";
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

    public Boolean deleteUser(String code) {

        Boolean res = false;
        String sql = "DELETE FROM account WHERE code_user = '" + code + "' ";
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

    public List<Account> getAll() {
        List<Account> listAccount = new ArrayList<Account>();
        String sql = "SELECT * FROM account order by permisson";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String code = rs.getString("code_user");
                String userName = rs.getString("user_name");
                String phone = rs.getString("phone");
                String per = rs.getString("permisson");
                Account record = new Account(code, userName, phone, per);
                listAccount.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listAccount;
    }

    public List<String> getAllName() {
        List<String> listName = new ArrayList<>();
        String sql = "SELECT user_name FROM account order by permisson";
        Connection conn = DBConnectionDTO.getConnection();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String userName = rs.getString("user_name");
                listName.add(userName);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return listName;
    }

}
