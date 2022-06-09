package model.DTO;

public class OrderDetailDisplay {

    String codeSPDetail;
    String nameSp;
    Integer sl;
    Double donGiaXuat;

    public OrderDetailDisplay() {
    }

    public OrderDetailDisplay(String codeSPDetail, String nameSp, Integer sl, Double donGiaXuat) {
        this.codeSPDetail = codeSPDetail;
        this.nameSp = nameSp;
        this.sl = sl;
        this.donGiaXuat = donGiaXuat;
    }

    public String getCodeSPDetail() {
        return codeSPDetail;
    }

    public void setCodeSPDetail(String codeSPDetail) {
        this.codeSPDetail = codeSPDetail;
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
