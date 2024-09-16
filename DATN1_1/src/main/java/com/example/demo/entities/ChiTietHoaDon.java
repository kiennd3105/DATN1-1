package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CHITIETHOADON")
public class ChiTietHoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long ID;

    @Column(name = "MACTHD")
    private String MACTHD;

    @Column(name = "TONGTIEN")
    private String TONGTIEN;

    @Column(name = "SOLUONG")
    private Integer SOLUONG;

    @Column(name = "GIABAN")
    private String GIABAN;

    @Column(name = "TRANGTHAI")
    private Integer TRANGTHAI;

    @Column(name = "NGAYTAO")
    private Date NGAYTAO;

    @Column(name = "NGAYSUA")
    private Date NGAYSUA;


    @Column(name = "IDHOADON")
    private Long IDHOADON;

    @Column(name="IDCTSP")
     private Long IDCTSP;

    @Column(name = "GHICHU")
    private String GHICHU;
}
