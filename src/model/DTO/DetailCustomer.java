package model.DTO;

public class DetailCustomer {
    String nameKH;
    String phone;
    Double giamGia;
    Double tongTien;
    Double coc;

    public DetailCustomer() {
    }

    public DetailCustomer(String loaiKh, String nameKH, String phone,   Double coc, Double giamGia, Double tongTien) {
        this.nameKH = nameKH;
        this.phone = phone;
        this.coc = coc;
        this.giamGia = giamGia;
        this.tongTien = tongTien;
    }

    public Double getCoc() {
        return coc;
    }

    public void setCoc(Double coc) {
        this.coc = coc;
    }

    public String getNameKH() {
        return nameKH;
    }

    public void setNameKH(String nameKH) {
        this.nameKH = nameKH;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(Double giamGia) {
        this.giamGia = giamGia;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }
}
