package model.Nhap;

import java.util.Date;

public class SanPham {

    String codeSP;
    String name;
    Integer status;
    Date creatAt;
    Date updateAt;
    String productCode;

    public SanPham() {
    }

    public String getCodeSP() {
        return codeSP;
    }

    public void setCodeSP(String codeSPp) {
        this.codeSP = codeSPp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatAt() {
        return creatAt;
    }

    public void setCreatAt(Date creatAt) {
        this.creatAt = creatAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

}
