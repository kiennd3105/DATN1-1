package com.example.demo.entities;

import com.example.demo.dtos.ChiTietHoaDonRep;
import com.example.demo.dtos.HoaDonRep;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
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
    private String id ;

    @Column(name = "MAHD", length = 10)
    private String maHD;

    @Column(name = "MAVOUNCHER", length = 10)
    private String maVoucher;

    @Column(name = "NGAYTAO")
    private LocalDateTime ngayTao;

    @Column(name = "NGAYSUA")
    private LocalDateTime ngaySua;

    @Column(name = "THANHTIEN")
    private String thanhTien;

    @Column(name = "NGAYTHANHTOAN")
    private LocalDateTime ngayThanhToan;

    @Column(name = "NGAYNHANHANG")
    private LocalDateTime ngayNhanHang;

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

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
        if (this.maHD == null) {
            // Tạo mã hóa đơn tự động
            this.maHD = "HD" + UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        }
    }
    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
    private List<ChiTietHoaDon> chiTietHoaDons; // Quan hệ với ChiTietHoaDon

    public HoaDonRep toResponse(List<ChiTietHoaDonRep> chiTietHoaDons) {
        return new HoaDonRep(
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
                khachHang != null ? khachHang.getTenKH() : null,
                khachHang != null ? khachHang.getSdt() : null,
                nhanVien != null ? nhanVien.getTenNV() : null,
                chiTietHoaDons
        );
    }

}

