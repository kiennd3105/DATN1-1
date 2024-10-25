package com.example.demo.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KhachHangReponse {
    private String id ;
    private String ma;
    private String tenKH;
    private String email;
    private String passw;
    private String gioiTinh;
    private String sdt;
    private String diaChi;
    private Integer trangThai;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;

}
