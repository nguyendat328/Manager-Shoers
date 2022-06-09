package controller.NhapController;

import java.util.ArrayList;
import service.NhapService.SanPhamService;

import javax.swing.*;
import java.util.List;
import model.Nhap.SanPham;

public class SanPhamController {
//------------dat code- 27 /07 /20----------------------//

    SanPhamService sanPhamService = new SanPhamService();

    public List<String> getThuongHieu() {
        return sanPhamService.getThuongHieu();
    }

    public List<String> getLoaiSP() {
        return sanPhamService.getLoaiSP();
    }

    public List<SanPham> getSPCode(String codeSP) {
        return sanPhamService.getSPCode(codeSP);
    }

    public List<SanPham> getNameSanPham(String ThuongHieu, String loaiSP, String nameSP) {
        return sanPhamService.getNameSanPham(ThuongHieu, loaiSP, nameSP);
    }

    public String getThuongHieuCode(String ThuongHieu) {
        return sanPhamService.getThuongHieuCode(ThuongHieu);
    }

    public String getLoaiSPCode(String loaiSP) {
        return sanPhamService.getLoaiSPCode(loaiSP);
    }

    public Boolean insertLoaiSP(String code, String loaiSP) {
        return sanPhamService.insertLoaiSP(code, loaiSP);
    }

    public Boolean insertSP(String code, String codeLoaiSP, String codeProduct, String nameSP) {
        return sanPhamService.insertSP(code, codeLoaiSP, codeProduct, nameSP);
    }

    public Boolean insertSPDetail(String code, String codeSP, int soLuong, double donGiaNhap, double donGiaXuat) {
        return sanPhamService.insertSPDetail(code, codeSP, soLuong, donGiaNhap, donGiaXuat);
    }

    public Integer getTotalSPDetail(String codeSP) {
        return sanPhamService.getTotalSPDetail(codeSP);
    }

    public Boolean deleteSPDetail(String codeSPDetail) {
        return sanPhamService.deleteSPDetail(codeSPDetail);
    }

    public Boolean deleteSP(String codeSP) {
        return sanPhamService.deleteSP(codeSP);
    }

    public Boolean deleteSPDetailMuti(String listCodeSPDetail) {
        return sanPhamService.deleteSPDetailMuti(listCodeSPDetail);

    }

    public Boolean updateSPdetailByNewPrice(String codeSP, double donGiaXuatle) {
        return sanPhamService.updateSPdetailByNewPrice(codeSP, donGiaXuatle);
    }
}
