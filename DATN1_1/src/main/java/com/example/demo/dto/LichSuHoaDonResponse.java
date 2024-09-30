package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LichSuHoaDonResponse {
    private String id;
    private String ngayTao;
    private int trangThai;
    private String nguoiThaoTac;
    private String hoaDonId;
    private String ghiChu;
    private String thoiGianCapNhat;
}
