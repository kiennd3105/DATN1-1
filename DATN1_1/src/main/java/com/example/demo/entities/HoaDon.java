package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="HOADON")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer ID;
    @Column(name="MAHD")
    private String MAHD;
    @Column(name="MAVOUNCHER")
    private String MAVOUNCHER;
    @Column(name="NGAYTAO")
    private Date NGAYTAO;
    @Column(name="NGAYSUA")
    private Date NGAYSUA;
    @Column(name="THANHTIEN")
    private String THANHTIEN;
    @Column(name="NGAYTHANHTOAN")
    private Date NGAYTHANHTOAN;
    @Column(name="NGAYNHANHANG")
    private Date NGAYNHANHANG;
    @Column(name="TRANGTHAI")
    private int TRANGTHAI;
    @Column(name="LOAIHD")
    private int LOAIHD;
    @Column(name="PHIVANCHUYEN")
    private String PHIVANCHUYEN;
    @Column(name="THONGTINGIAOHANG")
    private String THONGTINGIAOHANG;
    @Column(name="IDNV")
    private Long IDNV;
    @Column(name="IDKH")
    private Long IDKH;




}
