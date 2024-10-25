package com.example.demo.dtos;

import com.example.demo.dtos.ChiTietHoaDonRep;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonRep {
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
    private String tenKH;
    private String sdt;
    private String tenNV;
    private List<ChiTietHoaDonRep> chiTietHoaDons;
}



