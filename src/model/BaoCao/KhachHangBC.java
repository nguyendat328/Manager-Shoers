/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BaoCao;

/**
 *
 * @author khai
 */
public class KhachHangBC {

    private String code_kh;
    private String name_kh;
    private String mst;
    private String stk;
    private String ngan_hang;
    private String address;
    private String phone;
    private double phai_thu;
    private String loaiKhach;

    public KhachHangBC(String code_kh, String name_kh, String mst, String stk, String ngan_hang, String address, String phone, String loaiKhach) {
        this.code_kh = code_kh;
        this.name_kh = name_kh;
        this.mst = mst;
        this.stk = stk;
        this.ngan_hang = ngan_hang;
        this.address = address;
        this.phone = phone;
        this.loaiKhach = loaiKhach;
    }

    public String getCode_kh() {
        return code_kh;
    }

    public void setCode_kh(String code_kh) {
        this.code_kh = code_kh;
    }

    public String getName_kh() {
        return name_kh;
    }

    public void setName_kh(String name_kh) {
        this.name_kh = name_kh;
    }

    public String getMst() {
        return mst;
    }

    public void setMst(String mst) {
        this.mst = mst;
    }

    public String getStk() {
        return stk;
    }

    public void setStk(String stk) {
        this.stk = stk;
    }

    public String getNgan_hang() {
        return ngan_hang;
    }

    public void setNgan_hang(String ngan_hang) {
        this.ngan_hang = ngan_hang;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getPhai_thu() {
        return phai_thu;
    }

    public void setPhai_thu(double phai_thu) {
        this.phai_thu = phai_thu;
    }

    public String getLoaiKhach() {
        return loaiKhach;
    }

    public void setLoaiKhach(String loaiKhach) {
        this.loaiKhach = loaiKhach;
    }

}
