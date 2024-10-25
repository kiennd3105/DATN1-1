package com.example.demo.entities;


import com.example.demo.dtos.SanPhamResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "SANPHAM")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPham {
    @Id
    @Column(name = "ID")
    private String id  ;

    @Column(name = "MA")
    private String maSP;

    @Column(name = "TEN")
    private String tenSP;

    @Column(name = "THANHPHAN")
    private String thanhPhan;

    @Column(name = "CONGDUNG")
    private String congDung;

    @Column(name = "TUOIMIN")
    private Integer tuoiMin;

    @Column(name = "TUOIMAX")
    private Integer tuoiMax;

    @Column(name = "HDSD")
    private String hdsd;

    @Column(name = "NGAYTAO")
    private LocalDateTime ngayTao;

    @Column(name = "NGAYSUA")
    private LocalDateTime ngaySua;


    @Column(name = "TRANGTHAI")
    private Integer trangThai;

    @Column(name = "MOTA")
    private String moTa;

    @Column(name = "IDDANHMUC")
    private String idDanhMuc;

    @Column(name = "IDTHUONGHIEU")
    private String idThuongHieu;

    @ManyToOne
    @JoinColumn(name = "IDGIAMGIA")
    private GiamGia giamGia;

    @OneToMany(mappedBy = "sanPham", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChiTietSanPham> listCTSP = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }

    public SanPhamResponse toResponse() {
        String giamGiaTen = (giamGia != null) ? giamGia.getTen() : null; // Check for null in giamGia

        // Calculate total quantity where trangThai = 1
        int tongSoLuong = listCTSP.stream()
                .filter(ctsp -> ctsp.getTrangThai() == 1) // Only include items with trangThai = 1
                .mapToInt(ChiTietSanPham::getSoLuong)
                .sum();

        return new SanPhamResponse(
                id,
                maSP,
                tenSP,
                thanhPhan,
                congDung,
                tuoiMin,
                tuoiMax,
                tongSoLuong,  // Include total quantity
                ngayTao,
                ngaySua,
                hdsd,
                trangThai,
                moTa,
                idDanhMuc,
                idThuongHieu,
                giamGiaTen
        );
    }

}