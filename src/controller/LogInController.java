/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import model.File.Account;
import service.LoginService;

/**
 *
 * @author choco
 */
public class LogInController {

    LoginService LS = new LoginService();

    public List<Account> checkLogin(String userName, String passWord) {
        return LS.checkLogin(userName, passWord);
    }

}
