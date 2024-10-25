package com.example.demo.entities;

import com.example.demo.dtos.NhanVienReponse;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "NHANVIEN")
public class NhanVien {
    @Id
    @Column(name = "ID")
    private String id ;
    @Column(name = "MA")
    private String ma;

    @Column(name = "Ten")
    private String tenNV;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSW")
    private String passw;

    @Column(name = "GIOITINH")
    private String gioiTinh;

    @Column(name = "IMG")
    private String img;

    @Column(name = "DIACHI")
    private String diaChi;

    @Column(name = "TRANGTHAI")
    private Integer trangThai;

    @Column(name = "NGAYTAO")
    private LocalDateTime ngayTao;

    @Column(name = "NGAYSUA")
    private LocalDateTime ngaySua;
    @PrePersist
    public void prePersist() {
        if (this.id == null) {
            this.id = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        }
    }
    public NhanVienReponse toResponse() {
        NhanVienReponse response = new NhanVienReponse();
        response.setId(this.id);
        response.setMa(this.ma);
        response.setTenNV(this.tenNV);
        response.setEmail(this.email);
        response.setGioiTinh(this.gioiTinh);
        response.setImg(this.img);
        response.setDiaChi(this.diaChi);
        response.setTrangThai(this.trangThai);
        response.setNgayTao(this.ngayTao);
        response.setNgaySua(this.ngaySua);
        return response;
    }

}
