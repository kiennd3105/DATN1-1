package com.example.demo.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class NhanVienReponse {
    private String id ;
    private String ma;
    private String tenNV;
    private String email;
    private String passw;
    private String gioiTinh;
    private String img;
    private String diaChi;
    private Integer trangThai;
    private LocalDateTime ngayTao;
    private LocalDateTime ngaySua;

}
