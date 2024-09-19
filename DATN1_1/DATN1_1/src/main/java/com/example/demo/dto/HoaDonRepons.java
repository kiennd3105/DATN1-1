package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonRepons {
    private String  id;
    private String maHD;
    private String maVoucher;
    private String ngayTao;
    private String ngaySua;
    private String thanhTien;
    private String ngayThanhToan;
    private String ngayNhanHang;
    private int trangThai;
    private int loaiHD;
    private String phiVanChuyen;
    private String thongTinGiaoHang;
    private String IDKH;
    private String IDNV;

}
