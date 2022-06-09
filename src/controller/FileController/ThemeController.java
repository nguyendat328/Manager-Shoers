/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.FileController;

import java.util.List;
import model.File.Theme;
import service.FileSevice.ThemeService;

/**
 *
 * @author khai
 */
public class ThemeController {

    ThemeService themeS = new ThemeService();

    public List<Theme> getTheme(String nameTheme) {
        return themeS.getTheme(nameTheme);
    }

    public List<String> getNameTheme() {
        return themeS.getNameTheme();
    }

    public List<Theme> getAllTheme() {
        return themeS.getAllTheme();
    }

}
