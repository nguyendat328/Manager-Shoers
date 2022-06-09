/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.BaoCaoController;

import java.time.LocalDate;
import java.util.List;
import model.BaoCao.ThuChi;
import service.BaoCaoService.ThuChiService;

/**
 *
 * @author choco
 */
public class ThuChiController {

    ThuChiService TCS = new ThuChiService();

    public List<ThuChi> getThuChiByDate(LocalDate date) {
        return TCS.getThuChiByDate(date);
    }

    public List<ThuChi> getThuChiByMonth(Integer month, Integer year) {
        return TCS.getThuChiByMonth(month, year);

    }

    public List<ThuChi> getThuChiByYear(Integer year) {
        return TCS.getThuChiByYear(year);
    }

    public Double getTonCuoi() {
        return TCS.getTonCuoi();
    }
}
