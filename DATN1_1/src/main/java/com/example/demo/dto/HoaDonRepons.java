package com.example.demo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HoaDonRepons {
    private Integer ID;
    private String MAHD;
    private String MAVOUNCHER;
    private Date NGAYTAO;
    private Date NGAYSUA;
    private String THANHTIEN;
    private Date NGAYTHANHTOAN;
    private Date NGAYNHANHANG;
    private int TRANGTHAI;
    private int LOAIHD;
    private String PHIVANCHUYEN;
    private String THONGTINGIAOHANG;
    private int IDNV;
    private int IDKH;
}
