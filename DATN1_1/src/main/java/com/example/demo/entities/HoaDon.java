package com.example.demo.entities;

import com.example.demo.dto.HoaDonRepons;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "HOADON")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    @Column(name = "MAHD")
    private String maHD;

    @Column(name = "MAVOUCHER")
    private String maVoucher;

    @Column(name = "NGAYTAO")
    private String ngayTao;

    @Column(name = "NGAYSUA")
    private String ngaySua;

    @Column(name = "THANHTIEN")
    private String thanhTien;

    @Column(name = "NGAYTHANHTOAN")
    private String ngayThanhToan;

    @Column(name = "NGAYNHANHANG")
    private String ngayNhanHang;

    @Column(name = "TRANGTHAI")
    private int trangThai;

    @Column(name = "LOAIHD")
    private int loaiHD;

    @Column(name = "PHIVANCHUYEN")
    private String phiVanChuyen;

    @Column(name = "THONGTINGIAOHANG")
    private String thongTinGiaoHang;

    @Column(name = "IDNV")
    private String IDNV;

    @Column(name = "IDKH")
    private String IDKH;

    public HoaDonRepons toResponse() {
        return new HoaDonRepons(id, maHD, maVoucher, ngayTao, ngaySua, thanhTien, ngayThanhToan, ngayNhanHang, trangThai, loaiHD, phiVanChuyen, thongTinGiaoHang, IDNV, IDKH);
    }


}
