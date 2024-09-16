package com.example.demo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@NoArgsConstructor
@Getter
@Setter
public class ChiTietHoaDonResponse {

    private Long ID;

    private String MACTHD;

    private String TONGTIEN;

    private Integer SOLUONG;

    private String GIABAN;

    private Integer TRANGTHAI;

    private Date NGAYTAO;

    private Date NGAYSUA;

    private Long IDHOADON;

    private String GHICHU;

    private Long IDCTSP;
}
