/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.BaoCaoController;

import java.util.List;
import model.BaoCao.Kho;
import service.BaoCaoService.KhoService;

/**
 *
 * @author choco
 */
public class KhoController {

    KhoService khoService = new KhoService();

    public List<Kho> getTonKho(String nameSP, String loaiSP, String thuongHieu, String fromDate, String toDate) {
        return khoService.getTonKho(nameSP, loaiSP, thuongHieu, fromDate, toDate);
    }

    public Boolean updateSPdetail(String codeSPDetail, int soLuong, double donGiaNhap, double donGiaXuatle, double donGiaXuatBuon) {
        return khoService.updateSPdetail(codeSPDetail, soLuong, donGiaNhap, donGiaXuatle, donGiaXuatBuon);
    }

}
