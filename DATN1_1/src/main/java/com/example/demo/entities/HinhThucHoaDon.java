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
@Table(name="HINHTHUCHOADON")
public class HinhThucHoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer ID ;
    @Column(name="MAGIAODICH")
    private String MAGIAODICH ;
    @Column(name="NGAYTAO")
    private Date NGAYTAO ;
    @Column(name="NGAYTHANHTOAN")
    private Date  NGAYTHANHTOAN ;
    @Column(name="SOTIENTRA")
    private String SOTIENTRA ;
    @Column(name="NGAYCAPNHAT")
    private Date  NGAYCAPNHAT ;
    @Column(name="GHICHU")
    private String GHICHU ;
    @Column(name="TRANGTHAI")
    private int TRANGTHAI ;
    @Column(name="HINHTHUCTHANHTOAN")
    private int HINHTHUCTHANHTOAN ;
    @Column(name="IDHD")
    private int IDHD;


}
