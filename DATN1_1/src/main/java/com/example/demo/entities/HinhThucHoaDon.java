package com.example.demo.entities;

import com.example.demo.dto.HinhThucHoaDonResponse;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "HINHTHUCHOADON")
public class HinhThucHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private String id;

    @Column(name = "MAGIAODICH")
    private String maGiaoDich;

    @Column(name = "NGAYTAO")
    private String ngayTao;

    @Column(name = "NGAYTHANHTOAN")
    private String ngayThanhToan;

    @Column(name = "SOTIENTRA")
    private String soTienTra;

    @Column(name = "NGAYCAPNHAT")
    private String ngayCapNhat;

    @Column(name = "GHICHU")
    private String ghiChu;

    @Column(name = "TRANGTHAI")
    private int trangThai;

    @Column(name = "HINHTHUCTHANHTOAN")
    private int hinhThucThanhToan;

    @ManyToOne
    @JoinColumn(name = "IDHOADON")
    private HoaDon hoaDon;

    public HinhThucHoaDonResponse toResponse() {
        return new HinhThucHoaDonResponse(
                id,
                maGiaoDich,
                ngayTao,
                ngayThanhToan,
                soTienTra,
                ngayCapNhat,
                ghiChu,
                trangThai,
                hinhThucThanhToan,
                this.hoaDon != null ? this.hoaDon.getId() : null

        );
    }


}
