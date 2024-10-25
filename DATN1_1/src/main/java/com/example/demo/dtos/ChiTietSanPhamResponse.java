package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChiTietSanPhamResponse {
    private String id;
    private String ma;
    private String gia;
    private String soNgaySuDung;
    private LocalDateTime ngaySanXuat;
    private LocalDateTime hsd;
    private LocalDateTime ngayNhap;
    private int soLuong;
    private int trangThai;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;

    private String tenSP;

}
