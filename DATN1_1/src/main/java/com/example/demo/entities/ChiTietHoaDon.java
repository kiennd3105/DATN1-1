package com.example.demo.entities;

import com.example.demo.dto.ChiTietHoaDonResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;
import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CHITIETHOADON")
public class ChiTietHoaDon {

    @Id
    @Column(name = "ID")
    private String id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

    @Column(name = "MACTHD")
    private String maCTHD;

    @Column(name = "TONGTIEN")
    private String tongTien;

    @Column(name = "SOLUONG")
    private int soLuong;

    @Column(name = "GIABAN")
    private String giaBan;

    @Column(name = "TRANGTHAI")
    private int trangThai;

    @Column(name = "NGAYTAO")
    private String ngayTao;

    @Column(name = "NGAYSUA")
    private String ngaySua;

    @Column(name = "GHICHU")
    private String ghiChu;
    @ManyToOne
    @JoinColumn (name = "IDHOADON")
    private HoaDon hoaDon;

    @Column(name="IDCTSP")
    private String idCTSP;

    public ChiTietHoaDonResponse toResponse() {
        return new ChiTietHoaDonResponse(id,maCTHD,tongTien,soLuong,ngayTao,trangThai,ngaySua,ghiChu, this.hoaDon != null ? this.hoaDon.getId() : null,idCTSP);
    }
}
