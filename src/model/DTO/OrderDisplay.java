/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DTO;

import java.util.Date;

/**
 *
 * @author Jarvis
 */
public class OrderDisplay {
    String nameSp;
    Integer sl;
    Double donGiaXuat;

    public OrderDisplay() {
    }

    public OrderDisplay(String nameSp, Integer sl, Double donGiaXuat) {
        this.nameSp = nameSp;
        this.sl = sl;
        this.donGiaXuat = donGiaXuat;
    }

    public String getNameSp() {
        return nameSp;
    }

    public void setNameSp(String nameSp) {
        this.nameSp = nameSp;
    }

    public Integer getSl() {
        return sl;
    }

    public void setSl(Integer sl) {
        this.sl = sl;
    }

    public Double getDonGiaXuat() {
        return donGiaXuat;
    }

    public void setDonGiaXuat(Double donGiaXuat) {
        this.donGiaXuat = donGiaXuat;
    }
}
