package com.example.demo.entities;

import com.example.demo.dtos.ChiTietSanPhamResponse;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CHITIETSANPHAM")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSanPham {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "MA")
    private String ma;

    @Column(name = "GIA")
    private String gia;

    @Column(name = "SONGAYSUDUNG")
    private String soNgaySuDung;



    @Column(name = "HSD")
    private LocalDateTime hsd;

    @Column(name = "NGAYSANXUAT")
    private LocalDateTime ngaySanXuat;

    @Column(name = "NGAYNHAP")
    private LocalDateTime ngayNhap;

    @Column(name = "SOLUONG")
    private int soLuong;

    @Column(name = "TRANGTHAI")
    private int trangThai;

    @Column(name = "NGAYTAO")
    private LocalDateTime ngayTao;

    @Column(name = "NGAYSUA")
    private LocalDateTime ngaySua;

    @ManyToOne
    @JoinColumn(name = "IDSP")
    private SanPham sanPham;

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

    public ChiTietSanPhamResponse toChiTietSanPhamResponse() {
        return new ChiTietSanPhamResponse(
                id,
                ma,
                gia,
                soNgaySuDung,
                ngaySanXuat,
                hsd,
                ngayNhap,
                soLuong,
                trangThai,
                ngayTao,
                ngaySua,
                sanPham != null ? sanPham.getTenSP() : null
        );
    }
}
