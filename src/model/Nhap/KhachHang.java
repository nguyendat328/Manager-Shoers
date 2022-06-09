/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Nhap;

/**
 *
 * @author choco
 */
public class KhachHang {

    private String code;
    private String name;
    private String diachi;
    private String sdt;
    private String fb;
    private String note;

    public KhachHang() {
    }

    public KhachHang(String code, String name, String diachi, String sdt, String fb, String note) {
        this.code = code;
        this.name = name;
        this.diachi = diachi;
        this.sdt = sdt;
        this.fb = fb;
        this.note = note;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

}
