package com.example.demo.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonReq {
    private String id;
    private String maHD;
    private String maVoucher;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private String thanhTien;
    private LocalDateTime ngayThanhToan;
    private LocalDateTime ngayNhanHang;
    private int trangThai;
    private int loaiHD;
    private String phiVanChuyen;
    private String thongTinGiaoHang;
    @NotNull(message = "Vui lòng nhập vào id sản phẩm")
    private String idKH;
    @NotNull(message = "Vui lòng nhập vào id sản phẩm")
    private String idNV;
    private List<ChiTietHoaDonRep> chiTietHoaDons;
}
