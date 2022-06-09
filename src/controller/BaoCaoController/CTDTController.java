/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.BaoCaoController;

import java.time.LocalDate;
import java.util.List;
import model.BaoCao.CTDT;
import service.BaoCaoService.CTDTService;

/**
 *
 * @author choco
 */
public class CTDTController {

    CTDTService CS = new CTDTService();

    public List<CTDT> getCTDT(LocalDate dateIn, LocalDate dateOut, String year, String month, String monthYear) {
        return CS.getCTDT(dateIn, dateOut, year, month, monthYear);
    }
}
