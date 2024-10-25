package com.example.demo.dtos;

import com.example.demo.entities.ChiTietHoaDon;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChiTietHoaDonRep {
    private String id;
    private String maCTHD;
    private String tongTien;
    private int soLuong;
    private String giaBan;
    private int trangThai;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;
    private String ghiChu;
    private String tenSP;
    private String gia;
    private String idHD;
}
