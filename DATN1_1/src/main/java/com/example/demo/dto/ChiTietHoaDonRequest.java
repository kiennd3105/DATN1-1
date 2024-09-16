package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class ChiTietHoaDonRequest {

    private String MACTHD;
    private String TONGTIEN;
    private Integer SOLUONG;
    private String GIABAN;
    private Integer TRANGTHAI;
    private Date NGAYTAO;
    private Date NGAYSUA;

    @NotNull(message = "IDHOADON không được để trống")
    private Long IDHOADON;

    @NotNull(message = "IDCTSP không được để trống")
    private Long IDCTSP;

    private String GHICHU;
}
