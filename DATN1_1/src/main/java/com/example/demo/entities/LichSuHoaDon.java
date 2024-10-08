package com.example.demo.entities;

import com.example.demo.dto.LichSuHoaDonResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "LICHSUHOADON")
public class LichSuHoaDon {
    @Id
    @Column(name = "ID")
    private String id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

    @Column(name = "NGAYTAO")
    private String ngayTao;

    @Column(name = "TRANGTHAI")
    private int trangThai;

    @Column(name = "NGUOITHAOTAC")
    private String nguoiThaoTac;

    @ManyToOne
    @JoinColumn(name = "IDHOADON")
    private HoaDon hoaDon;

    @Column(name = "GHI_CHU")
    private String ghiChu;

    @Column(name = "THOI_GIAN_CAP_NHAT")
    private String thoiGianCapNhat;

    public LichSuHoaDonResponse toResponse() {
        return new LichSuHoaDonResponse(
                id,
                ngayTao,
                trangThai,
                nguoiThaoTac,
                hoaDon != null ? hoaDon.getId() : null,
                ghiChu,
                thoiGianCapNhat
        );
    }
}
