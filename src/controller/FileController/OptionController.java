/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.FileController;

import java.time.LocalDate;
import java.util.List;
import model.File.Discount;
import model.File.Option;
import service.FileSevice.OptionService;

/**
 *
 * @author choco
 */
public class OptionController {

    OptionService OS = new OptionService();

    public List<Option> getOption() {
        return OS.getOption();
    }

    public Boolean updateOption(String nameTheme, int sellingMethod) {
        return OS.updateOption(nameTheme, sellingMethod);
    }

    public List<Discount> getDiscout() {
        return OS.getDiscout();
    }

    public Boolean updateDiscount(int dis, LocalDate fromDate, LocalDate toDate) {
        return OS.updateDiscount(dis, fromDate, toDate);
    }
}
