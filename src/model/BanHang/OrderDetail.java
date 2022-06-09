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
public class OrderDetail {

    private String codeSPDetail;
    private String code_Product;
    private String nameSP;
    private Integer quantity;
    private Double price;
    private Double amount;

    public OrderDetail() {
    }

    public OrderDetail(String codeSPDetail, String code_Product, String nameSP, Integer quantity, Double price, Double amount) {
        this.codeSPDetail = codeSPDetail;
        this.code_Product = code_Product;
        this.nameSP = nameSP;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
    }

    public String getCode_Product() {
        return code_Product;
    }

    public void setCode_Product(String code_Product) {
        this.code_Product = code_Product;
    }

    public String getCodeSPDetail() {
        return codeSPDetail;
    }

    public void setCodeSPDetail(String codeSPDetail) {
        this.codeSPDetail = codeSPDetail;
    }

    public String getNameSP() {
        return nameSP;
    }

    public void setNameSP(String nameSP) {
        this.nameSP = nameSP;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
