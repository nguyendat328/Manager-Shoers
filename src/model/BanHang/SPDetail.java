/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BanHang;

/**
 *
 * @author choco
 */
public class SPDetail {

    private String codeSPDetail;
    private Integer quantity;
    private Double priceIn;
    private Double priceOut;

    public SPDetail() {
    }

    public SPDetail(String codeSPDetail, Integer quantity, Double priceIn, Double priceOut) {
        this.codeSPDetail = codeSPDetail;
        this.quantity = quantity;
        this.priceIn = priceIn;
        this.priceOut = priceOut;
    }

    public String getCodeSPDetail() {
        return codeSPDetail;
    }

    public void setCodeSPDetail(String codeSPDetail) {
        this.codeSPDetail = codeSPDetail;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPriceIn() {
        return priceIn;
    }

    public void setPriceIn(Double priceIn) {
        this.priceIn = priceIn;
    }

    public Double getPriceOut() {
        return priceOut;
    }

    public void setPriceOut(Double priceOut) {
        this.priceOut = priceOut;
    }

}
