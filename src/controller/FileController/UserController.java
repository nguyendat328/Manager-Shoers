/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.FileController;

import java.util.List;
import model.File.Account;
import service.FileSevice.UserService;

/**
 *
 * @author choco
 */
public class UserController {

    UserService UN = new UserService();

    public String getUserName(String name) {
        return UN.getUserName(name);
    }

    public Boolean insertUser(String code, String userName, String password, String phone) {
        return UN.insertUser(code, userName, password, phone);
    }

    public List<Account> getAll() {
        return UN.getAll();
    }

    public List<String> getAllName() {
        return UN.getAllName();
    }

    public String getPassword(String code) {
        return UN.getPassword(code);
    }

    public Boolean updatePassWord(String code, String password) {
        return UN.updatePassWord(code, password);
    }

    public Boolean deleteUser(String code) {
        return UN.deleteUser(code);
    }
}
