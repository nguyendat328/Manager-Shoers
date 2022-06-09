package controller.NhapController;

import java.util.ArrayList;

import javax.swing.*;
import java.util.List;
import model.BaoCao.KhachHangBC;
import model.Nhap.KhachHang;
import service.NhapService.KhachHangService;

public class KhachHangController {
//------------dat code- 27 /07 /20----------------------//

    KhachHangService KH = new KhachHangService();

    public String getKhCode(String nameKH) {
        return KH.getKhCode(nameKH);
    }

    public Boolean insertKH(String codeKH, String nameKH, String face, String diaChi, String phone, String note) {
        return KH.insertKH(codeKH, nameKH, face, diaChi, phone, note);
    }

    //Viet
    public List<String> getNameKh(String nameKH) {
        return KH.getNameKh(nameKH);
    }

    public List<KhachHang> getKhInfor(String nameKH) {
        return KH.getKhInfor(nameKH);
    }

    //khai
    public List<KhachHangBC> getKhachHang() {
        return KH.getKhachHang();
    }

    public boolean deleteKhachHang(String code_kh) {
        return KH.deleteKhachHang(code_kh);
    }
//dat


    public Boolean updatePhaiThu(String nameKH, Double phaiThuMoi) {
        return KH.updatePhaiThu(nameKH, phaiThuMoi);
    }

    public Boolean insertCongNoKHTang(String nameKH, Double noTang) {
        return KH.insertCongNoKHTang(nameKH, noTang);

    }

    public Boolean insertCongNoKHGiam(String nameKH, Double noGiam) {
        return KH.insertCongNoKHGiam(nameKH, noGiam);
    }
}
