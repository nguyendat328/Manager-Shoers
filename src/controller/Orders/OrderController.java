/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Orders;

import java.util.List;
import model.BanHang.OrderDetail;
import model.BanHang.SPDetail;
import model.DTO.*;
import service.OrderService.OrderService;
import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.util.List;
import model.BanHang.OrderDetail;
import model.BanHang.SPDetail;
import service.OrderService.OrderService;

/**
 *
 * @author choco
 */
public class OrderController {

    OrderService OS = new OrderService();

    public Integer getTotalSP(String codeSP) {
        return OS.getTotalSP(codeSP);
    }

    public List<SPDetail> getListSPDetailOut(int typeSale, String codeSP, int soLuong) {
        return OS.getListSPDetailOut(typeSale, codeSP, soLuong);
    }

    public Boolean insertOrder(String codeOrder, String codeUser, String codeKH,
            Double coc, Double giamGia, Double tongTien, String status) {
        return OS.insertOrder(codeOrder, codeUser, codeKH, coc, giamGia, tongTien, status);
    }

    public Boolean insertOrderDetail(String codeOrderDetail, String codeOrder, String codeSPDetail,
            Integer soLuong, Double donGiaXuat) {
        return OS.insertOrderDetail(codeOrderDetail, codeOrder, codeSPDetail, soLuong, donGiaXuat);
    }

    public Boolean updateSpDetail(String codeOrderDetail, int slDaBan) {
        return OS.updateSpDetail(codeOrderDetail, slDaBan);
    }

    public Boolean updateNoKH(String codeKH, Double noTang) {
        return OS.updateNoKH(codeKH, noTang);
    }

    public Boolean insertThu(String codeUser, Double soTien, String description) {
        return OS.insertThu(codeUser, soTien, description);

    }

    public Boolean insertChi(String codeUser, Double soTien, String description) {
        return OS.insertChi(codeUser, soTien, description);
    }

    public boolean transactionBanHang(
            //order
            String codeOrderDraft, String codeOrder, String codeUser, String codeKH,
            Double coc, Double giamGia, Double tongTien, String status,
            //orderDetail
            List<OrderDetail> listOrderDetail,
            //tang thu chi
            String description, String loaiSP, String userName
    ) {
        return OS.transactionBanHang(codeOrderDraft, codeOrder, codeUser, codeKH, coc, giamGia, tongTien, status, listOrderDetail, description, loaiSP, userName);
    }

//    public boolean transactionBanNo(
//            //order
//            String codeOrderDraft, String codeOrder, String codeUser, String codeKH,
//            Double noCu, Double giamGia, Double tongTien, String status,
//            //orderDetail
//            List<OrderDetail> listOrderDetail
//    ) {
//        return OS.transactionBanNo(codeOrderDraft, codeOrder, codeUser, codeKH, noCu, giamGia, tongTien, status, listOrderDetail);
//    }

    // tuan
    public Boolean PDF(List<OrderDetail> ls, String nameKH, String codeOrder) throws FileNotFoundException, DocumentException {
        return OS.XuatPDF(ls, nameKH, codeOrder);
    }

    //    viet
    public List<OrderDTO> getListOrder(String status, String txtSearch, String fromDate, String toDate) {
        return OS.getListOrder(status, txtSearch, fromDate, toDate);
    }

    public List<OrderDetail> getListOrderDetail(String codeOrder) {
        return OS.getListOrderDetail(codeOrder);
    }

    public DetailCustomer getDetailCustomer(String codeOrderSer) {
        return OS.getDetailCustomer(codeOrderSer);
    }

    public boolean deleteOdrer(String codeOrder) {

        return OS.deleteOdrer(codeOrder);

    }

    public List<OrderDisplay> getListOrderDisplay(String codeOrderSer) {
        return OS.getListOrderDisplay(codeOrderSer);
    }

    public DisplayOrderInfoDTO getDisplayOrderInfoDTO(String codeOrderSer) {
        return OS.getDisplayOrderInfoDTO(codeOrderSer);
    }

    public boolean PDFDept(List<OrderDisplay> ls, DisplayOrderInfoDTO displayOrderInfoDTO)
            throws FileNotFoundException, DocumentException {
        return OS.XuatPDFDept(ls, displayOrderInfoDTO);
    }

}
