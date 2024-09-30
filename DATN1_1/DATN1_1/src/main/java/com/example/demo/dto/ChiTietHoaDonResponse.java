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
public class ChiTietHoaDonResponse {

    private String id;

    private String maCTHD;

    private String tongTien;

    private int soLuong;

    private String giaBan;

    private int trangThai;
    private String ngayTao;
    private String ngaySua;
    private String ghiChu;
    private String idCTSP;

}
