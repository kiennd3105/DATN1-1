package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SanPhamResponse {
    private String id;

    private String maSP;

    private String tenSP;

    private String thanhPhan;

    private String congDung;

    private Integer tuoiMin;

    private Integer tuoiMax;

    private Integer tongSoLuong;

    private LocalDateTime ngayTao;

    private LocalDateTime ngaySua;

    private String hdsd;

    private Integer trangThai;

    private String moTa;

    private String idDanhMuc;

    private String idThuongHieu;

    private String tenGiamGia;
}
