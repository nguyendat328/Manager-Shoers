/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BaoCao;

import java.time.LocalDate;

/**
 *
 * @author choco
 */
public class Kho {

    private String code;
    private String tenSP;
    private Integer soLuong;
    private Double giaNhap;
    private Double giaBanLe;
    private Double giaBanBuon;
    private String codeProduct;
    private String capacity;
    private String loaiSP;
    private String thuongHieu;
    private LocalDate ngayNhap;
    private LocalDate ngaySua;

    public Kho() {
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Kho(String code, String codeProduct, String capacity, String tenSP, Integer soLuong, Double giaNhap, Double giaBanLe, Double giaBanBuon, String loaiSP, String thuongHieu, LocalDate ngayNhap, LocalDate ngaySua) {
        this.code = code;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.giaBanLe = giaBanLe;
        this.giaBanBuon = giaBanBuon;
        this.loaiSP = loaiSP;
        this.thuongHieu = thuongHieu;
        this.ngayNhap = ngayNhap;
        this.ngaySua = ngaySua;
        this.codeProduct = codeProduct;
        this.capacity = capacity;
    }

    public Kho(String code, String tenSP, Integer soLuong, Double giaNhap, String loaiSP, String thuongHieu) {
        this.code = code;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaNhap = giaNhap;
        this.loaiSP = loaiSP;
        this.thuongHieu = thuongHieu;
    }

    public String getCodeProduct() {
        return codeProduct;
    }

    public void setCodeProduct(String codeProduct) {
        this.codeProduct = codeProduct;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
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

    public Double getGiaBanLe() {
        return giaBanLe;
    }

    public void setGiaBanLe(Double giaBanLe) {
        this.giaBanLe = giaBanLe;
    }

    public Double getGiaBanBuon() {
        return giaBanBuon;
    }

    public void setGiaBanBuon(Double giaBanBuon) {
        this.giaBanBuon = giaBanBuon;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public LocalDate getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(LocalDate ngaySua) {
        this.ngaySua = ngaySua;
    }

}
