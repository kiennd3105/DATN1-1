package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "KHACHHANG")
public class KhachHang {
    @Id
    @Column(name = "ID")
    private String id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();

    @Column(name = "MA", unique = true)
    private String ma;

    @Column(name = "Ten")
    private String tenKH;

    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSW")
    private String passw;

    @Column(name = "GIOITINH")
    private String gioiTinh;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "DIACHI")
    private String diaChi;

    @Column(name = "TRANGTHAI")
    private Integer trangThai;

    @Column(name = "NGAYTAO")
    private String ngayTao;

    @Column(name = "NGAYSUA")
    private String ngaySua;
}
