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
public class LichSuHoaDonResponse {
    private String id;
    private LocalDateTime ngayTao;
    private int trangThai;
    private String tenNhanVien;
    private String maHD;
    private String idHD;

}
