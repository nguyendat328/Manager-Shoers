/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BaoCao;

/**
 *
 * @author choco
 */
public class CTDT {

    private String dateIn;
    private String dateOut;
    private String khachHang;
    private String loaiKhach;
    private String tenHang;
    private Integer soLuong;
    private Double giaNhap;
    private Double giaXuat;
    private Double doanhThu;
    private Double laiGop;
    private String thanhToan;
    private String nhanVien;
    private String codeProduct;
    private String capacity;

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public CTDT(String dateIn, String dateOut, String khachHang, String loaiKhach,
            String tenHang, Integer soLuong, Double giaNhap, Double giaXuat, String thanhToan, String nhanVien, String codeProduct, String capacity) {
        this.dateIn = dateIn;
        this.dateOut = dateOut;
        this.khachHang = khachHang;
        this.loaiKhach = loaiKhach;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaXuat = giaXuat;
        this.thanhToan = thanhToan;
        this.nhanVien = nhanVien;
        this.doanhThu = soLuong * giaXuat;
        this.laiGop = this.doanhThu - (soLuong * giaNhap);
        this.codeProduct = codeProduct;
        this.capacity = capacity;
    }

    public CTDT() {
    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getLoaiKhach() {
        return loaiKhach;
    }

    public void setLoaiKhach(String loaiKhach) {
        this.loaiKhach = loaiKhach;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Double getGiaXuat() {
        return giaXuat;
    }

    public void setGiaXuat(Double giaXuat) {
        this.giaXuat = giaXuat;
    }

    public Double getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(Double doanhThu) {
        this.doanhThu = doanhThu;
    }

    public Double getLaiGop() {
        return laiGop;
    }

    public void setLaiGop(Double laiGop) {
        this.laiGop = laiGop;
    }

    public String getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(String thanhToan) {
        this.thanhToan = thanhToan;
    }

    public String getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(String nhanVien) {
        this.nhanVien = nhanVien;
    }

}
