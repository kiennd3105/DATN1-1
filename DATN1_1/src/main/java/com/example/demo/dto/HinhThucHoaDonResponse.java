package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HinhThucHoaDonResponse {
    private Integer ID;
    private String MAGIAODICH;
    private Date NGAYTAO;
    private Date NGAYTHANHTOAN;
    private String SOTIENTRA;
    private Date NGAYCAPNHAT;
    private String GHICHU;
    private int TRANGTHAI;
    private int HINHTHUCTHANHTOAN;
    private Integer IDHD;
}
