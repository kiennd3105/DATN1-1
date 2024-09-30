package com.example.demo.entities;

import com.example.demo.dto.HoaDonRepons;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "HOADON")
public class HoaDon {
    @Id
    @Column(name = "ID")
    private String id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

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

    @ManyToOne
    @JoinColumn(name = "IDNV")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "IDKH")
    private KhachHang khachHang;

    public HoaDonRepons toResponse() {
        return new HoaDonRepons(
                id,
                maHD,
                maVoucher,
                ngayTao,
                ngaySua,
                thanhTien,
                ngayThanhToan,
                ngayNhanHang,
                trangThai,
                loaiHD,
                phiVanChuyen,
                thongTinGiaoHang,
                nhanVien != null ? nhanVien.getTenNV(): null,
                khachHang != null ? khachHang.getTenKH(): null
        );
    }
}
