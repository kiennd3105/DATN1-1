package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChiTietSanPhamRequest {
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
    private String idSP;
}
