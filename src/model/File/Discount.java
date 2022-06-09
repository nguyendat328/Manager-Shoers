/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.File;

import java.time.LocalDate;

/**
 *
 * @author choco
 */
public class Discount {

    private int discount;
    private LocalDate fromDate;
    private LocalDate toDate;

    public Discount(int discount, LocalDate fromDate, LocalDate toDate) {
        this.discount = discount;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

}
