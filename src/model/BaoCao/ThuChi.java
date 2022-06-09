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
public class ThuChi {

    private String date;
    private Double thu;
    private Double chi;
    private Double tonQuy;
    private String description;

    public ThuChi(String date, Double thu, Double chi, Double tonQuy, String description) {
        this.date = date;
        this.thu = thu;
        this.chi = chi;
        this.tonQuy = tonQuy;
        this.description = description;
    }

    public ThuChi() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getThu() {
        return thu;
    }

    public void setThu(Double thu) {
        this.thu = thu;
    }

    public Double getChi() {
        return chi;
    }

    public void setChi(Double chi) {
        this.chi = chi;
    }

    public Double getTonQuy() {
        return tonQuy;
    }

    public void setTonQuy(Double tonQuy) {
        this.tonQuy = tonQuy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
