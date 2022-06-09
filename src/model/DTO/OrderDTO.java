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
public class OrderDTO {

    String codeOrder;
    String nameKh;
    Double tongTien;
    String creatAt;
    String userName;

    public OrderDTO(String codeOrder, String nameKh, Double tongTien, String creatAt, String userName) {
        this.codeOrder = codeOrder;
        this.nameKh = nameKh;
        this.tongTien = tongTien;
        this.creatAt = creatAt;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public OrderDTO() {
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }

    public String getNameKh() {
        return nameKh;
    }

    public void setNameKh(String nameKh) {
        this.nameKh = nameKh;
    }

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public String getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(String creatAt) {
        this.creatAt = creatAt;
    }

}
